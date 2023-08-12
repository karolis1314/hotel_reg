package org.register.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class ApplicationException {


    public static WebApplicationException errorMessage(Response.Status status, String message) {
        throw new WebApplicationException(Response.status(status)
                .entity(message)
                .build());
    }

    public static WebApplicationException internalServerError() {
        throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Internal Server Error")
                .build());
    }
}
