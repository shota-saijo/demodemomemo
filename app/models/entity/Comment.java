package models.entity;

import com.avaje.ebean.Model;

import javax.persistence.*;

@Entity
public class Comment extends Model {

  @Id @GeneratedValue private Long id;

  @ManyToOne
  @JoinColumn(name = "task_id")
  private Task task;

  @Column private String content;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column private boolean isClosed;

  public static Comment newInstance(String content, User user) {
    Comment comment = new Comment();
    comment.setContent(content);
    comment.setUser(user);
    comment.setClosed(false);
    return comment;
  }

  /** Getter and Setter */
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public boolean isClosed() {
    return isClosed;
  }

  public void setClosed(boolean closed) {
    isClosed = closed;
  }
}
