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

import com.diegogarcia.webapp.biblioteca.model.Libro;
import com.diegogarcia.webapp.biblioteca.service.LibroService;

@Controller
@RestController
@RequestMapping("libro")
public class LibroController {

    @Autowired
    LibroService libroService;

    // LISTAR LIBROS
    @GetMapping("/")
    public ResponseEntity<List<Libro>> listarLibros(){
        try {
            return ResponseEntity.ok(libroService.listaLibros());   
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // BUSCAR LIBROS
    @GetMapping("/{id}")
    public ResponseEntity<Libro> buscarLibro(@PathVariable Long id){
        try {
            Libro libro = libroService.buscaLibroporId(id);
            return ResponseEntity.ok(libro);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // AGREGAR LIBROS
    @PostMapping("/")
    public ResponseEntity<Map<String, Boolean>> agregarLibro(@RequestBody Libro libro){
        Map<String, Boolean> response = new HashMap<>();
        try {
            libroService.guardarLibro(libro);
            response.put("Libro se ha Agregado con Exito", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("Libro se ha Agregado con Exito", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // EDITAR LIBROS
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> editarLibro(@PathVariable Long id, Libro libroNuevo){
        Map<String, Boolean> response = new HashMap<>();
        try {
            Libro libro = libroService.buscaLibroporId(id);
            libro.setIsbn(libroNuevo.getIsbn());
            libro.setNombre(libroNuevo.getNombre());
            libro.setSinopsis(libroNuevo.getSinopsis());
            libro.setAutor(libroNuevo.getAutor());
            libro.setEditorial(libroNuevo.getEditorial());
            libro.setDisponibilidad(libroNuevo.getDisponibilidad());
            libro.setNumEstanteria(libroNuevo.getNumEstanteria());
            libro.setCluster(libroNuevo.getCluster());
            libro.setCategoria(libroNuevo.getCategoria());
            response.put("La edición del Libro ha sido Exitosa", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("La edición del Libro ha sido Exitosa", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // BORRAR LIBRO
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarLibro(@PathVariable Long id){
        Map<String, Boolean> response = new HashMap<>();
        try {
            Libro libro = libroService.buscaLibroporId(id);
            libroService.eliminarLibro(libro);
            response.put("El libro ha sido Eliminado con Exito", Boolean.TRUE);        
            return ResponseEntity.ok(response);   
        } catch (Exception e) {
            response.put("El libro ha sido Eliminado con Exito", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);    
        }
    }
}
