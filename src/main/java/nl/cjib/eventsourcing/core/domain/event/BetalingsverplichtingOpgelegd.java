package nl.cjib.eventsourcing.core.domain.event;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BetalingsverplichtingOpgelegd(String eventId,
                                            LocalDate eventDate,
                                            String verplichtingsnummer,
                                            String betalingskenmerk,
                                            LocalDate vervalDatum,
                                            String omschrijving,
                                            String betaalwijze,
                                            BigDecimal bedrag
) implements Event {

    public EventType eventType() {
        return EventType.BETALINGSVERPLICHTING_OPGELEGD;
    }
}
