package com.aliencode.rms_backend.CapaDatos.Dao;
import org.springframework.data.repository.CrudRepository;
import com.aliencode.rms_backend.CapaDatos.Entidades.Orden_compra;

public interface Orden_compraRepositorio extends CrudRepository<Orden_compra, Integer> {
    
}
