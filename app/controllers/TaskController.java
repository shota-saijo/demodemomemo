package controllers;

import com.google.inject.Inject;
import models.entity.Project;
import models.entity.User;
import models.repository.ProjectRepository;
import play.db.ebean.Transactional;
import play.mvc.Result;

public class TaskController extends BaseController {

  @Inject ProjectRepository projectRepository;

  @Transactional
  public Result showProjectTasks(Long userId, Long projectId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    Project project = projectRepository.findById(projectId);
    return ok(views.html.tasks_project.render(user, project));
  }
}
