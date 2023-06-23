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

import com.aliencode.rms_backend.CapaDatos.Entidades.Metodo_pago;
import com.aliencode.rms_backend.CapaDatos.Servicio.Metodo_pago.Metodo_pagoServicio;
import com.aliencode.rms_backend.CapaLogica.ValidacionDatos.GenericValidacion;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api")
public class Metodo_pagoControlador {
    
    GenericValidacion<Metodo_pago> validacion = new GenericValidacion<>();
    
    @Autowired
    private Metodo_pagoServicio servicio;

    //LISTAR
    @GetMapping("/pagos")
    public ResponseEntity<RespuestaJson> listar(){
        List<Metodo_pago> metodo_pagos = servicio.findAll();

        if(metodo_pagos != null){
            RespuestaJson respuesta= new RespuestaJson ("True", "Personsas encontradas", metodo_pagos);

            return new ResponseEntity<RespuestaJson>(respuesta, HttpStatus.OK);
        }else{
            RespuestaJson respuesta = new RespuestaJson("false", "No se encontraron personas", null);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
    }

    //GUARDAR
    @PostMapping("/pagos")
    public Metodo_pago guardar(@RequestBody Metodo_pago metodo_pago){
        
        if(validacion.validarCamposRequeridos(metodo_pago)){
            return servicio.save(metodo_pago);
        }else{
            throw new IllegalArgumentException("Los datos ingresados son incorrectos");
        }
        
        
    }

    //SELECIONAR
    @GetMapping("/pagos/{id_metodo_pago}")
    public Metodo_pago getUnaPago(@PathVariable Integer id_metodo_pago){
        return servicio.findById(id_metodo_pago);
    }

    //EDITAR
    @PutMapping("/pagos/{id_metodo_pago}")
    public Metodo_pago modificar(@RequestBody Metodo_pago metodo_pago, @PathVariable Integer id_metodo_pago){
        Metodo_pago PagoActual = servicio.findById(id_metodo_pago);
        PagoActual.setPersonas(metodo_pago.getPersonas());
        PagoActual.setTipo_pago(metodo_pago.getTipo_pago());
        PagoActual.setValor(metodo_pago.getValor());
        PagoActual.setEstado(metodo_pago.getEstado());
        return servicio.save(metodo_pago);
    }

    //ELIMINAR
    @DeleteMapping("/pagos/{id_metodo_pago}")
    public void eliminar(@PathVariable Integer id_metodo_pago){
        servicio.delete(id_metodo_pago);
    }
}

class RespuestaJson {
    private  String estado;
    private String mensaje;
    private List<Metodo_pago> body;
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
    public List<Metodo_pago> getBody() {
        return body;
    }
    public void setBody(List<Metodo_pago> body) {
        this.body = body;
    }
    public RespuestaJson () {
    }
    public RespuestaJson (String estado, String mensaje, List<Metodo_pago> body) {
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