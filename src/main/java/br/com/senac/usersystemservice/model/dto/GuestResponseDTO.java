package br.com.senac.usersystemservice.model.dto;

import br.com.senac.usersystemservice.model.Guest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class GuestResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private boolean active;

    public static GuestResponseDTO objectToDTO(Guest guest) {
        return new GuestResponseDTO(guest.getId(), guest.getName(), guest.getEmail(), guest.getPhone(), guest.isActive());
    }
}
