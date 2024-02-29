package com.keycloack.keycloackDemo.api;

import com.keycloack.keycloackDemo.service.RoleService;
import com.keycloack.keycloackDemo.service.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/roles")
public class UserRolesApi {
    private final RoleService roleService;

    @PutMapping("/user/{userId}")
    public ResponseEntity<?> assignRole(@PathVariable String userId, @RequestParam String roleName){
        roleService.assignRole(userId, roleName);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
