package com.example.bruno.recyclerview;

public class Filme {

    private int id;
    private String titulo;
    private String imageUrl;

    public Filme(int id, String titulo, String imageUrl) {
        this.id = id;
        this.titulo = titulo;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
