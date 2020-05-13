package br.com.senac.usersystemservice.repository;

import br.com.senac.usersystemservice.model.Guest;
import br.com.senac.usersystemservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

}
