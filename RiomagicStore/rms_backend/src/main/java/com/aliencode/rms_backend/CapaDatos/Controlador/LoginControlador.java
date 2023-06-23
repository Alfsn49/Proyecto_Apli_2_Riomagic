package com.aliencode.rms_backend.CapaDatos.Controlador;

import java.security.Key;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aliencode.rms_backend.CapaDatos.Entidades.Persona;
import com.aliencode.rms_backend.CapaDatos.Entidades.Rol;
import com.aliencode.rms_backend.CapaDatos.Entidades.Usuario;
import com.aliencode.rms_backend.CapaDatos.Servicio.Persona.PersonaServicio;
import com.aliencode.rms_backend.CapaDatos.Servicio.Usuario.UsuarioServicio;
import com.aliencode.rms_backend.CapaSeguridad.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api")
public class LoginControlador {
    @Autowired
    private PersonaServicio personaServicio;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        // Verificar si las credenciales son válidas
        Persona persona = personaServicio.findByEmail(email);

        if (persona != null && persona.getClave().equals(password)) {
            // Las credenciales son válidas, se puede realizar el inicio de sesión

            // Generar el token JWT
            String token = generarTokenJWT(persona);

            // Verificar el rol del usuario
            String rol = obtenerTipoRol(persona);

            if (rol.equals("Administrador")) {
                return ResponseEntity.ok("Inicio de sesión exitoso como Administrador\nToken: " + token);
            } else if (rol.equals("Usuario")) {
                return ResponseEntity.ok("Inicio de sesión exitoso como Usuario\nToken: " + token);
            } else {
                // Rol desconocido o sin asignar
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Rol desconocido o sin asignar");
            }
        } else {
            // Las credenciales son inválidas
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    // Método para generar el token JWT
    private String generarTokenJWT(Persona persona) {
        Claims claims = Jwts.claims();
        claims.put("rol", obtenerTipoRol(persona));
        claims.put("email", persona.getEmail());
        claims.put("password", persona.getClave());

        return jwtUtil.generarTokenJWT(Integer.toString(persona.getId_persona()), claims);
    }

    // Método para obtener el tipo de rol del usuario
    private String obtenerTipoRol(Persona persona) {
        Rol rol = persona.getRol();
        if (rol != null) {
            return rol.getNombre_rol();
        } else {
            return "Sin rol asignado";
        }
    }
}
