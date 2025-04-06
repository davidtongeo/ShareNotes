package com.david.shareNotes.types;

import java.util.List;

import com.david.shareNotes.entities.User;

public class returnableNote {
    private String title;
    private String content;
    private List<String> tags;
    private returnableUser user;

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

    public returnableNote(String title, String content, List<String> tags, returnableUser user) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.user = user;
    }

}
