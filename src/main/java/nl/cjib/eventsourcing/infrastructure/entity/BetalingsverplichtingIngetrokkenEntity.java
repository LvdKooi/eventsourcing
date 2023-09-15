package nl.cjib.eventsourcing.infrastructure.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue(value = "BETALINGSVERPLICHTING_INGETROKKEN")
public class BetalingsverplichtingIngetrokkenEntity extends EventEntity {
    private String betalingskenmerk;
}
