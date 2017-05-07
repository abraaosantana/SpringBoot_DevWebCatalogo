package com.br.devwebtecnologia.catalogo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.br.devwebtecnologia.catalogo.model.Alimento;

public interface AlimentoInterface extends JpaRepository<Alimento, Long> {

	public List<Alimento> findByNomeContainingIgnoreCase(String nome);
	
}
