package models.entity;

import com.avaje.ebean.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class TaskLabel extends Model {

  public static final Finder<Long, Task> find = new Finder<>(Task.class);

  @Id @GeneratedValue public Long id;

  @ManyToOne
  @JoinColumn(name = "task_id")
  public Task task;

  @ManyToOne
  @JoinColumn(name = "label_id")
  public Label label;

  public static TaskLabel getTaskLabel(Task task, Label label) {
    TaskLabel taskLabel = new TaskLabel();
    taskLabel.setTask(task);
    taskLabel.setLabel(label);
    return taskLabel;
  }
}
