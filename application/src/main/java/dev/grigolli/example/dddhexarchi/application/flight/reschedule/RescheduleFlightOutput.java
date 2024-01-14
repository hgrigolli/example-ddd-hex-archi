package dev.grigolli.example.dddhexarchi.application.flight.reschedule;

import dev.grigolli.example.dddhexarchi.domain.flight.Flight;

import java.time.LocalDate;
import java.time.LocalTime;

public record RescheduleFlightOutput(
        String id,
        LocalDate departureDate,
        LocalTime scheduledDepartureTime,
        LocalTime scheduledArrivalTime
) {

    public static RescheduleFlightOutput from(final Flight aFlight) {
        return new RescheduleFlightOutput(
                aFlight.getId().getValue(),
                aFlight.getDepartureDate(),
                aFlight.getScheduledDepartureTime(),
                aFlight.getScheduledArrivalTime());
    }

}