package models.entity;

import com.avaje.ebean.Model;
import lombok.Getter;
import lombok.Setter;
import play.data.format.Formats;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
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
}
