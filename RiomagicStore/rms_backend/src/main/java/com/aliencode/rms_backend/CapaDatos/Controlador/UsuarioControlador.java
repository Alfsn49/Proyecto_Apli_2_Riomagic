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
import com.aliencode.rms_backend.CapaDatos.Entidades.Usuario;
import com.aliencode.rms_backend.CapaDatos.Servicio.Usuario.UsuarioServicio;
import com.aliencode.rms_backend.CapaLogica.ValidacionDatos.GenericValidacion;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api")
public class UsuarioControlador {
    
    GenericValidacion<Usuario> validacion = new GenericValidacion<>();
    
    @Autowired
    private UsuarioServicio servicio;

    //LISTAR
    @GetMapping("/usuarios")
    public ResponseEntity<RespuestaJson> listar(){
        List<Usuario> usuarios = servicio.findAll();

        if(usuarios != null){
            RespuestaJson respuesta= new RespuestaJson ("True", "Personsas encontradas", usuarios);

            return new ResponseEntity<RespuestaJson>(respuesta, HttpStatus.OK);
        }else{
            RespuestaJson respuesta = new RespuestaJson("false", "No se encontraron personas", null);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }

    //GUARDAR
    @PostMapping("/usuarios")
    public Usuario guardar(@RequestBody Usuario usuario){
        if(validacion.validarCamposRequeridos(usuario)){
            return servicio.save(usuario);
        }else{
            throw new IllegalArgumentException("Los datos ingresados son incorrectos");
        }
        
        
    }

    //SELECIONAR
    @GetMapping("/usuarios/{id_usuario}")
    public ResponseEntity<ApiResponse> getUnUsuario(@PathVariable Integer id_usuario){
        Usuario usuario = servicio.findById(id_usuario);
        
        if (usuario != null) {
            ApiResponse response = new ApiResponse("éxito", "Persona encontrada", usuario);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse("error", "Persona no encontrada", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    //EDITAR
    @PutMapping("/usuarios/{id_usuario}")
    public Usuario modificar(@RequestBody Usuario usuario, @PathVariable Integer id_usuario){
        Usuario usuarioActual = servicio.findById(id_usuario);
        usuarioActual.setRol(usuario.getRol());
        usuarioActual.setCedula(usuario.getCedula());
        usuarioActual.setNombres(usuario.getNombres());
        usuarioActual.setApellidos(usuario.getApellidos());
        usuarioActual.setDireccion(usuario.getDireccion());
        usuarioActual.setTelefono(usuario.getTelefono());
        usuarioActual.setEmail(usuario.getEmail());
        usuarioActual.setClave(usuario.getClave());
        return servicio.save(usuarioActual);
    }

    //ELIMINAR
    @DeleteMapping("/usuarios/{id_usuario}")
    public void eliminar(@PathVariable Integer id_usuario){
        servicio.delete(id_usuario);
    }
}

class RespuestaJson {
    private  String estado;
    private String mensaje;
    private List<Usuario> body;
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
    public List<Usuario> getBody() {
        return body;
    }
    public void setBody(List<Usuario> body) {
        this.body = body;
    }
    public RespuestaJson () {
    }
    public RespuestaJson (String estado, String mensaje, List<Usuario> body) {
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
