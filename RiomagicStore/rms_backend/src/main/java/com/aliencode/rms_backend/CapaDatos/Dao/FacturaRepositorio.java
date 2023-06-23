package com.aliencode.rms_backend.CapaDatos.Dao;
import org.springframework.data.repository.CrudRepository;
import com.aliencode.rms_backend.CapaDatos.Entidades.Factura;

public interface FacturaRepositorio extends CrudRepository<Factura, Integer> {
    
}
