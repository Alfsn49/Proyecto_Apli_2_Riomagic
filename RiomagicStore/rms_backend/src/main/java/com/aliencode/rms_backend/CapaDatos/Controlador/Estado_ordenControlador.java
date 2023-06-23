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

import com.aliencode.rms_backend.CapaDatos.Entidades.Estado_orden;
import com.aliencode.rms_backend.CapaDatos.Servicio.Estado_orden.Estado_ordenServicio;
import com.aliencode.rms_backend.CapaLogica.ValidacionDatos.GenericValidacion;;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api")
public class Estado_ordenControlador {
    
    GenericValidacion<Estado_orden> validacion = new GenericValidacion<>();
    
    @Autowired
    private Estado_ordenServicio servicio;

    //LISTAR
    @GetMapping("/estados")
    public ResponseEntity<RespuestaJson> listar(){
        List<Estado_orden> estado_ordens = servicio.findAll();
        
        if(estado_ordens != null){
            RespuestaJson respuesta= new RespuestaJson ("True", "Personsas encontradas", estado_ordens);

            return new ResponseEntity<RespuestaJson>(respuesta, HttpStatus.OK);
        }else{
            RespuestaJson respuesta = new RespuestaJson("false", "No se encontraron personas", null);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }

    //GUARDAR
    @PostMapping("/estados")
    public Estado_orden guardar(@RequestBody Estado_orden estado_orden){
        
        if(validacion.validarCamposRequeridos(estado_orden)){
            return servicio.save(estado_orden);
        }else{
            throw new IllegalArgumentException("Los datos ingresados son incorrectos");
        }
             
    }

    //SELECIONAR
    @GetMapping("/estados/{id_estado_orden}")
    public ResponseEntity<ApiResponse> getUnaEstado_orden(@PathVariable Integer id_estado_orden){
        Estado_orden estado_orden = servicio.findById(id_estado_orden);
        
        if (estado_orden != null) {
            ApiResponse response = new ApiResponse("éxito", "Persona encontrada", estado_orden);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse("error", "Persona no encontrada", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    //EDITAR
    @PutMapping("/estados/{id_estado_orden}")
    public Estado_orden modificar(@RequestBody Estado_orden estado_orden, @PathVariable Integer id_estado_orden){
        Estado_orden EstadoActual = servicio.findById(id_estado_orden);
        EstadoActual.setEstado(estado_orden.getEstado());
        return servicio.save(estado_orden);
    }

    //ELIMINAR
    @DeleteMapping("/estados/{id_estado_orden}")
    public void eliminar(@PathVariable Integer id_estado_orden){
        servicio.delete(id_estado_orden);
    }
}

class RespuestaJson {
    private  String estado;
    private String mensaje;
    private List<Estado_orden> body;
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
    public List<Estado_orden> getBody() {
        return body;
    }
    public void setBody(List<Estado_orden> body) {
        this.body = body;
    }
    public RespuestaJson () {
    }
    public RespuestaJson (String estado, String mensaje, List<Estado_orden> body) {
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