package com.estoque.peixaria.repository;

import com.estoque.peixaria.model.MovimentacaoDiaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovimentacaoDiariaRepository extends JpaRepository<MovimentacaoDiaria, Long> {

    // Busca diretamente o último registro cadastrado para um produto específico
    @Query("SELECT m FROM MovimentacaoDiaria m WHERE LOWER(m.produto) = LOWER(:produto) ORDER BY m.id DESC LIMIT 1")
    MovimentacaoDiaria findUltimaMovimentacaoPorProduto(@Param("produto") String produto);
}