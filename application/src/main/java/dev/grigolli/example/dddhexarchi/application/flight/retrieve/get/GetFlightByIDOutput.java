package dev.grigolli.example.dddhexarchi.application.flight.retrieve.get;

import dev.grigolli.example.dddhexarchi.domain.flight.Flight;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightID;

import java.time.LocalDate;
import java.time.LocalTime;

public record GetFlightByIDOutput(
        FlightID id,
        String flightNumber,
        LocalDate departureDate,
        LocalTime scheduledDepartureTime,
        LocalTime scheduledArrivalTime,
        String aircraftID,
        String departureAirport,
        String arrivalAirport
) {
    public static GetFlightByIDOutput from(final Flight aFlight) {
        return new GetFlightByIDOutput(
                aFlight.getId(),
                aFlight.getFlightNumber(),
                aFlight.getDepartureDate(),
                aFlight.getScheduledDepartureTime(),
                aFlight.getScheduledArrivalTime(),
                aFlight.getAircraftID(),
                aFlight.getDepartureAirport(),
                aFlight.getArrivalAirport());
    }

}