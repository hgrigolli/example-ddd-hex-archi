package dev.grigolli.example.dddhexarchi.application.flight.reschedule;

import dev.grigolli.example.dddhexarchi.domain.flight.FlightID;

import java.time.LocalDate;
import java.time.LocalTime;

public record RescheduleFlightCommand(
        String id,
        LocalDate departureDate,
        LocalTime scheduledDepartureTime,
        LocalTime scheduledArrivalTime

) {
    public static RescheduleFlightCommand with(
            String flightID,
            LocalDate departureDate,
            LocalTime scheduledDepartureTime,
            LocalTime scheduledArrivalTime) {
        return new RescheduleFlightCommand(flightID, departureDate, scheduledDepartureTime, scheduledArrivalTime);
    }
}