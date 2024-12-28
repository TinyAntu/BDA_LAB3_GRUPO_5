package com.grupo5.Lab3.Controller;

import com.grupo5.Lab3.Model.Cliente;
import com.grupo5.Lab3.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    @Autowired
    private ClienteService clienteService;


    @PostMapping("/saveUser")
    public ResponseEntity<Cliente> saveUser(@RequestBody Cliente cliente) {
            System.out.println(cliente);  // Verifica los datos
             Cliente client = clienteService.createCliente(cliente);
            return ResponseEntity.ok(client);

    }
}
