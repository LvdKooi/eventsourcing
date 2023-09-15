package nl.cjib.eventsourcing.infrastructure.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Getter
@DiscriminatorValue(value = "FINANCIELEVERPLICHTING_OPGELEGD")
public class FinancieleVerplichtingOpgelegdEntity extends EventEntity {
   private String omschrijving;
   private BigDecimal bedrag;
}
