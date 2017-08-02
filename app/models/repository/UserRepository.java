package models.repository;

import org.mindrot.jbcrypt.BCrypt;

import models.entity.User;
import models.form.UserForm;

public class UserRepository {

	public User store(UserForm userForm) {
		User user = new User();
		user.setAccountId(userForm.getAccountId());
		user.setPassword(BCrypt.hashpw(userForm.getPassword(), BCrypt.gensalt()));
		user.save();
		return user;
	}

	public User findById(Long id) {
		return User.find.byId(id);
	}
	
	public User findByAccountId(String accountId) {
		return User.find.where().eq("accountId", accountId).findUnique();
	}

	public User findLoginUser(String accountId, String password) {
		User user = findByAccountId(accountId);
		if(user == null) {
			return null;
		}
		return BCrypt.checkpw(password, user.getPassword()) ? user : null;
	}

}
