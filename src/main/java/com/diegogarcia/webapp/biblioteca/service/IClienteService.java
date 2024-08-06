package com.diegogarcia.webapp.biblioteca.service;

import java.util.List;

import com.diegogarcia.webapp.biblioteca.model.Cliente;

public interface IClienteService {

    public List<Cliente> listarClientes();

    public void guardarClientes(Cliente cliente);

    public Cliente buscarClienteId(Long Id);

    public void eliminarCliente(Cliente cliente);
}
