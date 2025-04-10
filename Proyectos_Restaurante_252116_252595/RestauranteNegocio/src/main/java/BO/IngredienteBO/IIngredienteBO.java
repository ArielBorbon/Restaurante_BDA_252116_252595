/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BO.IngredienteBO;

import DTOS.Ingredientes.IngredienteConCantidadNecesariaDTO;
import DTOS.Ingredientes.NuevoIngredienteDTO;
import Entidades.Ingredientes.Ingrediente;
import NegocioException.NegocioException;
import java.util.List;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public interface IIngredienteBO {
    
    Ingrediente registrarIngredienteBO(NuevoIngredienteDTO nuevoIngrediente) throws Exception;
    
    void eliminarIngredienteBO(NuevoIngredienteDTO nuevoIngrediente) throws Exception;
    
    List<Ingrediente> obtenerListaIngredientesBO();
    
    void actualizarIngredienteBO(NuevoIngredienteDTO nuevoIngredienteDTO, double nuevoStock) throws Exception;
    
    Ingrediente buscarIngredientePorNombreYUnidadBO(String nombre, String unidadMedida) throws Exception;
    
    boolean tieneRelacionesActivasBO(String nombre, String unidadMedida) throws NegocioException;
    
    List<Ingrediente> obtenerIngredientesPorNombreProductoBO(String nombreProducto);
    
    List<IngredienteConCantidadNecesariaDTO> obtenerIngredientesConCantidadPorProductoBO(String nombreProducto);
}
