package models.entity;

import com.avaje.ebean.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comment extends Model {

  @Id @GeneratedValue() private String id;

  @ManyToOne private Task task;

  @Column private String content;
}
