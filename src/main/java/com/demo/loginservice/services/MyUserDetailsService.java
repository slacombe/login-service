package com.demo.loginservice.services;

import com.demo.loginservice.store.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	UserStore userStore;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = userStore.getUserByEmail(username);

		return user.map(u -> new User(u.getEmail(), u.getPassword(), new ArrayList<>()))
				.orElseThrow(() -> new UsernameNotFoundException(""));
	}
}
