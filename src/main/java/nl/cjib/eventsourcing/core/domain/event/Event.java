package nl.cjib.eventsourcing.core.domain.event;

import java.time.LocalDate;

public interface Event {

    String eventId();

    LocalDate eventDate();

    EventType eventType();

    String verplichtingsnummer();
}
