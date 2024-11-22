package com.atividade.projetointegrador.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author rafael
 */
public class CategoriaEntity {

    private Integer id;

    @NotNull(message = "Categoria não pode ser nulo")
    @NotBlank(message = "Categoria não pode ser vazio")
    private String categoria;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
