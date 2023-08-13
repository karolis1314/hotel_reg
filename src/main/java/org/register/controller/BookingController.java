package org.register.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.register.service.impl.BookingService;
import org.register.service.impl.GuestService;
import org.register.service.impl.RoomService;

@Path("/booking")
public class BookingController {

    private final BookingService bookingService;
    public BookingController() {
        this.bookingService = new BookingService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBookings() {
        return Response.status(Response.Status.OK)
                .build();
    }
}
