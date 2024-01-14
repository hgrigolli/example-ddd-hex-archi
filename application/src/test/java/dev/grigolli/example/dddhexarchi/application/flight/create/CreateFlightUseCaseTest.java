package dev.grigolli.example.dddhexarchi.application.flight.create;

import dev.grigolli.example.dddhexarchi.application.UseCaseTest;
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

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class CreateFlightUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateFlightUseCase useCase;

    @Mock
    private FlightGateway flightGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(flightGateway);
    }

    @Test
    void givenAValidCommand_whenCallExecute_thenShouldCreateFlight() {
        final var expectedFlightNumber = "JJ1234";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        // when
        final var aCommand = CreateFlightCommand.with(
                expectedFlightNumber,
                expectedDepartureDate,
                expectedScheduledDepartureTime,
                expectedScheduledArrivalTime,
                expectedAircraftID,
                expectedDepartureAirport,
                expectedArrivalAirport);

        when(flightGateway.create(any())).thenAnswer(returnsFirstArg());

        final var output = useCase.execute(aCommand);

        // then
        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        Mockito.verify(flightGateway,
                times(1))
                .create(Mockito.argThat(aFlight -> aFlight.getFlightNumber().equals(expectedFlightNumber)
                        && aFlight.getDepartureDate().equals(expectedDepartureDate)
                        && aFlight.getScheduledDepartureTime().equals(expectedScheduledDepartureTime)
                        && aFlight.getScheduledArrivalTime().equals(expectedScheduledArrivalTime)
                        && aFlight.getAircraftID().equals(expectedAircraftID)
                        && aFlight.getDepartureAirport().equals(expectedDepartureAirport)
                        && aFlight.getArrivalAirport().equals(expectedArrivalAirport)
                ));

    }

    @Test
    void givenAnInvalidCommandWithNullFlightNumber_whenCallExecute_thenShouldThrowException() {
        final String expectedFlightNumber = null;
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        final var expectedErrorMessage = "Flight number is required";
        final var expectedErrorCount = 1;

        // when
        final var aCommand = CreateFlightCommand.with(
                expectedFlightNumber,
                expectedDepartureDate,
                expectedScheduledDepartureTime,
                expectedScheduledArrivalTime,
                expectedAircraftID,
                expectedDepartureAirport,
                expectedArrivalAirport);

        // then
        final var actualException = Assertions.assertThrows(NotificationException.class, () -> useCase.execute(aCommand));

        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());

        Mockito.verify(flightGateway, times(0)).create(any());
    }

    @Test
    void givenAnInvalidCommandWithEmptyFlightNumber_whenCallExecute_thenShouldThrowException() {
        final var expectedFlightNumber = "";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        final var expectedErrorMessage = "Flight number is required";
        final var expectedErrorCount = 1;
        // when
        final var aCommand = CreateFlightCommand.with(
                expectedFlightNumber,
                expectedDepartureDate,
                expectedScheduledDepartureTime,
                expectedScheduledArrivalTime,
                expectedAircraftID,
                expectedDepartureAirport,
                expectedArrivalAirport);

        // then
        final var actualException = Assertions.assertThrows(NotificationException.class, () -> useCase.execute(aCommand));

        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());

        Mockito.verify(flightGateway, times(0)).create(any());
    }

    @Test
    void givenAnInvalidCommandWithNullDepartureDate_whenCallExecute_thenShouldThrowException() {
        final var expectedFlightNumber = "";
        final LocalDate expectedDepartureDate = null;
        final var expectedScheduledDepartureTime = LocalTime.of(17, 20);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        final var expectedErrorMessage = "Departure date is required";
        final var expectedErrorCount = 1;
        // when
        final var aCommand = CreateFlightCommand.with(
                expectedFlightNumber,
                expectedDepartureDate,
                expectedScheduledDepartureTime,
                expectedScheduledArrivalTime,
                expectedAircraftID,
                expectedDepartureAirport,
                expectedArrivalAirport);

        // then
        final var actualException = Assertions.assertThrows(NotificationException.class, () -> useCase.execute(aCommand));

        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());

        Mockito.verify(flightGateway, times(0)).create(any());
    }

    @Test
    void givenAnInvalidCommandWithNullScheduledDepartureTime_whenCallExecute_thenShouldThrowException() {
        final var expectedFlightNumber = "";
        final var expectedDepartureDate = LocalDate.of(2024, 1, 12);
        final LocalTime expectedScheduledDepartureTime = null;
        final var expectedScheduledArrivalTime = LocalTime.of(18, 10);
        final var expectedAircraftID = "B737";
        final var expectedDepartureAirport = "GRU";
        final var expectedArrivalAirport = "CGR";

        final var expectedErrorMessage = "Departure date is required";
        final var expectedErrorCount = 1;
        // when
        final var aCommand = CreateFlightCommand.with(
                expectedFlightNumber,
                expectedDepartureDate,
                expectedScheduledDepartureTime,
                expectedScheduledArrivalTime,
                expectedAircraftID,
                expectedDepartureAirport,
                expectedArrivalAirport);

        // then
        final var actualException = Assertions.assertThrows(NotificationException.class, () -> useCase.execute(aCommand));

        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());

        Mockito.verify(flightGateway, times(0)).create(any());
    }

}