package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.exceptions.MessageNotFoundException;
import org.example.models.Message;
import org.example.resources.beans.MessageFilterBeans;
import org.example.services.MessageService;

import java.util.List;

import static jakarta.ws.rs.core.Response.Status.CREATED;

@Path("messages")
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getAllMessages(@BeanParam MessageFilterBeans messageFilterBeans) {
        if (messageFilterBeans.getYear() > 0) {
            return messageService.getAllMessagesForYear(messageFilterBeans.getYear());
        }
        if (messageFilterBeans.getStart() >= 0 && messageFilterBeans.getSize() > 0) {
            return messageService.getAllMessagesPaginated(messageFilterBeans.getStart(), messageFilterBeans.getSize());
        }
        return messageService.getAllMessages();
    }

    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHelloMessageWithYourName(@MatrixParam("name") String name) {
        return "Hello , I am " + name + "! Welcome to the Message Service.";
    }

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

    @GET
    @Path("/{messageId}") // Path parameter to get a specific message by ID (example: /messages/1 , /messages/2)
    @Produces(MediaType.APPLICATION_JSON)
    public Message getMessageById(@PathParam("messageId") long id) {
        return messageService.getMessageById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMessage(Message message) {
        Message newMessage = messageService.addMessage(message);
        return Response.status(CREATED)
                .entity(newMessage)
                .build(); // Return a response with status 201 Created and the new message in the body

    }

    @PUT
    @Path("/{messageId}") // Path parameter to update a specific message by ID (example: /messages/1 , /messages/2)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Message updateMessage(@PathParam("messageId") long id, Message message) {
        return messageService.updateMessage(id, message);
    }

    @PATCH
    @Path("/{messageId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Message patchMessage(@PathParam("messageId") long id, Message partialMessage) {
        Message existingMessage = messageService.getMessageById(id); // Retrieve the existing message by ID
        if (existingMessage == null) {
            throw new MessageNotFoundException("Message with ID " + id + " not found.");
        }
        if (partialMessage.getMessage() != null) {
            existingMessage.setMessage(partialMessage.getMessage());// Update the message content if provided in json body
        }
        return messageService.updateMessage(id, existingMessage);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteAllMessages() {
        messageService.deleteAllMessages(); // Remove all messages from the list
    }

    @DELETE
    @Path("/{messageId}") // Path parameter to delete a specific message by ID (example: /messages/1 , /messages/2)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteMessage(@PathParam("messageId") long id) {
        Message message = messageService.getMessageById(id);
        if (message == null) {
            throw new MessageNotFoundException("Message with ID " + id + " not found.");
        }
        messageService.deleteMessage(id); // Remove the message from the list
    }

}
