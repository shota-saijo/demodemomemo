package controllers;

import com.google.inject.Inject;
import java.util.List;
import models.entity.Label;
import models.entity.Project;
import models.entity.User;
import models.form.LabelForm;
import models.repository.ProjectRepository;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Result;

public class LabelController extends BaseController {

  @Inject ProjectRepository projectRepository;

  @Inject FormFactory formFactory;

  public Result show(Long userId, Long projectId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }
    Project project = projectRepository.findById(projectId);
    return ok(views.html.label.render(user, project));
  }

  public Result create(Long userId, Long projectId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    Project project = projectRepository.findById(projectId);
    LabelForm labelForm = formFactory.form(LabelForm.class).bindFromRequest().get();
    projectRepository.addLabel(project, Label.newInstance(labelForm.getContent(), labelForm.getColor()));
    return redirect(routes.LabelController.show(userId, projectId));
  }

  public Result remove(Long userId, Long projectId, Long labelId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    Project project = projectRepository.findById(projectId);
    projectRepository.removeLabel(project, labelId);
    return redirect(routes.LabelController.show(userId, projectId));
  }
}
