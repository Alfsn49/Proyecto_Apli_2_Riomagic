package com.aliencode.rms_backend.CapaDatos.Dao;

import org.springframework.data.repository.CrudRepository;
import com.aliencode.rms_backend.CapaDatos.Entidades.Producto;

public interface ProductoRepositorio extends CrudRepository<Producto, Integer>{
   
}