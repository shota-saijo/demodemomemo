package models.repository;

import models.entity.User;
import play.db.jpa.JPA;

public class UserRepository {

	public User store() {
		User user = new User();
		user.setAccountId("saijo");
		user.setPassword("ooska");
		JPA.em().persist(user);
		return user;
	}

}
