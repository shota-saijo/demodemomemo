package controllers;

import java.util.List;

import com.google.inject.Inject;

import models.entity.Project;
import models.entity.Task;
import models.entity.User;
import models.security.UserAuthenticator;
import models.servise.ProjectTaskService;
import models.servise.ProjectsRelatedToUserService;
import play.mvc.Result;
import play.mvc.Security;

public class IndexController extends BaseController {

	@Inject
	ProjectsRelatedToUserService projectsRelatedToUserService;
	
	@Inject
	ProjectTaskService projectTaskService;
	
	/**
	 * 初期画面表示
	 * 
	 * @return 初期画面表示
	 */
	public Result index() {
		return ok(views.html.index.render());
	}

	/**
	 * サインアップ画面表示
	 * 
	 * @return サインアップ画面
	 */
	public Result signUp() {
		return ok(views.html.signup.render());
	}

	/**
	 * サインイン画面表示
	 * 
	 * @return サインイン画面
	 */
	public Result signIn() {
		return ok(views.html.signin.render());
	}
	
	/**
	 * ダッシュボード画面表示
	 * 
	 * @return ダッシュボード画面
	 */
	@Security.Authenticated(UserAuthenticator.class)
	public Result dashboard(Long userId) {
		if(!isLoggedIn(userId)) {
			return badRequest("500 your userId is not logged in.");
		}
		User user = userRepo.findById(userId);
		if(user == null) {
			return notFound("404 user is not found");
		}
		
		List<Project> adminProjects = projectsRelatedToUserService.getAdminProjects(user);
		List<Project> publicProjects = projectsRelatedToUserService.getPublicProjects(user);
		List<Task> tasks = projectTaskService.getUserTasks(user);
		return ok(views.html.dashboard.render(adminProjects, publicProjects, tasks, user.getId()));
	}
}
