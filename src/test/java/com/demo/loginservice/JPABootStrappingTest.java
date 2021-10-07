package com.demo.loginservice;

import com.demo.loginservice.model.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Persistence;

public class JPABootStrappingTest {
	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	@Test
	public void bootstrapping() {
		log.info("... bootstrapping ...");

		var emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		var em = emf.createEntityManager();
		em.getTransaction().begin();

		var user = em.find(User.class, 1L);
		log.info(String.format("User - email = '%s'", user.getEmail()));

		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
