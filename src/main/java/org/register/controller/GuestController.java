package org.register.controller;


import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.register.domain.dto.GuestDto;
import org.register.service.impl.GuestService;

import java.util.Optional;

@Path("/guest")
public class GuestController {

    private final GuestService guestService;

    public GuestController() {
        this.guestService = new GuestService();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAllGuest() {
        return Response.status(Response.Status.OK)
                .entity(guestService.getAllGuest())
                .build();
    }

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getTests() {
        return Response.status(Response.Status.OK)
                .entity("Fuck of")
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerGuest(GuestDto dto) {

        return Response.status(Response.Status.CREATED)
                .build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateGuest(@PathParam("id") Long id, GuestDto dto) {

        return Response.status(Response.Status.ACCEPTED)
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response updateGuest(@PathParam("id") Long id) {

        return Response.status(Response.Status.ACCEPTED)
                .build();
    }
}
