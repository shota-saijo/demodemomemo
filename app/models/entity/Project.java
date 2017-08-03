package models.entity;

import com.avaje.ebean.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Project extends Model {

  public static final Finder<Long, Project> find = new Finder<Long, Project>(Project.class);

  @Id @GeneratedValue private Long id;

  @NotNull @Column private String name;

  @Column private String description;

  @OneToMany private Set<Member> members;

  @OneToMany private List<Task> tasks;
}
