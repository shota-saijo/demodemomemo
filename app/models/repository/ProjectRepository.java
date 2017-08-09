package models.repository;

import com.google.inject.Inject;
import models.constant.Constant;
import models.entity.Member;
import models.entity.Project;
import models.entity.User;
import models.form.ProjectForm;

import java.time.LocalDate;
import java.util.List;

public class ProjectRepository {

  @Inject UserRepository userRepository;

  public Project store(ProjectForm projectForm, User user) {
    Project project = new Project();
    project.setName(projectForm.getProjectName());
    project.setDescription(projectForm.getDescription());
    project.setStartDate(
        projectForm.getStartDate().isEmpty()
            ? null
            : LocalDate.parse(projectForm.getStartDate(), Constant.DATE_FORMAT));
    project.setEndDate(
        projectForm.getEndDate().isEmpty()
            ? null
            : LocalDate.parse(projectForm.getEndDate(), Constant.DATE_FORMAT));
    project.setUser(user);
    project.save();
    return project;
  }

  public void update(Project project, ProjectForm projectForm) {
    project.setName(projectForm.getProjectName());
    project.setDescription(projectForm.getDescription());
    project.setStartDate(
        projectForm.getStartDate().isEmpty()
            ? null
            : LocalDate.parse(projectForm.getStartDate(), Constant.DATE_FORMAT));
    project.setEndDate(
        projectForm.getEndDate().isEmpty()
            ? null
            : LocalDate.parse(projectForm.getEndDate(), Constant.DATE_FORMAT));
    User user = userRepository.findById(projectForm.chief);
    project.getMembers().add(Member.newAdmin(project.getUser()));
    project.setUser(user);
    project.getMembers().removeIf(member -> member.getUser() == user);
    project.update();
  }

  public void addMember(Project project, Member member) {
    project.getMembers().add(member);
    project.update();
  }

  public List<Project> findByUser(User user) {
    return Project.find.where().eq("user", user).findList();
  }

  public Project findById(Long projectId) {
    return Project.find.byId(projectId);
  }
}
