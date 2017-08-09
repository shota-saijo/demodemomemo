package controllers;

import com.google.inject.Inject;
import models.entity.Project;
import models.entity.User;
import models.form.ProjectForm;
import models.repository.ProjectRepository;
import models.security.UserAuthenticator;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.mvc.Result;
import play.mvc.Security;

public class ProjectController extends BaseController {

  @Inject FormFactory formFactory;

  @Inject ProjectRepository projectRepository;

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result showNewProject(Long userId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
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
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }
    ProjectForm projectForm = formFactory.form(ProjectForm.class).bindFromRequest().get();
    projectRepository.store(projectForm, user);
    return redirect(routes.DashboardController.showAllProjects(userId));
  }

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result showProject(Long userId, Long projectId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    Project project = projectRepository.findById(projectId);
    return ok(views.html.edit_project.render(user, project));
  }

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result update(Long userId, Long projectId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    ProjectForm projectForm = formFactory.form(ProjectForm.class).bindFromRequest().get();
    Project project = projectRepository.findById(projectId);
    projectRepository.update(project, projectForm);
    return redirect(routes.ProjectController.showProject(userId, projectId));
  }
}
