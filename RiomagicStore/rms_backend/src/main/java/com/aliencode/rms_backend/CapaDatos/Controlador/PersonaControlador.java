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
import com.aliencode.rms_backend.CapaDatos.Servicio.Persona.PersonaServicio;
import com.aliencode.rms_backend.CapaLogica.ValidacionDatos.GenericValidacion;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api")
public class PersonaControlador {

    GenericValidacion<Persona> validacion = new GenericValidacion<>();

    Persona persona = new Persona();
    @Autowired
    private PersonaServicio servicio;

    // LISTAR
    @GetMapping("/personas")
    public ResponseEntity<RespuestaJson> listar() {
        List<Persona> personas = servicio.findAll();

         if(personas != null){
        RespuestaJson respuesta= new RespuestaJson ("True", "Personsas encontradas",
         personas);

        return new ResponseEntity<RespuestaJson>(respuesta, HttpStatus.OK);
        }else{
        RespuestaJson respuesta = new RespuestaJson("false", "No se encontraron personas", null);
         return new ResponseEntity<>(respuesta, HttpStatus.OK);
         }
    }

    // GUARDAR
    @PostMapping("/personas")
    public Persona guardar(@RequestBody Persona persona) {
        if (validacion.validarCamposRequeridos(persona)) {
            return servicio.save(persona);
        } else {
            throw new IllegalArgumentException("Los datos ingresados son incorrectos");
        }
    }

    // SELECIONAR
    @GetMapping("/personas/{id_persona}")
    public ResponseEntity<ApiResponse> getUnaPersona(@PathVariable Integer id_persona) {
        Persona persona = servicio.findById(id_persona);

        if (persona != null) {
            ApiResponse response = new ApiResponse("éxito", "Persona encontrada", persona);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse("error", "Persona no encontrada", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // EDITAR
    @PutMapping("/personas/{id_persona}")
    public Persona modificar(@RequestBody Persona persona, @PathVariable Integer id_persona) {
        Persona personaActual = servicio.findById(id_persona);
        personaActual.setRol(persona.getRol());
        personaActual.setDireccion(persona.getDireccion());
        personaActual.setCedula(persona.getCedula());
        personaActual.setNombres(persona.getNombres());
        personaActual.setApellidos(persona.getApellidos());
        personaActual.setFecha_nac(persona.getFecha_nac());
        personaActual.setTelefono(persona.getTelefono());
        personaActual.setEmail(persona.getEmail());
        personaActual.setClave(persona.getClave());
        return servicio.save(personaActual);
    }

    // ELIMINAR
    @DeleteMapping("/personas/{id_persona}")
    public void eliminar(@PathVariable Integer id_persona) {
        servicio.delete(id_persona);
    }
}

class RespuestaJson {
    private String estado;
    private String mensaje;
    private List<Persona> body;

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

    public List<Persona> getBody() {
        return body;
    }

    public void setBody(List<Persona> body) {
        this.body = body;
    }

    public RespuestaJson() {
    }

    public RespuestaJson(String estado, String mensaje, List<Persona> body) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.body = body;
    }
}

class ApiResponse {
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