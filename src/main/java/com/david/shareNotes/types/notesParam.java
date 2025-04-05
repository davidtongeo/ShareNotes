package com.david.shareNotes.types;

import java.util.List;

public class notesParam {
    private String title;
    private String contenido;
    private Long user_id;
    private List<String> tags;

    public List<String> getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }

    public String getContenido() {
        return contenido;
    }

    public Long getUser_id() {
        return user_id;
    }

}
