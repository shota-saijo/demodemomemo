package models.repository;

import com.google.inject.Inject;
import models.constant.TaskStatus;
import models.entity.*;
import models.form.TaskForm;
import play.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskRepository {

  @Inject LabelRepository labelRepository;

  @Inject MilestoneRepository milestoneRepository;

  @Inject UserRepository userRepository;

  public Task store(TaskForm taskForm, Project project) {
    Task task = new Task();
    task.setTitle(taskForm.getTitle());
    task.setContents(taskForm.getContent());
    task.setUser(userRepository.findById(taskForm.getUserId()));
    task.setProject(project);
    task.setStatus(TaskStatus.BEFORE_START);
    if (taskForm.getLabels() != null) {
      setLabel(
          task,
          Stream.of(taskForm.getLabels()).filter(id -> id != null).collect(Collectors.toList()));
    }
    if (taskForm.milestone != null) {
      task.setMilestone(milestoneRepository.findById(taskForm.milestone));
    }
    task.save();
    return task;
  }

  public void update(Task task, TaskForm taskForm) {
    task.setTitle(taskForm.getTitle());
    task.setContents(taskForm.getContent());
    task.setUser(userRepository.findById(taskForm.getUserId()));
    task.setStatus(TaskStatus.getTaskStatus(taskForm.getStatus()));
    if (taskForm.getLabels() != null) {
      setLabel(
          task,
          Stream.of(taskForm.getLabels()).filter(id -> id != null).collect(Collectors.toList()));
    }
    if (taskForm.milestone != null) {
      task.setMilestone(milestoneRepository.findById(taskForm.milestone));
    }
    task.update();
  }

  public Task findById(Long id) {
    return Task.find.byId(id);
  }

  public List<Task> findByMember(Member member) {
    return Task.find.where().eq("member", member).findList();
  }

  private void setLabel(Task task, List<Long> labelIds) {
    labelIds.forEach(id -> Logger.debug(id.toString()));
    List<Label> labels = labelRepository.findByIds(labelIds);
    List<TaskLabel> taskLabels =
        labels
            .stream()
            .map(label -> TaskLabel.getTaskLabel(task, label))
            .collect(Collectors.toList());
    task.setTaskLabels(taskLabels);
  }
}
