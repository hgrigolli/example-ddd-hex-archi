package dev.grigolli.example.dddhexarchi.application.flight.reschedule;

import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;

import java.util.Objects;

public class DefaultRescheduleFlightUseCase extends RescheduleFlightUseCase {

    private final FlightGateway flightGateway;

    public DefaultRescheduleFlightUseCase(final FlightGateway aFlightGateway) {
        this.flightGateway = Objects.requireNonNull(aFlightGateway);
    }

    @Override
    public RescheduleFlightOutput execute(RescheduleFlightCommand anInput) {
        //TODO: implement method
        return null;
    }

}