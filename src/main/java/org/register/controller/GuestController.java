package org.register.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class ApplicationResource {


    @GET
    @Path("/hi")
    public Response sayHi() {
        return Response.status(Response.Status.OK)
                .entity("Hellloooo")
                .build();
    }
}
