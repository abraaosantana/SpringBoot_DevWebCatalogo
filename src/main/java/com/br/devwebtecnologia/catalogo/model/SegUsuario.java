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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "seg_usuario")
public class SegUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;
	
	@Column(name = "nome")
	@NotEmpty(message = "*Informe seu nome!")
	@Pattern(message = "*Informe apenas letras no campo nome!", regexp ="^[A-Za-z áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]*$")
	private String nome;
	
	@Column(name = "ultimo_nome")
	@NotEmpty(message = "*Informe seu Sobrenome!")
	@Pattern(message = "*Informe apenas letras no campo Sobrenome!", regexp ="^[A-Za-z áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]*$")
	private String ultimoNome;
	
	@Column(name = "cpf", unique = true)
	@CPF(message = "*Informe um CPF válido!")
	private String cpf;
	
	@Column(name = "email")
	@NotEmpty(message = "*Informe um email!")
	@Email(message = "*Iforme um email válido!")
	private String email;
	
	@Column(name = "password")
	@NotEmpty(message = "*Informe sua senha!")
	@Length(min = 6, message = "*Sua senha deve conter no mínimo 6 caracteres!")
	@Transient
	private String password;
	
	
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
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Set<SegRole> getSegRole() {
		return segRole;
	}

	public void setSegRole(Set<SegRole> segRole) {
		this.segRole = segRole;
	}

}
