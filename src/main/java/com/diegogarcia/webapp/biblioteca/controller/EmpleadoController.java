package com.diegogarcia.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegogarcia.webapp.biblioteca.model.Empleados;
import com.diegogarcia.webapp.biblioteca.service.EmpleadoService;

@Controller
@RestController
@RequestMapping("empleado")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    
    // LISTAR EMPLEADOS
    @GetMapping("/")
    public ResponseEntity<List<Empleados>> listarEmpleado(){
        try {
            return ResponseEntity.ok(empleadoService.listarEmpleados());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // BUSCAR EMPLEADOS
    @GetMapping("/{id}")
    public ResponseEntity<Empleados> buscarEmpleados(@PathVariable Long id){
        try {
            Empleados empleado = empleadoService.buscarEmpleados(id);
            return ResponseEntity.ok(empleado);    
        } catch (Exception e) {
            return ResponseEntity.ok().body(null);    
        }
    }

    // AGREGAR EMPLEADO
    @PutMapping("/")
    public ResponseEntity<Map<String, Boolean>> agregarEmpelado(@RequestBody Empleados empleado){
        Map<String, Boolean> response = new HashMap<>();
        try {
            empleadoService.guardarEmpleado(empleado);
            response.put("Se agrego el empleado con exito", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("Se agrego el empleado con exito", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
