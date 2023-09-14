package nl.cjib.eventsourcing.mapper;

import nl.cjib.eventsourcing.api.dto.*;
import nl.cjib.eventsourcing.core.domain.aggregator.aggregate.Betalingsverplichting;
import nl.cjib.eventsourcing.core.domain.aggregator.aggregate.FinancieleVerplichting;
import nl.cjib.eventsourcing.core.domain.event.BetalingsverplichtingIngetrokken;
import nl.cjib.eventsourcing.core.domain.event.BetalingsverplichtingOpgelegd;
import nl.cjib.eventsourcing.core.domain.event.Event;
import nl.cjib.eventsourcing.core.domain.event.FinancieleVerplichtingOpgelegd;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    BetalingsverplichtingDTO map(Betalingsverplichting model);


    @AfterMapping
    default void sortGebeurtenissenAndVerplichtingen(@MappingTarget FinancieleVerplichtingDTO financieleVerplichting) {

        var sortedGebeurtenissen = sort(financieleVerplichting.getGebeurtenissen(), Comparator.comparing(EventDTO::getEventId).thenComparing(EventDTO::getEventDate));
        var sorterVerplichtingen = sort(financieleVerplichting.getBetalingsverplichtingen(), Comparator.comparing(BetalingsverplichtingDTO::getVervaldatum));

        financieleVerplichting.setGebeurtenissen(sortedGebeurtenissen);
        financieleVerplichting.setBetalingsverplichtingen(sorterVerplichtingen);
    }

    private static <T> Set<T> sort(Set<T> baseSet, Comparator<T> comparator) {
        return baseSet.stream()
                .sorted(comparator)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Mapping(target = "eventType", expression = "java(model.eventType())")
    BetalingsverplichtingIngetrokkenDTO map(BetalingsverplichtingIngetrokken model);

    @Mapping(target = "eventType", expression = "java(model.eventType())")
    BetalingsverplichtingOpgelegdDTO map(BetalingsverplichtingOpgelegd model);

    FinancieleVerplichtingDTO map(FinancieleVerplichting financieleVerplichting);

    @Mapping(target = "eventType", expression = "java(model.eventType())")
    FinancieleVerplichtingOpgelegdDTO map(FinancieleVerplichtingOpgelegd model);

    default EventDTO map(Event event) {
        if (event instanceof BetalingsverplichtingIngetrokken bvi) {
            return map(bvi);
        }

        if (event instanceof BetalingsverplichtingOpgelegd bvo) {
            return map(bvo);
        }

        if (event instanceof FinancieleVerplichtingOpgelegd fvo) {
            return map(fvo);
        }

        return null;
    }

}
