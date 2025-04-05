/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO.ProductoBO;

import DAO.Productos.ProductosDAO;
import DTOS.Productos.NuevoProductoDTO;
import DTOS.Productos.NuevoProductoOcupaIngredienteDTO;
import Entidades.Ingredientes.Ingrediente;
import Entidades.Productos.Estado_Producto;
import Entidades.Productos.Producto;
import Entidades.Productos.Tipo_Producto;
import NegocioException.NegocioException;
import java.util.List;

/**
 *
 * @author PC Gamer
 */
public class ProductoBO implements IProductoBO {

    private ProductosDAO productoDAO;

    public ProductoBO(ProductosDAO productoDAO) throws NegocioException {
                if (productoDAO == null) {
            throw new NegocioException("El DAO no puede ser null");
        }
        this.productoDAO = productoDAO;
    }
    
    
    @Override
    public List<Producto> mostrarListaProductosDisponiblesBO(){
        return productoDAO.mostrarListaProductosDisponibles();
    }
    
    
    
    @Override
    public List<Producto> filtrarPorNombreProductoDisponiblesBO(String nombreFiltro) {
        return productoDAO.filtrarPorNombreProductoDisponibles(nombreFiltro);
    }
    
    
    
    @Override
    public List<Producto> filtrarPorTipoProductoDisponiblesBO(Tipo_Producto tipoFiltro) {
        return productoDAO.filtrarPorTipoProductoDisponibles(tipoFiltro);
    }
    
    
    
    @Override
    public List<Producto> filtrarPorNombreYTipoProductoDisponiblesBO(String nombreFiltro, Tipo_Producto tipoFiltro) {
        return productoDAO.filtrarPorNombreYTipoProductoDisponibles(nombreFiltro, tipoFiltro);
    }
    
    @Override
    public List<Producto> mostrarListaProductosTodosBO(){
        return productoDAO.mostrarListaProductosTodos();
    }
    
    
    
    
    @Override
    public Producto registrarProductoBO(NuevoProductoDTO nuevoProducto) throws NegocioException {

 
        if (nuevoProducto == null) {
            throw new NegocioException("El DTO del producto no puede ser nulo.");
        }

        if (nuevoProducto.getNombre() == null || nuevoProducto.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del producto no puede estar vacío.");
        }

        if (nuevoProducto.getPrecio() <= 0) {
            throw new NegocioException("El precio del producto debe ser mayor a 0.");
        }

        if (nuevoProducto.getTipo() == null) {
            throw new NegocioException("El tipo de producto no puede ser nulo.");
        }

        if (nuevoProducto.getEstado() == null) {
            throw new NegocioException("El estado del producto no puede ser nulo.");
        }


        Producto existente = productoDAO.buscarProductoPorNombre(nuevoProducto.getNombre());
        if (existente != null) {
            throw new NegocioException("Ya existe un producto con ese nombre.");
        }


        return productoDAO.registrarProducto(nuevoProducto);
    }
    
    
    
    
    
    @Override
    public void deshabilitarProductoBO(NuevoProductoDTO productoAEliminar) throws NegocioException {
        if (productoAEliminar == null) {
            throw new NegocioException("El DTO del producto no puede ser nulo.");
        }
        
        if (productoAEliminar.getNombre() == null || productoAEliminar.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del producto no puede estar vacío.");
        }
        
        if (productoAEliminar.getPrecio() <= 0) {
            throw new NegocioException("El precio del producto debe ser mayor a 0.");
        }
        
        if (productoAEliminar.getTipo() == null) {
            throw new NegocioException("El tipo de producto no puede ser nulo.");
        }
        
        
        try {
            productoDAO.deshabilitarProducto(productoAEliminar);
        } catch (Exception e) {
            throw new NegocioException("Error al intentar deshabilitar el producto: " + e.getMessage(), e);
        }
    }

    
    
    
    
    
    
    
    
        @Override
    public Producto registrarProductoConIngredientesBO(NuevoProductoDTO nuevoProducto, 
            List<NuevoProductoOcupaIngredienteDTO> listaProductoIngrediente) throws NegocioException {
        
        if (nuevoProducto == null) {
            throw new NegocioException("El producto no puede ser nulo.");
        }
        
        if (nuevoProducto.getNombre() == null || nuevoProducto.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del producto no puede estar vacío.");
        }
        
        if (nuevoProducto.getPrecio() <= 0) {
            throw new NegocioException("El precio del producto debe ser mayor a 0.");
        }
        
        if (nuevoProducto.getTipo() == null) {
            throw new NegocioException("El tipo de producto no puede ser nulo.");
        }
        
        if (nuevoProducto.getEstado() == null) {
            nuevoProducto.setEstado(Estado_Producto.HABILITADO);
        }
        
        if (listaProductoIngrediente == null || listaProductoIngrediente.isEmpty()) {
            throw new NegocioException("El producto debe tener al menos un ingrediente asociado.");
        }
        
        try {
            return productoDAO.registrarProductoConIngredientes(nuevoProducto, listaProductoIngrediente);
        } catch (Exception e) {
            throw new NegocioException("Error al registrar producto con ingredientes: " + e.getMessage(), e);
        }
    }
    
    
    
    
    







        @Override
    public Producto modificarProductoBO(NuevoProductoDTO nuevoProductoDTO, List<NuevoProductoOcupaIngredienteDTO> nuevosIngredientesDTO) throws NegocioException {
        if (nuevoProductoDTO == null) {
            throw new NegocioException("El DTO del producto no puede ser nulo.");
        }
        
        if (nuevoProductoDTO.getNombre() == null || nuevoProductoDTO.getNombre().isBlank()) {
            throw new NegocioException("El nombre del producto no puede estar vacío.");
        }
        
        if (nuevosIngredientesDTO == null) {
            throw new NegocioException("La lista de ingredientes no puede ser nula.");
        }
        
        try {
            return productoDAO.modificarProducto(nuevoProductoDTO, nuevosIngredientesDTO);
        } catch (IllegalArgumentException e) {
            throw new NegocioException("Error al modificar producto: " + e.getMessage(), e);
        } catch (Exception e) {
        throw new NegocioException("Error inesperado al modificar producto.", e);
        }   
    }

    
    
    @Override
    public Producto buscarProductoPorNombreBO(String nombre) {
        return productoDAO.buscarProductoPorNombre(nombre);
    }

    @Override
    public List<Producto> filtrarPorNombreProductoTodosBO(String nombreFiltro) {
        return productoDAO.filtrarPorNombreProductoTodos(nombreFiltro);
    }

    @Override
    public List<Producto> filtrarPorTipoProductoTodosBO(Tipo_Producto tipoFiltro) {
        return productoDAO.filtrarPorTipoProductoTodos(tipoFiltro);
    }

    @Override
    public List<Producto> filtrarPorNombreYTipoProductoTodosBO(String nombreFiltro, Tipo_Producto tipoFiltro) {
        return productoDAO.filtrarPorNombreYTipoProductoTodos(nombreFiltro, tipoFiltro);
    }

    @Override
    public void habilitarProductoBO(NuevoProductoDTO productoAHabilitar) {
         productoDAO.habilitarProducto(productoAHabilitar);
    }
    
    

    
    
}

    
    
    

