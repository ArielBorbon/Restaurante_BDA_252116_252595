/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS.Productos;

import Entidades.Productos.Estado_Producto;
import static Entidades.Productos.Estado_Producto.HABILITADO;
import Entidades.Productos.Tipo_Producto;

/**
 * Clase DTO para productos
 * 
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class NuevoProductoDTO {
    private double precio;
    private String nombre;
    private Tipo_Producto tipo;
    private Estado_Producto estado = HABILITADO;

    public NuevoProductoDTO() {
    }

    public NuevoProductoDTO(double precio, String nombre, Tipo_Producto tipo) {
        this.precio = precio;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    
    
    
    public NuevoProductoDTO(double precio, String nombre, Tipo_Producto tipo , Estado_Producto estado) {
        this.precio = precio;
        this.nombre = nombre;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Estado_Producto getEstado() {
        return estado;
    }

    public void setEstado(Estado_Producto estado) {
        this.estado = estado;
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
