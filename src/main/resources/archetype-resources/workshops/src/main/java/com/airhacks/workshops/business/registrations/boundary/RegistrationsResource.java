package com.airhacks.workshops.business.registrations.boundary;

import com.airhacks.workshops.business.registrations.control.VatCalculator;
import com.airhacks.workshops.business.registrations.entity.Registration;
import java.net.URI;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Path("registrations")
public class RegistrationsResource {

    @Inject
    Registrations registrations;

    @Inject
    VatCalculator calculator;

    @POST
    public Response register(Registration request, @Context UriInfo info) {
        JsonObject registration = registrations.register(request);
        long id = registration.getInt(Registrations.CONFIRMATION_ID);
        URI uri = info.getAbsolutePathBuilder().path("/" + id).build();
        return Response.created(uri).entity(registration).build();
    }

    @GET
    @Path("{id}")
    public Registration find(@PathParam("id") long registrationId) {
        return registrations.find(registrationId);
    }

    @GET
    public Response all() {
        JsonArray registrationList = this.registrations.allAsJson();
        if (registrationList == null || registrationList.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(registrationList).build();
    }

    @GET
    @Path("{id}/dummy")
    public Registration dummy(@PathParam("id") int registrationId) {
        return new Registration(registrationId);
    }

}
