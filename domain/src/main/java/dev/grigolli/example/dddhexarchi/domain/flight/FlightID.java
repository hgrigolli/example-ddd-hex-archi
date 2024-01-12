package dev.grigolli.example.dddhexarchi.domain.flight;

import dev.grigolli.example.dddhexarchi.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class FlightID extends Identifier {

    private String value;

    private FlightID(String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static FlightID unique() {
        return new FlightID(UUID.randomUUID().toString().toLowerCase());
    }

    public static FlightID from(final String anID) {
        return new FlightID(anID);
    }

    public static FlightID from(final UUID anID) {
        return new FlightID((anID.toString().toLowerCase()));
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightID object = (FlightID) o;
        return Objects.equals(value, object.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }


}