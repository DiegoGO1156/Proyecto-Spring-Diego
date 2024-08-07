package com.diegogarcia.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegogarcia.webapp.biblioteca.model.Empleados;
import com.diegogarcia.webapp.biblioteca.repository.EmpleadoRepository;


@Service
public class EmpleadoService implements IEmpleadosService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleados> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public void guardarEmpleado(Empleados empleado) {
        empleadoRepository.save(empleado);
    }

    @Override
    public Empleados buscarEmpleados(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarEmpleado(Empleados empleado) {
        empleadoRepository.delete(empleado);
    }

}
