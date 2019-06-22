package com.sydoruk1ua.mdmg.model.entity;

import java.util.Objects;

public class Message {
    private Integer id;
    private String userEmail;
    private String value;

    public Message(String userEmail, String value) {
        this.userEmail = userEmail;
        this.value = value;
    }

    public Message(Integer id, String userEmail, String value) {
        this(userEmail, value);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&
                Objects.equals(userEmail, message.userEmail) &&
                Objects.equals(value, message.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userEmail, value);
    }
}
