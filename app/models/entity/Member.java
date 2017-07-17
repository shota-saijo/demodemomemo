package models.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;
import models.constant.MemberRole;

@Getter
@Setter
@Entity
public class Member {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
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
	private Set<Task> tasks;
}
