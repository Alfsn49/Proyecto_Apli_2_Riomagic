package com.aliencode.rms_backend.CapaLogica.ValidacionDatos;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import com.aliencode.rms_backend.CapaDatos.Entidades.Factura;

public class ValidacionTipoDato {

    private static final String REGEX_CARACTERES_ESPECIALES = "^[a-zA-Z0-9 ]*$";

    //VALIDACION PARA QUE NO SE INGRESE TEXTOS VACIOS O UN NULO
    public static boolean validarTexto(String texto) {
         Pattern patron = Pattern.compile("^[a-zA-Z]+$");
        return texto != null && !texto.isEmpty() && patron.matcher(texto).matches();
    }

    public static boolean validarEnteroPositivo(int numero) {
        return numero > 0;
    }

    public static boolean validarFloatPositivo(Float numero) {
        return numero > 0;
    }

    public static boolean validarEnteroNoNegativo(int numero) {
        return numero >= 0;
    }

    public static boolean validarCantidadStock(int numero) {
        return numero >= 0;
    }

    //VALIDACION PARA QUE NO INGREESE CARACTERES ESPECIALES
    public static boolean validarCaracteresEspeciales(String texto) {
        return texto != null && Pattern.matches(REGEX_CARACTERES_ESPECIALES, texto);
    }
    
    //VALIDACION codigo postlar con un maximo de 5 caracteres
     public static boolean validarLongitudNumerica(int numero) {
        String numeroStr = String.valueOf(numero);
        return numeroStr.length() == 5;
    }
    //VALIDACION PARA EL ESTADO QUE SOLO TENGA ESTOS TIPOS
    public static boolean validarEstado(String estado) {
        return estado.matches("(completado|procesando|cancelado)");
    }

    // Comparar la fecha recibida con la fecha actual
    public static boolean validarFechaLimite(Date fecha) {
        Calendar calendar = Calendar.getInstance(); 
        Date fechaActual = (Date) calendar.getTime(); 
        return !fecha.after(fechaActual);
    }

    public static boolean validarFecha(Factura factura) {
        Date fecha = factura.getFecha_factura();
        return fecha != null && fecha.getTime() != 0 && ValidacionTipoDato.validarFechaLimite(fecha);
    }


    // VALIDAR QUE UNA FACTURA NO SE REPITA O SEA UNICO//

    private static Set<Integer> numerosFactura;

    static {
        numerosFactura = new HashSet<>();
    }

    public static boolean validarNumeroFacturaUnico(int numeroFactura) {
        if (numerosFactura.contains(numeroFactura)) {
            return false; // El número de factura ya existe, no es único
        } else {
            numerosFactura.add(numeroFactura);
            return true; // El número de factura es único y se agrega al conjunto
        }
    }

    //VALIDACION PARA EL ESTADO QUE SOLO TENGA si esta o no cancelado el pago
    public static boolean validarEstadoPago(String estado) {
        return estado.matches("(pagano|No pagado)");
    }

    //VALIDACION PARA LA CEDULA QUE NO EXCEDA LOS 10 DIGITOS
    public static boolean validarCedula(String texto, int longitudMaxima) {
        return texto.length() <= longitudMaxima;
    }

    //VALIDACION PARA EL NUMERO DE TELEFONO que empieze por 09
    public static boolean validarTelefono(String telefono) {
        String patron = "^09\\d{8}$";
        return telefono.matches(patron) && telefono.length() <= 10;
    }

    //VALIDACION PARA QUE EL CORREO SEA VALIDO 
    public static boolean validarCorreo(String correo) {
        String patron = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(patron, correo);
    }

    public static boolean validarContrasenaSegura(String contrasena) {
        int longitudMinima = 8;
        String patron = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return contrasena.length() >= longitudMinima && Pattern.matches(patron, contrasena);
    }

    //VALIDACION PARA QUE SE AGREGE SOLO UN ENLACE EN LA IMGEN
    public static boolean validarEnlaceImagen(String enlace) {
        String patron = "^https?://[\\w.-]+(?:/\\S+)?$";
        return Pattern.matches(patron, enlace);
    }

    //VALIDAR QUE EL ROL SEA SOLO ADMIN Y CLIENTE 
    public static boolean validarRol(String rol) {
        return rol.equals("administrador") || rol.equals("cliente");
    }

    //VALIDAR DATO BOLEANBO
    public static boolean validarBooleano(boolean valor) {
        return valor == true || valor == false;
    }

    public static boolean validarBooleanoNoVacio(Boolean valor) {
        return valor != null && valor != false;
    }
}