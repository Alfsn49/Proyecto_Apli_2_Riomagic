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

import com.aliencode.rms_backend.CapaDatos.Entidades.Metodo_envio;
import com.aliencode.rms_backend.CapaDatos.Servicio.Metodo_envio.Metodo_envioServicio;
import com.aliencode.rms_backend.CapaLogica.ValidacionDatos.GenericValidacion;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api")
public class Metodo_envioControlador {
    
    GenericValidacion<Metodo_envio> validacion = new GenericValidacion<>();
    
    @Autowired
    private Metodo_envioServicio servicio;

    //LISTAR
    @GetMapping("/envios")
    public ResponseEntity<RespuestaJson> listar(){
        List<Metodo_envio> metodo_envios = servicio.findAll();
        if(metodo_envios != null){
            RespuestaJson respuesta= new RespuestaJson ("True", "Personsas encontradas", metodo_envios);

            return new ResponseEntity<RespuestaJson>(respuesta, HttpStatus.OK);
        }else{
            RespuestaJson respuesta = new RespuestaJson("false", "No se encontraron personas", null);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }

    //GUARDAR
    @PostMapping("/envios")
    public Metodo_envio guardar(@RequestBody Metodo_envio metodo_envio){
        
        if(validacion.validarCamposRequeridos(metodo_envio)){
            return servicio.save(metodo_envio);
        }else{
            throw new IllegalArgumentException("Los datos ingresados son incorrectos");
        }
        
        
    }

    //SELECIONAR
    @GetMapping("/envios/{id_metodo_envio}")
    public ResponseEntity<ApiResponse> getUnEnvio(@PathVariable Integer id_metodo_envio){
        Metodo_envio metodo_envio = servicio.findById(id_metodo_envio);

        if (metodo_envio != null) {
            ApiResponse response = new ApiResponse("éxito", "Persona encontrada", metodo_envio);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse("error", "Persona no encontrada", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    //EDITAR
    @PutMapping("/envios/{id_metodo_envio}")
    public Metodo_envio modificar(@RequestBody Metodo_envio metodo_envio, @PathVariable Integer id_metodo_envio){
        Metodo_envio EnvioActual = servicio.findById(id_metodo_envio);
        EnvioActual.setNombre(metodo_envio.getNombre());
        EnvioActual.setPrecio(metodo_envio.getPrecio());
        return servicio.save(metodo_envio);
    }

    //ELIMINAR
    @DeleteMapping("/envios/{id_metodo_envio}")
    public void eliminar(@PathVariable Integer id_metodo_envio){
        servicio.delete(id_metodo_envio);
    }
}

class RespuestaJson {
    private  String estado;
    private String mensaje;
    private List<Metodo_envio> body;
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
    public List<Metodo_envio> getBody() {
        return body;
    }
    public void setBody(List<Metodo_envio> body) {
        this.body = body;
    }
    public RespuestaJson () {
    }
    public RespuestaJson (String estado, String mensaje, List<Metodo_envio> body) {
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