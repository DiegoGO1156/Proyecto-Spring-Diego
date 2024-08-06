package com.diegogarcia.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegogarcia.webapp.biblioteca.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
