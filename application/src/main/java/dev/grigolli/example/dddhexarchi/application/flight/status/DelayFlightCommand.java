package dev.grigolli.example.dddhexarchi.application.flight.status;

import java.time.Duration;

public record DelayFlightCommand(String flightId, Duration delay) {

    public DelayFlightCommand with(String flightId, Duration delay) {
        return new DelayFlightCommand(flightId, delay);
    }

}
