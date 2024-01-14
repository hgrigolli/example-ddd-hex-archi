package dev.grigolli.example.dddhexarchi.application.flight.change;

public record ChangeAircraftFlightCommand(
        String flightID,
        String aircraftID
) {

    public static ChangeAircraftFlightCommand with(
            String flightID,
            String aircraftID
    ) {
        return new ChangeAircraftFlightCommand(
                flightID,
                aircraftID
        );

    }
}