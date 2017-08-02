package models.repository;

import java.util.List;

import models.entity.Member;
import models.entity.Project;

public class ProjectRepository {

	public List<Project> findByMember(Member member) {
		return Project.find.where().eq("members", member).findList();
	}
}
