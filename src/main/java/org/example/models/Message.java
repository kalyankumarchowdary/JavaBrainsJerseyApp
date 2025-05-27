package org.example.models;

import java.util.Date;

/*The Model Layer contains your POJOs (Plain Old Java Objects) which represent the domain data. This layer is often annotated for JPA/Hibernate if you're using a database.

/**
 * Message class representing a message entity with an ID, content, creation date, and author.
 */


public class Message {

    private long id;
    private String message;
    private Date created;
    private String author;

    public Message() {
    }

    public Message(long id, String message, String author) {
        this.id = id;
        this.message = message;
        this.created = new Date();
        this.author = author;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", created=" + created +
                ", author='" + author + '\'' +
                '}';
    }
}
