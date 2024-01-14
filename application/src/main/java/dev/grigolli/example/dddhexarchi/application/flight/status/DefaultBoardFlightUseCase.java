package dev.grigolli.example.dddhexarchi.application.flight.status;

import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightID;

import java.util.Objects;

public class DefaultBoardFlightUseCase extends BoardFlightUseCase {

    private final FlightGateway flightGateway;

    public DefaultBoardFlightUseCase(final FlightGateway aFlightGateway) {
        this.flightGateway = Objects.requireNonNull(aFlightGateway);
    }

    @Override
    public void execute(String anID) {
        final var flightID = FlightID.from(anID);

        final var aFlight = flightGateway.findById(flightID).orElseThrow(notFound(flightID));

        aFlight.board();

        flightGateway.update(aFlight);

    }

}
