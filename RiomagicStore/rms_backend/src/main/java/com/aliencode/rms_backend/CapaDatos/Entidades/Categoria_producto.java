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
@Table(name = "Categoria_producto")
public class Categoria_producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_categoria_producto;
    
    @Column(name = "nombre_categoria", nullable = false , length = 50)
    private String nombre_categoria;
    
    @JsonIgnore
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Producto> productos;

    //CONSTRUCTOR
    public Categoria_producto(){

    }
    public Categoria_producto(int id_categoria_producto, String nombre_categoria, List<Producto> productos) {
        super();
        this.id_categoria_producto = id_categoria_producto;
        this.nombre_categoria = nombre_categoria;
        this.productos = productos;
    }

    //SETT&GETT
   
     public int getId_categoria_producto() {
        return id_categoria_producto;
    }
    public void setId_categoria_producto(int id_categoria_producto) {
        this.id_categoria_producto = id_categoria_producto;
    }
    public String getNombre_categoria() {
        return nombre_categoria;
    }
    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }
    public List<Producto> getProductos() {
        return productos;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Categoria_producto [id_categoria_producto=" + id_categoria_producto + ", nombre_categoria="
                + nombre_categoria + ", productos=" + productos + "]";
    }
    
}