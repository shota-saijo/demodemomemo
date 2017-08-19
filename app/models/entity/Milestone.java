package models.entity;

import com.avaje.ebean.Model;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import play.data.format.Formats;

@Entity
public class Milestone extends Model {

  public static final Finder<Long, Milestone> find = new Finder<>(Milestone.class);

  @Id @GeneratedValue public Long id;

  @ManyToOne
  @JoinColumn(name = "project_id")
  public Project project;

  @Column
  @Formats.DateTime(pattern = "yyyy-MM-dd")
  public LocalDate startDate;

  @Column
  @Formats.DateTime(pattern = "yyyy-MM-dd")
  public LocalDate endDate;

  /** Getter and Setter */
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
}
