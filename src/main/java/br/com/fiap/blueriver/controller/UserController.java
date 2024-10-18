package br.com.fiap.blueriver.controller;

import br.com.fiap.blueriver.model.RegistrationDto;
import br.com.fiap.blueriver.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class UserController {

    @Autowired
    private RegistrationService service;

    @PostMapping(path = "")
    public ResponseEntity<RegistrationDto> registrate(@RequestBody RegistrationDto data){
        return ResponseEntity.ok(service.register(data));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<RegistrationDto> updateRegistration(@PathVariable String id, @RequestBody RegistrationDto data){
        return ResponseEntity.ok(service.updateRegistration(data, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RegistrationDto> deleteRegistration(@PathVariable String id){
        service.deleteRegistration(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RegistrationDto> getRegistration(@PathVariable String id){
        return ResponseEntity.ofNullable(service.getRegistration(id));
    }
}
