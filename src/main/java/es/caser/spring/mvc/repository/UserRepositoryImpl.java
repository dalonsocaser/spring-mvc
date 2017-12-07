package es.caser.spring.mvc.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import es.caser.spring.mvc.model.User;

@Repository
public class UserRepositoryImpl implements IUserRepository{
	private List<User> users=new ArrayList<>();
	@Override
	public User save(User user) {
		users.add(user);
		return user;
	}
	@Override
	public User findByUsername(String username) {
		Optional<User> matchingObject = users.stream().
			    filter(p -> p.getUsername().equals(username)).
			    findFirst();
		return matchingObject.orElse(null);
	}

}
