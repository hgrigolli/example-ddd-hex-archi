package dev.grigolli.example.dddhexarchi.application.flight.status;

import dev.grigolli.example.dddhexarchi.application.UseCaseTest;
import dev.grigolli.example.dddhexarchi.domain.flight.FlightGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

class InAirFlightUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultInAirFlightUseCase useCase;

    @Mock
    private FlightGateway flightGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(flightGateway);
    }

}