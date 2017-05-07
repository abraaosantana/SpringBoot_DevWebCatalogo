package com.br.devwebtecnologia.catalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.devwebtecnologia.catalogo.model.Vinho;

public interface VinhoInterface extends JpaRepository<Vinho, Long> {

	public List<Vinho> findByNomeContainingIgnoreCase(String nome);
	
}
