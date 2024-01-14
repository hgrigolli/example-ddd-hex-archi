package dev.grigolli.example.dddhexarchi.application.flight.change;

import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightID;

import java.util.Objects;

public class DefaultChangeAircraftFlightUseCase extends ChangeAircraftFlightUseCase {

    private final FlightGateway flightGateway;

    public DefaultChangeAircraftFlightUseCase(final FlightGateway aFlightGateway) {
        this.flightGateway = Objects.requireNonNull(aFlightGateway);
    }

    @Override
    public void execute(ChangeAircraftFlightCommand anInput) {
        final var aFlightId = FlightID.from(anInput.flightID());
        final var anAircraftId = anInput.aircraftID();

        final var aFlight = flightGateway.findById(aFlightId).orElseThrow(notFound(aFlightId));

        aFlight.changeAircraft(anAircraftId);

        flightGateway.update(aFlight);
    }

}