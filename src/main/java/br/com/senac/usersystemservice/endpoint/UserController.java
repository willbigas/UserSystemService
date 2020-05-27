package br.com.senac.usersystemservice.endpoint;

import br.com.senac.usersystemservice.model.Const;
import br.com.senac.usersystemservice.model.User;
import br.com.senac.usersystemservice.repository.RoleRepository;
import br.com.senac.usersystemservice.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Retorna lista de Usuários Ativos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna lista de Usuário Ativos")
    })
    @Secured({Const.ROLE_ADMIN})
    @GetMapping
    public List<User> findAllActives() {
        return userService.findAllActives();
    }

    @ApiOperation(value = "Retorna lista de todos Usuários")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna lista de todos Usuário")
    })
    @Secured({Const.ROLE_ADMIN})
    @GetMapping(value = "/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @ApiOperation(value = "Retorna usuário com o ID informado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna usuário com o ID informado")
    })
    @Secured({Const.ROLE_ADMIN})
    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> find(@PathVariable Long userId) {
        User user = userService.findById(userId);

        if (user != null) return ResponseEntity.ok(user);

        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Cria Usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cria Usuário")
    })
    @Secured({Const.ROLE_ADMIN})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@ApiParam(value = "Usuário que será Salvo", required = true) @Valid @RequestBody User user) {
        return userService.save(user);
    }

    @ApiOperation(value = "Alterar Usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Alterar Usuário")
    })
    @Secured({Const.ROLE_ADMIN})
    @PutMapping(value = "/{userId}")
    public ResponseEntity<User> change(@Valid @PathVariable Long userId, @RequestBody User user) {
        if (!userService.ifExists(userId)) {
            return ResponseEntity.notFound().build();
        }
        user.setId(userId);
        user = userService.save(user);
        return ResponseEntity.ok(user);
    }

    @ApiOperation(value = "Deleta Usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleta Usuário")
    })
    @Secured({Const.ROLE_ADMIN})
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        if (!userService.ifExists(userId)) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

}
