package models.entity;

import com.avaje.ebean.Model;
import lombok.Getter;
import lombok.Setter;
import models.constant.TaskStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Task extends Model {

  public static final Finder<Long, Task> find = new Finder<>(Task.class);

  @Id @GeneratedValue public Long id;

  @Column public String title;

  @Column public String contents;

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
}
