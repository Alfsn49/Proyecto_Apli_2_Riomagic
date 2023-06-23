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
@Table(name = "Pais")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pais;

    @Column(name = "nombre_pais", nullable = false , length = 50)
    private String nombre_pais;
    
    @JsonIgnore
    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL)
    private List<Direccion> direcciones;
    
    //CONSTRUCTOR
    public Pais(){

    }

    //SETT&GETT
    public Pais(int id_pais, String nombre_pais, List<Direccion> direcciones) {
        super();
        this.id_pais = id_pais;
        this.nombre_pais = nombre_pais;
        this.direcciones = direcciones;
    }

    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    public String getNombre_pais() {
        return nombre_pais;
    }

    public void setNombre_pais(String nombre_pais) {
        this.nombre_pais = nombre_pais;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Pais [id_pais=" + id_pais + ", nombre_pais=" + nombre_pais + ", direcciones=" + direcciones + "]";
    }
}