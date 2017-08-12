package controllers;

import com.google.inject.Inject;
import models.entity.Project;
import models.entity.Task;
import models.entity.User;
import models.form.TaskForm;
import models.repository.ProjectRepository;
import models.repository.TaskRepository;
import models.security.UserAuthenticator;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.mvc.Result;
import play.mvc.Security;

public class TaskController extends BaseController {

  @Inject ProjectRepository projectRepository;

  @Inject FormFactory formFactory;

  @Inject TaskRepository taskRepository;

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
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

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result showNewTask(Long userId, Long projectId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    Project project = projectRepository.findById(projectId);

    return ok(views.html.create_task.render(user, project));
  }

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result create(Long userId, Long projectId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    Project project = projectRepository.findById(projectId);

    TaskForm taskForm = formFactory.form(TaskForm.class).bindFromRequest().get();
    taskRepository.store(taskForm, project);

    return redirect(routes.TaskController.showProjectTasks(userId, projectId));
  }

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result showTask(Long userId, Long projectId, Long taskId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    Project project = projectRepository.findById(projectId);
    Task task = taskRepository.findById(taskId);

    return ok(views.html.edit_task.render(user, project, task));
  }

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result update(Long userId, Long projectId, Long taskId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    Task task = taskRepository.findById(taskId);

    TaskForm taskForm = formFactory.form(TaskForm.class).bindFromRequest().get();
    taskRepository.update(task, taskForm);

    return redirect(routes.TaskController.showProjectTasks(userId, projectId));
  }
}
