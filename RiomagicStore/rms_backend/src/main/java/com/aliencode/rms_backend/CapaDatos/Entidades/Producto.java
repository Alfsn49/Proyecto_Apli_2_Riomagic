package com.aliencode.rms_backend.CapaDatos.Entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producto;
	
    @ManyToOne
    @JoinColumn(name = "id_categoria_producto", nullable =false)
	private Categoria_producto categoria;

    @Column(name = "nombre", nullable = false , length = 50)
    private String nombre;

    @Column(name = "descripcion", nullable = false , length = 250)
    private String descripcion;

    @Column(name = "imagen_producto", nullable = false , length = 250)
    private String imagen_producto;

    @Column(name = "cantidad_stock", nullable = false)
    private int cantidad_stock;

    @Column(name = "precio", nullable = false)
    private float precio;
	
	@JsonIgnore
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Articulo_carrito> articulo_carritos;
	
	//CONSTRUCTOR
    public Producto(){

    }

	

	//SETT&GETT
	
	public Producto(int id_producto, Categoria_producto categoria, String nombre, String descripcion,
			String imagen_producto, int cantidad_stock, float precio, List<Articulo_carrito> articulo_carritos) {
		super();
		this.id_producto = id_producto;
		this.categoria = categoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen_producto = imagen_producto;
		this.cantidad_stock = cantidad_stock;
		this.precio = precio;
		this.articulo_carritos = articulo_carritos;
	}



	public int getId_producto() {
		return id_producto;
	}



	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}



	public Categoria_producto getCategoria() {
		return categoria;
	}



	public void setCategoria(Categoria_producto categoria) {
		this.categoria = categoria;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getImagen_producto() {
		return imagen_producto;
	}



	public void setImagen_producto(String imagen_producto) {
		this.imagen_producto = imagen_producto;
	}



	public int getCantidad_stock() {
		return cantidad_stock;
	}



	public void setCantidad_stock(int cantidad_stock) {
		this.cantidad_stock = cantidad_stock;
	}



	public float getPrecio() {
		return precio;
	}



	public void setPrecio(float precio) {
		this.precio = precio;
	}



	public List<Articulo_carrito> getArticulo_carritos() {
		return articulo_carritos;
	}



	public void setArticulo_carritos(List<Articulo_carrito> articulo_carritos) {
		this.articulo_carritos = articulo_carritos;
	}



	@Override
	public String toString() {
		return "Producto [id_producto=" + id_producto + ", categoria=" + categoria + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + ", imagen_producto=" + imagen_producto + ", cantidad_stock="
				+ cantidad_stock + ", precio=" + precio + ", articulo_carritos=" + articulo_carritos + "]";
	}

}