package br.com.senac.usersystemservice.endpoint;

import br.com.senac.usersystemservice.model.Const;
import br.com.senac.usersystemservice.model.Guest;
import br.com.senac.usersystemservice.model.dto.GuestDTO;
import br.com.senac.usersystemservice.model.dto.GuestResponseDTO;
import br.com.senac.usersystemservice.service.GuestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/guests")
@ApiOperation(value = "Convidados")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @ApiOperation(value = "Listar todos os Convidados Ativos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listar todos os Convidados Ativos")
    })
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @GetMapping
    public List<Guest> findAllAcives() {
        return guestService.findAllActives();
    }

    @ApiOperation(value = "Listar todos os Convidados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listar todos os Convidados")
    })
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @GetMapping(value = "/findAll")
    public List<Guest> findAll() {
        return guestService.findAll();
    }

    @ApiOperation(value = "Procurar Convidado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Procurar Convidado")
    })
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @GetMapping("/{guestId}")
    public ResponseEntity<Guest> findAll(@PathVariable Long guestId) {
        Guest guest = guestService.findById(guestId);

        if (guest != null) return ResponseEntity.ok(guest);

        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Criar novo Convidado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Criar novo Convidado")
    })
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GuestResponseDTO> create(@Valid @RequestBody GuestDTO dto) {
        Guest guest = guestService.save(dto.dtoTOObject());
        return new ResponseEntity<>(GuestResponseDTO.objectToDTO(guest), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Alterar Convidado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Alterar Convidado")
    })
    @PutMapping("/{guestId}")
    public ResponseEntity<Guest> change(@Valid @PathVariable Long guestId, @RequestBody Guest guest) {
        if (!guestService.ifExists(guestId)) {
            return ResponseEntity.notFound().build();
        }
        guest.setId(guestId);
        guest = guestService.save(guest);
        return ResponseEntity.ok(guest);
    }

    @ApiOperation(value = "Deleta Convidado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleta Convidado")
    })
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
