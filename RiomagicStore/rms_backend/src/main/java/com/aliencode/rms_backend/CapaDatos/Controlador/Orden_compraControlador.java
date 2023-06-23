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

import com.aliencode.rms_backend.CapaDatos.Entidades.Orden_compra;
import com.aliencode.rms_backend.CapaDatos.Entidades.Persona;
import com.aliencode.rms_backend.CapaDatos.Servicio.Orden_compra.Orden_compraServicio;
import com.aliencode.rms_backend.CapaLogica.ValidacionDatos.GenericValidacion;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api")
public class Orden_compraControlador {
    
    GenericValidacion<Orden_compra> validacion = new GenericValidacion<>();
    
    @Autowired
    private Orden_compraServicio servicio;

    //LISTAR
    @GetMapping("/ordenes")
    public ResponseEntity<RespuestaJson> listar(){
        List<Orden_compra> orden_compras = servicio.findAll();

        if(orden_compras != null){
            RespuestaJson respuesta= new RespuestaJson ("True", "Personsas encontradas", orden_compras);

            return new ResponseEntity<RespuestaJson>(respuesta, HttpStatus.OK);
        }else{
            RespuestaJson respuesta = new RespuestaJson("false", "No se encontraron personas", null);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }

    //GUARDAR
    @PostMapping("/ordenes")
    public Orden_compra guardar(@RequestBody Orden_compra orden_compra){
        
        if(validacion.validarCamposRequeridos(orden_compra)){
            return servicio.save(orden_compra);
        }else{
            throw new IllegalArgumentException("Los datos ingresados son incorrectos");
        }
        
        
    }

    //SELECIONAR
    @GetMapping("/ordenes/{id_orden_compra}")
    public ResponseEntity<ApiResponse> getUnOrden_compra(@PathVariable Integer id_orden_compra){
        Orden_compra orden_compra = servicio.findById(id_orden_compra);

        if(orden_compra != null){
            ApiResponse response = new ApiResponse("éxito", "Persona encontrada", orden_compra);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse("error", "Persona no encontrada", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    //EDITAR
    @PutMapping("/ordenes/{id_orden_compra}")
    public Orden_compra modificar(@RequestBody Orden_compra orden_compra, @PathVariable Integer id_orden_compra){
        Orden_compra ordenActual = servicio.findById(id_orden_compra);
        ordenActual.setPersonas(orden_compra.getPersonas());
        ordenActual.setMetodo_pago(orden_compra.getMetodo_pago());
        ordenActual.setDireccion(orden_compra.getDireccion());
        ordenActual.setMetodo_envio(ordenActual.getMetodo_envio());
        ordenActual.setEstado_orden(orden_compra.getEstado_orden());
        ordenActual.setFecha_orden(orden_compra.getFecha_orden());
        ordenActual.setTotal_orden(ordenActual.getTotal_orden());
        return servicio.save(ordenActual);
    }

    //ELIMINAR
    @DeleteMapping("/ordenes/{id_orden_compra}")
    public void eliminar(@PathVariable Integer id_orden_compra){
        servicio.delete(id_orden_compra);
    }
}

class RespuestaJson {
    private  String estado;
    private String mensaje;
    private List<Orden_compra> body;
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
    public List<Orden_compra> getBody() {
        return body;
    }
    public void setBody(List<Orden_compra> body) {
        this.body = body;
    }
    public RespuestaJson () {
    }
    public RespuestaJson (String estado, String mensaje, List<Orden_compra> body) {
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