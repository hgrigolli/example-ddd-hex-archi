package dev.grigolli.example.dddhexarchi.application.flight.retrieve.get;

import dev.grigolli.example.dddhexarchi.domain.flight.Flight;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightID;

public record GetFlightByIDOutput(
        FlightID id
) {

    public static GetFlightByIDOutput from(final FlightID anId) {
        return new GetFlightByIDOutput(anId);
    }

    public static GetFlightByIDOutput from(final Flight aFlight) {
        return new GetFlightByIDOutput(aFlight.getId());
    }

}