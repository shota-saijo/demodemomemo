package controllers;

import play.mvc.Result;

public class IndexController extends BaseController {

  /**
   * 初期画面表示
   *
   * @return 初期画面表示
   */
  public Result showIndex() {
    return ok(views.html.index.render());
  }

  /**
   * サインアップ画面表示
   *
   * @return サインアップ画面
   */
  public Result showSignUp() {
    return ok(views.html.signup.render());
  }

  /**
   * サインイン画面表示
   *
   * @return サインイン画面
   */
  public Result showSignIn() {
    return ok(views.html.signin.render());
  }
}
