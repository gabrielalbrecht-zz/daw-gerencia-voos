package br.edu.ifsul.testes;

import java.util.Calendar;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.model.Aeroporto;
import br.edu.ifsul.model.Classe;
import br.edu.ifsul.model.Passagem;
import br.edu.ifsul.model.Pessoa;
import br.edu.ifsul.model.Voo;
import br.edu.ifsul.model.VooAgendado;

class PersistirPassagem {
	EntityManager em;

	@BeforeEach
	void setUp() throws Exception {
		em = EntityManagerUtil.getEntityManager();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test() {
		try {
			Passagem p = new Passagem();
			p.setBagagem(2);
			p.setPessoa(em.find(Pessoa.class, 1));
			p.setClasse(em.find(Classe.class, 1));

			VooAgendado va = new VooAgendado();
			va.setAeronave("Aeronave2");
			va.setData(Calendar.getInstance());
			va.setTotalPassageiros(450);
			va.adicionarPassagem(p);

			Voo v = new Voo();
			v.setAtivo(Boolean.TRUE);
			v.setDescricao("Descricao3");
			v.setPeriodicidade("Diário");
			v.setTempoEstimado(2.00);
			v.adicionarAeroporto(em.find(Aeroporto.class, 1));
			v.adicionarVooAgendado(va);

			em.getTransaction().begin();
			em.persist(v);
			em.persist(p);
			em.persist(va);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro: " + e);
		}
	}

}
