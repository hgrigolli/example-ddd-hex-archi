package dev.grigolli.example.dddhexarchi.application.flight.retrieve.get;

import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightID;

import java.util.Objects;

public class DefaultGetFlightByIDUseCase extends GetFlightByIDUseCase {

    private final FlightGateway flightGateway;

    public DefaultGetFlightByIDUseCase(final FlightGateway aFlightGateway) {
        this.flightGateway = Objects.requireNonNull(aFlightGateway);
    }

    @Override
    public GetFlightByIDOutput execute(String anID) {
        final var aFlightID = FlightID.from(anID);

        return this.flightGateway.findById(aFlightID)
                .map(GetFlightByIDOutput::from)
                .orElseThrow(notFound(aFlightID));

    }

}