package dev.grigolli.example.dddhexarchi.application.flight.reschedule;

import dev.grigolli.example.dddhexarchi.application.UseCaseTest;
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
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RescheduleFlightUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultRescheduleFlightUseCase useCase;

    @Mock
    private FlightGateway flightGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(flightGateway);
    }

    @Test
    void givenAValidCommand_whenCallExecute_thenShouldRescheduleFlight() {
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

        when(flightGateway.findById(expectedID))
                .thenReturn(Optional.of(aFlight));


        final var expectedDepartureDate = LocalDate.of(2024, 1, 13);
        final var expectedScheduledDepartureTime = LocalTime.of(17, 30);
        final var expectedScheduledArrivalTime = LocalTime.of(18, 20);

        final var aCommand = RescheduleFlightCommand.with(
                expectedID.getValue(),
                expectedDepartureDate,
                expectedScheduledDepartureTime,
                expectedScheduledArrivalTime
        );

        when(flightGateway.update(any())).thenAnswer(returnsFirstArg());

        final var output = useCase.execute(aCommand);

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());
        Assertions.assertEquals(expectedID.getValue(), output.id());
        Assertions.assertEquals(expectedDepartureDate, output.departureDate());
        Assertions.assertEquals(expectedScheduledDepartureTime, output.scheduledDepartureTime());
        Assertions.assertEquals(expectedScheduledArrivalTime, output.scheduledArrivalTime());


    }

}