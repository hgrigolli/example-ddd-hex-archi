package dev.grigolli.example.dddhexarchi.application.flight.delete;

import dev.grigolli.example.dddhexarchi.application.UseCaseTest;
import dev.grigolli.example.dddhexarchi.domain.flight.Flight;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class DeleteFlightUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultDeleteFlightUseCase useCase;

    @Mock
    private FlightGateway flightGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(flightGateway);
    }

    @Test
    void givenValidCommand_whenExecute_thenShouldDeleteFlight() {
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );

        final var expectedID = aFlight.getId();

        doNothing().when(flightGateway).deleteById(expectedID);

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedID.getValue()));

        Mockito.verify(flightGateway, times(1)).deleteById(expectedID);
    }

    //Estar OK para esconder essa informação de proposito
    @Test
    void givenCommandWithInvalidId_whenExecute_thenShouldBeOK() {
        final var expectedID = FlightID.from("1234567890");

        doNothing().when(flightGateway).deleteById(expectedID);
        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedID.getValue()));

        Mockito.verify(flightGateway, times(1)).deleteById(expectedID);

    }

    @Test
    void givenValidCommand_whenGatewayThrowsError_thenShouldThrowException() {
        final var aFlight = Flight.newFlight(
                "JJ1234",
                LocalDate.of(2024, 1, 12),
                LocalTime.of(17, 20),
                LocalTime.of(18, 10),
                "B737",
                "GRU",
                "CGR"
        );

        final var expectedID = aFlight.getId();

        doThrow(new IllegalStateException("Gateway error"))
                .when(flightGateway).deleteById(expectedID);

        Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(expectedID.getValue()));

        Mockito.verify(flightGateway, times(1)).deleteById(expectedID);
    }

}