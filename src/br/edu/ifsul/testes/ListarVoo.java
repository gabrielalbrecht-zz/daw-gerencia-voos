package br.edu.ifsul.testes;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.model.Voo;

class ListarVoo {
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
			List<Voo> lista = em.createQuery("from Voo order by id").getResultList();
			for (Voo v : lista) {
				System.out.println(v.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro: " + e);
		}
	}

}
