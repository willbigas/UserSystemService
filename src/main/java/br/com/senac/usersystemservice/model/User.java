package br.com.senac.usersystemservice.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity(name = "user")
@Getter
@Setter
public class User implements Serializable {
    @ApiModelProperty(value = "ID do Usuário")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @ApiModelProperty(value = "Login/Username do Usuário")
    @NotEmpty(message = "Login é Obrigatório")
    @Column(unique = true)
    private String login;
    @ApiModelProperty(value = "Senha do Usuário")
    @NotEmpty(message = "Senha é Obrigatório")
    private String password;
    private boolean active;
    @ApiModelProperty(value = "Permissões do Usuário")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))
    private List<Role> roles;

    public User(){
        this.active= true;
    }
    public User(String login, String password, List<Role> roles) {
        super();
        this.login = login;
        this.roles = roles;
        this.password = password;
        this.active = true;
    }

    public User(User user) {
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.roles = user.getRoles();
        this.id = user.getId();
        this.active = true;
    }
}
