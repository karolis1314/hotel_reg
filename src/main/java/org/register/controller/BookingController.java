package org.register.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/booking")
public class BookingController {


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAllBookings() {
        return Response.status(Response.Status.OK)
                .build();
    }
}
