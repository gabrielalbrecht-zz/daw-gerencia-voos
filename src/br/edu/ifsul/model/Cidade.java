package br.edu.ifsul.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "cidade")
public class Cidade implements Serializable {

	@Id
	@SequenceGenerator(name = "seq_cidade", sequenceName = "seq_cidade_id", allocationSize = 1)
	@GeneratedValue(generator = "seq_cidade", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@NotBlank(message = "Campo nome é obrigatório.")
	@Length(max = 100, message = "O nome deve conter até {max} caracteres.")
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@NotBlank(message = "Campo país é obrigatório.")
	@Length(max = 100, message = "O país deve conter até {max} caracteres.")
	@Column(name = "pais", nullable = false, length = 100)
	private String pais;

	public Cidade() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
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
		Cidade other = (Cidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}
