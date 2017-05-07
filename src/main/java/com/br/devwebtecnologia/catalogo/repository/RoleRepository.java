package com.br.devwebtecnologia.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.devwebtecnologia.catalogo.model.SegRole;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<SegRole, Long>{
	SegRole findByRole(String role);

}
