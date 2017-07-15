package models.entity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.avaje.ebean.Model;

import lombok.Data;

@Data
@Entity
public class Project extends Model{

	@Id
	private UUID id;
	
	@Column
	@NotNull
	private String name;
	
	@Column
	private String description;
	
	@OneToMany
	private Set<Member> member;
	
	@OneToMany
	private List<Task> tasks;
}
