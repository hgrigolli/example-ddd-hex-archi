package dev.grigolli.example.dddhexarchi.infrastructure.flight.persistence;

import dev.grigolli.example.dddhexarchi.PostgreSQLGatewayTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@PostgreSQLGatewayTest
class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    void givenAValidFlight_whenSave_thenShouldPersist() {
        
    }

}
