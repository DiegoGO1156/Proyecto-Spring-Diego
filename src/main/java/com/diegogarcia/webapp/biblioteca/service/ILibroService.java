package com.diegogarcia.webapp.biblioteca.service;

import java.util.List;

import com.diegogarcia.webapp.biblioteca.model.Libro;

public interface ILibroService {
    public List<Libro> listaLibros();

    public Libro guardarLibro(Libro libro);

    public Libro buscaLibroporId(Long id);

    public void eliminarLibro(Libro libro);
}
