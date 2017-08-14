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
    if (taskForm.getMilestone() != null) {
      task.setMilestone(milestoneRepository.findById(taskForm.getMilestone()));
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
    if (taskForm.getMilestone() != null) {
      task.setMilestone(milestoneRepository.findById(taskForm.getMilestone()));
    }
    task.update();
  }

  public void AddComment(Task task, String content, User user) {
    task.getComments().add(Comment.newInstance(content, user));
    task.update();
  }

  public void updateComment(Task task, String content, Long commentId) {
    task.getComments()
        .stream()
        .filter(comment -> comment.getId() == commentId)
        .findFirst()
        .orElseThrow(IllegalArgumentException::new)
        .setContent(content);
    task.update();
  }

  public void removeComment(Task task, Long commentId) {
    task.getComments()
        .stream()
        .filter(comment -> comment.getId() == commentId)
        .findFirst()
        .orElseThrow(IllegalArgumentException::new)
        .setClosed(true);
    task.update();
  }

  public Task findById(Long id) {
    return Task.find.byId(id);
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
