package com.diegogarcia.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegogarcia.webapp.biblioteca.model.Prestamo;
import com.diegogarcia.webapp.biblioteca.repository.PrestamoRepository;

@Service
public class PrestamoService implements IPrestamoService{

    @Autowired
    PrestamoRepository prestamoRepository;

    @Override
    public List<Prestamo> listarPrestamo() {
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo buscarPrestamoPorId(Long id) {
       return prestamoRepository.findById(id).orElse(null);
    }

    @Override
    public Prestamo guardPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    @Override
    public void eliminarPrestamo(Prestamo prestamo) {
        prestamoRepository.delete(prestamo);
    }

    @Override
    public Boolean prestamoVigente(Prestamo prestamo) {
        Boolean flag = Boolean.FALSE;
            List<Prestamo> prestamos = listarPrestamo();
            for (Prestamo p : prestamos) {
                if(p.getVigencia() == Boolean.TRUE && !p.getId().equals(prestamo.getId())){
                    flag = Boolean.TRUE;
                }
            }
        return flag;
    }

}