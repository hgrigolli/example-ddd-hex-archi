package dev.grigolli.example.dddhexarchi.application.flight.update;

import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;

import java.util.Objects;

public class DefaultUpdateFlightUseCase extends UpdateFlightUseCase {

    private final FlightGateway flightGateway;

    public DefaultUpdateFlightUseCase(final FlightGateway aFlightGateway) {
        this.flightGateway = Objects.requireNonNull(aFlightGateway);
    }

    @Override
    public UpdateFlightOutput execute(UpdateFlightCommand anInput) {
        //TODO: implement method
        return null;
    }

}