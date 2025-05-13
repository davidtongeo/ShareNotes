package com.david.shareNotes.types;

public class returnableComment {
    private returnableUser user;
    private String content;
    private Long noteId;

    public returnableUser getUser() {
        return user;
    }

    public void setUser(returnableUser user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public returnableComment(returnableUser user, String content, Long noteId) {
        this.user = user;
        this.content = content;
        this.noteId = noteId;
    }

    public returnableComment() {
    }

}
