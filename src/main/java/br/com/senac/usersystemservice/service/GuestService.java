package br.com.senac.usersystemservice.service;

import br.com.senac.usersystemservice.model.Guest;
import br.com.senac.usersystemservice.model.User;
import br.com.senac.usersystemservice.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private UserService userService;

    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    public List<Guest> findAllActives() {
        return guestRepository.findAllByActiveTrue();
    }

    public Guest findById(Long id) {
        return guestRepository.findById(id).orElse(null);
    }

    public Guest save(Guest guest, Long userId) {
        User user = userService.findById(userId);

        if (guest.getUsers() == null || guest.getUsers().isEmpty()) {
            guest.setUsers(new ArrayList<>());
        }

        if (user != null) {
            guest.getUsers().add(user);
        }

        return guestRepository.save(guest);
    }

    public Guest save(Guest guest) {
        return guestRepository.save(guest);
    }


    public void delete(Guest guest) {
        guestRepository.delete(guest);
    }

    public void deleteById(Long guestId) {
        guestRepository.deleteById(guestId);
    }

    public void inactive(Long guestId) {
        Guest guest = findById(guestId);
        guest.setActive(false);
        guestRepository.save(guest);
    }

    public boolean ifExists(Long id) {
        return guestRepository.existsById(id);
    }
}
