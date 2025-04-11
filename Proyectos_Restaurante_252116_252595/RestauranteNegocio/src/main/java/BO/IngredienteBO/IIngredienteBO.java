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
 * Interfaz de IngredienteBO
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public interface IIngredienteBO {

    /**
     * Registra un nuevo ingrediente en el sistema.
     *
     * @param nuevoIngrediente Objeto DTO con la información del nuevo
     * ingrediente.
     * @return El ingrediente registrado.
     * @throws Exception Si ocurre un error durante el registro.
     */
    Ingrediente registrarIngredienteBO(NuevoIngredienteDTO nuevoIngrediente) throws Exception;

    /**
     * Elimina un ingrediente del sistema.
     *
     * @param nuevoIngrediente Ingrediente a eliminar.
     * @throws Exception Si el ingrediente no puede ser eliminado o no existe.
     */
    void eliminarIngredienteBO(NuevoIngredienteDTO nuevoIngrediente) throws Exception;

    /**
     * Obtiene la lista completa de ingredientes registrados.
     *
     * @return Lista de ingredientes.
     */
    List<Ingrediente> obtenerListaIngredientesBO();

    /**
     * Actualiza la información de un ingrediente, principalmente su stock.
     *
     * @param nuevoIngredienteDTO DTO con los datos actuales del ingrediente.
     * @param nuevoStock Nuevo valor de stock para el ingrediente.
     * @throws Exception Si ocurre un error durante la actualización.
     */
    void actualizarIngredienteBO(NuevoIngredienteDTO nuevoIngredienteDTO, double nuevoStock) throws Exception;

    /**
     * Busca un ingrediente por su nombre y unidad de medida.
     *
     * @param nombre Nombre del ingrediente.
     * @param unidadMedida Unidad de medida del ingrediente (ej. gramos,
     * litros).
     * @return El ingrediente encontrado.
     * @throws Exception Si no se encuentra el ingrediente o ocurre un error.
     */
    Ingrediente buscarIngredientePorNombreYUnidadBO(String nombre, String unidadMedida) throws Exception;

    /**
     * Verifica si un ingrediente tiene relaciones activas con productos.
     *
     * @param nombre Nombre del ingrediente.
     * @param unidadMedida Unidad de medida del ingrediente.
     * @return {@code true} si el ingrediente tiene relaciones activas,
     * {@code false} si no.
     * @throws NegocioException Si ocurre un error al verificar las relaciones.
     */
    boolean tieneRelacionesActivasBO(String nombre, String unidadMedida) throws NegocioException;

    /**
     * Obtiene los ingredientes que forman parte de un producto específico.
     *
     * @param nombreProducto Nombre del producto.
     * @return Lista de ingredientes usados en ese producto.
     */
    List<Ingrediente> obtenerIngredientesPorNombreProductoBO(String nombreProducto);

    /**
     * Obtiene los ingredientes necesarios para elaborar un producto, junto con
     * la cantidad requerida de cada uno.
     *
     * @param nombreProducto Nombre del producto.
     * @return Lista de ingredientes con sus cantidades necesarias encapsuladas
     * en un DTO.
     */
    List<IngredienteConCantidadNecesariaDTO> obtenerIngredientesConCantidadPorProductoBO(String nombreProducto);
}
