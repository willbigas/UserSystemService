package br.com.senac.usersystemservice.endpoint;

import br.com.senac.usersystemservice.model.Const;
import br.com.senac.usersystemservice.model.Guest;
import br.com.senac.usersystemservice.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/guests")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @GetMapping
    public List<Guest> findAll() {
        return guestService.findAll();
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @GetMapping("/{guestId}")
    public ResponseEntity<Guest> findAll(@PathVariable Long guestId) {
        Guest guest = guestService.findById(guestId);

        if (guest != null) return ResponseEntity.ok(guest);

        return ResponseEntity.notFound().build();
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Guest create(@Valid @RequestBody Guest guest) {
        return guestService.save(guest);
    }

    @PutMapping("/{guestId}")
    public ResponseEntity<Guest> change(@Valid @PathVariable Long guestId, @RequestBody Guest guest) {
        if (!guestService.ifExists(guestId)) {
            return ResponseEntity.notFound().build();
        }
        guest.setId(guestId);
        guest = guestService.save(guest);
        return ResponseEntity.ok(guest);
    }

    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @DeleteMapping("/{guestId}")
    public ResponseEntity<Void> delete(@PathVariable Long guestId) {
        if (!guestService.ifExists(guestId)) {
            return ResponseEntity.notFound().build();
        }
        guestService.deleteById(guestId);
        return ResponseEntity.noContent().build();
    }

}
