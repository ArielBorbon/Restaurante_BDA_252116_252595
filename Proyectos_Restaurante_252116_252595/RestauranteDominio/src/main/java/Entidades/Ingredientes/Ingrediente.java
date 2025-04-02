/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.Ingredientes;

import Entidades.Productos.ProductoOcupaIngrediente;
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
    private int stock;
    
    
    
    
        @OneToMany  ( mappedBy = "ingrediente", cascade = { CascadeType.PERSIST} )      //union con ProductoOcupaIngrediente
    private List<ProductoOcupaIngrediente> ingredientes;

    public Ingrediente() {
    }

    public Ingrediente(Long id, String nombre, String unidad_medida, int stock, List<ProductoOcupaIngrediente> ingredientes) {
        this.id = id;
        this.nombre = nombre;
        this.unidad_medida = unidad_medida;
        this.stock = stock;
        this.ingredientes = ingredientes;
    }

    public Ingrediente(String nombre, String unidad_medida, int stock, List<ProductoOcupaIngrediente> ingredientes) {
        this.nombre = nombre;
        this.unidad_medida = unidad_medida;
        this.stock = stock;
        this.ingredientes = ingredientes;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<ProductoOcupaIngrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<ProductoOcupaIngrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    
}
