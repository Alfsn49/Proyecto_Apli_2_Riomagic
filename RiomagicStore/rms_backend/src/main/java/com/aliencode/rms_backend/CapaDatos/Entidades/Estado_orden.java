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
@Table(name = "Estado_orden")
public class Estado_orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_estado_orden;

    @Column(name = "estado", nullable = false , length = 10)
    private String estado;
    
    @JsonIgnore
    @OneToMany(mappedBy = "estado_orden", cascade = CascadeType.ALL)
    private List<Orden_compra> orden_compra;
    
    //CONSTRUCTOR
    public Estado_orden(){

    }

    public Estado_orden(int id_estado_orden, String estado, List<Orden_compra> orden_compra) {
        super();
        this.id_estado_orden = id_estado_orden;
        this.estado = estado;
        this.orden_compra = orden_compra;
    }

    //SETT&GETT

    public int getId_estado_orden() {
        return id_estado_orden;
    }

    public void setId_estado_orden(int id_estado_orden) {
        this.id_estado_orden = id_estado_orden;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        return "Estado_orden [id_estado_orden=" + id_estado_orden + ", estado=" + estado + ", orden_compra="
        + orden_compra + "]";
    }
    
}