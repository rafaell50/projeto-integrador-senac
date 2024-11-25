package com.atividade.projetointegrador.Service;

import com.atividade.projetointegrador.data.NoticiaEntity;
import com.atividade.projetointegrador.data.NoticiaRepository;
import com.atividade.projetointegrador.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 @Service
public class NoticiaService {
    
     @Autowired
     NoticiaRepository noticiaRepository;
     
     public NoticiaEntity getNoticiaId(Integer noticiaId) {

        return noticiaRepository.findById(noticiaId).orElseThrow(() -> new ResourceNotFoundException("Notícia não encontrado " + noticiaId));
    }
     
     public NoticiaEntity criarNoticia(NoticiaEntity noticia) {

        noticiaRepository.save(noticia);

        return noticia;
    }
     
     public NoticiaEntity editarNoticia(Integer noticiaId, NoticiaEntity noticiaRequest) {

        NoticiaEntity noticia = getNoticiaId(noticiaId);
        noticia.setCategoria(noticiaRequest.getCategoria());
        noticia.setTitulo(noticiaRequest.getTitulo());
        noticia.setNomeNoticia(noticiaRequest.getNomeNoticia());
        noticiaRepository.save(noticia);

        return noticia;
    }
     
     public List<NoticiaEntity> listarTodasNoticias() {

        return noticiaRepository.findAll();
    }
     
     public void deletarNoticia(Integer noticiaId) {

        
        noticiaRepository.deleteById(noticiaId);
    }
     
     public List<NoticiaEntity> buscarAnalises(Integer categoriaId) {

        return noticiaRepository.findByCategoriaId(categoriaId);
    }
}
