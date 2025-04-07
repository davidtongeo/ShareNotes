package com.david.shareNotes.types;

public class likeType {
    private Long idNote;
    private Long idUser;

    public Long getIdNote() {
        return idNote;
    }

    public Long getIdUser() {
        return idUser;
    }

    public likeType(Long idNote, Long idUser) {
        this.idNote = idNote;
        this.idUser = idUser;
    }

}
