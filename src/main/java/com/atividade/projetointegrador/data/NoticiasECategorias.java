package com.atividade.projetointegrador.data;

import java.util.ArrayList;
import java.util.List;

public class NoticiasECategorias {
    
    private static List<CategoriaEntity> categorias = new ArrayList<>();
    private static List<NoticiaEntity> noticias = new ArrayList<>();
    
    public static void adicionarCategoria(CategoriaEntity categoria) {
        
        categorias.add(categoria);
    }
    public static void adicionarNoticias(NoticiaEntity noticia) {
        
        noticias.add(noticia);
    }
    
    public static void removerCategoria(Integer indice) {
        
        categorias.remove(indice);
    }
    
    public static void removerNoticia(Integer indice) {
        
        noticias.removeIf(noticia -> noticia.getId().equals(indice));
    }

    public static List<CategoriaEntity> getCategorias() {
        return categorias;
    }

    public static List<NoticiaEntity> getNoticias() {
        return noticias;
    }
}
