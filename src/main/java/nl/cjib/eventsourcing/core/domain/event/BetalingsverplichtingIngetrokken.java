package nl.cjib.eventsourcing.core.domain.event;

import java.time.LocalDate;

public record BetalingsverplichtingIngetrokken(String eventId,
                                               LocalDate eventDate,
                                               String betalingskenmerk) implements Event {

    public EventType eventType() {
        return EventType.BETALINGSVERPLICHTING_INGETROKKEN;
    }
}
