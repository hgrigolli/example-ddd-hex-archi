package dev.grigolli.example.dddhexarchi.domain.flight;

import dev.grigolli.example.dddhexarchi.domain.validation.ValidationHandler;
import dev.grigolli.example.dddhexarchi.domain.validation.Validator;

public class FlightValidator extends Validator {

    private final Flight flight;

    public FlightValidator(final Flight flight, final ValidationHandler aHandler) {
        super(aHandler);
        this.flight = flight;
    }

    @Override
    public void validate() {

    }
}