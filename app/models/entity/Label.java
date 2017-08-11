package models.entity;

import com.avaje.ebean.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Label extends Model{

    @Id
    @GeneratedValue
    public Long id;

    @Column
    public String content;

    @ManyToOne
    @JoinColumn(name = "project_id")
    public Project project;

    @ManyToOne
    @JoinColumn(name = "task_id")
    public Task task;
}
