package br.edu.ifsul.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "classe")
public class Classe implements Serializable {

	@Id
	@SequenceGenerator(name = "seq_classe", sequenceName = "seq_classe_id", allocationSize = 1)
	@GeneratedValue(generator = "seq_classe", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@NotBlank(message = "Campo nome é obrigatório.")
	@Length(max = 100, message = "O nome deve conter até {max} caracteres.")
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@Column(name = "valor", nullable = false, columnDefinition = "DECIMAL(10,2)")
	@Min(value = 0, message = "O valor não pode ser negativo!")
	private Double valor;

	public Classe() {

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Classe other = (Classe) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Double getValor() {
		return valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return nome;
	}
}
