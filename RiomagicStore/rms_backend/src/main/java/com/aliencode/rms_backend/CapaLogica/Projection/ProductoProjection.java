package com.aliencode.rms_backend.CapaLogica.Projection;


import com.fasterxml.jackson.annotation.JsonView;

public interface ProductoProjection {
    interface BasicInfo {}

    @JsonView(BasicInfo.class)
    int getId_producto();

    @JsonView(BasicInfo.class)
    String getNombre();

    @JsonView(BasicInfo.class)
    String getDescripcion();

    @JsonView(BasicInfo.class)
    double getPrecio();

    @JsonView(BasicInfo.class)
    String getCategoriaNombre();
}
