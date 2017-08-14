package controllers;

import com.google.inject.Inject;
import models.entity.Task;
import models.entity.User;
import models.repository.TaskRepository;
import models.security.UserAuthenticator;
import play.Logger;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.mvc.Result;
import play.mvc.Security;

public class CommentController extends BaseController {

  @Inject TaskRepository taskRepository;

  @Inject FormFactory formFactory;

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result create(Long userId, Long projectId, Long taskId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    String comment = formFactory.form().bindFromRequest().get("new_comment");

    Task task = taskRepository.findById(taskId);
    taskRepository.AddComment(task, comment, user);

    return redirect(routes.TaskController.showTask(userId, projectId, taskId));
  }

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result update(Long userId, Long projectId, Long taskId, Long commentId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    String comment = formFactory.form().bindFromRequest().get("comment");

    Logger.debug(comment);

    Task task = taskRepository.findById(taskId);
    taskRepository.updateComment(task, comment, commentId);

    return redirect(routes.TaskController.showTask(userId, projectId, taskId));
  }

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result remove(Long userId, Long projectId, Long taskId, Long commentId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    Task task = taskRepository.findById(taskId);
    taskRepository.removeComment(task, commentId);

    return redirect(routes.TaskController.showTask(userId, projectId, taskId));
  }
}
