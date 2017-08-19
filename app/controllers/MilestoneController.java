package controllers;

import com.google.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import models.entity.Milestone;
import models.entity.Project;
import models.entity.User;
import models.form.MilestoneForm;
import models.repository.ProjectRepository;
import play.Logger;
import play.data.FormFactory;
import play.mvc.Result;

public class MilestoneController extends BaseController {

  @Inject ProjectRepository projectRepository;

  @Inject FormFactory formFactory;

  public Result show(Long userId, Long projectId) {
    User user = userRepository.findById(userId);
    Project project = projectRepository.findById(projectId);
    List<MilestoneForm> milestoneForms =
        project
            .getMilestones()
            .stream()
            .map(MilestoneForm::newInstance)
            .collect(Collectors.toList());
    milestoneForms.stream().map(milestoneForm -> milestoneForm.getProgress()).forEach(Logger::debug);
    return ok(views.html.milestone.render(user, project, milestoneForms));
  }

  public Result create(Long userId, Long projectId) {
    Project project = projectRepository.findById(projectId);
    MilestoneForm milestoneForm = formFactory.form(MilestoneForm.class).bindFromRequest().get();
    projectRepository.addMilestone(
        project,
        Milestone.newInstance(
            milestoneForm.getName(), milestoneForm.getStart(), milestoneForm.getEnd()));
    return redirect(routes.MilestoneController.show(userId, projectId));
  }

  public Result remove(Long userId, Long projectId, Long milestoneId) {
    Project project = projectRepository.findById(projectId);
    projectRepository.removeMilestone(project, milestoneId);
    return redirect(routes.MilestoneController.show(userId, projectId));
  }
}
