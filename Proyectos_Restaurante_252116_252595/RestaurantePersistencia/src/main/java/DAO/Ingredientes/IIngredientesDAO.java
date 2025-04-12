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

    /**
     * Registra ingredientes nuevos
     *
     * @param nuevoIngrediente Manda un ingrediente DTO
     * @return regresa un nuevo ingrediente
     */
    Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngrediente);

    /**
     * Elimina ingredientes
     *
     * @param ingrediente manda un ingrediente
     */
    void eliminarIngrediente(Ingrediente ingrediente);

    /**
     * Muestra una lista de ingredientes
     *
     * @return regresa una lista de ingredientes
     */
    List<Ingrediente> mostrarListaIngredientes();

    /**
     * Actualiza un ingrediente
     *
     * @param nuevoIngredienteDTO manda un ingrediente DTO
     * @param nuevoStock manda una cantidad
     */
    void actualizarIngrediente(NuevoIngredienteDTO nuevoIngredienteDTO, double nuevoStock);

    /**
     * Busca un ingrediente por su nombre y unidad de medida
     *
     * @param nombre nombre del ingrediente
     * @param unidadMedida Unidad de medida del ingrediente
     * @return Regresa un ingrediente
     */
    Ingrediente buscarIngredientePorNombreYUnidad(String nombre, String unidadMedida);

    /**
     * Verificador de relaciones de ingredientes
     *
     * @param nombreIngrediente Nombre del ingrediente
     * @param unidadMedida Unidad de medida del ingrediente
     * @return Regresa un booleano
     */
    boolean tieneRelacionesActivas(String nombreIngrediente, String unidadMedida);

    /**
     * Obtiene una lista de ingredientes que esten relacionados con un producto
     *
     * @param nombreProducto Nombre del producto
     * @return regresa la lista
     */
    List<Ingrediente> obtenerIngredientesPorNombreProducto(String nombreProducto);

    /**
     * Lista de ingredientes con cantidades
     *
     * @param nombreProducto Nombre del producto
     * @return regresa la lista
     */
    List<IngredienteConCantidadNecesariaDTO> obtenerIngredientesConCantidadPorProducto(String nombreProducto);
}
