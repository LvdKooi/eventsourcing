package nl.cjib.eventsourcing.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import nl.cjib.eventsourcing.core.domain.event.EventType;

import java.time.LocalDate;

@JsonTypeInfo(
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "eventType",
        use = JsonTypeInfo.Id.NAME,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BetalingsverplichtingIngetrokkenDTO.class, name = "BETALINGSVERPLICHTING_INGETROKKEN"),
        @JsonSubTypes.Type(value = FinancieleVerplichtingOpgelegdDTO.class, name = "FINANCIELEVERPLICHTING_OPGELEGD"),
        @JsonSubTypes.Type(value = BetalingsverplichtingOpgelegdDTO.class, name = "BETALINGSVERPLICHTING_OPGELEGD")
})
@Data
public abstract class EventDTO {

    private String eventId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;

    private EventType eventType;

    private String verplichtingsnummer;
}
