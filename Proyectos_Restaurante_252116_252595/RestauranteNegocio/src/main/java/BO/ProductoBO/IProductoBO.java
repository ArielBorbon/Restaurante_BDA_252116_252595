/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BO.ProductoBO;

import DTOS.Productos.NuevoProductoDTO;
import DTOS.Productos.NuevoProductoOcupaIngredienteDTO;
import Entidades.Productos.Producto;
import Entidades.Productos.Tipo_Producto;
import NegocioException.NegocioException;
import java.util.List;

/**
 *
 * @author PC Gamer
 */
public interface IProductoBO {
    List<Producto> mostrarListaProductosDisponiblesBO();
    
    List<Producto> filtrarPorNombreProductoDisponiblesBO(String nombreFiltro);
    
    List<Producto> filtrarPorTipoProductoDisponiblesBO(Tipo_Producto tipoFiltro);
    
    List<Producto> filtrarPorNombreYTipoProductoDisponiblesBO(String nombreFiltro, Tipo_Producto tipoFiltro);
    
    Producto registrarProductoBO(NuevoProductoDTO nuevoProducto) throws NegocioException;
    
    void deshabilitarProductoBO(NuevoProductoDTO productoAEliminar) throws NegocioException;
    
    Producto registrarProductoConIngredientesBO(NuevoProductoDTO nuevoProducto, 
            List<NuevoProductoOcupaIngredienteDTO> listaProductoIngrediente) throws NegocioException;
    
    Producto modificarProductoBO(NuevoProductoDTO nuevoProductoDTO, List<NuevoProductoOcupaIngredienteDTO> nuevosIngredientesDTO) throws NegocioException;
    
    Producto buscarProductoPorNombreBO(String nombre);
    
    List<Producto> mostrarListaProductosTodosBO();
    
    List<Producto> filtrarPorNombreProductoTodosBO(String nombreFiltro);
    
    List<Producto> filtrarPorTipoProductoTodosBO(Tipo_Producto tipoFiltro);
    
    List<Producto> filtrarPorNombreYTipoProductoTodosBO(String nombreFiltro, Tipo_Producto tipoFiltro);
}
