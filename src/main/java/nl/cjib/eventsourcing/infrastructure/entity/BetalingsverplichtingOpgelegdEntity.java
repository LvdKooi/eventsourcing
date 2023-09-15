package nl.cjib.eventsourcing.infrastructure.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@DiscriminatorValue(value = "BETALINGSVERPLICHTING_OPGELEGD")
public class BetalingsverplichtingOpgelegdEntity extends EventEntity {
    private String betalingskenmerk;
    private LocalDate vervalDatum;
    private String omschrijving;
    private String betaalwijze;
    private BigDecimal bedrag;
}
