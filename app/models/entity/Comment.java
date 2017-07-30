package models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment extends Model{

	@Id
	@GeneratedValue()
	private String id;
	
	@ManyToOne
	private Task task;
	
	@Column
	private String content;
}
