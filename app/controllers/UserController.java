package controllers;

import com.google.inject.Inject;

import models.entity.User;
import models.form.UserForm;
import models.repository.UserRepository;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

public class UserController extends Controller {

	@Inject
	UserRepository userRepo;

	@Inject
	FormFactory formFactory;

	@Transactional
	public Result create() {
		UserForm userForm = formFactory.form(UserForm.class).bindFromRequest().get();
		User user = userRepo.findByAccountId(userForm.getAccountId());
		if (user != null) {
			flash("message", "アカウントID【" + userForm.getAccountId() + "】は使用されています。");
			return redirect(routes.IndexController.signUp());
		}
		User loginUser = userRepo.store(userForm);
		setSession(loginUser.getId());
		return redirect(routes.IndexController.dashboard());
	}

	@Transactional(readOnly = true)
	public Result login() {
		UserForm userForm = formFactory.form(UserForm.class).bindFromRequest().get();
		User user = userRepo.findLoginUser(userForm.getAccountId(), userForm.getPassword());
		if (user == null) {
			flash("message", "アカウントIDまたはパスワードが間違っています。");
			return redirect(routes.IndexController.signIn());
		}
		setSession(user.getId());
		return redirect(routes.IndexController.dashboard());
	}

	public Result logout() {
		clearSession();
		return redirect(routes.IndexController.signIn());
	}

	private void setSession(String userId) {
		session("loginUserId", userId);
	}

	private void clearSession() {
		session().clear();
	}
}
