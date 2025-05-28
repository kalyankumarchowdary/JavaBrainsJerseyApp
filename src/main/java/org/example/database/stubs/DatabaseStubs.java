package org.example.database.stubs;

import org.example.models.Message;

import java.util.HashMap;
import java.util.Map;

public class DatabaseStubs {

    // This class can be used to define static methods that return stubbed data for testing purposes.
    // Created this class instead of database

    private static final Map<Long, Message> messages = new HashMap<>();

    public static Map<Long, Message> getMessages() {
        return messages;
    }
}
