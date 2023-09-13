package nl.cjib.eventsourcing.core.domain.event;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FinancieleVerplichtingOpgelegd(String eventId,
                                             LocalDate eventDate,
                                             String verplichtingsnummer,
                                             String omschrijving,
                                             BigDecimal bedrag) implements Event {

    public EventType eventType() {
        return EventType.FINANCIELEVERPLICHTING_OPGELEGD;
    }
}
