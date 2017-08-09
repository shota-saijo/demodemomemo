package controllers;

import com.google.inject.Inject;
import models.constant.Constant;
import models.entity.User;
import models.form.UserForm;
import models.repository.UserRepository;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.mvc.Result;

public class UserController extends BaseController {

  @Inject FormFactory formFactory;

  @Transactional
  public Result createUser() {
    UserForm userForm = formFactory.form(UserForm.class).bindFromRequest().get();
    User user = userRepository.findByAccountId(userForm.getAccountId());
    if (user != null) {
      flash("message", "アカウントID【" + userForm.getAccountId() + "】は使用されています。");
      return redirect(routes.IndexController.showSignUp());
    }
    User loginUser = userRepository.store(userForm);
    setSession(loginUser.getId());
    return redirect(routes.DashboardController.showAllProjects(loginUser.getId()));
  }

  @Transactional
  public Result login() {
    UserForm userForm = formFactory.form(UserForm.class).bindFromRequest().get();
    User user = userRepository.findLoginUser(userForm.getAccountId(), userForm.getPassword());
    if (user == null) {
      flash("message", "アカウントIDまたはパスワードが間違っています。");
      return redirect(routes.IndexController.showSignIn());
    }
    setSession(user.getId());
    return redirect(routes.DashboardController.showAllProjects(user.getId()));
  }

  public Result logout() {
    clearSession();
    return redirect(routes.IndexController.showSignIn());
  }

  private void setSession(Long userId) {
    session(Constant.LOGIN_USER_ID, String.valueOf(userId));
  }

  private void clearSession() {
    session().clear();
  }
}
