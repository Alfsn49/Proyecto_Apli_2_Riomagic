package com.aliencode.rms_backend.CapaDatos.Entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Carrito_compra")
public class Carrito_compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_carrito_compra;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="id_persona", nullable =false)
    private Persona personas;
    
    @JsonIgnore
    @OneToMany(mappedBy="carrito_compra", cascade = CascadeType.ALL)
    private List<Articulo_carrito> articulo_carrito;

    //CONSTRUCTOR
    public Carrito_compra(){

    }

    public Carrito_compra(int id_carrito_compra, Persona personas, List<Articulo_carrito> articulo_carrito) {
        super();
        this.id_carrito_compra = id_carrito_compra;
        this.personas = personas;
        this.articulo_carrito = articulo_carrito;
    }

    //SETT&GETT

    public int getId_carrito_compra() {
        return id_carrito_compra;
    }

    public void setId_carrito_compra(int id_carrito_compra) {
        this.id_carrito_compra = id_carrito_compra;
    }

    public Persona getPersonas() {
        return personas;
    }

    public void setPersonas(Persona personas) {
        this.personas = personas;
    }

    public List<Articulo_carrito> getArticulo_carrito() {
        return articulo_carrito;
    }

    public void setArticulo_carrito(List<Articulo_carrito> articulo_carrito) {
        this.articulo_carrito = articulo_carrito;
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Carrito_compra [id_carrito_compra=" + id_carrito_compra + ", personas=" + personas
                + ", articulo_carrito=" + articulo_carrito + "]";
    }
}
