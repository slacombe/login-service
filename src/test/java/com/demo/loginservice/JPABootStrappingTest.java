package com.demo.loginservice;

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

		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
