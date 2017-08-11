package models.repository;

import com.google.inject.Inject;
import models.constant.MemberRole;
import models.entity.Member;
import models.entity.Project;
import models.entity.User;

import java.util.List;

import static com.avaje.ebean.Expr.eq;

public class MemberRepository {

  @Inject UserRepository userRepository;

  public void remove(Long memberId) {
    Member member = findById(memberId);
    member.delete();
  }

  public void changeRole(Long memberId, MemberRole role) {
    Member member = findById(memberId);
    member.setRole(role);
    member.update();
  }

  public Member findById(Long memberId) {
    return Member.find.byId(memberId);
  }

  public List<Member> findByUser(User user) {
    return Member.find.where().eq("user", user).findList();
  }

  public List<Member> findByAdmin(User user) {
    return Member.find
        .where()
        .and(eq("user", user), eq("role", MemberRole.ADMINISTRATOR))
        .findList();
  }

  public List<Member> findByPublic(User user) {
    return Member.find.where().and(eq("user", user), eq("role", MemberRole.PUBLIC)).findList();
  }
}
