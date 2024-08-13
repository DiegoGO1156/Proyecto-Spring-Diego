package com.diegogarcia.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegogarcia.webapp.biblioteca.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

}
