package dev.grigolli.example.dddhexarchi.application.flight.retrieve.list;

import dev.grigolli.example.dddhexarchi.application.UseCaseTest;
import dev.grigolli.example.dddhexarchi.domain.flight.Flight;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;
import dev.grigolli.example.dddhexarchi.domain.pagination.Pagination;
import dev.grigolli.example.dddhexarchi.domain.pagination.SearchQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.util.List.of;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class ListFlightUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultListFlightUseCase useCase;

    @Mock
    private FlightGateway flightGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(flightGateway);
    }

    @Test
    void givenAValidQuery_whenCallListFlights_thenShouldReturnFlights() {
        final var flights = of(
                Flight.newFlight(
                        "LA8138",
                        LocalDate.of(2024, 1, 14),
                        LocalTime.of(18, 20),
                        LocalTime.of(21, 10),
                        "A320",
                        "GRU",
                        "AEP"
                ),
                Flight.newFlight(
                        "G31534",
                        LocalDate.of(2024, 1, 14),
                        LocalTime.of(17, 25),
                        LocalTime.of(18, 55),
                        "B737",
                        "GRU",
                        "VIX"
                )
        );

        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "scheduledDepartureTime";
        final var expectedDirection = "asc";

        final var aQuery =
                new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        final var expectedPagination =
                new Pagination<>(expectedPage, expectedPerPage, flights.size(), flights);

        final var expectedItemsCount = 2;
        final var expectedResult = expectedPagination.map(FlightListOutput::from);

        when(flightGateway.findAll(aQuery))
                .thenReturn(expectedPagination);

        final var actualResult = useCase.execute(aQuery);

        Assertions.assertEquals(expectedItemsCount, actualResult.items().size());
        Assertions.assertEquals(expectedResult, actualResult);
        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(flights.size(), actualResult.total());

    }

    @Test
    void givenAValidQuery_whenHasNoResults_thenShouldReturnEmptyList() {
        final var clientes = List.<Flight>of();
        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "scheduledDepartureTime";
        final var expectedDirection = "asc";

        final var aQuery =
                new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        final var expectedPagination =
                new Pagination<>(expectedPage, expectedPerPage, 0, clientes);

        final var expectedItemsCount = 0;
        final var expectedResult = expectedPagination.map(FlightListOutput::from);

        when(flightGateway.findAll(aQuery))
                .thenReturn(expectedPagination);

        final var actualResult = useCase.execute(aQuery);

        Assertions.assertEquals(expectedItemsCount, actualResult.items().size());
        Assertions.assertEquals(expectedResult, actualResult);
        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(0, actualResult.total());
    }

    @Test
    void givenAValidQuery_whenGatewayThrowsException_shouldThrowException() {
        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "scheduledDepartureTime";
        final var expectedDirection = "asc";

        final var aQuery =
                new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        when(flightGateway.findAll(aQuery))
                .thenThrow(new RuntimeException("Error"));

        Assertions.assertThrows(RuntimeException.class, () -> useCase.execute(aQuery));
    }

}