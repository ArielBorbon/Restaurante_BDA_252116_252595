/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BO.ProductoBO;

import DTOS.Productos.NuevoProductoDTO;
import DTOS.Productos.NuevoProductoOcupaIngredienteDTO;
import Entidades.Ingredientes.Ingrediente;
import Entidades.Productos.Producto;
import Entidades.Productos.Tipo_Producto;
import NegocioException.NegocioException;
import java.util.List;

/**
 * Interfaz de productoBO
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public interface IProductoBO {

    /**
     *
     * @return
     */
    List<Producto> mostrarListaProductosDisponiblesBO();

    /**
     *
     * @param nombreFiltro
     * @return
     */
    List<Producto> filtrarPorNombreProductoDisponiblesBO(String nombreFiltro);

    /**
     *
     * @param tipoFiltro
     * @return
     */
    List<Producto> filtrarPorTipoProductoDisponiblesBO(Tipo_Producto tipoFiltro);

    /**
     *
     * @param nombreFiltro
     * @param tipoFiltro
     * @return
     */
    List<Producto> filtrarPorNombreYTipoProductoDisponiblesBO(String nombreFiltro, Tipo_Producto tipoFiltro);

    /**
     *
     * @param nuevoProducto
     * @return
     * @throws NegocioException
     */
    Producto registrarProductoBO(NuevoProductoDTO nuevoProducto) throws NegocioException;

    /**
     *
     * @param productoAEliminar
     * @throws NegocioException
     */
    void deshabilitarProductoBO(NuevoProductoDTO productoAEliminar) throws NegocioException;

    /**
     *
     * @param nuevoProducto
     * @param listaProductoIngrediente
     * @return
     * @throws NegocioException
     */
    Producto registrarProductoConIngredientesBO(NuevoProductoDTO nuevoProducto,
            List<NuevoProductoOcupaIngredienteDTO> listaProductoIngrediente) throws NegocioException;

    /**
     *
     * @param nuevoProductoDTO
     * @param nuevosIngredientesDTO
     * @return
     * @throws NegocioException
     */
    Producto modificarProductoBO(NuevoProductoDTO nuevoProductoDTO, List<NuevoProductoOcupaIngredienteDTO> nuevosIngredientesDTO) throws NegocioException;

    /**
     *
     * @param nombre
     * @return
     */
    Producto buscarProductoPorNombreBO(String nombre);

    /**
     *
     * @return
     */
    List<Producto> mostrarListaProductosTodosBO();

    /**
     *
     * @param nombreFiltro
     * @return
     */
    List<Producto> filtrarPorNombreProductoTodosBO(String nombreFiltro);

    /**
     *
     * @param tipoFiltro
     * @return
     */
    List<Producto> filtrarPorTipoProductoTodosBO(Tipo_Producto tipoFiltro);

    /**
     *
     * @param nombreFiltro
     * @param tipoFiltro
     * @return
     */
    List<Producto> filtrarPorNombreYTipoProductoTodosBO(String nombreFiltro, Tipo_Producto tipoFiltro);

    /**
     * 
     * @param productoAHabilitar 
     */
    void habilitarProductoBO(NuevoProductoDTO productoAHabilitar);
}
