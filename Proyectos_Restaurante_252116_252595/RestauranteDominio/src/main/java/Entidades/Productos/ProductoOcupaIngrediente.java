/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.Productos;

import Entidades.Ingredientes.Ingrediente;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author PC Gamer
 */
@Entity
@Table (name = "productos_ingredientes")
public class ProductoOcupaIngrediente implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_productos_ingredientes")
    private Long id;
    
    @Column (name = "cantidad_necesaria" , nullable = false)
    private int cantidad_necesaria;
    

    @ManyToOne ()
    @JoinColumn (name = "id_ingrediente" , nullable = false)            //Union con ingredientes
    private Ingrediente ingrediente;
    
    
    @ManyToOne ()
    @JoinColumn (name = "id_producto" , nullable = false)               //union con producto
    private Producto producto;

    public ProductoOcupaIngrediente() {
    }

    public ProductoOcupaIngrediente(Long id, int cantidad_necesaria, Ingrediente ingrediente, Producto producto) {
        this.id = id;
        this.cantidad_necesaria = cantidad_necesaria;
        this.ingrediente = ingrediente;
        this.producto = producto;
    }

    public ProductoOcupaIngrediente(int cantidad_necesaria, Ingrediente ingrediente, Producto producto) {
        this.cantidad_necesaria = cantidad_necesaria;
        this.ingrediente = ingrediente;
        this.producto = producto;
    }
    

    
    
    
    
    
}
