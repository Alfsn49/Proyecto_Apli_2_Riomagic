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

import com.aliencode.rms_backend.CapaDatos.Entidades.Factura;
import com.aliencode.rms_backend.CapaDatos.Servicio.Factura.FacturaServicio;
import com.aliencode.rms_backend.CapaLogica.ValidacionDatos.GenericValidacion;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api")
public class FacturaControlador {
    
    GenericValidacion<Factura> validacion = new GenericValidacion<>();
    
    @Autowired
    private FacturaServicio servicio;

    //LISTAR
    @GetMapping("/facturas")
    public ResponseEntity<RespuestaJson> listar(){
        List<Factura> facturas = servicio.findAll();

        if(facturas != null){
            RespuestaJson respuesta= new RespuestaJson ("True", "Personsas encontradas", facturas);

            return new ResponseEntity<RespuestaJson>(respuesta, HttpStatus.OK);
        }else{
            RespuestaJson respuesta = new RespuestaJson("false", "No se encontraron personas", null);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }

    //GUARDAR
    @PostMapping("/facturas")
    public Factura guardar(@RequestBody Factura factura){
        if(validacion.validarCamposRequeridos(factura)){
            return servicio.save(factura);
        }else{
            throw new IllegalArgumentException("Los datos ingresados son incorrectos");
        }
        
    }

    //SELECIONAR
    @GetMapping("/facturas/{id_factura}")
    public ResponseEntity<ApiResponse> getUnaFactura(@PathVariable Integer id_factura){
        Factura factura = servicio.findById(id_factura);
        if (factura != null) {
            ApiResponse response = new ApiResponse("éxito", "Persona encontrada", factura);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse("error", "Persona no encontrada", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    //EDITAR
    @PutMapping("/facturas/{id_factura}")
    public Factura modificar(@RequestBody Factura factura, @PathVariable Integer id_factura){
        Factura FacturaActual = servicio.findById(id_factura);
        FacturaActual.setPersonas(factura.getPersonas());
        FacturaActual.setOrden_compra(factura.getOrden_compra());
        FacturaActual.setArticulo_carrito(factura.getArticulo_carrito());
        FacturaActual.setNum_Factura(factura.getNum_Factura());
        FacturaActual.setSubtotal_sinIVA(factura.getSubtotal_sinIVA());
        return servicio.save(factura);
    }

    //ELIMINAR
    @DeleteMapping("/facturas/{id_factura}")
    public void eliminar(@PathVariable Integer id_factura){
        servicio.delete(id_factura);
    }
}

class RespuestaJson {
    private  String estado;
    private String mensaje;
    private List<Factura> body;
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
    public List<Factura> getBody() {
        return body;
    }
    public void setBody(List<Factura> body) {
        this.body = body;
    }
    public RespuestaJson () {
    }
    public RespuestaJson (String estado, String mensaje, List<Factura> body) {
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