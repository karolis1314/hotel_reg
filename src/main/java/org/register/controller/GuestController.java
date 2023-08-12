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

@Path("/guest")
public class GuestController {

    private final GuestService guestService;

    public GuestController() {
        this.guestService = new GuestService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGuest() {
        return Response.status(Response.Status.OK)
                .entity(guestService.getAllGuest())
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerGuest(GuestDto dto) {
        guestService.createGuest(dto);
        return Response.status(Response.Status.CREATED)
                .build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateGuest(@PathParam("id") Long id, GuestDto dto) {
        guestService.updateGuest(id, dto);
        return Response.status(Response.Status.ACCEPTED)
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response updateGuest(@PathParam("id") Long id) {
        guestService.deleteGuest(id);
        return Response.status(Response.Status.ACCEPTED)
                .build();
    }
}
