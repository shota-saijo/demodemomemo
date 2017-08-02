package models.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Finder;

import lombok.Getter;
import lombok.Setter;
import models.constant.TaskStatus;

@Getter
@Setter
@Entity
public class Task extends Model {

	public static final Finder<Long, Task> find = new Finder<Long, Task>(Task.class);
	
	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String title;

	@Column
	private String contents;

	@NotNull
	@ManyToOne
	private Project project;

	@OneToMany
	private Set<Comment> comments;

	@ManyToOne
	private Member member;

	@Column
	private TaskStatus status;
}