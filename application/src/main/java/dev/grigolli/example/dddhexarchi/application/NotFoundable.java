package dev.grigolli.example.dddhexarchi.application;

import dev.grigolli.example.dddhexarchi.domain.Identifier;
import dev.grigolli.example.dddhexarchi.domain.exceptions.NotFoundException;

import java.util.function.Supplier;

public interface NotFoundable {

    default Supplier<NotFoundException> notFound(final Identifier anIdentifier) {
        return () -> NotFoundException.with(anIdentifier.getClass(), anIdentifier);
    }
}
