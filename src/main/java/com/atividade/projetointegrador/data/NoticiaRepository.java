package com.atividade.projetointegrador.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepository extends JpaRepository<NoticiaEntity, Integer> {
    
    List<NoticiaEntity> findByCategoriaId(Integer categoriaId);
}
