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
@Table(name = "Opinion_usuario")
public class Opinion_usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_opinion_usuario;

    @OneToOne
    @JoinColumn(name = "id_persona", nullable =false)
    private Persona personas;
    
    @OneToOne
    @JoinColumn(name = "id_orden_compra", nullable =false)
    private Orden_compra orden_compra;

    @Column(name = "valor",nullable = false)
    private int valor;
    @Column(name = "tomentario", nullable = false , length = 50)
    private String comentario;

    //CONSTRUCTOR
    public Opinion_usuario(){

    }

    public Opinion_usuario(int id_opinion_usuario, Persona personas, Orden_compra orden_compra, int valor,
            String comentario) {
        super();        
        this.id_opinion_usuario = id_opinion_usuario;
        this.personas = personas;
        this.orden_compra = orden_compra;
        this.valor = valor;
        this.comentario = comentario;
    }

    //SETT&GETT

    public int getId_opinion_usuario() {
        return id_opinion_usuario;
    }

    public void setId_opinion_usuario(int id_opinion_usuario) {
        this.id_opinion_usuario = id_opinion_usuario;
    }

    public Persona getPersonas() {
        return personas;
    }

    public void setPersonas(Persona personas) {
        this.personas = personas;
    }

    public Orden_compra getOrden_compra() {
        return orden_compra;
    }

    public void setOrden_compra(Orden_compra orden_compra) {
        this.orden_compra = orden_compra;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    //TOSTRING

    @Override
    public String toString() {
        return "Opinion_usuario [id_opinion_usuario=" + id_opinion_usuario + ", personas=" + personas
                + ", orden_compra=" + orden_compra + ", valor=" + valor + ", comentario=" + comentario + "]";
    }

}