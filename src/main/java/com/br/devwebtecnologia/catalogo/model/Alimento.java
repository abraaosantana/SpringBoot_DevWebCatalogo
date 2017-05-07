package com.br.devwebtecnologia.catalogo.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Alimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
	@NotBlank
	private String beneficios;
	
	@NotBlank
	private String composicao;
	
	@NotBlank
	private String variedades;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private AlimentoGrupo grupo;

	@NotNull
	private int caloria;
	
	@NotNull
	private int porcaoDiaria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}

	public String getComposicao() {
		return composicao;
	}

	public void setComposicao(String composicao) {
		this.composicao = composicao;
	}

	public String getVariedades() {
		return variedades;
	}

	public void setVariedades(String variedades) {
		this.variedades = variedades;
	}

	public AlimentoGrupo getGrupo() {
		return grupo;
	}

	public void setGrupo(AlimentoGrupo grupo) {
		this.grupo = grupo;
	}

	public int getCaloria() {
		return caloria;
	}

	public void setCaloria(int caloria) {
		this.caloria = caloria;
	}

	public int getPorcaoDiaria() {
		return porcaoDiaria;
	}

	public void setPorcaoDiaria(int porcaoDiaria) {
		this.porcaoDiaria = porcaoDiaria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alimento other = (Alimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
