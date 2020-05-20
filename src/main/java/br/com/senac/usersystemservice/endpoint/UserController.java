package br.com.senac.usersystemservice.endpoint;

import br.com.senac.usersystemservice.model.Const;
import br.com.senac.usersystemservice.model.User;
import br.com.senac.usersystemservice.repository.RoleRepository;
import br.com.senac.usersystemservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Secured({Const.ROLE_CLIENT,Const.ROLE_ADMIN})
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @Secured({Const.ROLE_ADMIN})
    @GetMapping("/{userId}")
    public ResponseEntity<User> findAll(@PathVariable Long userId) {
        User user = userService.findById(userId);

        if (user != null) return ResponseEntity.ok(user);

        return ResponseEntity.notFound().build();
    }

    @Secured({Const.ROLE_ADMIN})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @Secured({Const.ROLE_ADMIN})
    @PutMapping("/{userId}")
    public ResponseEntity<User> change(@Valid @PathVariable Long userId, @RequestBody User user) {
        if (!userService.ifExists(userId)) {
            return ResponseEntity.notFound().build();
        }
        user.setId(userId);
        user = userService.save(user);
        return ResponseEntity.ok(user);
    }

    @Secured({Const.ROLE_ADMIN})
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        if (!userService.ifExists(userId)) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

}
