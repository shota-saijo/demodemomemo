package controllers;

import com.avaje.ebean.annotation.Transactional;
import com.google.inject.Inject;
import models.entity.Project;
import models.entity.User;
import models.form.ProjectForm;
import models.repository.MemberRepository;
import models.repository.ProjectRepository;
import models.security.UserAuthenticator;
import play.data.FormFactory;
import play.mvc.Result;
import play.mvc.Security;

public class ProjectController extends BaseController {

  @Inject FormFactory formFactory;

  @Inject ProjectRepository projectRepository;

  @Inject MemberRepository memberRepository;

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result newProject(Long userId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepo.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }
    return ok(views.html.create_project.render(user));
  }

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result create(Long userId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepo.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }
    ProjectForm projectForm = formFactory.form(ProjectForm.class).bindFromRequest().get();
    Project project = projectRepository.store(projectForm);
    memberRepository.storeAdmin(project, user);
    return redirect(routes.IndexController.showDashboard(userId));
  }

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result showProject(Long userId, Long projectId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepo.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    Project project = projectRepository.findById(projectId);
    return ok(views.html.project.render(project));
  }
}
