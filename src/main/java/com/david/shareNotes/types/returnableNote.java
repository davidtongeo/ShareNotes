package com.david.shareNotes.types;

import java.util.List;

public class returnableNote {
    private String title;
    private String content;
    private List<String> tags;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<String> getTags() {
        return tags;
    }

    public returnableNote(String title, String content, List<String> tags) {
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

}
