package dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence;

import dev.grigolli.example.dddhexarchi.RepositoryTest;
import dev.grigolli.example.dddhexarchi.domain.flight.Flight;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.time.LocalTime;

@RepositoryTest
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
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);

        anEntity.setFlightNumber(null);

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
        // when
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);

        anEntity.setFlightNumber("");

        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.saveAndFlush(anEntity));

        Assertions.assertInstanceOf(ConstraintViolationException.class, actualException.getCause());

    }

    @Test
    void givenAInvalidFlightWithDepartureDateNull_whenSave_thenShouldThrowException() {

        final var expectedProperty = "departureDate";
        final var expectedMessage = "not-null property references a null or transient value : dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence.FlightJpaEntity.departureDate";

        // when
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);

        anEntity.setDepartureDate(null);

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
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);

        anEntity.setScheduledDepartureTime(null);

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
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);

        anEntity.setScheduledArrivalTime(null);

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
                "B737",
                "GRU",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);

        anEntity.setAircraftID(null);

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

        final var anEntity = FlightJpaEntity.from(aFlight);

        anEntity.setAircraftID("");

        //when
        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.saveAndFlush(anEntity));

        //then
        Assertions.assertInstanceOf(ConstraintViolationException.class, actualException.getCause());
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
                "GRU",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);

        anEntity.setDepartureAirport(null);

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
                "GRU",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);

        anEntity.setDepartureAirport("");

        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.saveAndFlush(anEntity));

        Assertions.assertInstanceOf(ConstraintViolationException.class, actualException.getCause());
    }

    @Test
    void givenAInvalidFlightWithDepartureAirportMoreThan3Char_whenSave_thenShouldThrowException() {

        //given
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);

        // when
        anEntity.setDepartureAirport("CGRA");

        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.saveAndFlush(anEntity));

        Assertions.assertInstanceOf(DataException.class, actualException.getCause());
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
                "GRU",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);

        anEntity.setDepartureAirport("CG");

        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.saveAndFlush(anEntity));

        Assertions.assertInstanceOf(ConstraintViolationException.class, actualException.getCause());
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
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);

        anEntity.setArrivalAirport(null);

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
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);

        anEntity.setArrivalAirport("");

        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.saveAndFlush(anEntity));

        Assertions.assertInstanceOf(ConstraintViolationException.class, actualException.getCause());
    }

    @Test
    void givenAInvalidFlightWithArrivalAirportMoreThan3Char_whenSave_thenShouldThrowException() {
        // when
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);

        anEntity.setArrivalAirport("CGRA");

        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.saveAndFlush(anEntity));

        Assertions.assertInstanceOf(DataException.class, actualException.getCause());
    }

    @Test
    void givenAInvalidFlightWithArrivalAirportLessThan3Char_whenSave_thenShouldThrowException() {

        // when
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );

        final var anEntity = FlightJpaEntity.from(aFlight);

        anEntity.setArrivalAirport("CG");

        final var actualException =
                Assertions.assertThrows(DataIntegrityViolationException.class,
                        () -> flightRepository.saveAndFlush(anEntity));

        Assertions.assertInstanceOf(ConstraintViolationException.class, actualException.getCause());

    }


}
