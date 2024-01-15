package dev.grigolli.example.dddhexarchi.infrastructure.flight;

import dev.grigolli.example.dddhexarchi.domain.flight.Flight;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightID;
import dev.grigolli.example.dddhexarchi.domain.pagination.Pagination;
import dev.grigolli.example.dddhexarchi.domain.pagination.SearchQuery;
import dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence.FlightJpaEntity;
import dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence.FlightRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static dev.grigolli.example.dddhexarchi.infrastructure.utils.SpecificationUtils.like;

@Component
public class FlightPostgreSQLGateway implements FlightGateway {

    private final FlightRepository flightRepository;

    public FlightPostgreSQLGateway(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight create(Flight aFlight) {
        return save(aFlight);
    }

    @Override
    public void deleteById(FlightID anID) {
        final String anIDValue = anID.getValue();
        if(this.flightRepository.existsById(anIDValue)) {
            this.flightRepository.deleteById(anIDValue);
        }
    }

    @Override
    public Optional<Flight> findById(FlightID anID) {
        return this.flightRepository.findById(anID.getValue())
                .map(FlightJpaEntity::toAggregate);
    }

    @Override
    public Flight update(Flight aFlight) {
        return save(aFlight);
    }

    @Override
    public Pagination<Flight> findAll(SearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()),aQuery.sort())
        );
        //Busca dinamica pelo criterio terms
        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult = this.flightRepository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(FlightJpaEntity::toAggregate).toList()
        );
    }

    @Override
    public List<FlightID> existsByIds(Iterable<FlightID> flightIDS) {
        final var ids = StreamSupport.stream(flightIDS.spliterator(), false)
                .map(FlightID::getValue)
                .toList();

        return this.flightRepository.existsByIds(ids).stream()
                .map(FlightID::from)
                .toList();
    }

    private Flight save(Flight aFlight) {
        return this.flightRepository.save(FlightJpaEntity.from(aFlight)).toAggregate();
    }

    private Specification<FlightJpaEntity> assembleSpecification(final String str) {
        final Specification<FlightJpaEntity> flightNumberLike = like("flightNumber", str);
        final Specification<FlightJpaEntity> departureAirportLike = like("departureAirport", str);
        final Specification<FlightJpaEntity> arrivalAirportLike = like("arrivalAirport", str);
        final Specification<FlightJpaEntity> flightStatusLike = like("flightStatus", str);
        final Specification<FlightJpaEntity> aircraftIDLike = like("aircraftID", str);

        return flightNumberLike.or(departureAirportLike).or(arrivalAirportLike).or(flightStatusLike).or(aircraftIDLike);
    }
}
