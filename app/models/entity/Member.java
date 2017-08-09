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

  public static Member newAdmin(User user) {
    Member member = new Member();
    member.setUser(user);
    member.setRole(MemberRole.ADMINISTRATOR);
    return member;
  }

  public static Member newPublic(User user) {
    Member member = new Member();
    member.setUser(user);
    member.setRole(MemberRole.PUBLIC);
    return member;
  }
}
