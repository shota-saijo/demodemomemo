package models.repository;

import models.entity.Member;
import models.entity.Project;

import java.util.List;

public class ProjectRepository {

  public List<Project> findByMember(Member member) {
    return Project.find.where().eq("members", member).findList();
  }
}
