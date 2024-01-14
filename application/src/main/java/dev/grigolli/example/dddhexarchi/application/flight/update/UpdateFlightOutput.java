package dev.grigolli.example.dddhexarchi.application.flight.update;

import dev.grigolli.example.dddhexarchi.domain.flight.Flight;

public record UpdateFlightOutput(
        String id
) {

    public static UpdateFlightOutput from(final Flight aFlight) {
        return new UpdateFlightOutput(aFlight.getId().getValue());
    }

}