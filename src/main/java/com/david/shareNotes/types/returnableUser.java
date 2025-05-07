package com.david.shareNotes.types;

public class returnableUser {
    private String username;
    private Long id;

    private boolean isAdmin;

    public returnableUser(String username, Long id, boolean isAdmin) {
        this.username = username;
        this.id = id;
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

}
