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

import com.aliencode.rms_backend.CapaDatos.Entidades.Direccion;
import com.aliencode.rms_backend.CapaDatos.Servicio.Direccion.DireccionServicio;
import com.aliencode.rms_backend.CapaLogica.ValidacionDatos.GenericValidacion;;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api")
public class DireccionControlador {
    GenericValidacion<Direccion> validacion = new GenericValidacion<>();
    
    @Autowired
    private DireccionServicio servicio;

    //LISTAR
    @GetMapping("/direcciones")
    public ResponseEntity<RespuestaJson> listar(){
        List<Direccion> direccions= servicio.findAll();

        if(direccions != null){
            RespuestaJson respuesta= new RespuestaJson ("True", "Personsas encontradas", direccions);

            return new ResponseEntity<RespuestaJson>(respuesta, HttpStatus.OK);
        }else{
            RespuestaJson respuesta = new RespuestaJson("false", "No se encontraron personas", null);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }

    //GUARDAR
    @PostMapping("/direcciones")
    public Direccion guardar(@RequestBody Direccion direccion){
        if(validacion.validarCamposRequeridos(direccion)){
            return servicio.save(direccion);
        }else{
            throw new IllegalArgumentException("Los datos ingresados son incorrectos");
        }
    }

    //SELECIONAR
    @GetMapping("/direcciones/{id_direccion}")
    public ResponseEntity<ApiResponse> getUnaDireccion(@PathVariable Integer id_direccion){
        Direccion direccion = servicio.findById(id_direccion);

        if (direccion != null) {
            ApiResponse response = new ApiResponse("éxito", "Persona encontrada", direccion);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse("error", "Persona no encontrada", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    //EDITAR
    @PutMapping("/direcciones/{id_direccion}")
    public Direccion modificar(@RequestBody Direccion direccion, @PathVariable Integer id_direccion){
        Direccion DireccionActual = servicio.findById(id_direccion);
        DireccionActual.setPais(direccion.getPais());
        DireccionActual.setProvincia(direccion.getProvincia());
        DireccionActual.setCuidad(direccion.getCuidad());
        DireccionActual.setCodigo_postal(direccion.getCodigo_postal());
        DireccionActual.setCalle_principal(direccion.getCalle_principal());
        DireccionActual.setCalle_secundaria(direccion.getCalle_secundaria());
        
        return servicio.save(direccion);
    }

    //ELIMINAR
    @DeleteMapping("/direcciones/{id_direccion}")
    public void eliminar(@PathVariable Integer id_direccion){
        servicio.delete(id_direccion);
    }
}

class RespuestaJson {
    private  String estado;
    private String mensaje;
    private List<Direccion> body;
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
    public List<Direccion> getBody() {
        return body;
    }
    public void setBody(List<Direccion> body) {
        this.body = body;
    }
    public RespuestaJson () {
    }
    public RespuestaJson (String estado, String mensaje, List<Direccion> body) {
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