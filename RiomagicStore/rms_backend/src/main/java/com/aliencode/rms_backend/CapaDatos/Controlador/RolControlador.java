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

import com.aliencode.rms_backend.CapaDatos.Entidades.Rol;
import com.aliencode.rms_backend.CapaDatos.Servicio.Rol.RolServicio;
import com.aliencode.rms_backend.CapaLogica.ValidacionDatos.GenericValidacion;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api")
public class RolControlador {
    
    GenericValidacion<Rol> validacion = new GenericValidacion<>();
    
    @Autowired
    private RolServicio servicio;

    //LISTAR
    @GetMapping("/roles")
    public ResponseEntity<RespuestaJson> listar(){
        List<Rol> rols = servicio.findAll();

        if(rols != null){
            RespuestaJson respuesta= new RespuestaJson ("True", "Roles encontrados", rols);

            return new ResponseEntity<RespuestaJson>(respuesta, HttpStatus.OK);
        }else{
            RespuestaJson respuesta = new RespuestaJson("false", "No se encontraron roles", null);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }

    //GUARDAR
    @PostMapping("/roles")
    public Rol guardar(@RequestBody Rol rol){
        return servicio.save(rol);
        
    }

    //SELECIONAR
    @GetMapping("/roles/{id_rol}")
    public Rol getUnRol(@PathVariable Integer id_rol){
        return servicio.findById(id_rol);
    }

    //EDITAR
    @PutMapping("/roles/{id_rol}")
    public Rol modificar(@RequestBody Rol rol, @PathVariable Integer id_rol){
        Rol rolActual = servicio.findById(id_rol);
        rolActual.setNombre_rol(rol.getNombre_rol());
        rolActual.setEstado(rol.isEstado());

        return servicio.save(rolActual);
    }

    //ELIMINAR
    @DeleteMapping("/roles/{id_rol}")
    public void eliminar(@PathVariable Integer id_rol){
        servicio.delete(id_rol);
    }
}

class RespuestaJson {
    private  String estado;
    private String mensaje;
    private List<Rol> body;
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
    public List<Rol> getBody() {
        return body;
    }
    public void setBody(List<Rol> body) {
        this.body = body;
    }
    public RespuestaJson () {
    }
    public RespuestaJson (String estado, String mensaje, List<Rol> body) {
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