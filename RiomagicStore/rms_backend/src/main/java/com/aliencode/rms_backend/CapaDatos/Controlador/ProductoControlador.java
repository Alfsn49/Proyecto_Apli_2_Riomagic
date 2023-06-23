package com.aliencode.rms_backend.CapaDatos.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.aliencode.rms_backend.CapaDatos.Entidades.Persona;
import com.aliencode.rms_backend.CapaDatos.Entidades.Producto;
import com.aliencode.rms_backend.CapaDatos.Servicio.Producto.ProductoServicio;
import com.aliencode.rms_backend.CapaLogica.ValidacionDatos.GenericValidacion;


@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api")
public class ProductoControlador {
    
    GenericValidacion<Producto> validacion = new GenericValidacion<>();
    
    @Autowired
    private ProductoServicio servicio;

    //LISTAR
    @GetMapping("/productos")
    public ResponseEntity<RespuestaJson>  listar(){
        List<Producto> productos = servicio.findAll();

        if(productos != null){
            RespuestaJson respuesta= new RespuestaJson ("True", "Personsas encontradas", productos);

            return new ResponseEntity<RespuestaJson>(respuesta, HttpStatus.OK);
        }else{
            RespuestaJson respuesta = new RespuestaJson("false", "No se encontraron personas", null);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }

    //GUARDAR
    @PostMapping("/productos")
    public Producto guardar(@RequestBody Producto producto){
        
        if(validacion.validarCamposRequeridos(producto)){
            return servicio.save(producto);
        }else{
            throw new IllegalArgumentException("Los datos ingresados no son correctos");
        }
        
    }

    //SELECIONAR
    @GetMapping("/productos/{id_producto}")
    public ResponseEntity<ApiResponse> getUnProducto(@PathVariable Integer id_producto){
        Producto producto = servicio.findById(id_producto);
        
        if (producto != null) {
            ApiResponse response = new ApiResponse("éxito", "Persona encontrada", producto);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse("error", "Persona no encontrada", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    //EDITAR
    @PutMapping("/productos/{id_producto}")
    public Producto modificar(@RequestBody Producto producto, @PathVariable Integer id_producto){
        Producto productoActual = servicio.findById(id_producto);
        productoActual.setCategoria(producto.getCategoria());
        productoActual.setNombre(producto.getNombre());
        productoActual.setDescripcion(producto.getDescripcion());
        productoActual.setImagen_producto(producto.getImagen_producto());
        productoActual.setCantidad_stock(producto.getCantidad_stock());
        productoActual.setPrecio(producto.getPrecio());

        return servicio.save(productoActual);
    }

    //ELIMINAR
    @DeleteMapping("/productos/{id_producto}")
    public void eliminar(@PathVariable Integer id_producto){
        servicio.delete(id_producto);
    }

}

class RespuestaJson {
    private  String estado;
    private String mensaje;
    private List<Producto> body;
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public List<Producto> getBody() {
        return body;
    }
    public void setBody(List<Producto> body) {
        this.body = body;
    }
    public RespuestaJson () {
    }
    public RespuestaJson (String estado, String mensaje, List<Producto> body) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.body = body;
    }
}

class ApiResponse{
    private String estado;
    private String mensaje;
    private Object body;
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public Object getBody() {
        return body;
    }
    public void setBody(Object body) {
        this.body = body;
    }
    public ApiResponse() {
    }
    public ApiResponse(String estado, String mensaje, Object body) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.body = body;
    }
}