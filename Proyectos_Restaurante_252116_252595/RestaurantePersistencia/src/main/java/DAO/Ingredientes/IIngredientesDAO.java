/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO.Ingredientes;

import DTOS.Ingredientes.IngredienteConCantidadNecesariaDTO;
import DTOS.Ingredientes.NuevoIngredienteDTO;
import Entidades.Ingredientes.Ingrediente;
import java.util.List;

/**
 * Interfaz de Ingredientes DAO
 * 
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public interface IIngredientesDAO {

    Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngrediente);
    
    void eliminarIngrediente(Ingrediente ingrediente);
    
    List<Ingrediente> mostrarListaIngredientes();
    
    void actualizarIngrediente(NuevoIngredienteDTO nuevoIngredienteDTO, double nuevoStock);
    
    Ingrediente buscarIngredientePorNombreYUnidad(String nombre, String unidadMedida);
    
    boolean tieneRelacionesActivas(String nombreIngrediente, String unidadMedida);
    
    List<Ingrediente> obtenerIngredientesPorNombreProducto(String nombreProducto);
    
    List<IngredienteConCantidadNecesariaDTO> obtenerIngredientesConCantidadPorProducto(String nombreProducto);
}
