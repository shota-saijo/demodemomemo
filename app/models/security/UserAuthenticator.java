package models.security;

import controllers.routes;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

public class UserAuthenticator extends Security.Authenticator {

	@Override
	public String getUsername(Http.Context ctx) {
		return ctx.session().get("loginUserId");
	}

	@Override
	public Result onUnauthorized(Http.Context ctx) {
		return redirect(routes.IndexController.index());
	}
}
