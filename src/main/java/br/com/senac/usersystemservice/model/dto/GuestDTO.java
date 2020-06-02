package br.com.senac.usersystemservice.model.dto;

import br.com.senac.usersystemservice.model.Guest;
import br.com.senac.usersystemservice.model.User;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Getter
public class GuestDTO {

    private Long id;
    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;
    private String phone;
    private boolean active;
    private User user;

    public Guest dtoTOObject() {
        return new Guest(name, email, phone);
    }

}
