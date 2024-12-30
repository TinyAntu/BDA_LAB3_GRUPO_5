package Laboratorio3BdaGrupo5.BackendLab3.repository;

import Laboratorio3BdaGrupo5.BackendLab3.models.Cliente;

import java.util.function.Supplier;

public interface ClienteRepository {

    Cliente getClienteById(Integer idCliente);

    void createCliente(Cliente cliente);

    void updateCliente(Cliente cliente);

    void deleteCliente(Integer idCliente);

    Cliente findByEmail(String email);

    Cliente getClienteByEmail(String email);

    public <T> T registerSessionUserAndInsertOperation(Integer id_cliente, Supplier<T> operation);
}
