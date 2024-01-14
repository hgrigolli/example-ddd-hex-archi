package dev.grigolli.example.dddhexarchi.application.flight.delete;

import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightID;

import java.util.Objects;

public class DefaultDeleteFlightUseCase extends DeleteFlightUseCase {

    private final FlightGateway flightGateway;

    public DefaultDeleteFlightUseCase(final FlightGateway aFlightGateway) {
        this.flightGateway = Objects.requireNonNull(aFlightGateway);
    }

    @Override
    public void execute(String anID) {
        this.flightGateway.deleteById(FlightID.from(anID));
    }

}
