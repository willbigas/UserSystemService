package br.com.senac.usersystemservice.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {
    @ApiModelProperty(value = "Código do Usuário")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @ApiModelProperty(value = "Login/Username do Usuário")
    @NotNull(message = "Login é Obrigatório")
    private String login;
    @ApiModelProperty(value = "Senha do Usuário")
    @NotNull(message = "Senha é Obrigatório")
    private String password;
    @ApiModelProperty(value = "Permissões do Usuário")
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
