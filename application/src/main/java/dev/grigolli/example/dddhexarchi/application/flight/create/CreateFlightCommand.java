package dev.grigolli.example.dddhexarchi.application.flight.create;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateFlightCommand(
        String flightNumber,
        LocalDate departureDate,
        LocalTime scheduledDepartureTime,
        LocalTime scheduledArrivalTime,
        String aircraftID,
        String departureAirport,
        String arrivalAirport
) {
    public static CreateFlightCommand with(
            final String flightNumber,
            final LocalDate departureDate,
            final LocalTime scheduledDepartureTime,
            final LocalTime scheduledArrivalTime,
            final String aircraftID,
            final String departureAirport,
            final String arrivalAirport) {
        return new CreateFlightCommand(
                flightNumber,
                departureDate,
                scheduledDepartureTime,
                scheduledArrivalTime,
                aircraftID,
                departureAirport,
                arrivalAirport);
    }
}