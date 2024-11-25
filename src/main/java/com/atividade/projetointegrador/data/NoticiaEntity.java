package com.atividade.projetointegrador.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "noticia")
public class NoticiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @NotNull(message = "Categoria não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    CategoriaEntity categoria;

    @NotBlank(message = "Título não pode ser vazio")
    String titulo;

    @NotBlank(message = "Notícia não pode ser vazio")
    String nomeNoticia;

}
