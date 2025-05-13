package com.david.shareNotes.types;

import java.util.List;

import com.david.shareNotes.entities.Comment;

public class returnableNote {
    private Long id;

    public Long getId() {
        return id;
    }

    private String title;
    private String content;
    private List<String> tags;
    private returnableUser user;
    private Long like;
    private List<returnableComment> comments;

    public List<returnableComment> getComments() {
        return comments;
    }

    public void setComments(List<returnableComment> comments) {
        this.comments = comments;
    }

    public Long getLike() {
        return like;
    }

    public returnableUser getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<String> getTags() {
        return tags;
    }

    public returnableNote(String title, String content, List<String> tags, returnableUser user, Long id, Long like,
            List<returnableComment> comments) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.user = user;
        this.id = id;
        this.like = like;
        this.comments = comments;
    }

}
