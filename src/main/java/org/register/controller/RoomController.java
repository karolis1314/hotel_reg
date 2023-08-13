package org.register.controller;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.register.service.impl.RoomService;

@Path("/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController() {
        this.roomService = new RoomService();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRooms() {
        return Response.status(Response.Status.OK)
                .entity(roomService.getAllRooms())
                .build();
    }

    @GET
    @Path("/booked")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookedRooms() {
        return Response.status(Response.Status.OK)
                .entity(roomService.getAllBookedRooms())
                .build();
    }

    @GET
    @Path("/available")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvailableRooms() {
        return Response.status(Response.Status.OK)
                .entity(roomService.getAllAvailableRooms())
                .build();
    }
}
