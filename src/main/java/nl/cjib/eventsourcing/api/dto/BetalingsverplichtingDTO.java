package nl.cjib.eventsourcing.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BetalingsverplichtingDTO {
    private String betalingskenmerk;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate vervaldatum;
    private String omschrijving;
    private String betaalwijze;
    private BigDecimal bedrag;
}
