CREATE TYPE flightStatus AS ENUM (
    'SCHEDULED',
    'DELAYED',
    'CANCELLED',
    'BOARDING',
    'ON_HOLD',
    'DEPARTED',
    'LANDED',
    'ARRIVED'
);

CREATE TABLE flight (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    flight_number VARCHAR(255) NOT NULL,
    CHECK (LENGTH(flight_number) > 2),
    departure_date DATE NOT NULL,
    scheduled_departure_time TIME NOT NULL,
    actual_departure_time TIME,
    scheduled_arrival_time TIME NOT NULL,
    actual_arrival_time TIME,
    aircraft_id VARCHAR(10) NOT NULL,
    CHECK (LENGTH(aircraft_id) > 2),
    departure_airport VARCHAR(3) NOT NULL,
    CHECK (LENGTH(departure_airport) = 3),
    arrival_airport VARCHAR(3) NOT NULL,
    CHECK (LENGTH(arrival_airport) = 3),
    status flightStatus NOT NULL,
    delay INTEGER,
    on_hold_until TIMESTAMP
);