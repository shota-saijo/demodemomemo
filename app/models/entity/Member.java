package models.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.avaje.ebean.Model;

import lombok.Getter;
import lombok.Setter;
import models.constant.MemberRole;

@Getter
@Setter
@Entity
public class Member extends Model{

	public static final Finder<Long, Member> find = new Finder<Long, Member>(Member.class);
	
	@Id
	@GeneratedValue()
	private String id;

	@NotNull
	@ManyToOne
	private Project project;

	@NotNull
	@ManyToOne
	private User user;

	@Column
	private MemberRole role;

	@OneToMany
	private List<Task> tasks;
}
