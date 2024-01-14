package dev.grigolli.example.dddhexarchi.application.flight.status;

import java.time.LocalDateTime;

public record OnHoldFlightCommand(String flightId, LocalDateTime onHoldUntil) {

    public OnHoldFlightCommand with(String flightId, LocalDateTime onHoldUntil) {
        return new OnHoldFlightCommand(flightId, onHoldUntil);
    }
}
