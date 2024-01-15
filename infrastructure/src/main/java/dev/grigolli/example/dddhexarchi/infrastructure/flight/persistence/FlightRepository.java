package dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends JpaRepository<FlightJpaEntity, String> {

    Page<FlightJpaEntity> findAll(Specification<FlightJpaEntity> whereClause, Pageable pageable);

    @Query(value = "select f.id from Flight f where f.id in :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);
}
