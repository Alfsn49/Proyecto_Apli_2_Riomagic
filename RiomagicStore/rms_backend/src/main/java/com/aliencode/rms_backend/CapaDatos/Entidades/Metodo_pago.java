package com.aliencode.rms_backend.CapaDatos.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Metodo_pago")
public class Metodo_pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_metodo_pago;

    @OneToOne
    @JoinColumn(name="id_persona", nullable =false)
    private Persona personas;
    
    @Column(name = "tipo_pago", nullable = false , length = 50)
    private String tipo_pago;

    @Column(name = "valor",nullable = false)
    private float valor;
    @Column(name = "estado", nullable = false , length = 20)
    private String estado;
    
    @JsonIgnore
    @OneToOne(mappedBy = "metodo_pago", cascade = CascadeType.ALL)
    private Orden_compra orden_compras;
    //CONSTRUCTOR
    public Metodo_pago(){

    }
    public Metodo_pago(int id_metodo_pago, Persona personas, String tipo_pago, float valor, String estado,
            Orden_compra orden_compras) {
        this.id_metodo_pago = id_metodo_pago;
        this.personas = personas;
        this.tipo_pago = tipo_pago;
        this.valor = valor;
        this.estado = estado;
        this.orden_compras = orden_compras;
    }
    public int getId_metodo_pago() {
        return id_metodo_pago;
    }
    public void setId_metodo_pago(int id_metodo_pago) {
        this.id_metodo_pago = id_metodo_pago;
    }
    public Persona getPersonas() {
        return personas;
    }
    public void setPersonas(Persona personas) {
        this.personas = personas;
    }
    public String getTipo_pago() {
        return tipo_pago;
    }
    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Orden_compra getOrden_compras() {
        return orden_compras;
    }
    public void setOrden_compras(Orden_compra orden_compras) {
        this.orden_compras = orden_compras;
    }
    @Override
    public String toString() {
        return "Metodo_pago [id_metodo_pago=" + id_metodo_pago + ", personas=" + personas + ", tipo_pago=" + tipo_pago
                + ", valor=" + valor + ", estado=" + estado + ", orden_compras=" + orden_compras + "]";
    }
    
}