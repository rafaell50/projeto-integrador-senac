package com.atividade.projetointegrador.controller;

import com.atividade.projetointegrador.data.CategoriaEntity;
import com.atividade.projetointegrador.data.NoticiaEntity;
import com.atividade.projetointegrador.data.NoticiasECategorias;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("noticias", NoticiasECategorias.getNoticias());
        return "index";
    }

    @GetMapping("/cadastro_categoria")
    public String exibirCadastroCategoria(Model model) {

        CategoriaEntity categoria = new CategoriaEntity();
        model.addAttribute("categoria", categoria);
        return "cadastro_categoria";
    }

    @PostMapping("/cadastro_categoria")
    public String registrarCategoria(@Valid @ModelAttribute("categoria") CategoriaEntity categoria, BindingResult result, Model model) {

        if (result.hasErrors()) {

            return "cadastro_categoria";
        }

        Integer categoriasSize = NoticiasECategorias.getCategorias().size();
        categoria.setId(categoriasSize + 1);
        NoticiasECategorias.adicionarCategoria(categoria);
        model.addAttribute("categorias", NoticiasECategorias.getCategorias());

        return "redirect:/";
    }

    @GetMapping("/cadastro_noticia")
    public String exibirCadastroNoticia(Model model) {

        model.addAttribute("noticia", new NoticiaEntity());
        model.addAttribute("categorias", NoticiasECategorias.getCategorias());
        return "cadastro_noticia";
    }

    @PostMapping("/cadastro_noticia")
    public String registrarNoticia(@Valid @ModelAttribute("noticia") NoticiaEntity noticia, BindingResult result, Model model) {

        if (result.hasErrors()) {

            model.addAttribute("categorias", NoticiasECategorias.getCategorias());
            return "cadastro_noticia";
        }

        // Recupera o ID da categoria (por exemplo, de um formulário)
        Integer categoriaId = noticia.getCategoria().getId();

        // Busca a categoria correspondente usando um laço for
        CategoriaEntity categoria = null;
        for (CategoriaEntity cat : NoticiasECategorias.getCategorias()) {
            if (cat.getId().equals(categoriaId)) {
                categoria = cat;
                break; // Interrompe o laço assim que encontra a categoria
            }
        }
        
        noticia.setCategoria(categoria);

        Integer noticiaSize = NoticiasECategorias.getNoticias().size();
        noticia.setId(noticiaSize + 1);
        NoticiasECategorias.adicionarNoticias(noticia);
        model.addAttribute("noticias", NoticiasECategorias.getCategorias());
        model.addAttribute("categorias", NoticiasECategorias.getCategorias());

        return "redirect:/";
    }

    @GetMapping("/deletar/{id}")
    public String deletarNoticia(@PathVariable(value = "id") Integer id) {
        NoticiasECategorias.removerNoticia(id);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editarNoticia(@PathVariable(value = "id") Integer id) {

        return "redirect:/";
    }

    @GetMapping("/categoria")
    public String exibirNoticaCatetoria(Model model) {

        model.addAttribute("noticia", new NoticiaEntity());
        model.addAttribute("categorias", NoticiasECategorias.getCategorias());
        return "cadastro_noticia";
    }

}
