package com.atividade.projetointegrador.controller;

import com.atividade.projetointegrador.Service.CategoriaService;
import com.atividade.projetointegrador.Service.NoticiaService;
import com.atividade.projetointegrador.data.CategoriaEntity;
import com.atividade.projetointegrador.data.NoticiaEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SiteController {

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    NoticiaService noticiaService;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("noticias", noticiaService.listarTodasNoticias());
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

        categoriaService.criarCategoria(categoria);
        model.addAttribute("categorias", categoriaService.listarTodasCategorias());

        return "redirect:/";
    }

    @GetMapping("/cadastro_noticia")
    public String exibirCadastroNoticia(Model model) {

        model.addAttribute("noticia", new NoticiaEntity());
        model.addAttribute("categorias", categoriaService.listarTodasCategorias());
        return "cadastro_noticia";
    }

    @PostMapping("/cadastro_noticia")
    public String registrarNoticia(@Valid @ModelAttribute("noticia") NoticiaEntity noticia, BindingResult result, Model model) {

        if (result.hasErrors()) {

            model.addAttribute("categorias", categoriaService.listarTodasCategorias());
            return "cadastro_noticia";
        }

        // Recupera o ID da categoria (por exemplo, de um formulário)
        Integer categoriaId = noticia.getCategoria().getId();

        // Busca a categoria correspondente usando um laço for
        CategoriaEntity categoria = null;
        for (CategoriaEntity cat : categoriaService.listarTodasCategorias()) {
            if (cat.getId().equals(categoriaId)) {
                categoria = cat;
                break; // Interrompe o laço assim que encontra a categoria
            }
        }

        noticia.setCategoria(categoria);

        noticiaService.criarNoticia(noticia);
        model.addAttribute("noticias", noticiaService.listarTodasNoticias());
        model.addAttribute("categorias", categoriaService.listarTodasCategorias());

        return "redirect:/";
    }

    @GetMapping("/deletar/{id}")
    public String deletarNoticia(@PathVariable(value = "id") Integer id) {
        noticiaService.deletarNoticia(id);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editarNoticia(@PathVariable int id, Model model) {

        NoticiaEntity noticia = noticiaService.getNoticiaId(id);
        CategoriaEntity categoria = categoriaService.getCategoriaId(noticia.getCategoria().getId());

        model.addAttribute("noticia", noticia);
        model.addAttribute("categorias", categoriaService.listarTodasCategorias());

        return "cadastro_noticia";
    }

    @PostMapping("/editar_noticia")
    public String editarNoticia(@Valid @ModelAttribute("noticia") NoticiaEntity noticia, BindingResult result, Model model) {

        
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listarTodasCategorias());
            return "cadastro_noticia";
        }

        
        noticiaService.editarNoticia(noticia.getId(), noticia);

        
        model.addAttribute("noticias", noticiaService.listarTodasNoticias());
        model.addAttribute("categorias", categoriaService.listarTodasCategorias());

        
        return "redirect:/";
    }

    @GetMapping("/categoria")
    public String exibirNoticaCatetoria(Model model) {

        model.addAttribute("noticia", new NoticiaEntity());
        model.addAttribute("categorias", categoriaService.listarTodasCategorias());
        return "cadastro_noticia";
    }

}
