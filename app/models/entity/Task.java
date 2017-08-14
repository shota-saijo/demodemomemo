package models.entity;

import com.avaje.ebean.Model;
import models.constant.TaskStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Task extends Model {

  public static final Finder<Long, Task> find = new Finder<>(Task.class);

  @Id @GeneratedValue public Long id;

  @Column public String title;

  @Column(length = 255 * 4)
  public String contents;

  @Column public TaskStatus status;

  @ManyToOne
  @JoinColumn(name = "project_id")
  public Project project;

  @ManyToOne
  @JoinColumn(name = "user_id")
  public User user;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "task", fetch = FetchType.EAGER)
  public List<Comment> comments = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "task", fetch = FetchType.EAGER)
  public List<TaskLabel> taskLabels = new ArrayList<>();

  @OneToOne(cascade = CascadeType.ALL)
  public Milestone milestone;

  /** Getter and Setter */
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public List<TaskLabel> getTaskLabels() {
    return taskLabels;
  }

  public void setTaskLabels(List<TaskLabel> taskLabels) {
    this.taskLabels = taskLabels;
  }

  public Milestone getMilestone() {
    return milestone;
  }

  public void setMilestone(Milestone milestone) {
    this.milestone = milestone;
  }
}
