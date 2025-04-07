package com.david.shareNotes.types;

import java.util.List;

import com.david.shareNotes.entities.Like;

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

    public returnableNote(String title, String content, List<String> tags, returnableUser user, Long id, Long like) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.user = user;
        this.id = id;
        this.like = like;
    }

}
