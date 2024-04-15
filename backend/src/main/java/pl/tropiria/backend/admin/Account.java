package pl.tropiria.backend.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.tropiria.backend.role.Role;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "account")
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String login;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "is_active")
    boolean isActive;

    @JsonIgnore
    @ManyToMany
    List<Role> roles;
}
