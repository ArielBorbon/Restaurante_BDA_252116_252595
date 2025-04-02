/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS.Productos;

import Entidades.Productos.Tipo_Producto;

/**
 *
 * @author PC Gamer
 */
public class NuevoProductoDTO {
    private double precio;
    private String nombre;
    private Tipo_Producto tipo;

    public NuevoProductoDTO() {
    }

    
    
    
    public NuevoProductoDTO(double precio, String nombre, Tipo_Producto tipo) {
        this.precio = precio;
        this.nombre = nombre;
        this.tipo = tipo;
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


    
    
    
}
