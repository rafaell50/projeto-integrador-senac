package com.atividade.projetointegrador.Service;

import com.atividade.projetointegrador.data.CategoriaEntity;
import com.atividade.projetointegrador.data.CategoriaRepository;
import com.atividade.projetointegrador.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    @Autowired
    CategoriaRepository categoriaRepository;
    
    public CategoriaEntity getCategoriaId(Integer categoriaId) {

        return categoriaRepository.findById(categoriaId).orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrado " + categoriaId));
    }
    
    public CategoriaEntity criarCategoria(CategoriaEntity categoria) {

        
        categoria.setId(null);
        categoriaRepository.save(categoria);

        return categoria;
    }
    
    public CategoriaEntity atualizarCategoria(Integer categoriaId, CategoriaEntity categoriaRequest) {

        CategoriaEntity categoria = getCategoriaId(categoriaId);
        categoria.setNomeCategoria(categoriaRequest.getNomeCategoria());
        
        categoriaRepository.save(categoria);

        return categoria;
    }
    
    public List<CategoriaEntity> listarTodasCategorias() {

        return categoriaRepository.findAll();
    }
    
    public void deletarCategoria(Integer categoriaId) {

        
        categoriaRepository.deleteById(categoriaId);
    }
}
