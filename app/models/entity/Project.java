package models.entity;

import com.avaje.ebean.Model;
import lombok.Getter;
import lombok.Setter;
import play.data.format.Formats;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Project extends Model {

  public static final Finder<Long, Project> find = new Finder<>(Project.class);

  @Id @GeneratedValue public Long id;

  @NotNull @Column public String name;

  @Column public String description;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "user_id")
  public User user;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "project", fetch = FetchType.EAGER)
  public List<Milestone> milestones = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "project", fetch = FetchType.EAGER)
  public List<Label> labels = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "project", fetch = FetchType.EAGER)
  public List<Member> members = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "project", fetch = FetchType.EAGER)
  public List<Task> tasks = new ArrayList<>();
}
