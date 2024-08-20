package com.diegogarcia.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegogarcia.webapp.biblioteca.model.Libro;
import com.diegogarcia.webapp.biblioteca.model.Prestamo;
import com.diegogarcia.webapp.biblioteca.service.PrestamoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;





@Controller
@RestController
@RequestMapping("/prestamo")
public class PrestamoController {
    @Autowired
    PrestamoService prestamoService;

    // LISTAR PRESTAMOS
    @GetMapping("/")
    public ResponseEntity <?> listarPrestamos() {
        Map<String, Boolean> response = new HashMap<>();
        try {
            return ResponseEntity.ok(prestamoService.listarPrestamo());
        } catch (Exception e) {
            response.put("El Listar no se pudo realizar con Exito", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // AGREGAR PRESTAMOS
    @PostMapping("/")
    public ResponseEntity <Map<String, Boolean>> agregarPrestamo(@RequestBody  Prestamo prestamo) {
        Map<String, Boolean> response = new HashMap<>();
        try {
            if(!prestamoService.prestamoVigente(prestamo)){
                prestamoService.guardPrestamo(prestamo);
                response.put("Se ha generado el Prestamo con exito", Boolean.TRUE);
                return ResponseEntity.ok(response);
            }else{
                response.put("El Cliente tiene un prestamo vigente", Boolean.FALSE);
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("No se genero el Prestamo", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // BUSCAR PRESTAMOS
   @GetMapping("/{id}")
   public ResponseEntity<?> buscarPrestamo(@PathVariable Long id) {
        Map<String, Boolean> response = new HashMap<>();
       try {
        Prestamo prestamo = prestamoService.buscarPrestamoPorId(id);
        return ResponseEntity.ok(prestamo);
       } catch (Exception e) {
        response.put("El prestamo no se pudo encontrar", Boolean.FALSE);
        return ResponseEntity.badRequest().body(response);
       }
   }

   // EDITAR PRESTAMO
   @PutMapping("/{id}")
   public ResponseEntity <Map<String, Boolean>> editarPrestamo(@PathVariable Long id,  Prestamo prestamoNuevo) {
       Map<String, Boolean> response = new HashMap<>();
       try {
        Prestamo prestamo = prestamoService.buscarPrestamoPorId(id);
        if(!prestamoService.prestamoVigente(prestamo)){
            prestamo.setLibro(prestamoNuevo.getLibro());
            prestamo.setCliente(prestamoNuevo.getCliente());
            prestamo.setEmpleado(prestamoNuevo.getEmpleado());
            prestamo.setFechaPrestamo(prestamoNuevo.getFechaPrestamo());
            prestamo.setFechaDevolucion(prestamoNuevo.getFechaDevolucion());
            prestamo.setVigencia(prestamoNuevo.getVigencia());
            response.put("Se editaron los datos del Prestamo con exito", Boolean.TRUE);
            return ResponseEntity.ok(response);
        }else{
            response.put("No se lograron editar los datos con exito", Boolean.FALSE);
        return ResponseEntity.badRequest().body(response);
        }
    } catch (Exception e) {
        response.put("No se lograron editar los datos con exito", Boolean.FALSE);
        return ResponseEntity.badRequest().body(response);
       }
   } 
    
   @DeleteMapping("/{id}")
   public ResponseEntity <Map<String, Boolean>> eliminarPrestamo(@PathVariable Long id){
    Map<String, Boolean> response = new HashMap<>();
        try {
            Prestamo prestamo = prestamoService.buscarPrestamoPorId(id);
            prestamoService.eliminarPrestamo(prestamo);
            response.put("Se ha eliminado el Prestamo con exito", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("No se elimino el Prestamo", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
