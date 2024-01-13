package dev.grigolli.example.dddhexarchi.domain.flight;

import dev.grigolli.example.dddhexarchi.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

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
    void givenAInvalidFlightWithDepartureAirportLessThan3Char_whenCallNewFlight_thenShouldThrowADomainException() {
        // given
        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 13);
        final var expectedScheduledDepartureTime = LocalTime.of(18, 10);
        final var expectedScheduledArrivalTime = LocalTime.of(19, 0);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GR";
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
        Assertions.assertEquals("Airport code must be 3 characters", domainException.getMessage());
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

    @Test
    void givenAInvalidFlightWithArrivalAirportLessThan3Char_whenCallNewFlight_thenShouldThrowADomainException() {
        // given
        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 13);
        final var expectedScheduledDepartureTime = LocalTime.of(18, 10);
        final var expectedScheduledArrivalTime = LocalTime.of(19, 0);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CG";

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
        Assertions.assertEquals("Airport code must be 3 characters", domainException.getMessage());
    }

    @Test
    void givenAFlight_whenCallCancel_thenShouldCancelTheFlight() {
        // given
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );

        // when
        aFlight.cancel();

        // then
        Assertions.assertEquals(FlightStatus.CANCELLED, aFlight.getStatus());
    }

    @Test
    void givenAFlight_whenCallReschedule_thenShouldRescheduleTheFlight() {
        // given
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );

        // when
        aFlight.reschedule(
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 30),
                LocalTime.of(18, 20)
        );

        // then
        Assertions.assertEquals(LocalDate.of(2024, 1, 12), aFlight.getDepartureDate());
        Assertions.assertEquals(LocalTime.of(17, 30), aFlight.getScheduledDepartureTime());
        Assertions.assertEquals(LocalTime.of(18, 20), aFlight.getScheduledArrivalTime());
    }

    @Test
    void givenAFlight_whenCallBoard_thenShouldBoardTheFlight() {
        // given
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );

        // when
        aFlight.board();

        // then
        Assertions.assertEquals(FlightStatus.BOARDING, aFlight.getStatus());
    }

    @Test
    void givenAFlight_whenCallDepart_thenShouldDepartTheFlight() {
        // given
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );

        // when
        aFlight.board();
        aFlight.depart(LocalTime.of(17, 32));

        // then
        Assertions.assertEquals(FlightStatus.DEPARTED, aFlight.getStatus());
    }

    @Test
    void givenAFlight_whenCallLand_thenShouldLandTheFlight() {
        // given
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );

        // when
        aFlight.board();
        aFlight.depart(LocalTime.of(17, 32));
        aFlight.inAir();
        aFlight.land();

        // then
        Assertions.assertEquals(FlightStatus.LANDED, aFlight.getStatus());
    }

    @Test
    void givenAFlight_whenCallArrive_thenShouldArriveTheFlight() {
        // given
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );
        final var expectedDuration = Duration.of(35, ChronoUnit.MINUTES);

        // when
        aFlight.board();
        aFlight.depart(LocalTime.of(17, 32));
        aFlight.inAir();
        aFlight.land();
        aFlight.arrive(LocalTime.of(18, 7));

        // then
        Assertions.assertEquals(FlightStatus.ARRIVED, aFlight.getStatus());
        Assertions.assertEquals(expectedDuration, aFlight.getFlightTime());
    }

    @Test
    void givenAFlight_whenCallDelay_thenShouldDelayTheFlight() {
        // given
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );
        final var expectedDelay = Duration.of(30, ChronoUnit.MINUTES);

        // when
        aFlight.delay(expectedDelay);

        // then
        Assertions.assertEquals(FlightStatus.DELAYED, aFlight.getStatus());
        // Assertions.assertEquals(expectedDelay, aFlight.getDelay());
        // calculate arrival time
    }

    @Test
    void givenAFlight_whenCallDelayWithNegativeValue_thenShouldThrowADomainException() {
        // given
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(18, 10),
                LocalTime.of(19, 0),
                "B737",
                "GRU",
                "CGR"
        );
        final var expectedDelay = Duration.of(-30, ChronoUnit.MINUTES);

        // when
        final var illegalArgumentException = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> aFlight.delay(expectedDelay)
        );

        // then
        Assertions.assertEquals("Delay must be greater than zero", illegalArgumentException.getMessage());
    }

    @Test
    void givenAFlight_whenCallDelayWithZeroValue_thenShouldThrowADomainException() {
        // given
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(18, 10),
                LocalTime.of(19, 0),
                "B737",
                "GRU",
                "CGR"
        );
        final var expectedDelay = Duration.of(0, ChronoUnit.MINUTES);

        // when
        final var illegalArgumentException = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> aFlight.delay(expectedDelay)
        );

        // then
        Assertions.assertEquals("Delay must be greater than zero", illegalArgumentException.getMessage());
    }

    @Test
    void givenAFlight_whenCallDelayWithNullValue_thenShouldThrowADomainException() {
        // given
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(18, 10),
                LocalTime.of(19, 0),
                "B737",
                "GRU",
                "CGR"
        );
        final Duration expectedDelay = null;

        // when
        final var illegalArgumentException = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> aFlight.delay(expectedDelay)
        );

        // then
        Assertions.assertEquals("Delay is required", illegalArgumentException.getMessage());
    }

    @Test
    void givenAFlight_whenCallOnHold_thenShouldPutTheFlightOnHold() {
        // given
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 13),
                LocalTime.of(18, 10),
                LocalTime.of(19, 0),
                "B737",
                "GRU",
                "CGR"
        );
        final var expectedOnHoldUntil = LocalDateTime.of(2024, 1, 14, 20, 10);

        // when
        aFlight.board();
        aFlight.onHold(expectedOnHoldUntil);

        // then
        Assertions.assertEquals(FlightStatus.ON_HOLD, aFlight.getStatus());
        // calculate new arrival time and departure time
    }

    @Test
    void givenAFlight_whenCallOnHoldWithNullValue_thenShouldThrowADomainException() {
        // given
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 13),
                LocalTime.of(18, 10),
                LocalTime.of(19, 0),
                "B737",
                "GRU",
                "CGR"
        );
        final LocalDateTime expectedOnHoldUntil = null;

        // when
        final var illegalArgumentException = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> aFlight.onHold(expectedOnHoldUntil)
        );

        // then
        Assertions.assertEquals("On hold until is required", illegalArgumentException.getMessage());
    }

    @Test
    void givenAFlight_whenCallInAir_thenShouldPutTheFlightInAir() {
        // given
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 13),
                LocalTime.of(18, 10),
                LocalTime.of(19, 0),
                "B737",
                "GRU",
                "CGR"
        );

        // when
        aFlight.board();
        aFlight.depart(LocalTime.of(17, 32));
        aFlight.inAir();

        // then
        Assertions.assertEquals(FlightStatus.IN_AIR, aFlight.getStatus());
    }

    @Test
    void givenAScheduledFlight_whenChangeAircraft_thenShouldChangeTheAircraft() {
        // given
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 13),
                LocalTime.of(18, 10),
                LocalTime.of(19, 0),
                "B737",
                "GRU",
                "CGR"
        );
        final var expectedAircraftID = "A320";

        // when
        aFlight.changeAircraft(expectedAircraftID);

        // then
        Assertions.assertEquals(expectedAircraftID, aFlight.getAircraftID());
    }

    @Test
    void givenADelayedFlight_whenChangeAircraft_thenShouldChangeTheAircraft() {
        // given
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 13),
                LocalTime.of(18, 10),
                LocalTime.of(19, 0),
                "B737",
                "GRU",
                "CGR"
        );
        final var expectedAircraftID = "A320";
        aFlight.delay(Duration.of(30, ChronoUnit.MINUTES));

        // when
        aFlight.changeAircraft(expectedAircraftID);

        // then
        Assertions.assertEquals(expectedAircraftID, aFlight.getAircraftID());
    }

    @Test
    void givenAOnHoldFlight_whenChangeAircraft_thenShouldChangeTheAircraft() {
        // given
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 13),
                LocalTime.of(18, 10),
                LocalTime.of(19, 0),
                "B737",
                "GRU",
                "CGR"
        );
        final var expectedAircraftID = "A320";

        aFlight.board();
        aFlight.onHold(LocalDateTime.of(2024, 1, 14, 20, 10));

        // when
        aFlight.changeAircraft(expectedAircraftID);

        // then
        Assertions.assertEquals(expectedAircraftID, aFlight.getAircraftID());
    }

    @Test
    void givenADepartedFlight_whenChangeAircraft_thenShouldThrowADomainException() {
        // given
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2023, 1, 13),
                LocalTime.of(18, 10),
                LocalTime.of(19, 0),
                "B737",
                "GRU",
                "CGR"
        );
        final var expectedAircraftID = "A320";

        aFlight.board();
        aFlight.depart(LocalTime.of(17, 32));

        // when
        final var illegalStateException = Assertions.assertThrows(
                IllegalStateException.class,
                () -> aFlight.changeAircraft(expectedAircraftID)
        );

        // then
        Assertions.assertEquals("Flight can only change aircraft if it is scheduled, delayed or on hold", illegalStateException.getMessage());
    }


}
