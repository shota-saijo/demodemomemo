package models.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;
import models.constant.TaskStatus;

@Getter
@Setter
@Entity
public class Task {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@Column
	private String title;

	@Column
	private String contents;

	@NotNull
	@ManyToOne
	private Project project;

	@ElementCollection
	@CollectionTable(name = "task_comments", joinColumns = @JoinColumn(name = "task_id"))
	private List<String> comments;

	@ManyToOne
	private Member member;

	@Column
	private TaskStatus status;
}