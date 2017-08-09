package models.entity;

import com.avaje.ebean.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comment extends Model {

  @Id @GeneratedValue public Long id;

  @ManyToOne
  @JoinColumn(name = "task_id")
  public Task task;

  @Column public String content;

  @ManyToOne
  @JoinColumn(name = "user_id")
  public User user;
}
