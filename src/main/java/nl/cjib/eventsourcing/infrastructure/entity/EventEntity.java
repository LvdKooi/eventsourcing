package nl.cjib.eventsourcing.infrastructure.entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import nl.cjib.eventsourcing.core.domain.event.EventType;
import org.hibernate.annotations.DiscriminatorFormula;

import java.time.LocalDate;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@DiscriminatorFormula("event_type")
public abstract class EventEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long eventId;

    @EqualsAndHashCode.Include
    private LocalDate eventDate;

    @Enumerated(value = EnumType.STRING)
    private EventType eventType;

    private String verplichtingsnummer;
}