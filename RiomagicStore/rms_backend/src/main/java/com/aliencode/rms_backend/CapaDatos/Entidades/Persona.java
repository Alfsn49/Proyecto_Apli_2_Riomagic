package com.aliencode.rms_backend.CapaDatos.Entidades;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_persona;
    
    @ManyToOne
    @JoinColumn(name = "id_rol", nullable =false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "id_direccion", nullable =false)
    private Direccion direccion;
 
    @Column(name = "cedula", nullable = false , length = 11)
    private String cedula;
    @Column(name = "nombres", nullable = false , length = 50)
    private String nombres;
    @Column(name = "apellidos", nullable = false , length = 50)
    private String apellidos;
    @Column(name = "fecha_nac", nullable = false)
    private Date fecha_nac;
    @Column(name = "telefono", nullable = false , length = 10)
    private String telefono;
    @Column(name = "email", nullable = false , length = 50)
    private String email;
    @Column(name = "clave", nullable = false , length = 50)
    private String clave;

    @JsonIgnore
    @OneToMany(mappedBy = "personas", cascade = CascadeType.ALL)
    private List<Orden_compra> orden_compra;

    @JsonIgnore
    @OneToOne(mappedBy = "personas", cascade = CascadeType.ALL)
    private Opinion_usuario  opinion_usuario;

    @JsonIgnore
    @OneToOne(mappedBy = "personas", cascade = CascadeType.ALL)
    private Factura factura;

    @JsonIgnore
    @OneToOne(mappedBy ="personas", cascade = CascadeType.ALL)
    private Carrito_compra carrito_compra;
    
    @JsonIgnore
    @OneToOne(mappedBy = "personas", cascade = CascadeType.ALL)
    private Metodo_pago metodo_pago;
    //CONSTRUCTOR

    public Persona(){

    }

    public Persona(int id_persona, Rol rol, Direccion direccion, String cedula, String nombres, String apellidos,
            Date fecha_nac, String telefono, String email, String clave, List<Orden_compra> orden_compra,
            Opinion_usuario opinion_usuario, Factura factura, Carrito_compra carrito_compra, Metodo_pago metodo_pago) {
        this.id_persona = id_persona;
        this.rol = rol;
        this.direccion = direccion;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha_nac = fecha_nac;
        this.telefono = telefono;
        this.email = email;
        this.clave = clave;
        this.orden_compra = orden_compra;
        this.opinion_usuario = opinion_usuario;
        this.factura = factura;
        this.carrito_compra = carrito_compra;
        this.metodo_pago = metodo_pago;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
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

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
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

    public List<Orden_compra> getOrden_compra() {
        return orden_compra;
    }

    public void setOrden_compra(List<Orden_compra> orden_compra) {
        this.orden_compra = orden_compra;
    }

    public Opinion_usuario getOpinion_usuario() {
        return opinion_usuario;
    }

    public void setOpinion_usuario(Opinion_usuario opinion_usuario) {
        this.opinion_usuario = opinion_usuario;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Carrito_compra getCarrito_compra() {
        return carrito_compra;
    }

    public void setCarrito_compra(Carrito_compra carrito_compra) {
        this.carrito_compra = carrito_compra;
    }

    public Metodo_pago getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(Metodo_pago metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    @Override
    public String toString() {
        return "Persona [id_persona=" + id_persona + ", rol=" + rol + ", direccion=" + direccion + ", cedula=" + cedula
                + ", nombres=" + nombres + ", apellidos=" + apellidos + ", fecha_nac=" + fecha_nac + ", telefono="
                + telefono + ", email=" + email + ", clave=" + clave + ", orden_compra=" + orden_compra
                + ", opinion_usuario=" + opinion_usuario + ", factura=" + factura + ", carrito_compra=" + carrito_compra
                + ", metodo_pago=" + metodo_pago + "]";
    }

}