package com.diegogarcia.webapp.biblioteca.service;

import java.util.List;

import com.diegogarcia.webapp.biblioteca.model.Prestamo;

public interface IPrestamoService {

    public List<Prestamo> listarPrestamo();

    public Prestamo buscarPrestamoPorId(Long id);

    public Prestamo guardPrestamo(Prestamo prestamo);

    public void eliminarPrestamo(Prestamo prestamo);

    public Boolean prestamoVigente(Prestamo prestamo);

    public Boolean cambioDeDisponibilidad(Prestamo prestamo);
}
