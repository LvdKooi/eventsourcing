package nl.cjib.eventsourcing.infrastructure.repository;

import nl.cjib.eventsourcing.infrastructure.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    Set<EventEntity> findAllByVerplichtingsnummer(String verplichtingsnummer);
}
