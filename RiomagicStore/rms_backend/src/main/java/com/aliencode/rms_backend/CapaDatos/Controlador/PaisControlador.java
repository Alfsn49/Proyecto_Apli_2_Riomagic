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

import com.aliencode.rms_backend.CapaDatos.Entidades.Pais;
import com.aliencode.rms_backend.CapaDatos.Servicio.Pais.PaisServicio;
import com.aliencode.rms_backend.CapaLogica.ValidacionDatos.GenericValidacion;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api")
public class PaisControlador {
    GenericValidacion<Pais> validacion = new GenericValidacion<>();

    @Autowired
    private PaisServicio servicio;

    //LISTAR
    @GetMapping("/pais")
    public ResponseEntity<RespuestaJson> listar(){
        List<Pais> pais = servicio.findAll();

        if(pais != null){
            RespuestaJson respuesta= new RespuestaJson ("True", "Paises encontradas", pais);

            return new ResponseEntity<RespuestaJson>(respuesta, HttpStatus.OK);
        }else{
            RespuestaJson respuesta = new RespuestaJson("false", "No se encontraron paises", null);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }

    //GUARDAR
    @PostMapping("/pais")
    public Pais guardar(@RequestBody Pais pais){
        if(validacion.validarCamposRequeridos(pais)){
                return servicio.save(pais);
        }else
        {
            throw new IllegalArgumentException("Los datos ingresados son incorrectos");
        }
        
    }

    //SELECIONAR
    @GetMapping("/pais/{id_pais}")
    public ResponseEntity<ApiResponse> getUnPais(@PathVariable Integer id_pais){
        Pais pais = servicio.findById(id_pais);
        
        if (pais != null) {
            ApiResponse response = new ApiResponse("éxito", "Pais encontrada", pais);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse("error", "Pais no encontrada", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    //EDITAR
    @PutMapping("/pais/{id_pais}")
    public Pais modificar(@RequestBody Pais pais, @PathVariable Integer id_pais){
        Pais paisActual = servicio.findById(id_pais);
        paisActual.setNombre_pais(pais.getNombre_pais());
        return servicio.save(paisActual);
    }

    //ELIMINAR
    @DeleteMapping("/pais/{id_pais}")
    public void eliminar(@PathVariable Integer id_pais){
        servicio.delete(id_pais);
    }
}

class RespuestaJson{
    private  String estado;
    private String mensaje;
    private List<Pais> body;
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
    public List<Pais> getBody() {
        return body;
    }
    public void setBody(List<Pais> body) {
        this.body = body;
    }
    public RespuestaJson () {
    }
    public RespuestaJson (String estado, String mensaje, List<Pais> body) {
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