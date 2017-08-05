package models.entity;

import com.avaje.ebean.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class User extends Model {

  public static final Finder<Long, User> find = new Finder<>(User.class);

  @Id @GeneratedValue public Long id;

  @NotNull
  @Column(unique = true)
  public String accountId;

  @Column public String password;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
  public List<Member> members = new ArrayList<>();
}
