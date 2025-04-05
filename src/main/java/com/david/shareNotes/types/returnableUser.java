package com.david.shareNotes.types;

public class returnableUser {
    private String username;
    private Long id;

    public returnableUser(String username, Long id) {
        this.username = username;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

}
