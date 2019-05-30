package br.edu.ifsul.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "voo_agendado")
public class VooAgendado implements Serializable {

	@Id
	@SequenceGenerator(name = "seq_voo_agendado", sequenceName = "seq_voo_agendado_id", allocationSize = 1)
	@GeneratedValue(generator = "seq_voo_agendado", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@NotBlank(message = "Campo aeronave é obrigatório.")
	@Length(max = 30, message = "A aeronave deve conter até {max} caracteres.")
	@Column(name = "aeronave", nullable = false, length = 30)
	private String aeronave;

	@Min(value = 0, message = "O total de passageiros não pode ser negativo!")
	@Column(name = "total_passageiros", nullable = true)
	private Integer totalPassageiros;

	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)
	private Calendar data;

	@ManyToOne
	@JoinColumn(name = "voo", referencedColumnName = "id", nullable = false)
	@ForeignKey(name = "fk_voo")
	private Voo voo;

	@OneToMany(mappedBy = "vooAgendado", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Passagem> passagens = new ArrayList<>();

	public VooAgendado() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAeronave() {
		return aeronave;
	}

	public void setAeronave(String aeronave) {
		this.aeronave = aeronave;
	}

	public Integer getTotalPassageiros() {
		return totalPassageiros;
	}

	public void setTotalPassageiros(Integer totalPassageiros) {
		this.totalPassageiros = totalPassageiros;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Voo getVoo() {
		return voo;
	}

	public void setVoo(Voo voo) {
		this.voo = voo;
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
		VooAgendado other = (VooAgendado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void adicionarPassagem(Passagem p) {
		passagens.add(p);
		p.setVooAgendado(this);
	}

	public void removerPassagem(Passagem p) {
		passagens.remove(p);
	}

	public void removerPassagem(int index) {
		passagens.remove(index);
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataString = sdf.format(data.getTime());
		return dataString.concat(" - ".concat(aeronave.toString()));
	}
}
