package nl.cjib.eventsourcing.mapper;

import nl.cjib.eventsourcing.api.dto.*;
import nl.cjib.eventsourcing.core.domain.aggregator.aggregate.Betalingsverplichting;
import nl.cjib.eventsourcing.core.domain.aggregator.aggregate.FinancieleVerplichting;
import nl.cjib.eventsourcing.core.domain.event.BetalingsverplichtingIngetrokken;
import nl.cjib.eventsourcing.core.domain.event.BetalingsverplichtingOpgelegd;
import nl.cjib.eventsourcing.core.domain.event.Event;
import nl.cjib.eventsourcing.core.domain.event.FinancieleVerplichtingOpgelegd;
import org.mapstruct.Mapping;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    BetalingsverplichtingDTO map(Betalingsverplichting model);

    @Mapping(target = "eventType", expression = "java(model.eventType())")
    BetalingsverplichtingIngetrokkenDTO map(BetalingsverplichtingIngetrokken model);

    @Mapping(target = "eventType", expression = "java(model.eventType())")
    BetalingsverplichtingOpgelegdDTO map(BetalingsverplichtingOpgelegd model);

    FinancieleVerplichtingDTO map(FinancieleVerplichting financieleVerplichting);

    @Mapping( target = "eventType", expression = "java(model.eventType())")
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
