package models.repository;

import java.util.List;

import models.entity.Member;
import models.entity.Task;

public class TaskRepository {

	public List<Task> findByMember(Member member) {
		return Task.find.where().eq("member", member).findList();
	}
	
}
