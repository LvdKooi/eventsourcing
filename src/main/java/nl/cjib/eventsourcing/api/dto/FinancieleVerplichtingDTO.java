package nl.cjib.eventsourcing.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class FinancieleVerplichtingDTO {
    private String verplichtingsnummer;
    private String omschrijving;
    private BigDecimal openstaandSaldo;
    private Set<BetalingsverplichtingDTO> betalingsverplichtingen;
    private Set<EventDTO> gebeurtenissen;
}
