package models.repository;

import com.google.inject.Inject;
import java.util.List;
import models.entity.Label;
import models.entity.Member;
import models.entity.Project;
import models.entity.Task;
import models.entity.User;
import models.form.ProjectForm;

public class ProjectRepository {

  @Inject UserRepository userRepository;

  public Project store(ProjectForm projectForm, User user) {
    Project project = new Project();
    project.setName(projectForm.getProjectName());
    project.setDescription(projectForm.getDescription());
    project.getLabels().addAll(Label.basicLabel());
    project.setUser(user);
    project.save();
    return project;
  }

  public void update(Project project, ProjectForm projectForm) {
    project.setName(projectForm.getProjectName());
    project.setDescription(projectForm.getDescription());
    User user = userRepository.findById(projectForm.getChief());
    project.getMembers().add(Member.newAdmin(project.getUser()));
    project.setUser(user);
    project.getMembers().removeIf(member -> member.getUser() == user);
    project.update();
  }

  public void addMember(Project project, Member member) {
    project.getMembers().add(member);
    project.update();
  }

  public void addTask(Project project, Task task) {
    project.getTasks().add(task);
    project.update();
  }

  public void addLabel(Project project, Label label) {
    project.getLabels().add(label);
    project.update();
  }

  public void removeLabel(Project project, Long labelId) {
    project.getLabels().removeIf(label -> label.getId() == labelId);
    project.update();
  }

  public List<Project> findByUser(User user) {
    return Project.find.where().eq("user", user).findList();
  }

  public Project findById(Long projectId) {
    return Project.find.byId(projectId);
  }
}
