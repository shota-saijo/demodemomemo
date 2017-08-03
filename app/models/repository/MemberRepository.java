package models.repository;

import models.entity.Member;
import models.entity.User;

import java.util.List;

public class MemberRepository {

  public List<Member> findByUser(User user) {
    return Member.find.where().eq("user", user).findList();
  }
}
