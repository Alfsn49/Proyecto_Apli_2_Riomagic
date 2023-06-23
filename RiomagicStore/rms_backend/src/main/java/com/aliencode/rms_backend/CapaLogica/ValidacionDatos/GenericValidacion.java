package com.aliencode.rms_backend.CapaLogica.ValidacionDatos;
import java.sql.Date;

import com.aliencode.rms_backend.CapaDatos.Entidades.Articulo_carrito;
import com.aliencode.rms_backend.CapaDatos.Entidades.Categoria_producto;
import com.aliencode.rms_backend.CapaDatos.Entidades.Direccion;
import com.aliencode.rms_backend.CapaDatos.Entidades.Estado_orden;
import com.aliencode.rms_backend.CapaDatos.Entidades.Factura;
import com.aliencode.rms_backend.CapaDatos.Entidades.Metodo_envio;
import com.aliencode.rms_backend.CapaDatos.Entidades.Metodo_pago;
import com.aliencode.rms_backend.CapaDatos.Entidades.Opinion_usuario;
import com.aliencode.rms_backend.CapaDatos.Entidades.Orden_compra;
import com.aliencode.rms_backend.CapaDatos.Entidades.Pais;
import com.aliencode.rms_backend.CapaDatos.Entidades.Persona;
import com.aliencode.rms_backend.CapaDatos.Entidades.Producto;
import com.aliencode.rms_backend.CapaDatos.Entidades.Rol;
import com.aliencode.rms_backend.CapaDatos.Entidades.Usuario;

public class GenericValidacion <T>{
    public boolean validarCamposRequeridos(T entidad) {

        // Lógica para validar los campos requeridos de la entidad "Articulo Carrito"
        if (entidad instanceof Articulo_carrito) {
            Articulo_carrito articulo_carrito = (Articulo_carrito) entidad;
            return ValidacionTipoDato.validarEnteroPositivo(articulo_carrito.getCantidad());
            // Lógica para validar los campos requeridos de la entidad "Categoria Producto"
        }else if (entidad instanceof Categoria_producto){
            Categoria_producto categoria_producto = (Categoria_producto) entidad;
            return ValidacionTipoDato.validarTexto(categoria_producto.getNombre_categoria())
            && ValidacionTipoDato.validarCaracteresEspeciales(categoria_producto.getNombre_categoria());
            // Lógica para validar los campos requeridos de la entidad "Direccion"
        }else if (entidad instanceof Direccion){
            Direccion direccion = (Direccion) entidad;
            return ValidacionTipoDato.validarTexto(direccion.getProvincia())
            && ValidacionTipoDato.validarCaracteresEspeciales(direccion.getProvincia())
            && ValidacionTipoDato.validarTexto(direccion.getCuidad())
            && ValidacionTipoDato.validarCaracteresEspeciales(direccion.getCuidad())
            && ValidacionTipoDato.validarLongitudNumerica(direccion.getCodigo_postal())
            && ValidacionTipoDato.validarEnteroNoNegativo(direccion.getCodigo_postal())
            && ValidacionTipoDato.validarTexto(direccion.getCalle_principal())
            && ValidacionTipoDato.validarCaracteresEspeciales(direccion.getCalle_principal())
            && ValidacionTipoDato.validarTexto(direccion.getCalle_secundaria())
            && ValidacionTipoDato.validarCaracteresEspeciales(direccion.getCalle_secundaria());
            // Lógica para validar los campos requeridos de la entidad "Estado Orden"
        }else if (entidad instanceof Estado_orden){
            Estado_orden estado_orden = (Estado_orden) entidad;
            return ValidacionTipoDato.validarTexto(estado_orden.getEstado())
            && ValidacionTipoDato.validarCaracteresEspeciales(estado_orden.getEstado())
            && ValidacionTipoDato.validarEstado(estado_orden.getEstado());
            // Lógica para validar los campos requeridos de la entidad "Factura"
        }else if (entidad instanceof Factura){
            Factura factura = (Factura) entidad;
            Date fecha = factura.getFecha_factura();
            int numeroFactura = factura.getNum_Factura();
            return ValidacionTipoDato.validarEnteroPositivo(factura.getNum_Factura())
            && ValidacionTipoDato.validarNumeroFacturaUnico(numeroFactura)
            && ValidacionTipoDato.validarFecha(factura) 
            && ValidacionTipoDato.validarFechaLimite(fecha)
            && ValidacionTipoDato.validarFloatPositivo(factura.getSubtotal_sinIVA());
        } else if (entidad instanceof Metodo_envio){
            Metodo_envio metodo_envio = (Metodo_envio) entidad;
            return ValidacionTipoDato.validarTexto(metodo_envio.getNombre())
            && ValidacionTipoDato.validarCaracteresEspeciales(metodo_envio.getNombre())
            && ValidacionTipoDato.validarFloatPositivo(metodo_envio.getPrecio());
        } else if (entidad instanceof Metodo_pago){
            Metodo_pago metodo_pago = (Metodo_pago) entidad;
            return ValidacionTipoDato.validarTexto(metodo_pago.getTipo_pago())
            && ValidacionTipoDato.validarCaracteresEspeciales(metodo_pago.getTipo_pago())
            && ValidacionTipoDato.validarFloatPositivo(metodo_pago.getValor())
            && ValidacionTipoDato.validarTexto(metodo_pago.getEstado())
            && ValidacionTipoDato.validarCaracteresEspeciales(metodo_pago.getEstado())
            && ValidacionTipoDato.validarEstadoPago(metodo_pago.getEstado());
        }else if (entidad instanceof Opinion_usuario){
            Opinion_usuario opinion_usuario = (Opinion_usuario) entidad;
            return ValidacionTipoDato.validarEnteroPositivo(opinion_usuario.getValor())
            && ValidacionTipoDato.validarLongitudNumerica(opinion_usuario.getValor())
            && ValidacionTipoDato.validarCaracteresEspeciales(opinion_usuario.getComentario());
        }else if (entidad instanceof Orden_compra){
            Orden_compra orden_compra = (Orden_compra) entidad;
            Date fecha = orden_compra.getFecha_orden();
            return ValidacionTipoDato.validarFechaLimite(fecha)
            && ValidacionTipoDato.validarFloatPositivo(orden_compra.getTotal_orden());
        }else if (entidad instanceof Pais){
            Pais pais = (Pais) entidad;
            return ValidacionTipoDato.validarTexto(pais.getNombre_pais())
            && ValidacionTipoDato.validarCaracteresEspeciales(pais.getNombre_pais());
        }else if (entidad instanceof Persona){
            Persona persona = (Persona) entidad;
            Date fecha = persona.getFecha_nac();
            return ValidacionTipoDato.validarTexto(persona.getCedula())
            && ValidacionTipoDato.validarCaracteresEspeciales(persona.getCedula())
            && ValidacionTipoDato.validarCedula(persona.getCedula(), 10)
            && ValidacionTipoDato.validarTexto(persona.getNombres())
            && ValidacionTipoDato.validarCaracteresEspeciales(persona.getNombres())
            && ValidacionTipoDato.validarTexto(persona.getApellidos())
            && ValidacionTipoDato.validarCaracteresEspeciales(persona.getApellidos())
            && ValidacionTipoDato.validarFechaLimite(fecha)
            && ValidacionTipoDato.validarTexto(persona.getTelefono())
            && ValidacionTipoDato.validarCaracteresEspeciales(persona.getTelefono())
            && ValidacionTipoDato.validarTelefono(persona.getTelefono())
            && ValidacionTipoDato.validarTexto(persona.getEmail())
            && ValidacionTipoDato.validarCorreo(persona.getEmail())
            && ValidacionTipoDato.validarTexto(persona.getClave())
            && ValidacionTipoDato.validarContrasenaSegura(persona.getClave());
        }else if (entidad instanceof Producto){
            Producto producto = (Producto) entidad;
            return ValidacionTipoDato.validarTexto(producto.getNombre())
            && ValidacionTipoDato.validarCaracteresEspeciales(producto.getNombre())
            && ValidacionTipoDato.validarTexto(producto.getDescripcion())
            && ValidacionTipoDato.validarCaracteresEspeciales(producto.getDescripcion())
            && ValidacionTipoDato.validarTexto(producto.getImagen_producto())
            && ValidacionTipoDato.validarEnlaceImagen(producto.getImagen_producto())
            && ValidacionTipoDato.validarCantidadStock(producto.getCantidad_stock())
            && ValidacionTipoDato.validarFloatPositivo(producto.getPrecio());
        }else if (entidad instanceof Rol){
            Rol rol = (Rol) entidad;
            return ValidacionTipoDato.validarTexto(rol.getNombre_rol())
            && ValidacionTipoDato.validarCaracteresEspeciales(rol.getNombre_rol())
            && ValidacionTipoDato.validarRol(rol.getNombre_rol())
            && ValidacionTipoDato.validarBooleanoNoVacio(rol.isEstado())
            && ValidacionTipoDato.validarBooleano(rol.isEstado());
        }else if (entidad instanceof Usuario){
            Usuario usuario = (Usuario) entidad;
            return ValidacionTipoDato.validarTexto(usuario.getCedula())
            && ValidacionTipoDato.validarCaracteresEspeciales(usuario.getCedula())
            && ValidacionTipoDato.validarCedula(usuario.getCedula(), 10)
            && ValidacionTipoDato.validarTexto(usuario.getNombres())
            && ValidacionTipoDato.validarCaracteresEspeciales(usuario.getNombres())
            && ValidacionTipoDato.validarTexto(usuario.getApellidos())
            && ValidacionTipoDato.validarCaracteresEspeciales(usuario.getApellidos())
            && ValidacionTipoDato.validarTexto(usuario.getDireccion())
            && ValidacionTipoDato.validarCaracteresEspeciales(usuario.getDireccion())
            && ValidacionTipoDato.validarTexto(usuario.getTelefono())
            && ValidacionTipoDato.validarCaracteresEspeciales(usuario.getTelefono())
            && ValidacionTipoDato.validarTelefono(usuario.getTelefono())
            && ValidacionTipoDato.validarTexto(usuario.getEmail())
            && ValidacionTipoDato.validarCorreo(usuario.getEmail())
            && ValidacionTipoDato.validarTexto(usuario.getClave())
            && ValidacionTipoDato.validarContrasenaSegura(usuario.getClave());
        }

        return false;
    }

    
}
