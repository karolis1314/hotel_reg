package org.register.controller;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/room")
public class RoomController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAllRooms() {
        return Response.status(Response.Status.OK)
                .entity(Optional.empty())
                .build();
    }

    @GET
    @Path("/booked")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getBookedRooms() {
        return Response.status(Response.Status.OK)
                .entity(Optional.empty())
                .build();
    }

    @GET
    @Path("/available")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAvalibleRooms() {
        return Response.status(Response.Status.OK)
                .entity(Optional.empty())
                .build();
    }

    @POST
    @Path("/book/{firstName}/{lastName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response bookARoom(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
        return Response.status(Response.Status.OK)
                .build();
    }

    @POST
    @Path("/checkout/{firstName}/{lastName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response checkout(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
        return Response.status(Response.Status.OK)
                .build();
    }
}
