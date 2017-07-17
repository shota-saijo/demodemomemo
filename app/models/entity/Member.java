package models.entity;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.avaje.ebean.Model;

import lombok.Data;
import models.constant.MemberRole;

@Data
@Entity
public class Member extends Model{

	@Id
	private UUID id;
	
	@NotNull
	@ManyToOne
	private Project project;
	
	@NotNull
	@ManyToOne
	private User user;
	
	@Column
	private MemberRole role;
	
	@OneToMany
	private Set<Task> tasks;
}
