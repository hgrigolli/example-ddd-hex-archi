package dev.grigolli.example.dddhexarchi.application.flight.create;

import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;

import java.util.Objects;

public class DefaultCreateFlightUseCase extends CreateFlightUseCase {

    private final FlightGateway flightGateway;

    public DefaultCreateFlightUseCase(final FlightGateway aFlightGateway) {
        this.flightGateway = Objects.requireNonNull(aFlightGateway);
    }

    @Override
    public CreateFlightOutput execute(CreateFlightCommand anInput) {
        //TODO: implement method
        return null;
    }

}