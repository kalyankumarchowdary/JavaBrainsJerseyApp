package org.example.services;

import org.example.database.stubs.DatabaseStubs;
import org.example.exceptions.MessageNotFoundException;
import org.example.models.Message;
import java.util.ArrayList;
import java.util.Calendar;
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

    public List<Message> getAllMessagesForYear(int year) {
        // This method retrieves all messages for a specific year.
        // In a real application, you would filter messages based on the year.
        List<Message> messagesForYear = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (Message message : messages.values()) {
            cal.setTime(message.getCreated());
            if (cal.get(Calendar.YEAR) == year) {
                messagesForYear.add(message);
            }
        }
        return messagesForYear;
    }

    public List<Message> getAllMessagesPaginated(int start, int size) {
        // This method retrieves a paginated list of messages.
        List<Message> messageList = new ArrayList<>(messages.values());
        if (start + size > messageList.size()) { // Check if the requested size exceeds the available messages
            return messageList.subList(start, messageList.size()); // if the requested size exceeds the available messages, return from start to the end of the list
        }
        return messageList.subList(start, start + size); // return the sublist from start to start + size
    }

    public Message getMessageById(long id) {
        // This method retrieves a specific message by its ID.
        return messages.get(id);
    }

    public Message addMessage(Message message) {
        // This method adds a new message to the collection.
        long newId = messages.size() + 1; // Simple ID generation logic (Incrementing  the size of the map by increasing id by 1)
        message.setId(newId);// Setting the new ID to the message
        messages.put(newId, message);// Adding the new message to the map
        return message;
    }

    public Message updateMessage(long id, Message message) {
        // This method updates an existing message by its ID.
        if (messages.containsKey(id)) {
            message.setId(id); // Ensure the ID remains the same
            messages.put(id, message);
            return message;
        }
        throw new MessageNotFoundException("Message with ID " + id + " not found.");

    }

    public void deleteMessage(long id) {
        // This method deletes a message by its ID.
        if (messages.containsKey(id)) {
            messages.remove(id); // Remove the message from the map
        } else {
            throw new MessageNotFoundException("Message with ID " + id + " not found.");
        }
    }

    public void deleteAllMessages() {
        // This method deletes all messages.
        messages.clear(); // Clear the map to remove all messages
    }
}
