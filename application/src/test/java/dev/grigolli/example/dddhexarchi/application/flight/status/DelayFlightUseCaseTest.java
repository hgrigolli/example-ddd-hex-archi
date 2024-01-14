package dev.grigolli.example.dddhexarchi.application.flight.status;

import dev.grigolli.example.dddhexarchi.application.UseCaseTest;
import dev.grigolli.example.dddhexarchi.domain.flight.Flight;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.mockito.Mockito.when;

class DelayFlightUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultDelayFlightUseCase useCase;

    @Mock
    private FlightGateway flightGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(flightGateway);
    }

    @Test
    void givenAValidCommand_whenCallExecute_thenFlightShouldBeDelayed() {
        // given
        final var flight = Flight.newFlight(
                "LA8138",
                LocalDate.of(2024, 1, 14),
                LocalTime.of(18, 20),
                LocalTime.of(21, 10),
                "A320",
                "GRU",
                "AEP"
        );

        final var expectedFlightID = flight.getId();
        final var expectedDelay = Duration.of(90, ChronoUnit.MINUTES);

        when(flightGateway.findById(expectedFlightID)).thenReturn(java.util.Optional.of(flight));

        final var command = new DelayFlightCommand(expectedFlightID.getValue(), expectedDelay);

        // when
        Assertions.assertDoesNotThrow(() -> useCase.execute(command));
        Mockito.verify(flightGateway, Mockito.times(1)).update(
                Mockito.argThat(aFlight -> aFlight.getDelay().equals(expectedDelay))
        );
    }

}