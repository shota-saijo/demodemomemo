package controllers;

import com.avaje.ebean.annotation.Transactional;
import com.google.inject.Inject;
import models.constant.MemberRole;
import models.entity.Project;
import models.entity.Task;
import models.entity.User;
import models.repository.MemberRepository;
import models.security.UserAuthenticator;
import play.Logger;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;
import java.util.stream.Collectors;

public class IndexController extends BaseController {

  @Inject MemberRepository memberRepository;

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

  /**
   * ダッシュボード画面表示
   *
   * @return ダッシュボード画面
   */
  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result showDashboard(Long userId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepo.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    List<Project> adminProjects =
        memberRepository
            .findByUser(user)
            .stream()
            .filter(member -> member.getRole().equals(MemberRole.ADMINISTRATOR))
            .map(admin -> admin.getProject())
            .collect(Collectors.toList());
    ;
    List<Project> publicProjects =
        memberRepository
            .findByUser(user)
            .stream()
            .filter(member -> member.getRole().equals(MemberRole.PUBLIC))
            .map(admin -> admin.getProject())
            .collect(Collectors.toList());
    ;
    List<Task> tasks =
        memberRepository
            .findByUser(user)
            .stream()
            .flatMap(member -> member.getTasks().stream())
            .collect(Collectors.toList());

    adminProjects.forEach(p -> Logger.debug(p.getName()));
    adminProjects.forEach(p -> Logger.debug(p.getTasks() == null ? "is null" : "is not null"));
    publicProjects.forEach(p -> Logger.debug(p.getTasks() == null ? "is null" : "is not null"));

    return ok(views.html.dashboard.render(adminProjects, publicProjects, tasks, user.getId()));
  }
}
