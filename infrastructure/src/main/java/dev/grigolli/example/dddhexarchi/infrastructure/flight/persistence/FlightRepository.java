package dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<FlightJpaEntity, String> {
}
