package controllers;

import com.google.inject.Inject;
import models.entity.Project;
import models.entity.Task;
import models.entity.User;
import models.repository.MemberRepository;
import models.repository.ProjectRepository;
import models.security.UserAuthenticator;
import play.db.ebean.Transactional;
import play.mvc.Result;
import play.mvc.Security;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DashboardController extends BaseController {

  @Inject MemberRepository memberRepository;

  @Inject ProjectRepository projectRepository;

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result showAllProjects(Long userId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    List<Project> projects = new ArrayList<>();
    projects.addAll(projectRepository.findByUser(user));
    projects.addAll(
        memberRepository
            .findByUser(user)
            .stream()
            .map(member -> member.getProject())
            .collect(Collectors.toList()));

    List<Task> tasks =
        projects
            .stream()
            .flatMap(project -> project.getTasks().stream())
            .collect(Collectors.toList());

    return ok(views.html.dashboard_projects.render(projects, tasks, user.getId(), "all"));
  }

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result showChiefProjects(Long userId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    List<Project> projects = projectRepository.findByUser(user);

    List<Task> tasks =
        projects
            .stream()
            .flatMap(project -> project.getTasks().stream())
            .collect(Collectors.toList());

    return ok(views.html.dashboard_projects.render(projects, tasks, user.getId(), "chief"));
  }

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result showAdminProjects(Long userId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    List<Project> projects =
        memberRepository
            .findByAdmin(user)
            .stream()
            .map(member -> member.getProject())
            .collect(Collectors.toList());

    List<Task> tasks =
        projects
            .stream()
            .flatMap(project -> project.getTasks().stream())
            .collect(Collectors.toList());

    return ok(views.html.dashboard_projects.render(projects, tasks, user.getId(), "admin"));
  }

  @Transactional
  @Security.Authenticated(UserAuthenticator.class)
  public Result showPublicProjects(Long userId) {
    if (!isLoggedIn(userId)) {
      return badRequest("500 your userId is not logged in.");
    }
    User user = userRepository.findById(userId);
    if (user == null) {
      return notFound("404 user is not found");
    }

    List<Project> projects =
        memberRepository
            .findByPublic(user)
            .stream()
            .map(member -> member.getProject())
            .collect(Collectors.toList());

    List<Task> tasks =
        projects
            .stream()
            .flatMap(project -> project.getTasks().stream())
            .collect(Collectors.toList());

    return ok(views.html.dashboard_projects.render(projects, tasks, user.getId(), "public"));
  }
}
