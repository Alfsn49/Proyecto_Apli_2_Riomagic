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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_rol;

    @Column(name = "nombre_rol", nullable = false , length = 50)
    private String nombre_rol;
    @Column(name = "estado",nullable = false)
    private boolean estado;

    @JsonIgnore
    @OneToOne(mappedBy = "rol", cascade = CascadeType.ALL)
    private Usuario usuario;
    
    @JsonIgnore
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    private List< Persona> persona;

    //CONSTRUCTOR
    public Rol(){
        
    }

    public Rol(int id_rol, String nombre_rol, boolean estado, Usuario usuario, List<Persona> persona) {
        this.id_rol = id_rol;
        this.nombre_rol = nombre_rol;
        this.estado = estado;
        this.usuario = usuario;
        this.persona = persona;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Persona> getPersona() {
        return persona;
    }

    public void setPersona(List<Persona> persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "Rol [id_rol=" + id_rol + ", nombre_rol=" + nombre_rol + ", estado=" + estado + ", usuario=" + usuario
                + ", persona=" + persona + "]";
    }
    
}