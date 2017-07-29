package controllers;

import models.security.UserAuthenticator;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

public class IndexController extends Controller {

	/**
	 * 初期画面表示
	 * 
	 * @return 初期画面表示
	 */
	public Result index() {
		return ok(views.html.index.render());
	}

	/**
	 * サインアップ画面表示
	 * 
	 * @return サインアップ画面
	 */
	public Result signUp() {
		return ok(views.html.signup.render());
	}

	/**
	 * サインイン画面表示
	 * 
	 * @return サインイン画面
	 */
	public Result signIn() {
		return ok(views.html.signin.render());
	}

	/**
	 * ダッシュボード画面表示
	 * 
	 * @return ダッシュボード画面
	 */
	@Security.Authenticated(UserAuthenticator.class)
	public Result dashboard() {
		return ok(views.html.dashboard.render());
	}
}
