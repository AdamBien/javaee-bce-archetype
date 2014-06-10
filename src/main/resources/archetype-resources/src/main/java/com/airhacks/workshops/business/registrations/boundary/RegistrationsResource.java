package com.airhacks.workshops.business.registrations.boundary;

import com.airhacks.workshops.business.registrations.control.VatCalculator;
import com.airhacks.workshops.business.registrations.entity.Registration;
import java.net.URI;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("registrations")
public class RegistrationsResource {

    @Inject
    Registrations registrations;

    @Inject
    VatCalculator calculator;

    @POST
    public Response register(Registration request, @Context UriInfo info) {
        Registration registration = registrations.register(request);
        long id = registration.getId();
        URI uri = info.getAbsolutePathBuilder().path("/" + id).build();
        JsonObject confirmation = Json.createObjectBuilder().
                add("price", registration.getTotalPrice()).
                add("confirmation-id", registration.getId()).build();
        return Response.created(uri).entity(confirmation).build();
    }

    @GET
    @Path("{id}")
    public Registration find(@PathParam("id") int registrationId) {
        return registrations.find(registrationId);
    }

    @GET
    @Path("{id}/dummy")
    public Registration dummy(@PathParam("id") int registrationId) {
        return new Registration(true, 1, 1);
    }

}
