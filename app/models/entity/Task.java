package models.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.avaje.ebean.Model;

import lombok.Data;
import models.constant.TaskStatus;

@Data
@Entity
public class Task extends Model{

	@Id
	private UUID id;
	
	@Column
	private String title;
	
	@Column
	private String contents;
	
	@NotNull
	@ManyToOne
	private Project project;
	
	@OneToMany
	private List<Comment> comments;
	
	@ManyToOne
	private Member member;
	
	@Column
	private TaskStatus status;
}