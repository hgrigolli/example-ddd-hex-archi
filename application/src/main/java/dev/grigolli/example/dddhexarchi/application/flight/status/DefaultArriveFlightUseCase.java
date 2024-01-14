package dev.grigolli.example.dddhexarchi.application.flight.status;

import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightID;

import java.util.Objects;

public class DefaultArriveFlightUseCase extends ArriveFlightUseCase {

    private final FlightGateway flightGateway;

    public DefaultArriveFlightUseCase(final FlightGateway aFlightGateway) {
        this.flightGateway = Objects.requireNonNull(aFlightGateway);
    }

    @Override
    public void execute(String anID) {
        final var flightID = FlightID.from(anID);
        final var aFlight = flightGateway.findById(flightID).orElseThrow(notFound(flightID));

        aFlight.arrive();

        flightGateway.update(aFlight);
    }

}
