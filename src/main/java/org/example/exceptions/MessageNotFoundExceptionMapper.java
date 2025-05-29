package org.example.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.example.models.ErrorMessage;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Provider // This annotation registers the custom exception mapper with JAX-RS
public class MessageNotFoundExceptionMapper implements ExceptionMapper<MessageNotFoundException> {

    @Override
    public Response toResponse(MessageNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 404, "Message with Id not found");
        return Response.status(NOT_FOUND)
                .entity(errorMessage)
                .build();
    }
}
