package models.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.avaje.ebean.Model;

import lombok.Data;

@Data
@Entity
public class User extends Model {

    @Id
    private UUID id;

    @NotNull
    @Column(name = "account_id")
    private String accountId;

    @Column
    private String password;
}
