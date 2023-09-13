package nl.cjib.eventsourcing.api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinancieleVerplichtingOpgelegdDTO extends EventDTO {
    private String verplichtingsnummer;
    private String omschrijving;
    private BigDecimal bedrag;
}
