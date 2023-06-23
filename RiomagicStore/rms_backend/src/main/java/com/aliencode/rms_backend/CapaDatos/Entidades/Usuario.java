package com.aliencode.rms_backend.CapaDatos.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;
    
    @OneToOne
    @JoinColumn(name = "id_rol", nullable =false)
    private Rol rol;

    @Column(name = "cedula", nullable = false , length = 11)
    private String cedula;
    @Column(name = "nombres", nullable = false , length = 50)
    private String nombres;
    @Column(name = "apellidos", nullable = false , length = 50)
    private String apellidos;
    @Column(name = "direccion", nullable = false , length = 50)
    private String direccion;
    @Column(name = "telefono", nullable = false , length = 10)
    private String telefono;
    @Column(name = "email", nullable = false , length = 50)
    private String email;
    @Column(name = "clave", nullable = false , length = 50)
    private String clave;
    
    //CONSTRUCTOR
    public Usuario(){

    }

    public Usuario(int id_usuario, Rol rol, String cedula, String nombres, String apellidos, String direccion,
            String telefono, String email, String clave) {
        super();
        this.id_usuario = id_usuario;
        this.rol = rol;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.clave = clave;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        return "Usuario [id_usuario=" + id_usuario + ", rol=" + rol + ", cedula=" + cedula + ", nombres=" + nombres
                + ", apellidos=" + apellidos + ", direccion=" + direccion + ", telefono=" + telefono + ", email="
                + email + ", clave=" + clave + "]";
    }
}