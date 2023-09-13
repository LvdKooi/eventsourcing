package nl.cjib.eventsourcing.api.dto;

import lombok.Data;

@Data
public class BetalingsverplichtingIngetrokkenDTO extends EventDTO {
    private String betalingskenmerk;
}
