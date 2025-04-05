/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO.Ingredientes;

import DTOS.Ingredientes.NuevoIngredienteDTO;
import Entidades.Ingredientes.Ingrediente;
import java.util.List;

/**
 *
 * @author PC Gamer
 */
public interface IIngredientesDAO {

    Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngrediente);
    
    void eliminarIngrediente(Ingrediente ingrediente);
    
    List<Ingrediente> mostrarListaIngredientes();
    
    void actualizarIngrediente(NuevoIngredienteDTO nuevoIngredienteDTO, double nuevoStock);
    
    Ingrediente buscarIngredientePorNombreYUnidad(String nombre, String unidadMedida);
    
    boolean tieneRelacionesActivas(String nombreIngrediente, String unidadMedida);
    
    public List<Ingrediente> obtenerIngredientesPorNombreProducto(String nombreProducto);
}
