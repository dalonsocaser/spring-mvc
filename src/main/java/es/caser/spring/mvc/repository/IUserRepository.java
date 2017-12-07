package es.caser.spring.mvc.repository;

import es.caser.spring.mvc.model.User;

public interface IUserRepository {
	User save (User user);

	User findByUsername(String username);
}
