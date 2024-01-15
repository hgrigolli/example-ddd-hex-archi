package dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence;

import dev.grigolli.example.dddhexarchi.PostgreSQLGatewayTest;
import dev.grigolli.example.dddhexarchi.domain.flight.Flight;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.PropertyValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.time.LocalTime;

@PostgreSQLGatewayTest
class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    void givenAValidFlight_whenSave_thenShouldPersist() {

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

        final var anEntity = FlightJpaEntity.from(aFlight);
        final var actualEntity = flightRepository.save(anEntity);

        // then
        Assertions.assertEquals(expectedFlightNumber, actualEntity.getFlightNumber());
        Assertions.assertEquals(expectedDepartureDate, actualEntity.getDepartureDate());
        Assertions.assertEquals(expectedScheduledDepartureTime, actualEntity.getScheduledDepartureTime());
        Assertions.assertEquals(expectedScheduledArrivalTime, actualEntity.getScheduledArrivalTime());
        Assertions.assertEquals(expectedAircraftID, actualEntity.getAircraftID());
        Assertions.assertEquals(expectedDepartureAirport, actualEntity.getDepartureAirport());
        Assertions.assertEquals(expectedArrivalAirport, actualEntity.getArrivalAirport());

    }

    @Test
    void givenAInvalidFlightWithFlightNumberNull_whenSave_thenShouldThrowException() {

        final var expectedProperty = "flightNumber";
        final var expectedMessage = "not-null property references a null or transient value : dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence.FlightJpaEntity.flightNumber";

        // when
        final var aFlight = Flight.newFlight(
                null,
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);
        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.save(anEntity));

        final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        // then
        Assertions.assertEquals(expectedProperty, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }

    @Test
    void givenAInvalidFlightWithFlightNumberEmpty_whenSave_thenShouldThrowException() {

        final var expectedProperty = "flightNumber";
        final var expectedMessage = "not-null property references a null or transient value : dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence.FlightJpaEntity.flightNumber";

        // when
        final var aFlight = Flight.newFlight(
                "",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);
        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.save(anEntity));

        final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        // then
        Assertions.assertEquals(expectedProperty, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }

    @Test
    void givenAInvalidFlightWithDepartureDateNull_whenSave_thenShouldThrowException() {

        final var expectedProperty = "departureDate";
        final var expectedMessage = "not-null property references a null or transient value : dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence.FlightJpaEntity.departureDate";

        // when
        final var aFlight = Flight.newFlight(
                "JJ1234",
                null,
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);
        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.save(anEntity));

        final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        // then
        Assertions.assertEquals(expectedProperty, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }

    @Test
    void givenAInvalidFlightWithScheduledDepartureTimeNull_whenSave_thenShouldThrowException() {

        final var expectedProperty = "scheduledDepartureTime";
        final var expectedMessage = "not-null property references a null or transient value : dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence.FlightJpaEntity.scheduledDepartureTime";

        // when
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                null,
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);
        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.save(anEntity));

        final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        // then
        Assertions.assertEquals(expectedProperty, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }

    @Test
    void givenAInvalidFlightWithScheduledArrivalTimeNull_whenSave_thenShouldThrowException() {

        final var expectedProperty = "scheduledArrivalTime";
        final var expectedMessage = "not-null property references a null or transient value : dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence.FlightJpaEntity.scheduledArrivalTime";

        // when
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                null,
                "B737",
                "GRU",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);
        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.save(anEntity));

        final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        // then
        Assertions.assertEquals(expectedProperty, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }

    @Test
    void givenAInvalidFlightWithAircraftIDNull_whenSave_thenShouldThrowException() {

        final var expectedProperty = "aircraftID";
        final var expectedMessage = "not-null property references a null or transient value : dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence.FlightJpaEntity.aircraftID";

        // when
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                null,
                "GRU",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);
        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.save(anEntity));

        final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        // then
        Assertions.assertEquals(expectedProperty, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }

    @Test
    void givenAInvalidFlightWithScheduledAircraftIDEmpty_whenSave_thenShouldThrowException() {

        final var expectedProperty = "aircraftID";
        final var expectedMessage = "not-null property references a null or transient value : dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence.FlightJpaEntity.aircraftID";

        // when
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "",
                "GRU",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);
        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.save(anEntity));

        final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        // then
        Assertions.assertEquals(expectedProperty, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }

    @Test
    void givenAInvalidFlightWithDepartureAirportNull_whenSave_thenShouldThrowException() {

        final var expectedProperty = "departureAirport";
        final var expectedMessage = "not-null property references a null or transient value : dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence.FlightJpaEntity.departureAirport";

        // when
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                null,
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);
        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.save(anEntity));

        final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        // then
        Assertions.assertEquals(expectedProperty, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }

    @Test
    void givenAInvalidFlightWithDepartureAirportEmpty_whenSave_thenShouldThrowException() {

        final var expectedProperty = "departureAirport";
        final var expectedMessage = "not-null property references a null or transient value : dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence.FlightJpaEntity.departureAirport";

        // when
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);
        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.save(anEntity));

        final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());
        // then
        Assertions.assertEquals(expectedProperty, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }

    @Test
    void givenAInvalidFlightWithDepartureAirportMoreThan3Char_whenSave_thenShouldThrowException() {

        final var expectedProperty = "departureAirport";
        final var expectedMessage = "size must be between 3 and 3";

        // when
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRUA",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);
        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.save(anEntity));

        final var actualCause = Assertions.assertInstanceOf(ConstraintViolationException.class, actualException.getCause());
        // then
        Assertions.assertEquals(expectedProperty, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }

    @Test
    void givenAInvalidFlightWithDepartureAirportLessThan3Char_whenSave_thenShouldThrowException() {

        final var expectedProperty = "departureAirport";
        final var expectedMessage = "size must be between 3 and 3";

        // when
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GR",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);
        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.save(anEntity));

        final var actualCause = Assertions.assertInstanceOf(ConstraintViolationException.class, actualException.getCause());
        // then
        Assertions.assertEquals(expectedProperty, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }

    @Test
    void givenAInvalidFlightWithArrivalAirportNull_whenSave_thenShouldThrowException() {

        final var expectedProperty = "arrivalAirport";
        final var expectedMessage = "not-null property references a null or transient value : dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence.FlightJpaEntity.arrivalAirport";

        // when
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                null
        );

        final var anEntity = FlightJpaEntity.from(aFlight);
        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.save(anEntity));

        final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        // then
        Assertions.assertEquals(expectedProperty, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }

    @Test
    void givenAInvalidFlightWithArrivalAirportEmpty_whenSave_thenShouldThrowException() {

        final var expectedProperty = "arrivalAirport";
        final var expectedMessage = "not-null property references a null or transient value : dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence.FlightJpaEntity.arrivalAirport";

        // when
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                ""
        );

        final var anEntity = FlightJpaEntity.from(aFlight);
        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.save(anEntity));

        final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        // then
        Assertions.assertEquals(expectedProperty, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }


    @Test
    void givenAInvalidFlightWithArrivalAirportMoreThan3Char_whenSave_thenShouldThrowException() {

        final var expectedProperty = "arrivalAirport";
        final var expectedMessage = "size must be between 3 and 3";

        // when
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGRA"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);
        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.save(anEntity));

        final var actualCause = Assertions.assertInstanceOf(ConstraintViolationException.class, actualException.getCause());
        // then
        Assertions.assertEquals(expectedProperty, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }

    @Test
    void givenAInvalidFlightWithArrivalAirportLessThan3Char_whenSave_thenShouldThrowException() {

        final var expectedProperty = "arrivalAirport";
        final var expectedMessage = "size must be between 3 and 3";

        // when
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CG"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);
        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.save(anEntity));

        final var actualCause = Assertions.assertInstanceOf(ConstraintViolationException.class, actualException.getCause());
        // then
        Assertions.assertEquals(expectedProperty, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }


}
