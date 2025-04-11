/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.Productos;

import Entidades.Comandas.DetalleComanda;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidad productos
 * 
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
@Entity
@Table (name = "productos")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_producto")
    private Long id;
    
    @Column(name = "precio", nullable = false, length = 70)
    private double precio;
    
    
    @Column (name = "nombre", nullable = false, length = 70)
    private String nombre;
    
    @Enumerated(EnumType.STRING)
    @Column (name = "tipo", nullable = false , length = 70)
    private Tipo_Producto tipo;
    
    
    @Enumerated(EnumType.STRING)
    @Column (name = "estado" , nullable = false , length = 70)
    private Estado_Producto estado;
    
    
    @OneToMany  ( mappedBy = "producto", cascade = { CascadeType.PERSIST} )         //union con productoOcupaIngrediente
    private List<ProductoOcupaIngrediente> productos;
        
    @OneToMany(mappedBy = "producto", cascade = CascadeType.PERSIST)
    private List<DetalleComanda> detallesComanda = new ArrayList<>();

    public Producto(Long id, double precio, String nombre, Tipo_Producto tipo, Estado_Producto estado, List<ProductoOcupaIngrediente> productos) {
        this.id = id;
        this.precio = precio;
        this.nombre = nombre;
        this.tipo = tipo;
        this.estado = estado;
        this.productos = productos;
    }

        

    public Producto() {
    }

    public Producto(Long id, double precio, String nombre, Tipo_Producto tipo, List<ProductoOcupaIngrediente> productos, Estado_Producto estado) {
        this.id = id;
        this.precio = precio;
        this.nombre = nombre;
        this.tipo = tipo;
        this.productos = (productos != null) ? productos : new ArrayList<>();
        this.estado = estado;
    }

    public Producto(Long id, double precio, String nombre, Tipo_Producto tipo , Estado_Producto estado) {
        this.id = id;
        this.precio = precio;
        this.nombre = nombre;
        this.tipo = tipo;
        this.estado = estado;
    }
    
    

    public Producto(double precio, String nombre, Tipo_Producto tipo, List<ProductoOcupaIngrediente> productos) {
        this.precio = precio;
        this.nombre = nombre;
        this.tipo = tipo;
        this.productos = (productos != null) ? productos : new ArrayList<>();
    }

    public Estado_Producto getEstado() {
        return estado;
    }

    public void setEstado(Estado_Producto estado) {
        this.estado = estado;
    }
    
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo_Producto getTipo() {
        return tipo;
    }

    public void setTipo(Tipo_Producto tipo) {
        this.tipo = tipo;
    }

    public List<ProductoOcupaIngrediente> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoOcupaIngrediente> productos) {
        this.productos = productos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.precio) ^ (Double.doubleToLongBits(this.precio) >>> 32));
        hash = 17 * hash + Objects.hashCode(this.nombre);
        hash = 17 * hash + Objects.hashCode(this.tipo);
        hash = 17 * hash + Objects.hashCode(this.productos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        return Objects.equals(this.productos, other.productos);
    }

        
        
        
        
        
        
        
        
        
        
        
        

    
}
