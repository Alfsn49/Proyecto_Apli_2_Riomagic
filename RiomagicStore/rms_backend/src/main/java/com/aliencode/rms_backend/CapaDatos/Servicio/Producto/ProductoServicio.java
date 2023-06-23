package com.aliencode.rms_backend.CapaDatos.Servicio.Producto;

import java.util.List;
import com.aliencode.rms_backend.CapaDatos.Entidades.Producto;
import com.aliencode.rms_backend.CapaDatos.Servicio.Producto.ProductoServicio;

public interface ProductoServicio {
    public List<Producto> findAll();
    public Producto save(Producto producto);
    public Producto findById(Integer id_producto);
    public void delete(Integer id_producto);
}
