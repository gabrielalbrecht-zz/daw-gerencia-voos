package br.edu.ifsul.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "voo")
public class Voo implements Serializable {

	@Id
	@SequenceGenerator(name = "seq_voo", sequenceName = "seq_voo_id", allocationSize = 1)
	@GeneratedValue(generator = "seq_voo", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@NotBlank(message = "Campo descrição é obrigatório.")
	@Column(name = "descricao", nullable = false, columnDefinition = "TEXT")
	private String descricao;

	@Column(name = "tempo_estimado", nullable = false, columnDefinition = "DECIMAL(10,2)")
	private Double tempoEstimado;

	@Column(name = "ativo")
	private Boolean ativo;

	@Column(name = "periodicidade", nullable = true)
	private String periodicidade;

	@ManyToMany
	@JoinTable(name = "escalas", joinColumns = @JoinColumn(name = "voo", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "aeroporto", referencedColumnName = "id", nullable = false))
	private Set<Aeroporto> aeroportos = new HashSet<>();

	@OneToMany(mappedBy = "voo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<VooAgendado> voosAgendados = new ArrayList<>();

	public Voo() {

	}

	public void adicionarAeroporto(Aeroporto a) {
		aeroportos.add(a);
	}

	public void adicionarVooAgendado(VooAgendado va) {
		voosAgendados.add(va);
		va.setVoo(this);
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
		Voo other = (Voo) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public Set<Aeroporto> getAeroportos() {
		return aeroportos;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getPeriodicidade() {
		return periodicidade;
	}

	public Double getTempoEstimado() {
		return tempoEstimado;
	}

	public List<VooAgendado> getVoosAgendados() {
		return voosAgendados;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void removerVooAgendado(int index) {
		voosAgendados.remove(index);
	}

	public void removerVooAgendado(VooAgendado va) {
		voosAgendados.remove(va);
	}

	public void setAeroportos(Set<Aeroporto> aeroportos) {
		this.aeroportos = aeroportos;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPeriodicidade(String periodicidade) {
		this.periodicidade = periodicidade;
	}

	public void setTempoEstimado(Double tempoEstimado) {
		this.tempoEstimado = tempoEstimado;
	}

	public void setVoosAgendados(List<VooAgendado> voosAgendados) {
		this.voosAgendados = voosAgendados;
	}

	@Override
	public String toString() {
		return descricao;
	}
}
