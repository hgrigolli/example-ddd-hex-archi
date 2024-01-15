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
    flightNumber VARCHAR(255) NOT NULL,
    departureDate DATE,
    scheduledDepartureTime TIME,
    actualDepartureTime TIME,
    scheduledArrivalTime TIME,
    actualArrivalTime TIME,
    aircraftID VARCHAR(10),
    departureAirport VARCHAR(3),
    arrivalAirport VARCHAR(3),
    status flightStatus,
    delay INTEGER,
    onHoldUntil TIMESTAMP,
    PRIMARY KEY (flightNumber)
    constraint chk_t_departureAirport check (departureAirport ~ '^[A-Z]{3}$')
    constraint chk_t_arrivalAirport check (arrivalAirport ~ '^[A-Z]{3}$')
);