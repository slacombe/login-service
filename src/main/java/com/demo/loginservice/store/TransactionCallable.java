package com.demo.loginservice.store;

import org.hibernate.Session;

@FunctionalInterface
public interface TransactionCallable {
	void execute(Session session);
}
