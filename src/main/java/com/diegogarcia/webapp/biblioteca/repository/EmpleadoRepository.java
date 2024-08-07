package com.diegogarcia.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegogarcia.webapp.biblioteca.model.Empleados;

public interface EmpleadoRepository extends JpaRepository<Empleados, Long> {

}
