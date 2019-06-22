package br.edu.ifsul.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "aeroporto")
public class Aeroporto implements Serializable {

	@Id
	@SequenceGenerator(name = "seq_aeroporto", sequenceName = "seq_aeroporto_id", allocationSize = 1)
	@GeneratedValue(generator = "seq_aeroporto", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@NotBlank(message = "Campo nome é obrigatório.")
	@Length(max = 100, message = "O nome deve conter até {max} caracteres.")
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@Column(name = "operacao_noturna")
	private Boolean operacaoNoturna;

	@ManyToOne
	@JoinColumn(name = "cidade", referencedColumnName = "id", nullable = false)
	@ForeignKey(name = "fk_cidade")
	private Cidade cidade;

	@ManyToMany
	@JoinTable(name = "escalas", joinColumns = @JoinColumn(name = "aeroporto", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "voo", referencedColumnName = "id", nullable = false))
	private Set<Voo> voos = new HashSet<>();

	public Aeroporto() {

	}

	public void adicionarVoo(Voo v) {
		voos.add(v);
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
		Aeroporto other = (Aeroporto) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Boolean getOperacaoNoturna() {
		return operacaoNoturna;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@SuppressWarnings("unlikely-arg-type")
	public void removerVoo(int index) {
		voos.remove(index);
	}

	public void removerVoo(Voo v) {
		voos.remove(v);
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setOperacaoNoturna(Boolean operacaoNoturna) {
		this.operacaoNoturna = operacaoNoturna;
	}

	@Override
	public String toString() {
		return nome;
	}

}
