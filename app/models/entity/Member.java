package models.entity;

import com.avaje.ebean.Model;
import lombok.Getter;
import lombok.Setter;
import models.constant.MemberRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
public class Member extends Model {

  public static final Finder<Long, Member> find = new Finder<Long, Member>(Member.class);

  @Id @GeneratedValue() private String id;

  @NotNull @ManyToOne private Project project;

  @NotNull @ManyToOne private User user;

  @Column private MemberRole role;

  @OneToMany private List<Task> tasks;
}
