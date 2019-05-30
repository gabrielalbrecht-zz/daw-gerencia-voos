package br.edu.ifsul.testes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.model.Aeroporto;
import br.edu.ifsul.model.Voo;
import br.edu.ifsul.model.VooAgendado;

class PersistirVooAgendado {
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
			VooAgendado va = new VooAgendado();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dtVoo = sdf.parse("28/04/2019");
			Calendar dtC = Calendar.getInstance();
			dtC.setTime(dtVoo);

			va.setAeronave("Aeronave1");
			va.setData(dtC);
			va.setTotalPassageiros(450);

			Voo v = new Voo();
			v.setAtivo(Boolean.TRUE);
			v.setDescricao("Descricao2");
			v.setPeriodicidade("Diário");
			v.setTempoEstimado(2.00);
			v.adicionarAeroporto(em.find(Aeroporto.class, 1));
			v.adicionarVooAgendado(va);

			em.getTransaction().begin();
			em.persist(v);
			em.persist(va);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro: " + e);
		}
	}

}
