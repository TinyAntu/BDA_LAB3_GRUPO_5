package Laboratorio3BdaGrupo5.BackendLab3.authentication.controller;

import Laboratorio3BdaGrupo5.BackendLab3.authentication.entities.AuthenticationResponse;
import Laboratorio3BdaGrupo5.BackendLab3.authentication.entities.LoginRequest;
import Laboratorio3BdaGrupo5.BackendLab3.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}