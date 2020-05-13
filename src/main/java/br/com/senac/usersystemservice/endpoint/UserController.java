package br.com.senac.usersystemservice.endpoint;

import br.com.senac.usersystemservice.model.User;
import br.com.senac.usersystemservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findAll(@PathVariable Long userId) {
        User user = userService.findById(userId);

        if (user != null) return ResponseEntity.ok(user);

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> change(@Valid @PathVariable Long userId, @RequestBody User user) {
        if (!userService.ifExists(userId)) {
            return ResponseEntity.notFound().build();
        }
        user.setId(userId);
        user = userService.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        if (!userService.ifExists(userId)) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }


}
