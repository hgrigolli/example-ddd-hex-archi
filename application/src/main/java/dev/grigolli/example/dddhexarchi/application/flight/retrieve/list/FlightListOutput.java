package dev.grigolli.example.dddhexarchi.application.flight.retrieve.list;

import dev.grigolli.example.dddhexarchi.domain.flight.Flight;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightID;

public record FlightListOutput(
        FlightID id
) {

    public static FlightListOutput from(final Flight aFlight) {
        return new FlightListOutput(aFlight.getId());
    }

}