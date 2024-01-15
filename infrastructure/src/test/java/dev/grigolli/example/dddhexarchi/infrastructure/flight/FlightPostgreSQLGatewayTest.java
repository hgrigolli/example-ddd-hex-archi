package dev.grigolli.example.dddhexarchi.infrastructure.flight;

import dev.grigolli.example.dddhexarchi.PostgreSQLGatewayTest;
import dev.grigolli.example.dddhexarchi.domain.flight.Flight;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightID;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightStatus;
import dev.grigolli.example.dddhexarchi.domain.pagination.SearchQuery;
import dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence.FlightJpaEntity;
import dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence.FlightRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.util.List.of;

@PostgreSQLGatewayTest
class FlightPostgreSQLGatewayTest {

    @Autowired
    private FlightPostgreSQLGateway flightPostgreSQLGateway;

    @Autowired
    private FlightRepository flightRepository;

    @Test
    void testInjectedDependencies() {
        Assertions.assertNotNull(flightPostgreSQLGateway);
        Assertions.assertNotNull(flightRepository);
    }

    @Test
    void givenAValidFlight_whenCallCreate_thenFlightIsCreated() {

        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        final var aFlight = Flight.newFlight(
                expectedFlightNumber,
                expectedDepartureDate,
                expectedScheduledDepartureTime,
                expectedScheduledArrivalTime,
                expectedAircraftID,
                expectedDepartureAirport,
                expectedArrivalAirport
        );

        Assertions.assertEquals(0, flightRepository.count());

        final var actualFlight = flightPostgreSQLGateway.create(aFlight);

        Assertions.assertEquals(1, flightRepository.count());

        Assertions.assertEquals(expectedFlightNumber, actualFlight.getFlightNumber());
        Assertions.assertEquals(expectedDepartureDate, actualFlight.getDepartureDate());
        Assertions.assertEquals(expectedScheduledDepartureTime, actualFlight.getScheduledDepartureTime());
        Assertions.assertEquals(expectedScheduledArrivalTime, actualFlight.getScheduledArrivalTime());
        Assertions.assertEquals(expectedAircraftID, actualFlight.getAircraftID());
        Assertions.assertEquals(expectedDepartureAirport, actualFlight.getDepartureAirport());
        Assertions.assertEquals(expectedArrivalAirport, actualFlight.getArrivalAirport());

        final var flightEntity = Assertions.assertDoesNotThrow(() ->
                flightRepository.findById(actualFlight.getId().getValue()).orElseThrow());

        Assertions.assertEquals(aFlight.getId().getValue(), flightEntity.getId());
        Assertions.assertEquals(expectedFlightNumber, flightEntity.getFlightNumber());
        Assertions.assertEquals(expectedDepartureDate, flightEntity.getDepartureDate());
        Assertions.assertEquals(expectedScheduledDepartureTime, flightEntity.getScheduledDepartureTime());
        Assertions.assertEquals(expectedScheduledArrivalTime, flightEntity.getScheduledArrivalTime());
        Assertions.assertEquals(expectedAircraftID, flightEntity.getAircraftID());
        Assertions.assertEquals(expectedDepartureAirport, flightEntity.getDepartureAirport());
        Assertions.assertEquals(expectedArrivalAirport, flightEntity.getArrivalAirport());

    }

    @Test
    void givenAValidFlight_whenCallUpdate_thenFlightShouldBeUpdated() {

        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        final var aFlight = Flight.newFlight(
                expectedFlightNumber,
                expectedDepartureDate,
                expectedScheduledDepartureTime,
                expectedScheduledArrivalTime,
                expectedAircraftID,
                expectedDepartureAirport,
                expectedArrivalAirport
        );

        Assertions.assertEquals(0, flightRepository.count());
        flightRepository.saveAndFlush(FlightJpaEntity.from(aFlight));
        Assertions.assertEquals(1, flightRepository.count());

        final var updatedFlight = aFlight.clone();

        updatedFlight.board();

        final var actualFlight = flightPostgreSQLGateway.update(updatedFlight);

        Assertions.assertEquals(expectedFlightNumber, actualFlight.getFlightNumber());
        Assertions.assertEquals(expectedDepartureDate, actualFlight.getDepartureDate());
        Assertions.assertEquals(expectedScheduledDepartureTime, actualFlight.getScheduledDepartureTime());
        Assertions.assertEquals(expectedScheduledArrivalTime, actualFlight.getScheduledArrivalTime());
        Assertions.assertEquals(expectedAircraftID, actualFlight.getAircraftID());
        Assertions.assertEquals(expectedDepartureAirport, actualFlight.getDepartureAirport());
        Assertions.assertEquals(expectedArrivalAirport, actualFlight.getArrivalAirport());
        Assertions.assertEquals(FlightStatus.BOARDING, actualFlight.getStatus());


        final var flightEntity = Assertions.assertDoesNotThrow(() ->
                flightRepository.findById(actualFlight.getId().getValue()).orElseThrow());

        Assertions.assertEquals(aFlight.getId().getValue(), flightEntity.getId());
        Assertions.assertEquals(expectedFlightNumber, flightEntity.getFlightNumber());
        Assertions.assertEquals(expectedDepartureDate, flightEntity.getDepartureDate());
        Assertions.assertEquals(expectedScheduledDepartureTime, flightEntity.getScheduledDepartureTime());
        Assertions.assertEquals(expectedScheduledArrivalTime, flightEntity.getScheduledArrivalTime());
        Assertions.assertEquals(expectedAircraftID, flightEntity.getAircraftID());
        Assertions.assertEquals(expectedDepartureAirport, flightEntity.getDepartureAirport());
        Assertions.assertEquals(expectedArrivalAirport, flightEntity.getArrivalAirport());
        Assertions.assertEquals(FlightStatus.BOARDING, flightEntity.getStatus());


    }

    @Test
    void givenAPrePersistedFlightAndValidFlightID_whenCallDelete_thenShouldDeleteFlight() {

        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        final var aFlight = Flight.newFlight(
                expectedFlightNumber,
                expectedDepartureDate,
                expectedScheduledDepartureTime,
                expectedScheduledArrivalTime,
                expectedAircraftID,
                expectedDepartureAirport,
                expectedArrivalAirport
        );

        Assertions.assertEquals(0, flightRepository.count());
        flightRepository.saveAndFlush(FlightJpaEntity.from(aFlight));
        Assertions.assertEquals(1, flightRepository.count());

        final var flightID = aFlight.getId();

        flightPostgreSQLGateway.deleteById(flightID);

        Assertions.assertEquals(0, flightRepository.count());
    }

    @Test
    void givenAPrePersistedFlightAndInvalidFlightID_whenCallDelete_thenShouldNotDeleteFlight() {

        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        final var aFlight = Flight.newFlight(
                expectedFlightNumber,
                expectedDepartureDate,
                expectedScheduledDepartureTime,
                expectedScheduledArrivalTime,
                expectedAircraftID,
                expectedDepartureAirport,
                expectedArrivalAirport
        );

        Assertions.assertEquals(0, flightRepository.count());
        flightRepository.saveAndFlush(FlightJpaEntity.from(aFlight));
        Assertions.assertEquals(1, flightRepository.count());

        final var flightID = FlightID.from("invalid");

        flightPostgreSQLGateway.deleteById(flightID);

        Assertions.assertEquals(1, flightRepository.count());
    }

    @Test
    void givenAValidFlight_whenCallFindByID_thenShouldReturnFlight() {

        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        final var aFlight = Flight.newFlight(
                expectedFlightNumber,
                expectedDepartureDate,
                expectedScheduledDepartureTime,
                expectedScheduledArrivalTime,
                expectedAircraftID,
                expectedDepartureAirport,
                expectedArrivalAirport
        );

        Assertions.assertEquals(0, flightRepository.count());
        flightRepository.saveAndFlush(FlightJpaEntity.from(aFlight));
        Assertions.assertEquals(1, flightRepository.count());

        final var optionalFlight = flightPostgreSQLGateway.findById(aFlight.getId());

        Assertions.assertTrue(optionalFlight.isPresent());

        final var actualFlight = optionalFlight.get();

        Assertions.assertEquals(aFlight.getId(), actualFlight.getId());
        Assertions.assertEquals(expectedFlightNumber, actualFlight.getFlightNumber());
        Assertions.assertEquals(expectedDepartureDate, actualFlight.getDepartureDate());
        Assertions.assertEquals(expectedScheduledDepartureTime, actualFlight.getScheduledDepartureTime());
        Assertions.assertEquals(expectedScheduledArrivalTime, actualFlight.getScheduledArrivalTime());
        Assertions.assertEquals(expectedAircraftID, actualFlight.getAircraftID());
        Assertions.assertEquals(expectedDepartureAirport, actualFlight.getDepartureAirport());
        Assertions.assertEquals(expectedArrivalAirport, actualFlight.getArrivalAirport());

    }

    @Test
    void givenAValidFlight_whenCallExistsById_thenShouldReturnTrue() {

        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        final var aFlight = Flight.newFlight(
                expectedFlightNumber,
                expectedDepartureDate,
                expectedScheduledDepartureTime,
                expectedScheduledArrivalTime,
                expectedAircraftID,
                expectedDepartureAirport,
                expectedArrivalAirport
        );

        Assertions.assertEquals(0, flightRepository.count());
        flightRepository.saveAndFlush(FlightJpaEntity.from(aFlight));
        Assertions.assertEquals(1, flightRepository.count());

        final var exists = flightPostgreSQLGateway.existsByIds(List.of(aFlight.getId()));

        Assertions.assertNotNull(exists);

    }

    @Test
    void givenAValidFlightIdNotStored_whenCallsFindByID_thenShouldReturnEmpty() {

        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        final var aFlight = Flight.newFlight(
                expectedFlightNumber,
                expectedDepartureDate,
                expectedScheduledDepartureTime,
                expectedScheduledArrivalTime,
                expectedAircraftID,
                expectedDepartureAirport,
                expectedArrivalAirport
        );

        Assertions.assertEquals(0, flightRepository.count());
        flightRepository.saveAndFlush(FlightJpaEntity.from(aFlight));
        Assertions.assertEquals(1, flightRepository.count());

        final var optionalFlight = flightPostgreSQLGateway.findById(FlightID.from("invalid"));

        Assertions.assertTrue(optionalFlight.isEmpty());
    }

    @Test
    void givenPrePersistedFlights_whenCallsFindAll_thenShouldReturnPaginated() {
        final var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 2;

        final var flightLA8138 = Flight.newFlight(
                "LA8138",
                LocalDate.of(2024, 1, 14),
                LocalTime.of(18, 20),
                LocalTime.of(21, 10),
                "A320",
                "GRU",
                "AEP"
        );

        final var flightG31534 = Flight.newFlight(
                "G31534",
                LocalDate.of(2024, 1, 14),
                LocalTime.of(17, 25),
                LocalTime.of(18, 55),
                "B737",
                "GRU",
                "VIX"
        );

        Assertions.assertEquals(0, flightRepository.count());
        flightRepository.saveAllAndFlush(
                List.of(
                    FlightJpaEntity.from(flightLA8138),
                    FlightJpaEntity.from(flightG31534)
                )
        );

        Assertions.assertEquals(2, flightRepository.count());

        final var query = new SearchQuery(0, 1, "", "flightNumber", "asc");

        final var actualPagination = flightPostgreSQLGateway.findAll(query);

        Assertions.assertEquals(expectedPage, actualPagination.currentPage());
        Assertions.assertEquals(expectedPerPage, actualPagination.perPage());
        Assertions.assertEquals(expectedTotal, actualPagination.total());
        Assertions.assertEquals(1, actualPagination.items().size());
        Assertions.assertEquals(flightG31534.getId(), actualPagination.items().get(0).getId());

    }

    @Test
    void givenAnEmptyFlightTable_whenCallsFindAll_thenShouldReturnEmptyPagination() {
        final var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 0;

        Assertions.assertEquals(0, flightRepository.count());

        final var query = new SearchQuery(0, 1, "", "flightNumber", "asc");

        final var actualPagination = flightPostgreSQLGateway.findAll(query);

        Assertions.assertEquals(expectedPage, actualPagination.currentPage());
        Assertions.assertEquals(expectedPerPage, actualPagination.perPage());
        Assertions.assertEquals(expectedTotal, actualPagination.total());
        Assertions.assertEquals(0, actualPagination.items().size());

    }

    @Test
    void givenPrePersistedFlights_whenCallsFindAllWithArrivalAirportFilter_thenShouldReturnPaginatedFiltered() {
        final var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 1;

        final var flightLA8138 = Flight.newFlight(
                "LA8138",
                LocalDate.of(2024, 1, 14),
                LocalTime.of(18, 20),
                LocalTime.of(21, 10),
                "A320",
                "GRU",
                "AEP"
        );

        final var flightG31534 = Flight.newFlight(
                "G31534",
                LocalDate.of(2024, 1, 14),
                LocalTime.of(17, 25),
                LocalTime.of(18, 55),
                "B737",
                "GRU",
                "VIX"
        );

        Assertions.assertEquals(0, flightRepository.count());
        flightRepository.saveAllAndFlush(
                List.of(
                        FlightJpaEntity.from(flightLA8138),
                        FlightJpaEntity.from(flightG31534)
                )
        );

        Assertions.assertEquals(2, flightRepository.count());

        final var query = new SearchQuery(0, 1, "VIX", "arrivalAirport", "asc");

        final var actualPagination = flightPostgreSQLGateway.findAll(query);

        Assertions.assertEquals(expectedPage, actualPagination.currentPage());
        Assertions.assertEquals(expectedPerPage, actualPagination.perPage());
        Assertions.assertEquals(expectedTotal, actualPagination.total());
        Assertions.assertEquals(1, actualPagination.items().size());
        Assertions.assertEquals(flightG31534.getId(), actualPagination.items().get(0).getId());
    }

}
