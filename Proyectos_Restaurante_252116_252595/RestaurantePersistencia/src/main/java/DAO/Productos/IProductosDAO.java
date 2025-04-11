/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO.Productos;

import DTOS.Productos.NuevoProductoDTO;
import DTOS.Productos.NuevoProductoOcupaIngredienteDTO;
import Entidades.Productos.Producto;
import Entidades.Productos.Tipo_Producto;
import java.util.List;

/**
 * Interfaz de productos DAO
 * 
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public interface IProductosDAO {
    List<Producto> mostrarListaProductosDisponibles();
    
    List<Producto> filtrarPorNombreProductoDisponibles(String nombreFiltro);
    
    List<Producto> filtrarPorTipoProductoDisponibles(Tipo_Producto tipoFiltro);
    
    List<Producto> filtrarPorNombreYTipoProductoDisponibles(String nombreFiltro, Tipo_Producto tipoFiltro);
    
    Producto registrarProducto(NuevoProductoDTO nuevoProducto);
    
    void deshabilitarProducto(NuevoProductoDTO productoAEliminar);
    
    Producto registrarProductoConIngredientes(NuevoProductoDTO nuevoProducto, 
            
    List<NuevoProductoOcupaIngredienteDTO> listaProductoIngrediente);
    
    Producto buscarProductoPorNombre(String nombre);
    
    Producto modificarProducto(NuevoProductoDTO nuevoProductoDTO, List<NuevoProductoOcupaIngredienteDTO> nuevosIngredientesDTO);
    
    List<Producto> filtrarPorTipoProductoTodos(Tipo_Producto tipoFiltro);
    
    List<Producto> filtrarPorNombreYTipoProductoTodos(String nombreFiltro, Tipo_Producto tipoFiltro);
    
    List<Producto> filtrarPorNombreProductoTodos(String nombreFiltro);
    
    public void habilitarProducto(NuevoProductoDTO productoAHabilitar);
}
