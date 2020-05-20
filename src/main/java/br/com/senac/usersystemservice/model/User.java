package br.com.senac.usersystemservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String login;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id")
    )
    private List<Role> roles;

    public User(String login, String password, List<Role> roles) {
        super();
        this.login = login;
        this.roles = roles;
        this.password = password;
    }

    public User(User user) {
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.roles = user.getRoles();
        this.id = user.getId();
    }
}
