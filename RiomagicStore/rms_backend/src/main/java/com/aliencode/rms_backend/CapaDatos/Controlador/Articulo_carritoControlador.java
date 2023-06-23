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

import com.aliencode.rms_backend.CapaDatos.Entidades.Articulo_carrito;
import com.aliencode.rms_backend.CapaDatos.Servicio.Articulo_carrito.Articulo_carritoService;
import com.aliencode.rms_backend.CapaLogica.ValidacionDatos.GenericValidacion;


@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api")
public class Articulo_carritoControlador {
    
    GenericValidacion<Articulo_carrito> validacion = new GenericValidacion<>();
    
    @Autowired
    private Articulo_carritoService servicio;

    //LISTAR
    @GetMapping("/carrito")
    public ResponseEntity<RespuestaJson> listar(){
        List<Articulo_carrito> articulo_carritos = servicio.findAll();

        if(articulo_carritos != null){
            RespuestaJson respuesta= new RespuestaJson ("True", "Personsas encontradas", articulo_carritos);

            return new ResponseEntity<RespuestaJson>(respuesta, HttpStatus.OK);
        }else{
            RespuestaJson respuesta = new RespuestaJson("false", "No se encontraron personas", null);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }

    //GUARDAR
    @PostMapping("/carrito")
    public Articulo_carrito guardar(@RequestBody Articulo_carrito articulo_carrito){
        if(validacion.validarCamposRequeridos(articulo_carrito)){
            return servicio.save(articulo_carrito);
        }else{
            throw new IllegalArgumentException("Los datos ingresados son incorrectos");
        }
    }

    //SELECIONAR
    @GetMapping("/carrito/{id_articulo_carrito}")
    public ResponseEntity<ApiResponse> getUnCarrito(@PathVariable Integer id_articulo_carrito){
        Articulo_carrito articulo_carrito = servicio.findById(id_articulo_carrito);

        if(articulo_carrito!= null){
            ApiResponse response = new ApiResponse("éxito", "Articulo Carrito encontrada", articulo_carrito);
            return ResponseEntity.ok(response);
        }else{
            ApiResponse response = new ApiResponse("error", "Articulo Carrito no encontrada", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    //EDITAR
    @PutMapping("/carrito/{id_articulo_carrito}")
    public Articulo_carrito modificar(@RequestBody Articulo_carrito articulo_carrito, @PathVariable Integer id_articulo_carrito){
        Articulo_carrito CarritoActual = servicio.findById(id_articulo_carrito);
        CarritoActual.setCarrito_compra(articulo_carrito.getCarrito_compra());
        CarritoActual.setProducto(articulo_carrito.getProducto());
        CarritoActual.setCantidad(articulo_carrito.getCantidad());
        return servicio.save(articulo_carrito);
    }

    //ELIMINAR
    @DeleteMapping("/carrito/{id_articulo_carrito}")
    public void eliminar(@PathVariable Integer id_articulo_carrito){
        servicio.delete(id_articulo_carrito);
    }
}

class RespuestaJson {
    private  String estado;
    private String mensaje;
    private List<Articulo_carrito> body;
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
    public List<Articulo_carrito> getBody() {
        return body;
    }
    public void setBody(List<Articulo_carrito> body) {
        this.body = body;
    }
    public RespuestaJson () {
    }
    public RespuestaJson (String estado, String mensaje, List<Articulo_carrito> body) {
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