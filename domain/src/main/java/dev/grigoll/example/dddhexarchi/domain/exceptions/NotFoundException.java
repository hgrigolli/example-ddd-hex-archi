package dev.grigoll.example.dddhexarchi.domain.exceptions;

import br.com.docenela.admin.domain.Identifier;

import java.util.List;


public class NotFoundException extends DomainException {

    protected NotFoundException(final String aMessage, final List<Error> anErrors) {
        super(aMessage, anErrors);
    }

    public static NotFoundException with(
            final Class<?> aClass,
            final Identifier id
    ) {
        final var anError = "%s with ID %s not found".formatted(
                aClass.getSimpleName().replace("ID", ""),
                id.getValue()
        );

        return new NotFoundException(anError, List.of(new Error(anError)));
    }

    public static NotFoundException with(final Error error) {
        return new NotFoundException(error.message(), List.of(error));
    }

}
