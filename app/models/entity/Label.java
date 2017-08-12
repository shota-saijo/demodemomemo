package models.entity;

import com.avaje.ebean.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter
@Setter
public class Label extends Model{

    public static final Finder<Long, Label> find = new Finder<>(Label.class);

    @Id
    @GeneratedValue
    public Long id;

    @Column
    public String content;

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
}
