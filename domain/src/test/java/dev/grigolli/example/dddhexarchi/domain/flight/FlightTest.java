package dev.grigolli.example.dddhexarchi.domain.flight;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

class FlightTest {

    @Test
    void givenAValidFlight_whenCallNewFlight_thenInstantiateAFlight() {
        // given
        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        // when
        final var aFlight = Flight.newFlight(
                expectedFlightNumber,
                expectedDepartureDate,
                expectedScheduledDepartureTime,
                expectedScheduledArrivalTime,
                expectedAircraftID,
                expectedDepartureAirport,
                expectedArrivalAirport
        );

        // then
        Assertions.assertNotNull(aFlight);
        Assertions.assertNotNull(aFlight.getId());
        Assertions.assertEquals(expectedFlightNumber, aFlight.getFlightNumber());
        Assertions.assertEquals(expectedDepartureDate, aFlight.getDepartureDate());
        Assertions.assertEquals(expectedScheduledDepartureTime, aFlight.getScheduledDepartureTime());
        Assertions.assertEquals(expectedScheduledArrivalTime, aFlight.getScheduledArrivalTime());
        Assertions.assertEquals(expectedAircraftID, aFlight.getAircraftID());
        Assertions.assertEquals(expectedDepartureAirport, aFlight.getDepartureAirport());
        Assertions.assertEquals(expectedArrivalAirport, aFlight.getArrivalAirport());
        Assertions.assertEquals(FlightStatus.SCHEDULED, aFlight.getStatus());

    }

}
