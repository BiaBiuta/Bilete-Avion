package com.example.examen.domain;

public class Client extends  Entity<Long> {
    String username ;
    String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client(String username, String name) {
        this.username = username;
        this.name = name;
    }
}
