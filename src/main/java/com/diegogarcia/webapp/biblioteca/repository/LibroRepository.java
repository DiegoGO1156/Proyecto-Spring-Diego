package com.diegogarcia.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegogarcia.webapp.biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
