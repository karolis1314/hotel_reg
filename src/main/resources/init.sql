CREATE DATABASE hotel;
\c hotel;

CREATE TABLE Guest (
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL
);

CREATE TABLE Room (
    id SERIAL PRIMARY KEY,
    available BOOLEAN NOT NULL,
    guest_id INT REFERENCES Guest(id)
);

CREATE TABLE Booking (
    id SERIAL PRIMARY KEY,
    guest_id INT REFERENCES Guest(id),
    room_id INT REFERENCES Room(id),
    checkedIn TIMESTAMP NOT NULL,
    checkedOut TIMESTAMP NOT NULL
);

ALTER TABLE Booking ADD CONSTRAINT fk_guest FOREIGN KEY (guest_id) REFERENCES Guest(id);
ALTER TABLE Booking ADD CONSTRAINT fk_room FOREIGN KEY (room_id) REFERENCES Room(id);

CREATE INDEX idx_booking_guest_id ON Booking(guest_id);
CREATE INDEX idx_booking_room_id ON Booking(room_id);
CREATE INDEX idx_room_guest_id ON Room(guest_id);
