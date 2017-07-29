package models.repository;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.mindrot.jbcrypt.BCrypt;

import models.entity.User;
import models.form.UserForm;
import play.db.jpa.JPA;

public class UserRepository {

	public User store(UserForm userForm) {
		User user = new User();
		user.setAccountId(userForm.getAccountId());
		user.setPassword(BCrypt.hashpw(userForm.getPassword(), BCrypt.gensalt()));
		JPA.em().persist(user);
		return user;
	}

	public User findByAccountId(String accountId) {
		CriteriaBuilder builder = JPA.em().getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root);
		query.where(builder.equal(root.get("accountId"), accountId));
		TypedQuery<User> tq = JPA.em().createQuery(query);
		List<User> users = tq.getResultList();
		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}

	public User findLoginUser(String accountId, String password) {
		CriteriaBuilder builder = JPA.em().getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root);
		query.where(builder.equal(root.get("accountId"), accountId));
		TypedQuery<User> tq = JPA.em().createQuery(query);
		List<User> users = tq.getResultList();
		if (users.isEmpty()) {
			return null;
		} else {
			return BCrypt.checkpw(password, users.get(0).getPassword()) ? users.get(0) : null;
		}
	}

}
