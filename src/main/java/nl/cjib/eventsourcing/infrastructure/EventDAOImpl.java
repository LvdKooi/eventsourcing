package nl.cjib.eventsourcing.infrastructure;

import lombok.RequiredArgsConstructor;
import nl.cjib.eventsourcing.core.domain.event.Event;
import nl.cjib.eventsourcing.infrastructure.repository.EventRepository;
import nl.cjib.eventsourcing.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EventDAOImpl implements EventDAO {

    private final EventRepository eventRepository;
    private final Mapper mapper;

    @Override
    public Set<Event> getEventsForVerplichtingnummer(String verplichtingnummer) {
        return eventRepository
                .findAllByVerplichtingsnummer(verplichtingnummer)
                .stream()
                .map(mapper::map)
                .collect(Collectors.toSet());
    }
}
