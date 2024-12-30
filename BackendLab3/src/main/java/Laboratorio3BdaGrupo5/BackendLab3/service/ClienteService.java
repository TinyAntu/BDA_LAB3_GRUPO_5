package Laboratorio3BdaGrupo5.BackendLab3.service;

import Laboratorio3BdaGrupo5.BackendLab3.models.Cliente;
import Laboratorio3BdaGrupo5.BackendLab3.repository.ClienteRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class ClienteService {
    @Autowired
    ClienteRepositoryImp clienteRepository;

    public Cliente getClienteById(Integer idCliente) {
        try {
            Cliente cliente = clienteRepository.getClienteById(idCliente);
            if (cliente != null) {
                return cliente;
            } else {
                throw new RuntimeException("Cliente no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el Cliente", e);
        }
    }

    public void createCliente(Cliente cliente) {
        try {
            String encryptedPassword = generateEncodedPassword(cliente.getPassword());
            cliente.setPassword(encryptedPassword);
            clienteRepository.createCliente(cliente);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el Cliente", e);
        }
    }

    private String generateEncodedPassword(String passsword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(passsword);
    }

    public void updateCliente(Cliente cliente) {
        try {
            if (getClienteById(cliente.getId_cliente()) != null) {
                String encryptedPassword = generateEncodedPassword(cliente.getPassword());
                cliente.setPassword(encryptedPassword);
                clienteRepository.updateCliente(cliente);
            } else {
                throw new RuntimeException("El cliente no existe en la base de datos");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el Cliente", e);
        }
    }

    public void deleteCliente(Integer idCliente) {
        try {
            if (getClienteById(idCliente) != null) {
                clienteRepository.deleteCliente(idCliente);
            } else {
                throw new RuntimeException("El cliente no existe en la base de datos");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el producto", e);
        }
    }

    public Cliente getClienteByEmail(String email) {
        try {
            Cliente cliente = clienteRepository.getClienteByEmail(email);
            if (cliente != null) {
                return cliente;
            } else {
                throw new RuntimeException("Cliente no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el Cliente", e);
        }
    }

}
