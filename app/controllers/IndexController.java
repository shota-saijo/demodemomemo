package controllers;

import com.google.inject.Inject;

import models.repository.UserRepository;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

public class IndexController extends Controller {

	// TODO 後で消す
	@Inject
	UserRepository userRepo;

	@Transactional
	public Result index() {

		// 後で消す
		userRepo.store();

		return ok(views.html.index.render());
	}
}
