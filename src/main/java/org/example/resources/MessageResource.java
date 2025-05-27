package org.example.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.models.Message;
import org.example.services.MessageService;

import java.util.List;

@Path("messages")
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    @Path("/text")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTextMessage() {
        // Assuming the service returns a string representation of all messages
        // If the service returns a list, you might want to convert it to a string format
        List<Message> messages = messageService.getAllMessages();
        StringBuilder sb = new StringBuilder();
        for (Message message : messages) {
            sb.append(message.toString()).append("\n");
        }
        return sb.toString();
    }

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getJsonMessage() {
        return messageService.getAllMessages();
    }
}
