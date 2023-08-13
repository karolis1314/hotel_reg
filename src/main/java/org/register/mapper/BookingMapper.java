package org.register.mapper;

import org.register.domain.dto.BookingDto;
import org.register.domain.entity.Booking;

public class BookingMapper {

    public static BookingDto toBookingDto(String guestFirstName, String guestLastName, boolean roomAvailability, Long roomNumber) {
        return new BookingDto(guestFirstName, guestLastName, roomNumber, roomAvailability);
    }
}
