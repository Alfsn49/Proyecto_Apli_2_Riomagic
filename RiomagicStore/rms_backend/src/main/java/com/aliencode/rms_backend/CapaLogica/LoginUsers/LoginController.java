// package com.aliencode.rms_backend.CapaLogica.LoginUsers;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.aliencode.rms_backend.CapaDatos.Entidades.Persona;
// import com.aliencode.rms_backend.CapaDatos.Servicio.Persona.PersonaServicio;
// import io.jsonwebtoken.JwtBuilder;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;

// import java.security.Key;
// import java.util.Date;
// import java.util.Map;

// @RestController
// @CrossOrigin(originPatterns = "*")
// @RequestMapping("/api")
// public class LoginController{
//     @Autowired
//     private PersonaServicio servicio;

//     @PostMapping("/login")
//     public ResponseEntity<String> login(@RequestBody Map<String, String> loginData) {
//         String username = loginData.get("username");
//         String password = loginData.get("password");
        
//         Persona persona = servicio.findByEmail(username);
//         if (persona != null && password.equals(persona.getClave())) {
//             String token = generateAuthToken(persona.getId_persona());
//             return ResponseEntity.ok(token);
//         }
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
//         }

//         @GetMapping("login/personas/{id_persona}")
//         public ResponseEntity<Persona> getPersonaPorId(@PathVariable Integer id_persona) {
//         Persona persona = servicio.findById(id_persona);
//         if (persona != null) {
//             return ResponseEntity.ok(persona);
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }


//     private String generateAuthToken(Integer personaId) {
//         long expMillis = System.currentTimeMillis() + 3600000;
//         Date exp = new Date(expMillis);
//         JwtBuilder builder = Jwts.builder()
//             .setId(personaId.toString())
//             .setExpiration(exp)
//             .signWith(getSigningKey());
//         String token = builder.compact();
//         return token;
//     }

//     private Key getSigningKey() {
//     Key signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//     return signingKey;
// }
// }