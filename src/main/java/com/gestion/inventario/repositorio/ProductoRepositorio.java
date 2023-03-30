package com.gestion.inventario.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.inventario.modelo.Producto;

public interface ProductoRepositorio  extends JpaRepository<Producto, Integer>{

}
