package com.demo.loginservice.store;

import com.demo.loginservice.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

@Service
public class UserStore {
	private final EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;

	public UserStore() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}

	public void inTransaction(TransactionCallable callback) {
		try {
			this.entityManager.getTransaction().begin();

			var session = this.entityManager.unwrap(Session.class);

			callback.execute(session);

			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
		}
	}

	public boolean emailExists(String email) {
		return getUserByEmail(email).isPresent();
	}

	public Optional<User> registerUser(User user) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(user);
			entityManager.getTransaction().commit();

			return Optional.of(user);
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
	}

	public Optional<User> getUserByEmail(String email) {
		var users = entityManager.createQuery("SELECT u from User u WHERE u.email = :email")
				.setParameter("email", email)
				.getResultList();
		return users.size() > 0 ? Optional.of((User) users.get(0)) : Optional.empty();
	}
}
