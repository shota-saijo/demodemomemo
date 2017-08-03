package models.entity;

import com.avaje.ebean.Model;
import lombok.Getter;
import lombok.Setter;
import models.constant.TaskStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Entity
public class Task extends Model {

  public static final Finder<Long, Task> find = new Finder<Long, Task>(Task.class);

  @Id @GeneratedValue private Long id;

  @Column private String title;

  @Column private String contents;

  @NotNull @ManyToOne private Project project;

  @OneToMany private Set<Comment> comments;

  @ManyToOne private Member member;

  @Column private TaskStatus status;
}
