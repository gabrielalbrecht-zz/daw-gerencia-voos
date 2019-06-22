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
import org.hibernate.validator.constraints.br.CPF;

@SuppressWarnings("serial")
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

	@Id
	@SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa_id", allocationSize = 1)
	@GeneratedValue(generator = "seq_pessoa", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@NotBlank(message = "Campo nome é obrigatório.")
	@Length(max = 100, message = "O nome deve conter até {max} caracteres.")
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@CPF(message = "CPF inválido!")
	@Column(name = "cpf", nullable = false, length = 15)
	private String cpf;

	@NotBlank(message = "Campo email é obrigatório.")
	@Length(max = 100, message = "O email deve conter até {max} caracteres.")
	@Column(name = "email", nullable = false, length = 100)
	private String email;

	@NotBlank(message = "Campo telefone é obrigatório.")
	@Length(max = 14, min = 14, message = "O telefone deve conter 11 caracteres.")
	@Column(name = "telefone", nullable = false, length = 30)
	private String telefone;

	public Pessoa() {

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
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return nome.concat(" - ").concat(cpf);
	}

}
