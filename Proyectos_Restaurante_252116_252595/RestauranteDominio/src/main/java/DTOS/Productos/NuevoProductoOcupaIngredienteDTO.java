/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS.Productos;

import DTOS.Ingredientes.NuevoIngredienteDTO;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class NuevoProductoOcupaIngredienteDTO {

    private String nombreIngrediente;
    private String unidadMedida;
    private String nombreProducto;
    private double cantidadNecesariaProducto;

    public NuevoProductoOcupaIngredienteDTO() {
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public NuevoProductoOcupaIngredienteDTO(String nombreIngrediente, String unidadMedida, String nombreProducto, double cantidadNecesariaProducto) {
        this.nombreIngrediente = nombreIngrediente;
        this.unidadMedida = unidadMedida;
        this.nombreProducto = nombreProducto;
        this.cantidadNecesariaProducto = cantidadNecesariaProducto;
    }

    
    
    
    public NuevoProductoOcupaIngredienteDTO(String nombreIngrediente, String nombreProducto, double cantidadNecesariaProducto) {
        this.nombreIngrediente = nombreIngrediente;
        this.nombreProducto = nombreProducto;
        this.cantidadNecesariaProducto = cantidadNecesariaProducto;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getCantidadNecesariaProducto() {
        return cantidadNecesariaProducto;
    }

    public void setCantidadNecesariaProducto(double cantidadNecesariaProducto) {
        this.cantidadNecesariaProducto = cantidadNecesariaProducto;
    }

    



    
    
    
}
