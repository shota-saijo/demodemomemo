package models.repository;

import models.constant.MemberRole;
import models.entity.Member;
import models.entity.Project;
import models.entity.User;

import java.util.List;

public class MemberRepository {

  public Member storeAdmin(Project project, User user) {
    Member member = new Member();
    member.setProject(project);
    member.setUser(user);
    member.setRole(MemberRole.ADMINISTRATOR);
    member.save();
    return member;
  }

  public Member storePublic(Project project, User user) {
    Member member = new Member();
    member.setProject(project);
    member.setUser(user);
    member.setRole(MemberRole.PUBLIC);
    member.save();
    return member;
  }

  public List<Member> findByUser(User user) {
    return Member.find.where().eq("user", user).findList();
  }
}
