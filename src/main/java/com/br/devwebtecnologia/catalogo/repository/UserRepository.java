package com.br.devwebtecnologia.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.devwebtecnologia.catalogo.model.SegUsuario;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<SegUsuario, Long> {
	SegUsuario findByEmail(String email);
}
