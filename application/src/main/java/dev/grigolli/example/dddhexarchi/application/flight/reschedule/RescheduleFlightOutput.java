package dev.grigolli.example.dddhexarchi.application.flight.reschedule;

import dev.grigolli.example.dddhexarchi.domain.flight.Flight;

public record RescheduleFlightOutput(
        String id
) {

    public static RescheduleFlightOutput from(final Flight aFlight) {
        return new RescheduleFlightOutput(aFlight.getId().getValue());
    }

}