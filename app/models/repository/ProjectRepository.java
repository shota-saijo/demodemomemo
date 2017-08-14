package models.repository;

import com.google.inject.Inject;
import models.constant.Constant;
import models.entity.*;
import models.form.ProjectForm;

import java.time.LocalDate;
import java.util.List;

public class ProjectRepository {

  @Inject UserRepository userRepository;

  public Project store(ProjectForm projectForm, User user) {
    Project project = new Project();
    project.setName(projectForm.getProjectName());
    project.setDescription(projectForm.getDescription());
    project.getLabels().addAll(Label.basicLabel("bug", "enhancement", "improvement", "help", "duplicate", "question"));
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

  public List<Project> findByUser(User user) {
    return Project.find.where().eq("user", user).findList();
  }

  public Project findById(Long projectId) {
    return Project.find.byId(projectId);
  }
}
