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
    
     void habilitarProductoBO(NuevoProductoDTO productoAHabilitar);
}
