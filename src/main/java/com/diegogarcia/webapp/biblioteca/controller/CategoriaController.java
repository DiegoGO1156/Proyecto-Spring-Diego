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

import com.diegogarcia.webapp.biblioteca.model.Categoria;
import com.diegogarcia.webapp.biblioteca.service.CategoriaService;

@Controller
@RestController
@RequestMapping("categoria")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    // LISTAR CATEGORIA
    @GetMapping("/")
    public List<Categoria> listaCategorias(){
        return categoriaService.listarCategoria();
    }

    // BUSCAR CATEGORIA POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id){
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // AGREGAR CATEGORIA
    @PostMapping("/")
    public ResponseEntity<Map<String, Boolean>> agregarCategoria(@RequestBody Categoria categoria){
        Map<String, Boolean> response = new HashMap<>();
        try {
            categoriaService.guardarCategoria(categoria);
            response.put("Categoria agregada con Exito", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("Categoria agregada con Exito", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // EDITAR CATEGORIA
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> editarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaNueva){
        Map <String, Boolean> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoria.setNombreCategoria(categoriaNueva.getNombreCategoria());
            categoriaService.guardarCategoria(categoria);
            response.put("La categoria se edito con exito", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("La categoria se edito con exito", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }


    // ELIMINAR CATEGORIA
    @DeleteMapping("/{id}")
    public ResponseEntity <Map<String, Boolean>> eliminarCategoria(@PathVariable Long id){
        Map<String,Boolean> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoriaService.eliminarCategoria(categoria);
            response.put("La categoria se ha eliminado con exito", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("La categoria se ha eliminado con exito", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
