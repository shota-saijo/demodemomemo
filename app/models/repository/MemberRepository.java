package models.repository;

import java.util.List;

import models.entity.Member;
import models.entity.User;

public class MemberRepository {

	public List<Member> findByUser(User user) {
		return Member.find.where().eq("user", user).findList();
	}
}
