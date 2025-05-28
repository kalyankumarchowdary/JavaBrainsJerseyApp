package org.example.services;

import org.example.database.stubs.DatabaseStubs;
import org.example.models.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/*The Service Layer contains the business logic of your application. It usually interacts with a DAO or repository to fetch/update data, and is used by your REST resource classes.
 * It is a good practice to keep the service layer thin and focused on business logic, while delegating data access to DAOs or repositories.
 */
public class MessageService {

    private Map<Long, Message> messages = DatabaseStubs.getMessages();

    public MessageService() {
        // Constructor can be used to initialize any resources or configurations if needed.
        messages.put(1L, new Message(1001,"Hello, World!","kalyan"));
        messages.put(2L, new Message(1002, "Welcome to the Service Layer!", "kalyan"));
    }

    public List<Message> getAllMessages() {
        // This method would typically interact with a DAO or repository to fetch messages.
        // For simplicity, we use a stubbed database.
        return new ArrayList<Message>(messages.values());


    }


}
