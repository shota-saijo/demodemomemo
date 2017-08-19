package models.entity;

import com.avaje.ebean.Model;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import play.data.format.Formats;

@Entity
public class Milestone extends Model {

  public static final Finder<Long, Milestone> find = new Finder<>(Milestone.class);

  @Id @GeneratedValue public Long id;

  @Column private String name;

  @Column
  @Formats.DateTime(pattern = "yyyy-MM-dd")
  public LocalDate startDate;

  @Column
  @Formats.DateTime(pattern = "yyyy-MM-dd")
  public LocalDate endDate;

  @ManyToOne
  @JoinColumn(name = "project_id")
  public Project project;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "milestone", fetch = FetchType.EAGER)
  private List<Task> tasks;

  public static Milestone newInstance(String name, LocalDate start, LocalDate end) {
    Milestone milestone = new Milestone();
    milestone.setName(name);
    milestone.setStartDate(start);
    milestone.setEndDate(end);
    return milestone;
  };

  /** Getter and Setter */
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }
}
