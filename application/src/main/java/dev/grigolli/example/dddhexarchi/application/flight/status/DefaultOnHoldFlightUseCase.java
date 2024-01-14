package dev.grigolli.example.dddhexarchi.application.flight.status;

import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightID;

import java.util.Objects;

public class DefaultOnHoldFlightUseCase extends OnHoldFlightUseCase {

    private final FlightGateway flightGateway;

    public DefaultOnHoldFlightUseCase(final FlightGateway aFlightGateway) {
        this.flightGateway = Objects.requireNonNull(aFlightGateway);
    }

    @Override
    public void execute(OnHoldFlightCommand aOnHoldFlightCommand) {
        final var aFlightId = FlightID.from(aOnHoldFlightCommand.flightId());
        final var aOnHoldUntil = aOnHoldFlightCommand.onHoldUntil();

        final var aFlight = this.flightGateway.findById(aFlightId).orElseThrow(notFound(aFlightId));

        aFlight.onHold(aOnHoldUntil);

        this.flightGateway.update(aFlight);
    }

}
