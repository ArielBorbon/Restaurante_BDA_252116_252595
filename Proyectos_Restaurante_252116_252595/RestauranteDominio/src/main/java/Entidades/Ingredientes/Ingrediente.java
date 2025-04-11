/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.Ingredientes;

import Entidades.Productos.ProductoOcupaIngrediente;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidad Ingredientes
 * 
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
@Entity
@Table(name = "Ingredientes")
public class Ingrediente implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_ingrediente")
    private Long id;
    
    @Column (name = "nombre", length = 70, nullable = false)
    private String nombre;
    
    @Column (name = "unidad_medida", length = 70, nullable = false)
    private String unidad_medida;
    
    @Column (name = "stock", nullable = false)
    private double stock;
    
    
    
    
        @OneToMany  ( mappedBy = "ingrediente", cascade = { CascadeType.PERSIST} )      //union con ProductoOcupaIngrediente
    private List<ProductoOcupaIngrediente> ingredientes;

    public Ingrediente() {
    }

    public Ingrediente(Long id, String nombre, String unidad_medida, double stock, List<ProductoOcupaIngrediente> ingredientes) {
        this.id = id;
        this.nombre = nombre;
        this.unidad_medida = unidad_medida;
        this.stock = stock;
        this.ingredientes = ingredientes;
    }

    public Ingrediente(String nombre, String unidad_medida, double stock, List<ProductoOcupaIngrediente> ingredientes) {
        this.nombre = nombre;
        this.unidad_medida = unidad_medida;
        this.stock = stock;
        this.ingredientes = ingredientes;
    }

    public Ingrediente(Long id, String nombre, String unidad_medida, double stock) {
        this.id = id;
        this.nombre = nombre;
        this.unidad_medida = unidad_medida;
        this.stock = stock;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public List<ProductoOcupaIngrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<ProductoOcupaIngrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.unidad_medida);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.stock) ^ (Double.doubleToLongBits(this.stock) >>> 32));
        hash = 37 * hash + Objects.hashCode(this.ingredientes);
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
        final Ingrediente other = (Ingrediente) obj;
        if (Double.doubleToLongBits(this.stock) != Double.doubleToLongBits(other.stock)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.unidad_medida, other.unidad_medida)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.ingredientes, other.ingredientes);
    }

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    
}
