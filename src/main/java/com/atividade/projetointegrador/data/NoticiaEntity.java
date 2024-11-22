package com.atividade.projetointegrador.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NoticiaEntity {
    
    Integer id;
    
    @NotNull(message = "Categoria não pode ser nulo")
    CategoriaEntity categoria;
    
    @NotBlank(message = "Título não pode ser vazio")
    String titulo;
    
    @NotBlank(message = "Notícia não pode ser vazio")
    String noticia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNoticia() {
        return noticia;
    }

    public void setNoticia(String noticia) {
        this.noticia = noticia;
    }
    
}
