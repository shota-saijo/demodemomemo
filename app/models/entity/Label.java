package models.entity;

import com.avaje.ebean.Model;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import models.constant.Color;
import scala.collection.generic.HasNewBuilder;

@Entity
public class Label extends Model {

  public static final Finder<Long, Label> find = new Finder<>(Label.class);

  @Id @GeneratedValue public Long id;

  @Column public String content;

  @Column private Color color;

  @ManyToOne
  @JoinColumn(name = "project_id")
  public Project project;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "label", fetch = FetchType.EAGER)
  public List<TaskLabel> taskLabels;

  public static List<Label> basicLabel() {
    Map<String, Color> map = new HashMap<>();
    map.put("bug", Color.DANGER);
    map.put("enhancement", Color.PRIMARY);
    map.put("improvement", Color.SUCCESS);
    map.put("help", Color.WARNING);
    map.put("duplicate", Color.DEFAULT);
    map.put("question", Color.INFO);

    return map.keySet().stream().map(content -> newInstance(content, map.get(content))).collect(Collectors.toList());
  }

  public static Label newInstance(String content, Color color) {
    Label label = new Label();
    label.setContent(content);
    label.setColor(color);
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

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }
}
