package com.keycloack.keycloackDemo.api;

import com.keycloack.keycloackDemo.dto.UserRegistrationRecord;
import com.keycloack.keycloackDemo.service.KeycloakUserService;
import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class KeycloakUserApi {
    private final KeycloakUserService keycloakUserService;

    @PostMapping
    public UserRegistrationRecord createUser(@RequestBody UserRegistrationRecord userRegistrationRecord){
        return keycloakUserService.createUser(userRegistrationRecord);

    }
    @GetMapping
    public UserRepresentation getUser(Principal principal){
        return keycloakUserService.getUserById(principal.getName());
    }
    @DeleteMapping("/{userid}")
    public void deleteUser(@PathVariable String userId){
        keycloakUserService.deleteUserById(userId);
    }

}
