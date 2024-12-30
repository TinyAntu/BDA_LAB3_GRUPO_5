package Laboratorio3BdaGrupo5.BackendLab3.authentication.service;

import Laboratorio3BdaGrupo5.BackendLab3.authentication.entities.AuthenticationResponse;
import Laboratorio3BdaGrupo5.BackendLab3.authentication.entities.LoginRequest;
import Laboratorio3BdaGrupo5.BackendLab3.authentication.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;

    public AuthenticationResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userService.getUserByEmail(request.getEmail());
        String jwtToken = jwtService.generateToken(user.generateExtraClaims(), user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(user.getId())
                .Name(user.getName())
                .build();
    }
}