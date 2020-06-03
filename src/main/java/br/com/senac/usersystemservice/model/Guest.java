package br.com.senac.usersystemservice.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity(name = "guest")
@Getter
@Setter
public class Guest {

    @ApiModelProperty(value = "ID do Convidado")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nome é Obrigatório")
    @ApiModelProperty(value = "Nome do Convidado")
    private String name;
    @Email
    @NotEmpty(message = "Email é Obrigatório")
    @ApiModelProperty(value = "E-mail do Convidado")
    @Column(unique = true)
    private String email;
    @ApiModelProperty(value = "Telefone do Convidado")
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)

    @JoinTable(name="guest_user",
            joinColumns=@JoinColumn(name="guest_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<User> users;


    private boolean active;

    public Guest() {
        this.active = true;
    }

    public Guest(String name, String email, String phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.active = true;
    }


}
