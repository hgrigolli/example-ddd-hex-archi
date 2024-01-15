package dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence;

import dev.grigolli.example.dddhexarchi.domain.flight.Flight;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightID;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity(name = "Flight")
@Table(name = "flight")
public class FlightJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "flight_number", nullable = false)
    @Size(min = 3, message = "size must greater than 3")
    private String flightNumber;

    @Column(name = "departure_date", nullable = false)
    private LocalDate departureDate;

    @Column(name = "scheduled_departure_time", nullable = false)
    private LocalTime scheduledDepartureTime;

    @Column(name = "actual_departure_time")
    private LocalTime actualDepartureTime;

    @Column(name = "scheduled_arrival_time", nullable = false)
    private LocalTime scheduledArrivalTime;

    @Column(name = "actual_arrival_time")
    private LocalTime actualArrivalTime;

    @Column(name = "aircraft_id", nullable = false)
    @Size(min = 1, max = 10, message = "size must be between 1 and 10")
    private String aircraftID;

    @Column(name = "departure_airport", nullable = false, length = 3)
    @Size(min = 3, max = 3, message = "size must be 3")
    private String departureAirport;

    @Column(name = "arrival_airport", nullable = false, length = 3)
    @Size(min = 3, max = 3, message = "size must be 3")
    private String arrivalAirport;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    @Column(name = "delay")
    private Duration delay;

    @Column(name = "on_hold_until")
    private LocalDateTime onHoldUntil;

    public FlightJpaEntity() {}

    private FlightJpaEntity(
            String anID,
            String flightNumber,
            LocalDate departureDate,
            LocalTime scheduledDepartureTime,
            LocalTime actualDepartureTime,
            LocalTime scheduledArrivalTime,
            LocalTime actualArrivalTime,
            String aircraftID,
            String departureAirport,
            String arrivalAirport,
            FlightStatus status,
            Duration delay,
            LocalDateTime onHoldUntil
    ) {
        this.id = anID;
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
        this.delay = delay;
        this.onHoldUntil = onHoldUntil;
    }

    public static FlightJpaEntity from(final Flight flight) {
        return new FlightJpaEntity(
                flight.getId().getValue(),
                flight.getFlightNumber(),
                flight.getDepartureDate(),
                flight.getScheduledDepartureTime(),
                flight.getActualDepartureTime(),
                flight.getScheduledArrivalTime(),
                flight.getActualArrivalTime(),
                flight.getAircraftID(),
                flight.getDepartureAirport(),
                flight.getArrivalAirport(),
                flight.getStatus(),
                flight.getDelay(),
                flight.getOnHoldUntil()
        );
    }

    public Flight toAggregate() {
        return Flight.with(
                FlightID.from(getId()),
                getFlightNumber(),
                getDepartureDate(),
                getScheduledDepartureTime(),
                getScheduledArrivalTime(),
                getActualArrivalTime(),
                getActualDepartureTime(),
                getAircraftID(),
                getDepartureAirport(),
                getArrivalAirport(),
                getStatus(),
                getDelay(),
                getOnHoldUntil()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    public void setScheduledDepartureTime(LocalTime scheduledDepartureTime) {
        this.scheduledDepartureTime = scheduledDepartureTime;
    }

    public LocalTime getActualDepartureTime() {
        return actualDepartureTime;
    }

    public void setActualDepartureTime(LocalTime actualDepartureTime) {
        this.actualDepartureTime = actualDepartureTime;
    }

    public LocalTime getScheduledArrivalTime() {
        return scheduledArrivalTime;
    }

    public void setScheduledArrivalTime(LocalTime scheduledArrivalTime) {
        this.scheduledArrivalTime = scheduledArrivalTime;
    }

    public LocalTime getActualArrivalTime() {
        return actualArrivalTime;
    }

    public void setActualArrivalTime(LocalTime actualArrivalTime) {
        this.actualArrivalTime = actualArrivalTime;
    }

    public String getAircraftID() {
        return aircraftID;
    }

    public void setAircraftID(String aircraftID) {
        this.aircraftID = aircraftID;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    public Duration getDelay() {
        return delay;
    }

    public void setDelay(Duration delay) {
        this.delay = delay;
    }

    public LocalDateTime getOnHoldUntil() {
        return onHoldUntil;
    }

    public void setOnHoldUntil(LocalDateTime onHoldUntil) {
        this.onHoldUntil = onHoldUntil;
    }
}
