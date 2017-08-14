package models.entity;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class Label extends Model {

  public static final Finder<Long, Label> find = new Finder<>(Label.class);

  @Id @GeneratedValue public Long id;

  @Column public String content;

  @ManyToOne
  @JoinColumn(name = "project_id")
  public Project project;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "label", fetch = FetchType.EAGER)
  public List<TaskLabel> taskLabels;

  public static List<Label> basicLabel(String... contents) {
    return Stream.of(contents).map(Label::newInstance).collect(Collectors.toList());
  }

  public static Label newInstance(String content) {
    Label label = new Label();
    label.setContent(content);
    return label;
  }

  /** Getter and Setter */
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public List<TaskLabel> getTaskLabels() {
    return taskLabels;
  }

  public void setTaskLabels(List<TaskLabel> taskLabels) {
    this.taskLabels = taskLabels;
  }
}
