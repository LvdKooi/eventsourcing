package nl.cjib.eventsourcing.core.usecases;

import lombok.RequiredArgsConstructor;
import nl.cjib.eventsourcing.core.command.BepaalFinancieleVerplichtingCommand;
import nl.cjib.eventsourcing.core.domain.aggregator.FinancieleVerplichtingAggregator;
import nl.cjib.eventsourcing.core.domain.aggregator.aggregate.FinancieleVerplichting;
import nl.cjib.eventsourcing.infrastructure.EventDAO;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BepaalFinancieleVerplichting {

    private final EventDAO eventDAO;

    public FinancieleVerplichting handle(BepaalFinancieleVerplichtingCommand command) {
        var events = eventDAO.getEventsForVerplichtingnummer(command.verplichtingnummer());
        return FinancieleVerplichtingAggregator.aggregateEvents(events, command.peildatum());
    }
}
