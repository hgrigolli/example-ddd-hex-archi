package dev.grigolli.example.dddhexarchi.application.flight.create;

import dev.grigolli.example.dddhexarchi.domain.exceptions.NotificationException;
import dev.grigolli.example.dddhexarchi.domain.flight.Flight;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;
import dev.grigolli.example.dddhexarchi.domain.validation.handler.Notification;

import java.util.Objects;

public class DefaultCreateFlightUseCase extends CreateFlightUseCase {

    private final FlightGateway flightGateway;

    public DefaultCreateFlightUseCase(final FlightGateway aFlightGateway) {
        this.flightGateway = Objects.requireNonNull(aFlightGateway);
    }

    @Override
    public CreateFlightOutput execute(CreateFlightCommand anInput) {
        final var flightNumber = anInput.flightNumber();
        final var departureDate = anInput.departureDate();
        final var scheduledDepartureTime = anInput.scheduledDepartureTime();
        final var scheduledArrivalTime = anInput.scheduledArrivalTime();
        final var aircraftID = anInput.aircraftID();
        final var departureAirport = anInput.departureAirport();
        final var arrivalAirport = anInput.arrivalAirport();

        final var notification = Notification.create();
        final var aFlight = notification.validate(() -> Flight.newFlight(flightNumber, departureDate, scheduledDepartureTime, scheduledArrivalTime, aircraftID, departureAirport, arrivalAirport));

        if(notification.hasErrors()) {
            throw new NotificationException("Could not create Aggregate Flight", notification);
        }

        return CreateFlightOutput.from(this.flightGateway.create(aFlight));
    }

}