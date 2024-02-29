package com.keycloack.keycloackDemo.service;

import com.keycloack.keycloackDemo.config.KeycloakConfig;
import com.keycloack.keycloackDemo.dto.UserRegistrationRecord;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class KeycloakUserServiceImpl implements KeycloakUserService {
    @Value("${keycloak.realm}")
    private String realm;
    private Keycloak keycloak;

    public KeycloakUserServiceImpl(KeycloakConfig keycloakConfig){
    }

    @Override
    public UserRegistrationRecord createUser(UserRegistrationRecord userRegistrationRecord) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername( userRegistrationRecord.username());
        user.setEmail(userRegistrationRecord.email());
        user.setUsername(userRegistrationRecord.username());
        user.setEmailVerified(true);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(userRegistrationRecord.password());
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        List<CredentialRepresentation> list = new ArrayList<>();
        list.add(credentialRepresentation);
        user.setCredentials(list);

        UsersResource userResource = getResource();

        Response response = userResource.create(user);

        if (Objects.equals(201 , response.getStatus())){
            return userRegistrationRecord;

        }

        return null;
    }

    private UsersResource getResource() {
        RealmResource realm1 = keycloak.realm(realm);
        return realm1.users();
    }

    private UsersResource getUsersResource() {
        RealmResource realm1 = keycloak.realm(realm);
        return realm1.users();
    }

    @Override
    public UserRepresentation getUserById(String userId) {

        return getUsersResource().get(userId).toRepresentation();
    }

    @Override
    public void deleteUserById(String userId) {
        getUsersResource().delete(userId);

    }
}
