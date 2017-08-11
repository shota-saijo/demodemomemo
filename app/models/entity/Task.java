package models.entity;

import com.avaje.ebean.Model;
import lombok.Getter;
import lombok.Setter;
import models.constant.TaskStatus;
import play.data.format.Formats;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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

  @Column
  @Formats.DateTime(pattern = "yyyy-MM-dd")
  public LocalDate startDate;

  @Column
  @Formats.DateTime(pattern = "yyyy-MM-dd")
  public LocalDate endDate;

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
  public List<Label> labels = new ArrayList<>();
}
