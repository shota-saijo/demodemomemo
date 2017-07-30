package models.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.avaje.ebean.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Project extends Model {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Column
	private String name;

	@Column
	private String description;

	@OneToMany
	private Set<Member> member;

	@OneToMany
	private List<Task> tasks;
}
