package models.entity;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;

import lombok.Data;

@Data
@Entity
public class Task extends Model{

	@Id
	private UUID id;
	
	private String title;
	
	private String contents;
	
	private Project project;
	
	private Set<Comment> comment;
	
	private Member member;
}