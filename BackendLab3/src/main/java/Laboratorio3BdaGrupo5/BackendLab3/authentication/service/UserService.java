package Laboratorio3BdaGrupo5.BackendLab3.authentication.service;

import Laboratorio3BdaGrupo5.BackendLab3.authentication.entities.User;
import Laboratorio3BdaGrupo5.BackendLab3.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private ClienteService clienteService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByEmail(username);
    }

    public User getUserByEmail(String email) {
        return User.clienteToUser(clienteService.getClienteByEmail(email));
    }

}
