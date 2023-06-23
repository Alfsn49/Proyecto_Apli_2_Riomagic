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

import com.aliencode.rms_backend.CapaDatos.Entidades.Categoria_producto;
import com.aliencode.rms_backend.CapaDatos.Servicio.Categoria_producto.Categoria_productoServicio;
import com.aliencode.rms_backend.CapaLogica.ValidacionDatos.GenericValidacion;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api")
public class Categoria_productoControlador {
    
    GenericValidacion<Categoria_producto> validacion = new GenericValidacion<>();
    
    @Autowired
    private Categoria_productoServicio servicio;

    //LISTAR
    @GetMapping("/categorias")
    public ResponseEntity<RespuestaJson> listar(){
        List<Categoria_producto> categoria_productos = servicio.findAll();

        if(categoria_productos != null){
            RespuestaJson respuesta= new RespuestaJson ("True", "Personsas encontradas", categoria_productos);

            return new ResponseEntity<RespuestaJson>(respuesta, HttpStatus.OK);
        }else{
            RespuestaJson respuesta = new RespuestaJson("false", "No se encontraron personas", null);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }

    //GUARDAR
    @PostMapping("/categorias")
    public Categoria_producto guardar(@RequestBody Categoria_producto categoria_producto){
        
        if(validacion.validarCamposRequeridos(categoria_producto)){
            return servicio.save(categoria_producto);
        }else{
            throw new IllegalArgumentException("Los datos ingresados son incorrectos");
        }
        
    }

    //SELECIONAR
    @GetMapping("/categorias/{id_categoria_producto}")
    public ResponseEntity<ApiResponse> getUnaCategoria(@PathVariable Integer id_categoria_producto){
        Categoria_producto categoria_producto = servicio.findById(id_categoria_producto);

        if (categoria_producto != null) {
            ApiResponse response = new ApiResponse("éxito", "Persona encontrada", categoria_producto);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse("error", "Persona no encontrada", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    //EDITAR
    @PutMapping("/categorias/{id_categoria_producto}")
    public Categoria_producto modificar(@RequestBody Categoria_producto categoria_producto, @PathVariable Integer id_categoria_producto){
        Categoria_producto CategoriaActual = servicio.findById(id_categoria_producto);
        CategoriaActual.setNombre_categoria(categoria_producto.getNombre_categoria());
        return servicio.save(categoria_producto);
    }

    //ELIMINAR
    @DeleteMapping("/categorias/{id_categoria_producto}")
    public void eliminar(@PathVariable Integer id_categoria_producto){
        servicio.delete(id_categoria_producto);
    }
}

class RespuestaJson {
    private  String estado;
    private String mensaje;
    private List<Categoria_producto> body;
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
    public List<Categoria_producto> getBody() {
        return body;
    }
    public void setBody(List<Categoria_producto> body) {
        this.body = body;
    }
    public RespuestaJson () {
    }
    public RespuestaJson (String estado, String mensaje, List<Categoria_producto> body) {
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