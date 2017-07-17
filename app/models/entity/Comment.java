package models.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Comment {

	@Id
	private UUID id;
	
	@NotNull
	@ManyToOne
	private String contents;
}
