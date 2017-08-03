package controllers;

import com.google.inject.Inject;
import models.constant.Constant;
import models.repository.UserRepository;
import play.mvc.Controller;

public class BaseController extends Controller {

  @Inject UserRepository userRepo;

  protected boolean isLoggedIn(Long userId) {
    return userId == Long.valueOf(session().get(Constant.LOGIN_USER_ID));
  }
}
