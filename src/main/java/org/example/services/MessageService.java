package org.example.services;

import org.example.models.Message;
import java.util.ArrayList;
import java.util.List;


/*The Service Layer contains the business logic of your application. It usually interacts with a DAO or repository to fetch/update data, and is used by your REST resource classes.
 * It is a good practice to keep the service layer thin and focused on business logic, while delegating data access to DAOs or repositories.
 */
public class MessageService {

    public List<Message> getAllMessages() {
        Message m1 = new Message(1001,"Hello, World!","kalyan");
        Message m2 = new Message(1002,"Welcome to the Service Layer!","kalyan");
        // This method would typically interact with a DAO or repository to fetch messages.
        // For simplicity, we return a static list here.
        ArrayList <Message> messageList = new ArrayList<>();
        messageList.add(m1);
        messageList.add(m2);
        return messageList;


    }


}
