package com.br.devwebtecnologia.catalogo.service;

import com.br.devwebtecnologia.catalogo.model.SegUsuario;

public interface UserService {
	public SegUsuario findUserByEmail(String email);
	public void saveUser(SegUsuario user);
}
