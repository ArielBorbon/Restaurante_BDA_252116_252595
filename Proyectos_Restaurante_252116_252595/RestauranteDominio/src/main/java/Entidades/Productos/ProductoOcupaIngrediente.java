/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.Productos;

import Entidades.Ingredientes.Ingrediente;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author PC Gamer
 */
@Entity
@Table (name = "productos_ingredientes" , uniqueConstraints = @UniqueConstraint( columnNames = {"id_producto", "id_ingrediente"} ))

public class ProductoOcupaIngrediente implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_productos_ingredientes")
    private Long id;
    
    @Column (name = "cantidad_necesaria" , nullable = false)
    private double cantidad_necesaria;
    

    @ManyToOne ()
    @JoinColumn (name = "id_ingrediente" , nullable = false)            //Union con ingredientes
    private Ingrediente ingrediente;
    
    
    @ManyToOne ()
    @JoinColumn (name = "id_producto" , nullable = false)               //union con producto
    private Producto producto;

    public ProductoOcupaIngrediente() {
    }

    public ProductoOcupaIngrediente(Long id, double cantidad_necesaria, Ingrediente ingrediente, Producto producto) {
        this.id = id;
        this.cantidad_necesaria = cantidad_necesaria;
        this.ingrediente = ingrediente;
        this.producto = producto;
    }

    public ProductoOcupaIngrediente(double cantidad_necesaria, Ingrediente ingrediente, Producto producto) {
        this.cantidad_necesaria = cantidad_necesaria;
        this.ingrediente = ingrediente;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCantidad_necesaria() {
        return cantidad_necesaria;
    }

    public void setCantidad_necesaria(double cantidad_necesaria) {
        this.cantidad_necesaria = cantidad_necesaria;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.cantidad_necesaria) ^ (Double.doubleToLongBits(this.cantidad_necesaria) >>> 32));
        hash = 17 * hash + Objects.hashCode(this.ingrediente);
        hash = 17 * hash + Objects.hashCode(this.producto);
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
        final ProductoOcupaIngrediente other = (ProductoOcupaIngrediente) obj;
        if (Double.doubleToLongBits(this.cantidad_necesaria) != Double.doubleToLongBits(other.cantidad_necesaria)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.ingrediente, other.ingrediente)) {
            return false;
        }
        return Objects.equals(this.producto, other.producto);
    }
    

    
    
    
    
    
}
