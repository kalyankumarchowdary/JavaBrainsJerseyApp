package org.example.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.jersey.server.JSONP;

@Path("messages")
public class MessageResource {

    @GET
    @Path("/text")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        return "My Message: Hello, World!";
    }


    @GET
    @Path("/json")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessages() {
        return "{\"message\": \"Hello World\"}";
    }
}
