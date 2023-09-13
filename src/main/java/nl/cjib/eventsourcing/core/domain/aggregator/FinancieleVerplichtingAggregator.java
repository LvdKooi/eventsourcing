package nl.cjib.eventsourcing.core.domain.aggregator;


import nl.cjib.eventsourcing.core.domain.aggregator.aggregate.Betalingsverplichting;
import nl.cjib.eventsourcing.core.domain.aggregator.aggregate.FinancieleVerplichting;
import nl.cjib.eventsourcing.core.domain.event.BetalingsverplichtingIngetrokken;
import nl.cjib.eventsourcing.core.domain.event.BetalingsverplichtingOpgelegd;
import nl.cjib.eventsourcing.core.domain.event.Event;
import nl.cjib.eventsourcing.core.domain.event.FinancieleVerplichtingOpgelegd;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FinancieleVerplichtingAggregator {

    public static FinancieleVerplichting aggregateEvents(Set<Event> events, LocalDate peildatum) {
        var baseVerplichting = FinancieleVerplichting.getDefaultVerplichting();

        for (var event : eventsTotEnMet(events, peildatum)) {
            baseVerplichting = switch (event.eventType()) {
                case FINANCIELEVERPLICHTING_OPGELEGD ->
                        handleFinancieleVerplichtingOpgelegd(baseVerplichting, (FinancieleVerplichtingOpgelegd) event);
                case BETALINGSVERPLICHTING_OPGELEGD ->
                        handleBetalingsverplichtingOpgelegd(baseVerplichting, (BetalingsverplichtingOpgelegd) event);
                case BETALINGSVERPLICHTING_INGETROKKEN ->
                        handleBetalingsverplichtingIngetrokken(baseVerplichting, (BetalingsverplichtingIngetrokken) event);
            };
        }

        return baseVerplichting;
    }

    private static Set<Event> eventsTotEnMet(Set<Event> events, LocalDate peildatum) {
        return events
                .stream()
                .filter(nietGebeurdNa(peildatum))
                .sorted(Comparator.comparing(Event::eventId).thenComparing(Event::eventDate))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private static Predicate<Event> nietGebeurdNa(LocalDate peildatum) {
        return event -> !event.eventDate().isAfter(peildatum);
    }

    private static FinancieleVerplichting handleBetalingsverplichtingIngetrokken(FinancieleVerplichting baseVerplichting, BetalingsverplichtingIngetrokken bvi) {
        return baseVerplichting
                .copyAndAddGebeurtenis(bvi)
                .copyAndverwijderBetalingsVerplichtingMetKenmerk(bvi.betalingskenmerk());
    }

    private static FinancieleVerplichting handleBetalingsverplichtingOpgelegd(FinancieleVerplichting baseVerplichting, BetalingsverplichtingOpgelegd bvo) {
        var newBetalingsVerplichting = mapFromBetalingsVerplichtingOpgelegd(bvo);

        return baseVerplichting
                .copyAndAddGebeurtenis(bvo)
                .copyAndAddBetalingsVerplichting(newBetalingsVerplichting);
    }

    private static Betalingsverplichting mapFromBetalingsVerplichtingOpgelegd(BetalingsverplichtingOpgelegd betalingsverplichtingOpgelegd) {
        return new Betalingsverplichting(betalingsverplichtingOpgelegd.betalingskenmerk(), betalingsverplichtingOpgelegd.vervalDatum(), betalingsverplichtingOpgelegd.omschrijving(), betalingsverplichtingOpgelegd.betaalwijze(), betalingsverplichtingOpgelegd.bedrag());
    }

    private static FinancieleVerplichting handleFinancieleVerplichtingOpgelegd(FinancieleVerplichting current, FinancieleVerplichtingOpgelegd financieleVerplichtingOpgelegd) {
        var updatedWithGebeurtenis = current
                .copyAndAddGebeurtenis(financieleVerplichtingOpgelegd);

        if (!current.heeftVerplichtingsnummer()) {
            return updatedWithGebeurtenis
                    .copyAndAddVerplichtingsnummer(financieleVerplichtingOpgelegd.verplichtingsnummer())
                    .copyAndAddOmschrijving(financieleVerplichtingOpgelegd.omschrijving())
                    .copyAndAddToOpenstaandeSaldo(financieleVerplichtingOpgelegd.bedrag());
        }

        return updatedWithGebeurtenis
                .copyAndAddToOpenstaandeSaldo(financieleVerplichtingOpgelegd.bedrag());
    }
}
