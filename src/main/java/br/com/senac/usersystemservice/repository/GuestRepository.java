package br.com.senac.usersystemservice.repository;

import br.com.senac.usersystemservice.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    List<Guest> findAllByActiveTrue();
}
