package dev.grigolli.example.dddhexarchi.domain.validation.handler;


import dev.grigolli.example.dddhexarchi.domain.exceptions.DomainException;
import dev.grigolli.example.dddhexarchi.domain.validation.DomainError;
import dev.grigolli.example.dddhexarchi.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(DomainError anError) {
        throw DomainException.with(anError);
    }

    @Override
    public ValidationHandler append(String anErrorMessage) {
        throw DomainException.with(new DomainError(anErrorMessage));
    }

    @Override
    public ValidationHandler append(ValidationHandler anHandler) {
        throw DomainException.with(anHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(final Validation aValidation) {
        try {
            aValidation.validate();
        } catch (final Exception ex) {
            throw DomainException.with(new DomainError(ex.getMessage()));
        }

        return this;
    }

    @Override
    public List<DomainError> getErrors() {
        return List.of();
    }
}
