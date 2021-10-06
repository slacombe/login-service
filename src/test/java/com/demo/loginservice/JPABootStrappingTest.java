package com.demo.loginservice.data;

import com.demo.loginservice.model.User;
import org.junit.jupiter.api.Test;

import javax.persistence.Persistence;
import java.util.logging.Logger;

public class JPABootStrappingTest {
	Logger log = Logger.getLogger(this.getClass().getName());

	@Test
	public void bootstrapping() {
		log.info("... bootstrapping ...");

		var emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		var em = emf.createEntityManager();
		em.getTransaction().begin();

		em.find(User.class, 1L);

		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
