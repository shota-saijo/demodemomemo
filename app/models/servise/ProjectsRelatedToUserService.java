package models.servise;

import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;

import models.constant.MemberRole;
import models.entity.Project;
import models.entity.User;
import models.repository.MemberRepository;

public class ProjectsRelatedToUserService {

	@Inject
	MemberRepository memberRepo;

	public List<Project> getProjects(User user) {
		return memberRepo.findByUser(user).stream().map(member -> member.getProject()).collect(Collectors.toList());

	}

	public List<Project> getAdminProjects(User user) {
		return memberRepo.findByUser(user).stream().filter(member -> member.getRole().equals(MemberRole.ADMINISTRATOR))
				.map(admin -> admin.getProject()).collect(Collectors.toList());
	}

	public List<Project> getPublicProjects(User user) {
		return memberRepo.findByUser(user).stream().filter(member -> member.getRole().equals(MemberRole.PUBLIC))
				.map(admin -> admin.getProject()).collect(Collectors.toList());
	}
}
