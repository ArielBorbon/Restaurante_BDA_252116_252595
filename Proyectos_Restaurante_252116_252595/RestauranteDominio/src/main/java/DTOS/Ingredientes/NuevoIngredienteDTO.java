/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS.Ingredientes;

/**
 *
 * @author PC Gamer
 */
public class NuevoIngredienteDTO {
    private String nombre;
    private String unidad_medida;
    private int stock;  

    public NuevoIngredienteDTO() {
    }

    public NuevoIngredienteDTO(String nombre, String unidad_medida, int stock) {
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    
    
    
}
