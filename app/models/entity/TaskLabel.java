package models.entity;

import com.avaje.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TaskLabel extends Model {

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

  /** Getter and Setter */
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }

  public Label getLabel() {
    return label;
  }

  public void setLabel(Label label) {
    this.label = label;
  }
}
