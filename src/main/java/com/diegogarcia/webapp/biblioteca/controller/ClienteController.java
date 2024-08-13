package com.diegogarcia.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegogarcia.webapp.biblioteca.model.Cliente;
import com.diegogarcia.webapp.biblioteca.service.ClienteService;

@Controller
@RestController
@RequestMapping("cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    // LISTAR CLIENTES
    @GetMapping("/")
    public List<Cliente> listarClientes(){
        return clienteService.listarClientes();
    }

    // BUSCAR CLIENTE POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClienteId(@PathVariable Long id){
        try {
            Cliente cliente = clienteService.buscarClienteId(id);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // AGREGAR CLIENTE
    @PostMapping("/")
    public ResponseEntity <Map<String, Boolean>> agregarCliente(@RequestBody Cliente cliente){
        Map<String, Boolean> response = new HashMap<>();
        try {
            clienteService.guardarClientes(cliente);
            response.put("Cliente agregado con exito", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
           response.put("Cliente agregado con exito", Boolean.FALSE);
           return ResponseEntity.badRequest().body(response);
        }
    }

    // EDITAR CLIENTE
    @PutMapping("/{id}")
    public ResponseEntity <Map<String, Boolean>> editarCliente(@PathVariable Long id, @RequestBody Cliente clienteNuevo){
        Map<String, Boolean> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarClienteId(id);
            cliente.setNombre(clienteNuevo.getNombre());
            cliente.setApellido(clienteNuevo.getApellido());
            cliente.setTelefono(clienteNuevo.getTelefono());
            response.put("Edición de Datos Exitosa", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("Edición de Datos Exitosa", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // ELIMINAR CLIENTE
    @DeleteMapping("/{id}")
    public ResponseEntity <Map<String, Boolean>> eliminarCliente(@PathVariable Long Id){
        Map<String, Boolean> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarClienteId(Id);
            clienteService.eliminarCliente(cliente);
            response.put("Se elimino el cliente con exito", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("Se elimino el cliente con exito", Boolean.TRUE);
            return ResponseEntity.badRequest().body(response);
        }
    }

}
