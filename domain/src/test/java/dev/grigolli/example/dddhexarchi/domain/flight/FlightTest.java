package dev.grigolli.example.dddhexarchi.domain.flight;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightTest {


    @Test
    void givenAValidFlight_whenCallCreate_thenInstantiateAFlight() {
        // given
        final var departureDate = LocalDate.of(2024, 1, 12);
        final var scheduledDepartureTime = LocalTime.of(17, 20);
        final var scheduledArrivalTime = LocalTime.of(18, 10);
        final var aircraftID = "A123";

        /*
         final LocalDate departureDate,
            final LocalDateTime scheduledDepartureTime,
            final LocalDateTime scheduledArrivalTime,
            final Duration flightTime,
            final String aircraftID,
            final String departureAirport,
            final String arrivalAirport
         */

    }

}
