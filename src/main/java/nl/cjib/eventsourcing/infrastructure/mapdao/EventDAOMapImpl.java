package nl.cjib.eventsourcing.infrastructure.mapdao;

import nl.cjib.eventsourcing.core.domain.event.BetalingsverplichtingIngetrokken;
import nl.cjib.eventsourcing.core.domain.event.BetalingsverplichtingOpgelegd;
import nl.cjib.eventsourcing.core.domain.event.Event;
import nl.cjib.eventsourcing.core.domain.event.FinancieleVerplichtingOpgelegd;
import nl.cjib.eventsourcing.infrastructure.EventDAO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

//Temporary implementation for POC
@Component
public class EventDAOMapImpl implements EventDAO {

    private final Map<String, Set<Event>> eventsMap = Map.of("VERPLICHTINGNUMMER1",
            Set.of(
                    new FinancieleVerplichtingOpgelegd("EVENT1", LocalDate.of(2023, 5, 1), "VERPLICHTINGSNUMMER1", "Verkeersboete", BigDecimal.valueOf(10400)),
                    new FinancieleVerplichtingOpgelegd("EVENT2", LocalDate.of(2023, 5, 1), "VERPLICHTINGSNUMMER1", "Administratiekosten", BigDecimal.valueOf(900)),
                    new BetalingsverplichtingOpgelegd("EVENT3", LocalDate.of(2023, 5, 1), "BETALINGSKENMERK", LocalDate.of(2023, 6, 1), "Direct betalen", "CONTANT", BigDecimal.valueOf(11300), "VERPLICHTINGSNUMMER1"),
                    new FinancieleVerplichtingOpgelegd("EVENT4", LocalDate.of(2023, 6, 1), "VERPLICHTINGSNUMMER1", "Kosten eerste aanmaning", BigDecimal.valueOf(5200)),
                    new BetalingsverplichtingIngetrokken("EVENT5", LocalDate.of(2023, 6, 1), "BETALINGSKENMERK"),
                    new BetalingsverplichtingOpgelegd("EVENT3", LocalDate.of(2023, 6, 1), "BETALINGSKENMERK2", LocalDate.of(2023, 6, 1), "Direct betalen", "CONTANT", BigDecimal.valueOf(16500), "VERPLICHTINGSNUMMER1")
            ));

    @Override
    public Set<Event> getEventsForVerplichtingnummer(String verplichtingnummer) {
        return eventsMap.getOrDefault(verplichtingnummer, Collections.emptySet());
    }
}
