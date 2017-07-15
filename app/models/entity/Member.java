package models.entity;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;

import lombok.Data;
import models.constant.MemberRole;

@Data
@Entity
public class Member extends Model{

	@Id
	private UUID id;
	
	@ManyToOne
	private Project project;
	
	@ManyToOne
	private User user;
	
	@Column
	private MemberRole role;
	
	private Set<Task> tasks;
}
