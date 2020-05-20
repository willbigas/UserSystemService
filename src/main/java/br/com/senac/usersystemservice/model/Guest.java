package br.com.senac.usersystemservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity(name = "guest")
@Getter
@Setter
@NoArgsConstructor
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Nome é Obrigatório")
    private String name;
    @Email
    @NotNull(message = "Email é Obrigatório")
    private String email;
    private String phone;

    @OneToOne
    private User user;

}
