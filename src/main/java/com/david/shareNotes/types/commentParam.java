package com.david.shareNotes.types;

public class commentParam {
    private Long userId;
    private Long noteId;
    private String content;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public commentParam(Long userId, Long noteId, String content) {
        this.userId = userId;
        this.noteId = noteId;
        this.content = content;
    }

    public commentParam() {
    }

}
