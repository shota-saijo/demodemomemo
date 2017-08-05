package models.entity;

import com.avaje.ebean.Model;
import lombok.Getter;
import lombok.Setter;
import models.constant.MemberRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Member extends Model {

  public static final Finder<Long, Member> find = new Finder<>(Member.class);

  @Id @GeneratedValue public Long id;

  @ManyToOne
  @JoinColumn(name = "project_id")
  public Project project;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "user_id")
  public User user;

  @Column public MemberRole role;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "member", fetch = FetchType.EAGER)
  public List<Task> tasks = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "creator", fetch = FetchType.EAGER)
  public List<Comment> comments = new ArrayList<>();
}
