package dev.grigolli.example.dddhexarchi.domain.flight;

import dev.grigolli.example.dddhexarchi.domain.validation.DomainError;
import dev.grigolli.example.dddhexarchi.domain.validation.ValidationHandler;
import dev.grigolli.example.dddhexarchi.domain.validation.Validator;

public class FlightValidator extends Validator {

    private final Flight flight;

    public FlightValidator(final Flight flight, final ValidationHandler aHandler) {
        super(aHandler);
        this.flight = flight;
    }

    private void checkFlightNumberConstraints() {
        final var flightNumber = this.flight.getFlightNumber();

        if(flightNumber == null) {
            this.validationHandler().append(new DomainError("Flight number is required"));
            return;
        }

        if(flightNumber.isBlank()) {
            this.validationHandler().append(new DomainError("Flight number is required"));
            return;
        }
    }

    private void checkDepartureDateConstraints() {
        final var departureDate = this.flight.getDepartureDate();

        if(departureDate == null) {
            this.validationHandler().append(new DomainError("Departure date is required"));
            return;
        }
    }

    private void checkScheduledDepartureTimeConstraints() {
        final var scheduledDepartureTime = this.flight.getScheduledDepartureTime();

        if(scheduledDepartureTime == null) {
            this.validationHandler().append(new DomainError("Scheduled departure time is required"));
            return;
        }
    }

    private void checkScheduledArrivalTimeConstraints() {
        final var scheduledArrivalTime = this.flight.getScheduledArrivalTime();

        if(scheduledArrivalTime == null) {
            this.validationHandler().append(new DomainError("Scheduled arrival time is required"));
            return;
        }
    }

    private void checkAircraftIDConstraints() {
        final var aircraftID = this.flight.getAircraftID();

        if(aircraftID == null) {
            this.validationHandler().append(new DomainError("Aircraft ID is required"));
            return;
        }

        if(aircraftID.isBlank()) {
            this.validationHandler().append(new DomainError("Aircraft ID is required"));
            return;
        }
    }

    private void checkDepartureAirportConstraints() {
        final var departureAirport = this.flight.getDepartureAirport();

        if(departureAirport == null) {
            this.validationHandler().append(new DomainError("Departure airport is required"));
            return;
        }

        if(departureAirport.isBlank()) {
            this.validationHandler().append(new DomainError("Departure airport is required"));
            return;
        }

        if(departureAirport.length() != 3) {
            this.validationHandler().append(new DomainError("Airport code must be 3 characters"));
            return;
        }
    }

    private void checkArrivalAirportConstraints() {
        final var arrivalAirport = this.flight.getArrivalAirport();

        if(arrivalAirport == null) {
            this.validationHandler().append(new DomainError("Arrival airport is required"));
            return;
        }

        if(arrivalAirport.isBlank()) {
            this.validationHandler().append(new DomainError("Arrival airport is required"));
            return;
        }

        if(arrivalAirport.length() != 3) {
            this.validationHandler().append(new DomainError("Airport code must be 3 characters"));
            return;
        }
    }

    @Override
    public void validate() {
        checkFlightNumberConstraints();
        checkDepartureDateConstraints();
        checkScheduledDepartureTimeConstraints();
        checkScheduledArrivalTimeConstraints();
        checkAircraftIDConstraints();
        checkDepartureAirportConstraints();
        checkArrivalAirportConstraints();
    }


}