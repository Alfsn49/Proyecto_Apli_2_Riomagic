package com.aliencode.rms_backend.CapaDatos.Servicio.Persona;

import java.util.List;
import com.aliencode.rms_backend.CapaDatos.Entidades.Persona;
import com.aliencode.rms_backend.CapaDatos.Servicio.Persona.PersonaServicio;
import com.aliencode.rms_backend.Clase_generica.GenericClass;

public interface PersonaServicio extends GenericClass<Persona> {
    Persona findByEmail(String email);
}
