package com.aliencode.rms_backend.CapaDatos.Entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Metodo_envio")
public class Metodo_envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_metodo_envio;

    @Column(name = "nombre", nullable = false , length = 50)
    private String nombre;
    @Column(name = "precio",nullable = false)
    private float precio;
    
    @JsonIgnore
    @OneToMany(mappedBy = "metodo_envio", cascade = CascadeType.ALL)
    private List<Orden_compra> orden_compra;

    //CONSTRUCTOR
    public Metodo_envio(){

    }

    public Metodo_envio(int id_metodo_envio, String nombre, float precio, List<Orden_compra> orden_compra) {
        super();
        this.id_metodo_envio = id_metodo_envio;
        this.nombre = nombre;
        this.precio = precio;
        this.orden_compra = orden_compra;
    }

    //SETT&GETT
    public int getId_metodo_envio() {
        return id_metodo_envio;
    }

    public void setId_metodo_envio(int id_metodo_envio) {
        this.id_metodo_envio = id_metodo_envio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public List<Orden_compra> getOrden_compra() {
        return orden_compra;
    }

    public void setOrden_compra(List<Orden_compra> orden_compra) {
        this.orden_compra = orden_compra;
    }

    //TOSTRING

    @Override
    public String toString() {
        return "Metodo_envio [id_metodo_envio=" + id_metodo_envio + ", nombre=" + nombre + ", precio=" + precio
                + ", orden_compra=" + orden_compra + "]";
    }
    
}
