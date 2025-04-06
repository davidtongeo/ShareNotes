package com.david.shareNotes.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "notes")
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private List<String> tags;

    private String contenido;
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User Usuario;

    // required by JPA
    public Notes() {
    }

    public Notes(String title, List<String> tags, String contenido, User usuario) {
        this.title = title;
        this.tags = tags;
        this.contenido = contenido;
        Usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public User getUsuario() {
        return Usuario;
    }

    public void setUsuario(User usuario) {
        Usuario = usuario;
    }

}
