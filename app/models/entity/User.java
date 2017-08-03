package models.entity;

import com.avaje.ebean.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class User extends Model {

  public static final Finder<Long, User> find = new Finder<Long, User>(User.class);

  @Id @GeneratedValue private Long id;

  @NotNull
  @Column(unique = true)
  private String accountId;

  @Column private String password;
}
