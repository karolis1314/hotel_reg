package org.register.factory;

import jakarta.ws.rs.container.ResourceContext;
import jakarta.ws.rs.core.Context;
import org.register.controller.GuestController;

public class GuestControllerResourceFactory {

    @Context
    private ResourceContext resourceContext;

    public GuestController getGuestController() {
        GuestController controller = new GuestController();
        resourceContext.getResource(GuestController.class);
        return controller;
    }
}
