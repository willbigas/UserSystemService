package br.com.senac.usersystemservice.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity(name = "guest")
@Getter
@Setter
public class Guest {
    @ApiModelProperty(value = "ID do Convidado")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Nome é Obrigatório")
    @ApiModelProperty(value = "Nome do Convidado")
    private String name;
    @Email
    @NotEmpty(message = "Email é Obrigatório")
    @ApiModelProperty(value = "E-mail do Convidado")
    @Column(unique = true)
    private String email;
    @ApiModelProperty(value = "Telefone do Convidado")
    private String phone;
    private boolean active;

    @OneToOne
    private User user;

    public Guest() {
        this.active = true;
    }
}
