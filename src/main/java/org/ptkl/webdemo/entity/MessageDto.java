package org.ptkl.webdemo.entity;

public class MessageDto {

    private int id;

    private String message;

    @Override
    public String toString() {
        return "MessageDto{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
