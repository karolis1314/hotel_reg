package org.register.mapper;

import org.register.domain.dto.BookingDto;
import org.register.domain.entity.Booking;

public class BookingMapper {

    public static BookingDto toBookingDto(Booking booking) {
        return new BookingDto(booking.getGuest().getFirstName(), booking.getGuest().getLastName(), booking.getRoom().getId(), booking.getRoom().getAvailable());
    }
}
