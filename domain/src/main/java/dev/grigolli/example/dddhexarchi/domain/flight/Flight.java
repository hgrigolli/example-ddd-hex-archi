package dev.grigolli.example.dddhexarchi.domain.flight;

import dev.grigolli.example.dddhexarchi.domain.AggregateRoot;
import dev.grigolli.example.dddhexarchi.domain.validation.ValidationHandler;
import dev.grigolli.example.dddhexarchi.domain.validation.handler.ThrowsValidationHandler;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Flight extends AggregateRoot<FlightID> implements Cloneable {

    private final String flightNumber;
    private LocalDate departureDate;
    private LocalTime scheduledDepartureTime;
    private LocalTime actualDepartureTime;
    private LocalTime scheduledArrivalTime;
    private LocalTime actualArrivalTime;
    private String aircraftID;
    private String departureAirport;
    private String arrivalAirport;
    private FlightStatus status;
    private Duration delay;
    private LocalDateTime onHoldUntil;

    private Flight(
            final FlightID anID,
            final String flightNumber,
            final LocalDate departureDate,
            final LocalTime scheduledDepartureTime,
            final LocalTime actualDepartureTime,
            final LocalTime scheduledArrivalTime,
            final LocalTime actualArrivalTime,
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
        if(actualArrivalTime == null || actualDepartureTime == null) {
            return null;
        }
        return Duration.between(actualDepartureTime, actualArrivalTime);
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

    public Duration getDelay() {
        return delay;
    }

    public LocalDateTime getOnHoldUntil() {
        return onHoldUntil;
    }

    public void cancel() {
        this.status = FlightStatus.CANCELLED;
        selfValidate();
    }

    public Flight reschedule(
            final LocalDate newDepartureDate,
            final LocalTime newScheduledDepartureTime,
            final LocalTime newScheduledArrivalTime
    ) {
        this.departureDate = newDepartureDate;
        this.scheduledDepartureTime = newScheduledDepartureTime;
        this.scheduledArrivalTime = newScheduledArrivalTime;
        selfValidate();
        return this;
    }

    public void board() {
        if (status == FlightStatus.SCHEDULED || status == FlightStatus.DELAYED || status == FlightStatus.ON_HOLD) {
            this.status = FlightStatus.BOARDING;
        } else {
            throw new IllegalStateException("Flight can only board if it is scheduled or delayed");
        }
        selfValidate();
    }

    public void depart(LocalTime departureDateTime) {
        if (status == FlightStatus.BOARDING || status == FlightStatus.ON_HOLD) {
            this.status = FlightStatus.DEPARTED;
            this.actualDepartureTime = departureDateTime;
        } else {
            throw new IllegalStateException("Flight can only depart if it is boarding or on hold");
        }
        selfValidate();
    }

    public void land() {
        if (status == FlightStatus.IN_AIR) {
            this.status = FlightStatus.LANDED;
        } else {
            throw new IllegalStateException("Flight can only land if it is in the air");
        }
        selfValidate();
    }

    public void arrive(LocalTime arrivalDateTime) {
        if (status == FlightStatus.LANDED) {
            this.status = FlightStatus.ARRIVED;
            this.actualArrivalTime = arrivalDateTime;
        } else {
            throw new IllegalStateException("Flight can only arrive if it has landed");
        }
        selfValidate();
    }

    public void delay(Duration expectedDelay) {
        if(expectedDelay == null){
            throw new IllegalArgumentException("Delay is required");
        }

        if(expectedDelay.isNegative()){
            throw new IllegalArgumentException("Delay must be greater than zero");
        }

        if(expectedDelay.isZero()) {
            throw new IllegalArgumentException("Delay must be greater than zero");
        }

        if (status == FlightStatus.SCHEDULED) {
            this.status = FlightStatus.DELAYED;
            this.delay = expectedDelay;
        } else {
            throw new IllegalStateException("Flight can only be delayed if it is scheduled");
        }
        selfValidate();
    }

    public void onHold(LocalDateTime expectedOnHoldUntil) {
        if(expectedOnHoldUntil == null){
            throw new IllegalArgumentException("On hold until is required");
        }

        if(expectedOnHoldUntil.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("On hold until must be in the future");
        }

        if (status == FlightStatus.BOARDING) {
            this.status = FlightStatus.ON_HOLD;
            this.onHoldUntil = expectedOnHoldUntil;
        } else {
            throw new IllegalStateException("Flight can only be put on hold if it is boarding");
        }
    }

    public void inAir() {
        if (status == FlightStatus.DEPARTED) {
            this.status = FlightStatus.IN_AIR;
        } else {
            throw new IllegalStateException("Flight can only be in air if it has departed");
        }
        selfValidate();
    }

    public void changeAircraft(String newAircraftID) {
        if (status == FlightStatus.SCHEDULED || status == FlightStatus.DELAYED || status == FlightStatus.ON_HOLD) {
            this.aircraftID = newAircraftID;
        } else {
            throw new IllegalStateException("Flight can only change aircraft if it is scheduled, delayed or on hold");
        }
        selfValidate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight flight)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getFlightNumber(), flight.getFlightNumber()) && Objects.equals(getDepartureDate(), flight.getDepartureDate()) && Objects.equals(getScheduledDepartureTime(), flight.getScheduledDepartureTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFlightNumber(), getDepartureDate(), getScheduledDepartureTime());
    }
}