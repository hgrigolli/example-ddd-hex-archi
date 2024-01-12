package dev.grigolli.example.dddhexarchi.domain.flight;

import dev.grigolli.example.dddhexarchi.domain.AggregateRoot;
import dev.grigolli.example.dddhexarchi.domain.validation.ValidationHandler;
import dev.grigolli.example.dddhexarchi.domain.validation.handler.ThrowsValidationHandler;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Flight extends AggregateRoot<FlightID> implements Cloneable {

    private String flightNumber;
    private LocalDate departureDate;
    private LocalTime scheduledDepartureTime;
    private LocalTime actualDepartureTime;
    private LocalTime scheduledArrivalTime;
    private LocalTime actualArrivalTime;
    private Duration flightTime;
    private String aircraftID;
    private String departureAirport;
    private String arrivalAirport;
    private FlightStatus status;

    private Flight(
            final FlightID anID,
            final String flightNumber,
            final LocalDate departureDate,
            final LocalTime scheduledDepartureTime,
            final LocalTime actualDepartureTime,
            final LocalTime scheduledArrivalTime,
            final LocalTime actualArrivalTime,
            final Duration flightTime,
            final String aircraftID,
            final String departureAirport,
            final String arrivalAirport,
            final FlightStatus status
    ) {
        super(anID);
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.scheduledDepartureTime = scheduledDepartureTime;
        this.actualDepartureTime = actualDepartureTime;
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.actualArrivalTime = actualArrivalTime;
        this.flightTime = flightTime;
        this.aircraftID = aircraftID;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.status = status;
        selfValidate();
    }

    public static Flight newFlight(
            final String flightNumber,
            final LocalDate departureDate,
            final LocalTime scheduledDepartureTime,
            final LocalTime scheduledArrivalTime,
            final String aircraftID,
            final String departureAirport,
            final String arrivalAirport
    ) {
        final var id = FlightID.unique();
        return new Flight(
                id,
                flightNumber,
                departureDate,
                scheduledDepartureTime,
                null,
                scheduledArrivalTime,
                null,
                null,
                aircraftID,
                departureAirport,
                arrivalAirport,
                FlightStatus.SCHEDULED
        );
    }

    public static Flight with(
            final FlightID anID,
            final String flightNumber,
            final LocalDate departureDate,
            final LocalTime scheduledDepartureTime,
            final LocalTime scheduledArrivalTime,
            final LocalTime actualArrivalTime,
            final LocalTime actualDepartureTime,
            final Duration flightTime,
            final String aircraftID,
            final String departureAirport,
            final String arrivalAirport,
            final FlightStatus status

    ) {
        return new Flight(anID,
                flightNumber,
                departureDate,
                scheduledDepartureTime,
                actualDepartureTime,
                scheduledArrivalTime,
                actualArrivalTime,
                flightTime,
                aircraftID,
                departureAirport,
                arrivalAirport,
                status
        );
    }

    @Override
    public void validate(ValidationHandler handler) {
        new FlightValidator(this, handler).validate();
    }

    private void selfValidate() {
        this.validate(new ThrowsValidationHandler());
    }

    @Override
    public Flight clone() {
        try {
            Flight clone = (Flight) super.clone();
            // copy mutable state here, so the clone can't change the internals of the original
            // In case there are any lists, a copy should be done here.
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalTime getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    public LocalTime getActualDepartureTime() {
        return actualDepartureTime;
    }

    public LocalTime getScheduledArrivalTime() {
        return scheduledArrivalTime;
    }

    public LocalTime getActualArrivalTime() {
        return actualArrivalTime;
    }

    public Duration getFlightTime() {
        return flightTime;
    }

    public String getAircraftID() {
        return aircraftID;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public FlightStatus getStatus() {
        return status;
    }
}