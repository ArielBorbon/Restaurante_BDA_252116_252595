/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS.Ingredientes;

import Entidades.Ingredientes.Unidad_Medida;

/**
 *
 * @author PC Gamer
 */
public class IngredienteConCantidadNecesariaDTO {
    private String nombreIngrediente;
    private String unidadMedida;
    private double cantidadIngredienteNecesaria;

    public IngredienteConCantidadNecesariaDTO() {
    }

    public IngredienteConCantidadNecesariaDTO(String nombreIngrediente, String unidadMedida, double cantidadIngredienteNecesaria) {
        this.nombreIngrediente = nombreIngrediente;
        this.unidadMedida = unidadMedida;
        this.cantidadIngredienteNecesaria = cantidadIngredienteNecesaria;
    }
    
    

    
    
    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public double getCantidadIngredienteNecesaria() {
        return cantidadIngredienteNecesaria;
    }

    public void setCantidadIngredienteNecesaria(double cantidadIngredienteNecesaria) {
        this.cantidadIngredienteNecesaria = cantidadIngredienteNecesaria;
    }


    
    
    
}
