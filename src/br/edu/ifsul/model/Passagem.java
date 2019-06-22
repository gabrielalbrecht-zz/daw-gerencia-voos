package br.edu.ifsul.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

import org.hibernate.annotations.ForeignKey;

@SuppressWarnings("serial")
@Entity
@Table(name = "passagem")
public class Passagem implements Serializable {

	@Id
	@SequenceGenerator(name = "seq_passagem", sequenceName = "seq_passagem_id", allocationSize = 1)
	@GeneratedValue(generator = "seq_passagem", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_compra", nullable = false)
	private Calendar dataCompra;

	@Min(value = 0, message = "A bagagem não pode ser negativa!")
	@Column(name = "bagagem", nullable = true)
	private Integer bagagem;

	@ManyToOne
	@JoinColumn(name = "vooAgendado", referencedColumnName = "id", nullable = false)
	@ForeignKey(name = "fk_voo_agendado")
	private VooAgendado vooAgendado;

	@ManyToOne
	@JoinColumn(name = "pessoa", referencedColumnName = "id", nullable = false)
	@ForeignKey(name = "fk_pessoa")
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name = "classe", referencedColumnName = "id", nullable = false)
	@ForeignKey(name = "fk_classe")
	private Classe classe;

	public Passagem() {
		this.dataCompra = Calendar.getInstance();
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
		Passagem other = (Passagem) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public Integer getBagagem() {
		return bagagem;
	}

	public Classe getClasse() {
		return classe;
	}

	public Calendar getDataCompra() {
		return dataCompra;
	}

	public String getDataCompraAsString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataString = sdf.format(dataCompra.getTime());
		return dataString;
	}

	public Integer getId() {
		return id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public VooAgendado getVooAgendado() {
		return vooAgendado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setBagagem(Integer bagagem) {
		this.bagagem = bagagem;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setVooAgendado(VooAgendado vooAgendado) {
		this.vooAgendado = vooAgendado;
	}

	@Override
	public String toString() {
		String ret = vooAgendado.toString().concat(" - Bagagem: ".concat(bagagem.toString()));
		return ret;
	}
}
