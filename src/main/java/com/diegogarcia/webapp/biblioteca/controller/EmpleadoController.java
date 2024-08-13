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
    @PostMapping("/")
    public ResponseEntity<Map<String, Boolean>> agregarEmpelado(@RequestBody Empleados empleado){
        Map<String, Boolean> response = new HashMap<>();
        try {
            if(!empleadoService.verificarDpiDupl(empleado)){
                empleadoService.guardarEmpleado(empleado);
                response.put("Se agrego el empleado con exito", Boolean.TRUE);
                return ResponseEntity.ok(response);
            }else{
                response.put("El dpi del Empleado se encuentra duplicado", Boolean.FALSE);
                return ResponseEntity.badRequest().body(response);
            }
            
        } catch (Exception e) {
            response.put("Se agrego el empleado con exito", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // EDITAR EMPLEADOS
    @PutMapping("/{id}")
    public ResponseEntity <Map<String, Boolean>> editarEmpleado(@PathVariable Long id, Empleados empleadoNuevo){
        Map<String, Boolean> response = new HashMap<>();
        try {
            Empleados empleado = empleadoService.buscarEmpleados(id);
            if (!empleadoService.verificarDpiDupl(empleadoNuevo)) {
                empleado.setNombre(empleadoNuevo.getNombre());
                empleado.setApellido(empleadoNuevo.getApellido());
                empleado.setDpi(empleadoNuevo.getDpi());
                empleado.setDireccion(empleadoNuevo.getDireccion());
                empleado.setTelefono(empleadoNuevo.getTelefono());
                response.put("Se ha editado el empleado con exito", Boolean.TRUE);
                return ResponseEntity.ok(response);
            }else{
                response.put("El dpi se encuentra duplicado", Boolean.FALSE);
                return ResponseEntity.badRequest().body(response); 
            }
        } catch (Exception e) {
            response.put("Se ha editado el empleado con exito", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // ELIMINAR EMPLEADO
    @DeleteMapping("/{id}")
    public ResponseEntity <Map<String, Boolean>> eliminarEmpleado(@PathVariable Long id){
        Map<String, Boolean> response = new HashMap<>();
        try {
            Empleados empleado = empleadoService.buscarEmpleados(id);
            empleadoService.eliminarEmpleado(empleado);
            response.put("Se elimino el Empleado con exito", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("Se elimino el empleado con exito", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    } 
}
