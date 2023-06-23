package com.aliencode.rms_backend.CapaDatos.Entidades;

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
import jakarta.persistence.Table;

@Entity
@Table(name = "Articulo_carrito")
public class Articulo_carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_articulo_carrito;

   
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable =false)
    private Producto producto;

  
    @ManyToOne
    @JoinColumn(name = "id_carrito_compra", nullable =false)
    private Carrito_compra carrito_compra;
    
    @Column(name = "cantidad",nullable = false)
    private int cantidad;
    
    @JsonIgnore
    @OneToMany(mappedBy =  "articulo_carrito", cascade = CascadeType.ALL)
    private List<Factura> facturas;

    //CONSTRUCTOR
    public Articulo_carrito(){

    }

    public Articulo_carrito(int id_articulo_carrito, Producto producto, Carrito_compra carrito_compra, int cantidad,
            List<Factura> facturas) {
        super();
        this.id_articulo_carrito = id_articulo_carrito;
        this.producto = producto;
        this.carrito_compra = carrito_compra;
        this.cantidad = cantidad;
        this.facturas = facturas;
    }

    //SETT&GETT
    
    public int getId_articulo_carrito() {
        return id_articulo_carrito;
    }

    public void setId_articulo_carrito(int id_articulo_carrito) {
        this.id_articulo_carrito = id_articulo_carrito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Carrito_compra getCarrito_compra() {
        return carrito_compra;
    }

    public void setCarrito_compra(Carrito_compra carrito_compra) {
        this.carrito_compra = carrito_compra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    //TOSTRING

    @Override
    public String toString() {
        return "Articulo_carrito [id_articulo_carrito=" + id_articulo_carrito + ", producto=" + producto
                + ", carrito_compra=" + carrito_compra + ", cantidad=" + cantidad + ", facturas=" + facturas + "]";
    }

   
}
