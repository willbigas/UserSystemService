package br.com.senac.usersystemservice.service;

import br.com.senac.usersystemservice.model.Guest;
import br.com.senac.usersystemservice.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    public Guest findById(Long id) {
        return guestRepository.findById(id).orElse(null);
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

    public boolean ifExists(Long id) {
        return guestRepository.existsById(id);
    }
}
