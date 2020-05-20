package br.com.senac.usersystemservice.config;

import br.com.senac.usersystemservice.model.Const;
import br.com.senac.usersystemservice.model.Role;
import br.com.senac.usersystemservice.model.User;
import br.com.senac.usersystemservice.repository.RoleRepository;
import br.com.senac.usersystemservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {

        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            createUser("admin", passwordEncoder.encode("1234"), Const.ROLE_ADMIN);
            createUser( "cliente", passwordEncoder.encode("1234"), Const.ROLE_CLIENT);
        }

    }

    public void createUser(String login, String password, String roleName) {
        Role role = new Role(roleName);

        this.roleRepository.save(role);
        User user = new User(login, password, Arrays.asList(role));
        userRepository.save(user);
    }

}
