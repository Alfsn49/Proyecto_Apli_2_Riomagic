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

import com.aliencode.rms_backend.CapaDatos.Entidades.Opinion_usuario;
import com.aliencode.rms_backend.CapaDatos.Servicio.Opinion_usuario.Opinion_usuarioServicio;
import com.aliencode.rms_backend.CapaLogica.ValidacionDatos.GenericValidacion;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api")
public class Opinion_usuarioControlador {
    
    GenericValidacion<Opinion_usuario> validacion = new GenericValidacion<>();
    
    @Autowired
    private Opinion_usuarioServicio servicio;

    //LISTAR
    @GetMapping("/opiniones")
    public ResponseEntity<RespuestaJson> listar(){
        List<Opinion_usuario> opinion_usuarios = servicio.findAll();
        if(opinion_usuarios != null){
            RespuestaJson respuesta= new RespuestaJson ("True", "Personsas encontradas", opinion_usuarios);

            return new ResponseEntity<RespuestaJson>(respuesta, HttpStatus.OK);
        }else{
            RespuestaJson respuesta = new RespuestaJson("false", "No se encontraron personas", null);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }   

    //GUARDAR
    @PostMapping("/opiniones")
    public Opinion_usuario guardar(@RequestBody Opinion_usuario opinion_usuario){
        
        if(validacion.validarCamposRequeridos(opinion_usuario)){
            return servicio.save(opinion_usuario);
        }else{
            throw new IllegalArgumentException("Los datos ingresados son incorrectos");
        }
        
            
    }

    //SELECIONAR
    @GetMapping("/opiniones/{id_opinion_usuario}")
    public ResponseEntity<ApiResponse> getUnaOpinion(@PathVariable Integer id_opinion_usuario){
        Opinion_usuario opinion_usuario = servicio.findById(id_opinion_usuario);
        if (opinion_usuario != null) {
            ApiResponse response = new ApiResponse("éxito", "Persona encontrada", opinion_usuario);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse("error", "Persona no encontrada", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    //EDITAR
    @PutMapping("/opiniones/{id_opinion_usuario}")
    public Opinion_usuario modificar(@RequestBody Opinion_usuario opinion_usuario, @PathVariable Integer id_opinion_usuario){
        Opinion_usuario opinionActual = servicio.findById(id_opinion_usuario);
        opinionActual.setPersonas(opinionActual.getPersonas());
        opinionActual.setOrden_compra(opinionActual.getOrden_compra());
        opinionActual.setValor(opinion_usuario.getValor());
        opinionActual.setComentario(opinion_usuario.getComentario());
        return servicio.save(opinion_usuario);
    }

    //ELIMINAR
    @DeleteMapping("/opiniones/{id_opinion_usuario}")
    public void eliminar(@PathVariable Integer id_opinion_usuario){
        servicio.delete(id_opinion_usuario);
    }
}

class RespuestaJson {
    private  String estado;
    private String mensaje;
    private List<Opinion_usuario> body;
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
    public List<Opinion_usuario> getBody() {
        return body;
    }
    public void setBody(List<Opinion_usuario> body) {
        this.body = body;
    }
    public RespuestaJson () {
    }
    public RespuestaJson (String estado, String mensaje, List<Opinion_usuario> body) {
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