package dev.grigolli.example.dddhexarchi.application.flight.change;

import dev.grigolli.example.dddhexarchi.application.UseCaseTest;
import dev.grigolli.example.dddhexarchi.domain.exceptions.DomainException;
import dev.grigolli.example.dddhexarchi.domain.exceptions.NotificationException;
import dev.grigolli.example.dddhexarchi.domain.flight.Flight;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ChangeAircraftFlightUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultChangeAircraftFlightUseCase useCase;

    @Mock
    private FlightGateway flightGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(flightGateway);
    }

    @Test
    void givenAValidCommand_whenCallExecute_thenAircraftShouldBeChanged() {
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
        final var expectedAircraftID = "B737";

        final var aCommand = ChangeAircraftFlightCommand.with(expectedFlightID.getValue(), expectedAircraftID);

        when(flightGateway.findById(expectedFlightID)).thenReturn(java.util.Optional.of(flight));

        // when
        Assertions.assertDoesNotThrow(() -> useCase.execute(aCommand));
        Mockito.verify(flightGateway, Mockito.times(1)).update(
                Mockito.argThat(aFlight -> aFlight.getAircraftID().equals(expectedAircraftID))
        );
    }

    @Test
    void givenAnInvalidCommandWithNullAircraftID_whenCallExecute_thenShouldThrowException() {
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
        final String expectedAircraftID = null;
        final var expectedErrorMessage = "Aircraft ID is required";
        final var expectedErrorCount = 1;

        when(flightGateway.findById(expectedFlightID)).thenReturn(java.util.Optional.of(flight));

        final var aCommand = ChangeAircraftFlightCommand.with(expectedFlightID.getValue(), expectedAircraftID);

        // when
        final var actualExpection = Assertions.assertThrows(DomainException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualExpection.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualExpection.getErrors().size());

    }

}