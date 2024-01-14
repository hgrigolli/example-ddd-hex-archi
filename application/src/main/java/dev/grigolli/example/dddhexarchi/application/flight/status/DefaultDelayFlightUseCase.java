package dev.grigolli.example.dddhexarchi.application.flight.status;

import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightID;

import java.util.Objects;

public class DefaultDelayFlightUseCase extends DelayFlightUseCase {

    private final FlightGateway flightGateway;

    public DefaultDelayFlightUseCase(final FlightGateway aFlightGateway) {
        this.flightGateway = Objects.requireNonNull(aFlightGateway);
    }

    @Override
    public void execute(DelayFlightCommand aDelayFlightCommand) {
        final var flightID = FlightID.from(aDelayFlightCommand.flightId());

        final var aFlight = flightGateway.findById(flightID).orElseThrow(notFound(flightID));

        aFlight.delay(aDelayFlightCommand.delay());

        flightGateway.update(aFlight);
    }

}
