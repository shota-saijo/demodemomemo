package models.security;

import controllers.routes;
import models.constant.Constant;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

public class UserAuthenticator extends Security.Authenticator {

  @Override
  public String getUsername(Http.Context ctx) {
    return ctx.session().get(Constant.LOGIN_USER_ID);
  }

  @Override
  public Result onUnauthorized(Http.Context ctx) {
    return redirect(routes.IndexController.index());
  }
}
