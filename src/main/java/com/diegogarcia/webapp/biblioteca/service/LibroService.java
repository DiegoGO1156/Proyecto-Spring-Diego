package com.diegogarcia.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegogarcia.webapp.biblioteca.model.Libro;
import com.diegogarcia.webapp.biblioteca.repository.LibroRepository;


@Service
public class LibroService implements ILibroService {

    @Autowired
    LibroRepository libroRepository;

    @Override
    public List<Libro> listaLibros() {
        return libroRepository.findAll();
    }

    @Override
    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Libro buscaLibroporId(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarLibro(Libro libro) {
        libroRepository.delete(libro);
    }

}
