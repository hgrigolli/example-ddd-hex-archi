package dev.grigolli.example.dddhexarchi.application.flight.create;

import dev.grigolli.example.dddhexarchi.domain.flight.Flight;

public record CreateFlightOutput(
        String id
) {

    public static CreateFlightOutput from(final Flight aFlight) {
        return new CreateFlightOutput(aFlight.getId().getValue());
    }

}