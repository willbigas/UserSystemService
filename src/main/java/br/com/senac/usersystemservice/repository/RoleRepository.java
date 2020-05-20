package br.com.senac.usersystemservice.repository;

import br.com.senac.usersystemservice.model.Guest;
import br.com.senac.usersystemservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
