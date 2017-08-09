package controllers;

import com.google.inject.Inject;
import models.constant.MemberRole;
import models.entity.Member;
import models.entity.Project;
import models.entity.User;
import models.repository.MemberRepository;
import models.repository.ProjectRepository;
import models.security.UserAuthenticator;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.mvc.Result;
import play.mvc.Security;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberController extends BaseController {

  @Inject ProjectRepository projectRepository;

  @Inject MemberRepository memberRepository;

  @Inject FormFactory formFactory;

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result showProjectMembers(Long userId, Long projectId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    Project project = projectRepository.findById(projectId);
    List<User> users = new ArrayList<>();
    return ok(views.html.members_project.render(user, project, users, ""));
  }

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result searchUser(Long userId, Long projectId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    String accountId = formFactory.form().bindFromRequest().get("accountId");

    if (accountId.isEmpty()) {
      return redirect(routes.MemberController.showProjectMembers(userId, projectId));
    }

    Project project = projectRepository.findById(projectId);
    List<User> projectUsers = project.getMembers().stream().map(member -> member.getUser()).collect(Collectors.toList());
    List<User> users = userRepository.findLikeAccountId(accountId).stream().filter(u -> !projectUsers.contains(u)).collect(Collectors.toList());

    return ok(views.html.members_project.render(user, project, users, accountId));
  }

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result create(Long userId, Long projectId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    Long memberUserId = Long.parseLong(formFactory.form().bindFromRequest().get("userId"));
    String role = formFactory.form().bindFromRequest().get("role");

    Project project = projectRepository.findById(projectId);
    User memberUser = userRepository.findById(memberUserId);
    Member member;
    switch (role) {
      case "admin":
        member = Member.newAdmin(memberUser);
        projectRepository.addMember(project, member);
        break;
      case "public":
        member = Member.newPublic(memberUser);
        projectRepository.addMember(project, member);
        break;
    }

    return redirect(routes.MemberController.showProjectMembers(userId, projectId));
  }

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result update(Long userId, Long projectId, Long memberId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    String role = formFactory.form().bindFromRequest().get("role");
    switch (role) {
      case "admin":
        memberRepository.changeRole(memberId, MemberRole.ADMINISTRATOR);
        break;
      case "public":
        memberRepository.changeRole(memberId, MemberRole.PUBLIC);
        break;
    }

    return redirect(routes.MemberController.showProjectMembers(userId, projectId));
  }

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result remove(Long userId, Long projectId, Long memberId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    memberRepository.remove(memberId);

    return redirect(routes.MemberController.showProjectMembers(userId, projectId));
  }
}
