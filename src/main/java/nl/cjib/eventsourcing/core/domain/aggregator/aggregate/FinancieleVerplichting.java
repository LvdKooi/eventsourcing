package nl.cjib.eventsourcing.core.domain.aggregator.aggregate;

import nl.cjib.eventsourcing.core.domain.event.Event;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public record FinancieleVerplichting(String verplichtingsnummer,
                                     String omschrijving,
                                     BigDecimal openstaandSaldo,
                                     Set<Betalingsverplichting> betalingsverplichtingen,
                                     Set<Event> gebeurtenissen) {


    public FinancieleVerplichting {
        betalingsverplichtingen = Optional.ofNullable(betalingsverplichtingen).orElseGet(HashSet::new);
        gebeurtenissen = Optional.ofNullable(gebeurtenissen).orElseGet(HashSet::new);
    }

    public static FinancieleVerplichting getDefaultVerplichting() {
        return
                new FinancieleVerplichting(null, null, null, null, null);
    }


    public boolean heeftVerplichtingsnummer() {
        return Objects.nonNull(this.verplichtingsnummer);
    }

    public FinancieleVerplichting copyAndAddVerplichtingsnummer(String verplichtingsnummer) {
        return new FinancieleVerplichting(verplichtingsnummer, this.omschrijving, this.openstaandSaldo, this.betalingsverplichtingen, this.gebeurtenissen);
    }


    public FinancieleVerplichting copyAndAddOmschrijving(String omschrijving) {
        return new FinancieleVerplichting(this.verplichtingsnummer, omschrijving, this.openstaandSaldo, this.betalingsverplichtingen, this.gebeurtenissen);
    }

    public FinancieleVerplichting copyAndAddToOpenstaandeSaldo(BigDecimal amountToAdd) {
        var newAmount = Optional.ofNullable(this.openstaandSaldo)
                .map(saldo -> saldo.add(amountToAdd))
                .orElse(amountToAdd);

        return new FinancieleVerplichting(this.verplichtingsnummer, this.omschrijving, newAmount, this.betalingsverplichtingen, this.gebeurtenissen);
    }

    public FinancieleVerplichting copyAndAddBetalingsVerplichting(Betalingsverplichting betalingsverplichting) {
        var newBetalingsVerplichtingen = new HashSet<>(betalingsverplichtingen);
        newBetalingsVerplichtingen.add(betalingsverplichting);

        return new FinancieleVerplichting(this.verplichtingsnummer, this.omschrijving, this.openstaandSaldo, newBetalingsVerplichtingen, this.gebeurtenissen);
    }

    public FinancieleVerplichting copyAndverwijderBetalingsVerplichtingMetKenmerk(String kenmerk) {
        var newBetalingsVerplichtingen = betalingsverplichtingen
                .stream().filter(heeftBetalingsKenmerkGelijkAan(kenmerk).negate())
                .collect(Collectors.toSet());

        return new FinancieleVerplichting(this.verplichtingsnummer, this.omschrijving, this.openstaandSaldo, newBetalingsVerplichtingen, this.gebeurtenissen);
    }

    private static Predicate<Betalingsverplichting> heeftBetalingsKenmerkGelijkAan(String kenmerk) {
        return betalingsverplichting -> betalingsverplichting.betalingskenmerk().equals(kenmerk);
    }

    public FinancieleVerplichting copyAndAddGebeurtenis(Event event) {

        var gebeurtenissen =
                Optional.ofNullable(this.gebeurtenissen)
                        .map(HashSet::new)
                        .orElseGet(HashSet::new);

        gebeurtenissen.add(event);

        return new FinancieleVerplichting(this.verplichtingsnummer, this.omschrijving, this.openstaandSaldo, this.betalingsverplichtingen, gebeurtenissen);
    }

}
