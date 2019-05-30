package br.edu.ifsul.testes;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.model.Aeroporto;
import br.edu.ifsul.model.Cidade;
import br.edu.ifsul.model.Voo;

class PersistirVoo {
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
			Voo v = new Voo();
			v.setAtivo(Boolean.TRUE);
			v.setDescricao("Descricao do Voo1");
			v.setPeriodicidade("Diário");
			v.setTempoEstimado(3.00);

			Aeroporto a = new Aeroporto();
			a.setCidade(em.find(Cidade.class, 1));
			a.setNome("Aeroporto2");
			a.setOperacaoNoturna(Boolean.TRUE);

			v.adicionarAeroporto(a);

			em.getTransaction().begin();
			em.persist(a);
			em.persist(v);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro: " + e);
		}
	}

}
