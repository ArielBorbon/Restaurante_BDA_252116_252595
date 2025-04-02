/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.Productos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author PC Gamer
 */
@Entity
@Table (name = "productos")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_producto")
    private Long id;
    
    @Column(name = "precio")
    private float precio;
    
    
    @Column (name = "nombre")
    private String nombre;
    
    @Column (name = "tipo")
    private String tipo;
    
    
        @OneToMany  ( mappedBy = "producto", cascade = { CascadeType.PERSIST} )         //union con productoOcupaIngrediente
    private List<ProductoOcupaIngrediente> productos;

    public Producto() {
    }

    public Producto(Long id, float precio, String nombre, String tipo, List<ProductoOcupaIngrediente> productos) {
        this.id = id;
        this.precio = precio;
        this.nombre = nombre;
        this.tipo = tipo;
        this.productos = productos;
    }

    public Producto(float precio, String nombre, String tipo, List<ProductoOcupaIngrediente> productos) {
        this.precio = precio;
        this.nombre = nombre;
        this.tipo = tipo;
        this.productos = productos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<ProductoOcupaIngrediente> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoOcupaIngrediente> productos) {
        this.productos = productos;
    }

        
        
        
        
        
        
        
        
        
        
        
        

    
}
