package com.diegogarcia.webapp.biblioteca.service;

import java.util.List;

import com.diegogarcia.webapp.biblioteca.model.Empleados;

public interface IEmpleadosService {
    
    public List<Empleados> listarEmpleados();

    public void guardarEmpleado(Empleados empleado);

    public Empleados buscarEmpleados(Long id);

    public void eliminarEmpleado(Empleados empleado);

    public Boolean verificarDpiDupl(Empleados empleados );
}
