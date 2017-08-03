package models.repository;

import models.entity.Member;
import models.entity.Task;

import java.util.List;

public class TaskRepository {

  public List<Task> findByMember(Member member) {
    return Task.find.where().eq("member", member).findList();
  }
}
