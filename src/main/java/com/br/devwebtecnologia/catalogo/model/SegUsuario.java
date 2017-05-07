package com.br.devwebtecnologia.catalogo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "seg_usuario")
public class SegUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;
	
	@Column(name = "email")
	@Email(message = "*Por favor iforme um email válido!")
	@NotEmpty(message = "*Por favor informe um email!")
	private String email;
	
	@Column(name = "password")
	@Length(min = 6, message = "*Sua senha deve conter no mínimo 6 caracteres!")
	@NotEmpty(message = "*Por favor informe sua senha!")
	@Transient
	private String password;
	
	@Column(name = "nome")
	@NotEmpty(message = "*Por favor informe seu nome!")
	private String nome;
	
	@Column(name = "ultimo_nome")
	@NotEmpty(message = "*Por favor informe seu último nome")
	private String ultimoNome;
	
	@Column(name = "ativo")
	private boolean ativo;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "seg_usuario_role", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_role"))
	private Set<SegRole> segRole;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Set<SegRole> getRole() {
		return segRole;
	}

	public void setRoles(Set<SegRole> segRole) {
		this.segRole = segRole;
	}

}
