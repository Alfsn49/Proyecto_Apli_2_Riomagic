package com.aliencode.rms_backend.CapaDatos.Servicio.Metodo_pago;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aliencode.rms_backend.CapaDatos.Entidades.Metodo_pago;
import com.aliencode.rms_backend.CapaDatos.Dao.Metodo_pagoRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class Metodo_pagoImplementacion implements Metodo_pagoServicio{
    @Autowired
    private Metodo_pagoRepositorio repositorio;
    //LISTAR PRODUCTOS
    @Override
    @Transactional(readOnly=true)
    public List<Metodo_pago> findAll() {
        return (List<Metodo_pago>) repositorio.findAll();
    }
    //GUARDAR
    @Override
    @Transactional(readOnly=false)
    public Metodo_pago save(Metodo_pago metodo_pago){
        return repositorio.save(metodo_pago);
    }
    //ELIMINAR
    @Override
    @Transactional(readOnly=false)
    public void delete(Integer id_metodo_pago){
        repositorio.deleteById(id_metodo_pago);
    }
    //SELECIONAR
    @Override
    @Transactional(readOnly=true)
    public Metodo_pago findById(Integer id_metodo_pago) {
        return repositorio.findById(id_metodo_pago).orElse(null);
    }
}