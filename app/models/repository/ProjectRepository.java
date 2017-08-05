package models.repository;

import models.constant.Constant;
import models.constant.MemberRole;
import models.entity.Member;
import models.entity.Project;
import models.entity.User;
import models.form.ProjectForm;

import java.time.LocalDate;

public class ProjectRepository {

  public Project store(ProjectForm projectForm) {
    Project project = new Project();
    project.setName(projectForm.getProjectName());
    project.setDescription(projectForm.getDescription());
    if (!projectForm.getStartDate().isEmpty()) {
      project.setStartDate(LocalDate.parse(projectForm.getStartDate(), Constant.DATE_FORMAT));
    }
    if (!projectForm.getEndDate().isEmpty()) {
      project.setEndDate(LocalDate.parse(projectForm.getEndDate(), Constant.DATE_FORMAT));
    }
    project.save();
    return project;
  }

  public Project findById(Long projectId) {
    return Project.find.byId(projectId);
  }

}
