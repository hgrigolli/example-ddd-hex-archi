package dev.grigolli.example.dddhexarchi.application.flight.retrieve.get;

import dev.grigolli.example.dddhexarchi.application.UseCaseTest;
import dev.grigolli.example.dddhexarchi.domain.exceptions.NotFoundException;
import dev.grigolli.example.dddhexarchi.domain.flight.Flight;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class GetFlightByIDUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultGetFlightByIDUseCase useCase;

    @Mock
    private FlightGateway flightGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(flightGateway);
    }

    @Test
    void givenAValidID_whenExecuteGetFlightByID_thenShouldReturnFlight() {
        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        final var aFlight = Flight.newFlight(expectedFlightNumber, expectedDepartureDate, expectedScheduledDepartureTime, expectedScheduledArrivalTime, expectedAircraftID, expectedDepartureAirport, expectedArrivalAirport);

        final var expectedID = aFlight.getId();

        when(flightGateway.findById(expectedID))
                .thenReturn(java.util.Optional.of(aFlight.clone()));

        final var actualFlight = useCase.execute(expectedID.getValue());

        Assertions.assertNotNull(actualFlight);
        Assertions.assertEquals(expectedID, actualFlight.id());
        Assertions.assertEquals(expectedFlightNumber, actualFlight.flightNumber());
        Assertions.assertEquals(expectedDepartureDate, actualFlight.departureDate());
        Assertions.assertEquals(expectedScheduledDepartureTime, actualFlight.scheduledDepartureTime());
        Assertions.assertEquals(expectedScheduledArrivalTime, actualFlight.scheduledArrivalTime());
        Assertions.assertEquals(expectedAircraftID, actualFlight.aircraftID());
        Assertions.assertEquals(expectedDepartureAirport, actualFlight.departureAirport());
        Assertions.assertEquals(expectedArrivalAirport, actualFlight.arrivalAirport());

    }

    @Test
    void givenAInvalidID_whenCallsGetClienteByID_shouldReturnNotFound() {
        final var expectedID = FlightID.from("1234");
        final var expectedErrorMessage = "Flight with ID %s not found".formatted(expectedID.getValue());

        when(flightGateway.findById(expectedID))
                .thenReturn(java.util.Optional.empty());

        final var actualException = Assertions.assertThrows(
                NotFoundException.class,
                () -> useCase.execute(expectedID.getValue())
        );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

    }

    @Test
    void givenAValidID_whenGatewayThrowsError_shouldReturnError() {
        final var expectedID = FlightID.from("1234");
        final var expectedErrorMessage = "Gateway Error";

        when(flightGateway.findById(expectedID))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(expectedID.getValue())
        );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

    }

}