/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS.Ingredientes;

/**
 * Clase DTO para ingredientes
 * 
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class NuevoIngredienteDTO {
    private String nombre;
    private String unidad_medida;
    private double stock;  

    public NuevoIngredienteDTO() {
    }

    public NuevoIngredienteDTO(String nombre, String unidad_medida, double stock) {
        this.nombre = nombre;
        this.unidad_medida = unidad_medida;
        this.stock = stock;
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


    
    
    
}
