package dev.grigolli.example.dddhexarchi.application.flight.reschedule;

import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightID;
import dev.grigolli.example.dddhexarchi.domain.validation.handler.Notification;

import java.util.Objects;

public class DefaultRescheduleFlightUseCase extends RescheduleFlightUseCase {

    private final FlightGateway flightGateway;

    public DefaultRescheduleFlightUseCase(final FlightGateway aFlightGateway) {
        this.flightGateway = Objects.requireNonNull(aFlightGateway);
    }

    @Override
    public RescheduleFlightOutput execute(RescheduleFlightCommand anInput) {
        final var aFlightID = FlightID.from(anInput.id());

        final var aFlight = this.flightGateway.findById(aFlightID).orElseThrow(notFound(aFlightID));

        final var notification = Notification.create();
        final var rescheduledFlight = notification.validate(() -> aFlight.reschedule(anInput.departureDate(), anInput.scheduledDepartureTime(), anInput.scheduledArrivalTime()));

        return RescheduleFlightOutput.from(this.flightGateway.update(rescheduledFlight));
    }

}