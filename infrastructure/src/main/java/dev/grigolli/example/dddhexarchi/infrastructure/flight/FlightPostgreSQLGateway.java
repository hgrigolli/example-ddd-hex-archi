package dev.grigolli.example.dddhexarchi.infrastructure.flight;

import dev.grigolli.example.dddhexarchi.domain.flight.Flight;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightID;
import dev.grigolli.example.dddhexarchi.domain.pagination.Pagination;
import dev.grigolli.example.dddhexarchi.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public class FlightPostgreSQLGateway implements FlightGateway {

    @Override
    public Flight create(Flight aDomain) {
        return null;
    }

    @Override
    public void deleteById(FlightID anID) {

    }

    @Override
    public Optional<Flight> findById(FlightID anID) {
        return Optional.empty();
    }

    @Override
    public Flight update(Flight aDomain) {
        return null;
    }

    @Override
    public Pagination<Flight> findAll(SearchQuery aQuery) {
        return null;
    }

    @Override
    public List<FlightID> existsByIds(Iterable<FlightID> flightIDS) {
        return null;
    }
}
