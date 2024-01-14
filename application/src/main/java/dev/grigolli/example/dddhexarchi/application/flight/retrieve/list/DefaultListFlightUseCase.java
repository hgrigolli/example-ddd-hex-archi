package dev.grigolli.example.dddhexarchi.application.flight.retrieve.list;

import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;
import dev.grigolli.example.dddhexarchi.domain.pagination.Pagination;
import dev.grigolli.example.dddhexarchi.domain.pagination.SearchQuery;

import java.util.Objects;

public class DefaultListFlightUseCase extends ListFlightUseCase {

    private final FlightGateway flightGateway;

    public DefaultListFlightUseCase(final FlightGateway aFlightGateway) {
        this.flightGateway = Objects.requireNonNull(aFlightGateway);
    }

    @Override
    public Pagination<FlightListOutput> execute(SearchQuery anInput) {
        return this.flightGateway.findAll(anInput)
                .map(FlightListOutput::from);
    }

}