package com.diegogarcia.webapp.biblioteca.service;

import java.util.List;

import com.diegogarcia.webapp.biblioteca.model.Categoria;

public interface ICategoriaService {

    public List<Categoria> listarCategoria();

    public void guardarCategoria(Categoria categoria);

    public Categoria buscarCategoriaPorId(Long Id);

    public void eliminarCategoria(Categoria categoria);

}
