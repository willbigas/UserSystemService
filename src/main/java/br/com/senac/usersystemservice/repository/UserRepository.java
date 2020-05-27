package br.com.senac.usersystemservice.repository;

import br.com.senac.usersystemservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    List<User> findAllByActiveTrue();
}
