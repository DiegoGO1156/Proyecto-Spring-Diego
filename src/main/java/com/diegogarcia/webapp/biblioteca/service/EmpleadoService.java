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

    @Override
    public Boolean verificarDpiDupl(Empleados empleados) {
        Boolean flag = Boolean.FALSE;

        List<Empleados> empleado = listarEmpleados();
        for (Empleados e : empleado) {
            if(e.getDpi().equals(empleados.getDpi()) && !e.getId().equals(empleados.getId())){
                flag = Boolean.TRUE;
            }
        }

        return flag;
    }

}
