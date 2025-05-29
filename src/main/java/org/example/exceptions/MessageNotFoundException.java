package org.example.exceptions;

public class MessageNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -1234567890123456L; // Unique identifier for serialization
    /**
     * Constructs a new MessageNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public MessageNotFoundException(String message) {
        super(message);
    }
}
