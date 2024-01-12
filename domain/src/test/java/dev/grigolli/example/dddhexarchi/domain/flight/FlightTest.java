package dev.grigolli.example.dddhexarchi.domain.flight;

import dev.grigolli.example.dddhexarchi.domain.exceptions.DomainException;
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

    @Test
    void givenAInvalidFlightWithNullFlightNumber_whenCallNewFlight_thenShouldThrowADomainException() {
        // given
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        // when
        final var domainException = Assertions.assertThrows(
                DomainException.class,
                () -> Flight.newFlight(
                        null,
                        expectedDepartureDate,
                        expectedScheduledDepartureTime,
                        expectedScheduledArrivalTime,
                        expectedAircraftID,
                        expectedDepartureAirport,
                        expectedArrivalAirport
                )
        );

        // then
        Assertions.assertEquals("Flight number is required", domainException.getMessage());
    }

    @Test
    void givenAInvalidFlightWithEmptyFlightNumber_whenCallNewFlight_thenShouldThrowADomainException() {
        // given
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        // when
        final var domainException = Assertions.assertThrows(
                DomainException.class,
                () -> Flight.newFlight(
                        "",
                        expectedDepartureDate,
                        expectedScheduledDepartureTime,
                        expectedScheduledArrivalTime,
                        expectedAircraftID,
                        expectedDepartureAirport,
                        expectedArrivalAirport
                )
        );

        // then
        Assertions.assertEquals("Flight number is required", domainException.getMessage());
    }

    @Test
    void givenAInvalidFlightWithNullDepartureDate_whenCallNewFlight_thenShouldThrowADomainException() {
        // given
        final var expectedFlightNumber = "JJ1234";
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        // when
        final var domainException = Assertions.assertThrows(
                DomainException.class,
                () -> Flight.newFlight(
                        expectedFlightNumber,
                        null,
                        expectedScheduledDepartureTime,
                        expectedScheduledArrivalTime,
                        expectedAircraftID,
                        expectedDepartureAirport,
                        expectedArrivalAirport
                )
        );

        // then
        Assertions.assertEquals("Departure date is required", domainException.getMessage());
    }

    @Test
    void givenAInvalidFlightWithNullScheduledDepartureTime_whenCallNewFlight_thenShouldThrowADomainException() {
        // given
        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        // when
        final var domainException = Assertions.assertThrows(
                DomainException.class,
                () -> Flight.newFlight(
                        expectedFlightNumber,
                        expectedDepartureDate,
                        null,
                        expectedScheduledArrivalTime,
                        expectedAircraftID,
                        expectedDepartureAirport,
                        expectedArrivalAirport
                )
        );

        // then
        Assertions.assertEquals("Scheduled departure time is required", domainException.getMessage());
    }

    @Test
    void givenAInvalidFlightWithNullScheduledArrivalTime_whenCallNewFlight_thenShouldThrowADomainException() {
        // given
        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        // when
        final var domainException = Assertions.assertThrows(
                DomainException.class,
                () -> Flight.newFlight(
                        expectedFlightNumber,
                        expectedDepartureDate,
                        expectedScheduledDepartureTime,
                        null,
                        expectedAircraftID,
                        expectedDepartureAirport,
                        expectedArrivalAirport
                )
        );

        // then
        Assertions.assertEquals("Scheduled arrival time is required", domainException.getMessage());
    }

    @Test
    void givenAInvalidFlightWithNullAircraftID_whenCallNewFlight_thenShouldThrowADomainException() {
        // given
        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        // when
        final var domainException = Assertions.assertThrows(
                DomainException.class,
                () -> Flight.newFlight(
                        expectedFlightNumber,
                        expectedDepartureDate,
                        expectedScheduledDepartureTime,
                        expectedScheduledArrivalTime,
                        null,
                        expectedDepartureAirport,
                        expectedArrivalAirport
                )
        );

        // then
        Assertions.assertEquals("Aircraft ID is required", domainException.getMessage());
    }

    @Test
    void givenAInvalidFlightWithEmptyAircraftID_whenCallNewFlight_thenShouldThrowADomainException() {
        // given
        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        // when
        final var domainException = Assertions.assertThrows(
                DomainException.class,
                () -> Flight.newFlight(
                        expectedFlightNumber,
                        expectedDepartureDate,
                        expectedScheduledDepartureTime,
                        expectedScheduledArrivalTime,
                        expectedAircraftID,
                        expectedDepartureAirport,
                        expectedArrivalAirport
                )
        );

        // then
        Assertions.assertEquals("Aircraft ID is required", domainException.getMessage());
    }

    @Test
    void givenAInvalidFlightWithNullDepartureAirport_whenCallNewFlight_thenShouldThrowADomainException() {
        // given
        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedArrivalAirport = "CGR";

        // when
        final var domainException = Assertions.assertThrows(
                DomainException.class,
                () -> Flight.newFlight(
                        expectedFlightNumber,
                        expectedDepartureDate,
                        expectedScheduledDepartureTime,
                        expectedScheduledArrivalTime,
                        expectedAircraftID,
                        null,
                        expectedArrivalAirport
                )
        );

        // then
        Assertions.assertEquals("Departure airport is required", domainException.getMessage());
    }

    @Test
    void givenAInvalidFlightWithEmptyDepartureAirport_whenCallNewFlight_thenShouldThrowADomainException() {
        // given
        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "";
        final var expectedArrivalAirport = "CGR";

        // when
        final var domainException = Assertions.assertThrows(
                DomainException.class,
                () -> Flight.newFlight(
                        expectedFlightNumber,
                        expectedDepartureDate,
                        expectedScheduledDepartureTime,
                        expectedScheduledArrivalTime,
                        expectedAircraftID,
                        expectedDepartureAirport,
                        expectedArrivalAirport
                )
        );

        // then
        Assertions.assertEquals("Departure airport is required", domainException.getMessage());
    }

    @Test
    void givenAInvalidFlightWithNullArrivalAirport_whenCallNewFlight_thenShouldThrowADomainException() {
        // given
        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";

        // when
        final var domainException = Assertions.assertThrows(
                DomainException.class,
                () -> Flight.newFlight(
                        expectedFlightNumber,
                        expectedDepartureDate,
                        expectedScheduledDepartureTime,
                        expectedScheduledArrivalTime,
                        expectedAircraftID,
                        expectedDepartureAirport,
                        null
                )
        );

        // then
        Assertions.assertEquals("Arrival airport is required", domainException.getMessage());
    }

    @Test
    void givenAInvalidFlightWithEmptyArrivalAirport_whenCallNewFlight_thenShouldThrowADomainException() {
        // given
        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "";

        // when
        final var domainException = Assertions.assertThrows(
                DomainException.class,
                () -> Flight.newFlight(
                        expectedFlightNumber,
                        expectedDepartureDate,
                        expectedScheduledDepartureTime,
                        expectedScheduledArrivalTime,
                        expectedAircraftID,
                        expectedDepartureAirport,
                        expectedArrivalAirport
                )
        );

        // then
        Assertions.assertEquals("Arrival airport is required", domainException.getMessage());
    }

}
