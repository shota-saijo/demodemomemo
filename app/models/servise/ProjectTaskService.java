package models.servise;

import com.google.inject.Inject;
import models.entity.Project;
import models.entity.Task;
import models.entity.User;
import models.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectTaskService {

  @Inject MemberRepository memberRepo;

  public List<Task> getTasksOfAllProjects(List<Project> projects) {
    return projects
        .stream()
        .flatMap(project -> project.getTasks().stream())
        .collect(Collectors.toList());
  }

  public List<Task> getUserTasks(User user) {
    return memberRepo
        .findByUser(user)
        .stream()
        .flatMap(member -> member.getTasks().stream())
        .collect(Collectors.toList());
  }
}
