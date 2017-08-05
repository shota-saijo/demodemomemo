package models.entity;

import com.avaje.ebean.Model;
import lombok.Getter;
import lombok.Setter;
import models.constant.TaskStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

  @ManyToOne
  @JoinColumn(name = "project_id")
  public Project project;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "task", fetch = FetchType.EAGER)
  public List<Comment> comments = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "member_id")
  public Member member;

  @Column public TaskStatus status;
}
