package com.aliencode.rms_backend.CapaLogica.Productos;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliencode.rms_backend.CapaDatos.Dao.ProductoRepositorio;
import com.aliencode.rms_backend.CapaDatos.Entidades.Producto;



@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api")
public class ProductosApi {
    @Autowired
    ProductoRepositorio servicio;

    @GetMapping("/productos-admin")
    public List<Map<String, Object>> listar() {
        Iterable<Producto> productos = servicio.findAll();
        List<Map<String, Object>> productosProyectados = new ArrayList<>();

        for (Producto producto : productos) {
            Map<String, Object> productoProyectado = new LinkedHashMap<>();
            productoProyectado.put("id_producto", producto.getId_producto());
            productoProyectado.put("nombre", producto.getNombre());
            productoProyectado.put("imagen", producto.getImagen_producto());
            productoProyectado.put("descripcion", producto.getDescripcion());
            productoProyectado.put("precio", producto.getPrecio());
            productoProyectado.put("stock", producto.getCantidad_stock());
            productoProyectado.put("categoria", producto.getCategoria().getNombre_categoria());

            productosProyectados.add(productoProyectado);
        }

        return productosProyectados;
    }
}
