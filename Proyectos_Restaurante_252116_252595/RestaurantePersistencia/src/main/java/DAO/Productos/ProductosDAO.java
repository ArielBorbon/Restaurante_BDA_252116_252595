/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Productos;

import DTOS.Productos.NuevoProductoDTO;
import DTOS.Productos.NuevoProductoOcupaIngredienteDTO;
import Entidades.Ingredientes.Ingrediente;
import Entidades.Productos.Estado_Producto;
import Entidades.Productos.Producto;
import Entidades.Productos.ProductoOcupaIngrediente;
import Entidades.Productos.Tipo_Producto;
import ManejadorConexiones.ManejadorConexiones;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author PC Gamer
 */
public class ProductosDAO {

    public List<Producto> mostrarListaProductosDisponibles() {
     
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpql = "SELECT p FROM Producto p WHERE p.estado = :estado";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpql, Producto.class);
        query.setParameter("estado", Estado_Producto.HABILITADO);
        return query.getResultList();
}
    
    public List<Producto> filtrarPorNombreProductoDisponibles(String nombreFiltro) {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = "SELECT p FROM Producto p WHERE p.nombre LIKE :nombreFiltrador AND p.estado = :estado";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);
        query.setParameter("nombreFiltrador", "%" + nombreFiltro + "%");
        query.setParameter("estado", Estado_Producto.HABILITADO);
        return query.getResultList();
    }
    
    public List<Producto> filtrarPorTipoProductoDisponibles(Tipo_Producto tipoFiltro) {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = "SELECT p FROM Producto p WHERE p.tipo = :tipoFiltrador AND p.estado = :estado";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);
        query.setParameter("tipoFiltrador", tipoFiltro);
        query.setParameter("estado", Estado_Producto.HABILITADO);
        return query.getResultList();
    }
    
    public List<Producto> filtrarPorNombreYTipoProductoDisponibles(String nombreFiltro, Tipo_Producto tipoFiltro) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = "SELECT p FROM Producto p WHERE p.nombre LIKE :nombreFiltrador AND p.tipo = :tipoFiltrador AND p.estado = :estado";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);
        query.setParameter("nombreFiltrador", "%" + nombreFiltro + "%");
        query.setParameter("tipoFiltrador", tipoFiltro);
        query.setParameter("estado", Estado_Producto.HABILITADO);
        return query.getResultList();
    }


    public Producto registrarProducto(NuevoProductoDTO nuevoProducto) {

        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        Producto producto = new Producto();
        producto.setNombre(nuevoProducto.getNombre());
        producto.setPrecio(nuevoProducto.getPrecio());
        producto.setTipo(nuevoProducto.getTipo());
        producto.setEstado(nuevoProducto.getEstado());

        entityManager.persist(producto);
        entityManager.getTransaction().commit();
        return producto;
    }

public void deshabilitarProducto(NuevoProductoDTO productoAEliminar) {
    EntityManager entityManager = ManejadorConexiones.getEntityManager();
    entityManager.getTransaction().begin();

    try {
        String jpql = """
                      SELECT p FROM Producto p WHERE p.nombre = :nombre 
                      AND p.precio = :precio AND p.tipo = :tipo
                      """;
        Producto producto = entityManager.createQuery(jpql, Producto.class)
                .setParameter("nombre", productoAEliminar.getNombre())
                .setParameter("precio", productoAEliminar.getPrecio())
                .setParameter("tipo", productoAEliminar.getTipo())
                .getSingleResult();

        if (producto != null) {
            producto.setEstado(Estado_Producto.DESHABILITADO); 
            entityManager.merge(producto); // Actualizar en la BD
        }

        entityManager.getTransaction().commit();

    } catch (NoResultException e) {
        System.out.println("No se encontró el producto para deshabilitar.");
        entityManager.getTransaction().rollback();
    } catch (Exception e) {
        entityManager.getTransaction().rollback();
        throw e;
    } finally {
        entityManager.close();
    }
}

            

    
    
    
    public Producto registrarProductoConIngredientes(NuevoProductoDTO nuevoProducto, 
        List<NuevoProductoOcupaIngredienteDTO> listaProductoIngrediente) {
    
    EntityManager entityManager = ManejadorConexiones.getEntityManager();
    
    try {
        entityManager.getTransaction().begin();
        
        Producto producto = new Producto();
        producto.setNombre(nuevoProducto.getNombre());
        producto.setPrecio(nuevoProducto.getPrecio());
        producto.setTipo(nuevoProducto.getTipo());
        producto.setEstado(nuevoProducto.getEstado());


        if (producto.getProductos() == null) {
            producto.setProductos(new ArrayList<>());
        }

        entityManager.persist(producto);
        
        for (NuevoProductoOcupaIngredienteDTO dto : listaProductoIngrediente) {
            String jpql = """
                SELECT i FROM Ingrediente i 
                WHERE i.nombre = :nombreIngrediente 
                AND i.unidad_medida = :unidadMedida
                """;
            
            TypedQuery<Ingrediente> query = entityManager.createQuery(jpql, Ingrediente.class);
            query.setParameter("nombreIngrediente", dto.getNombreIngrediente());
            query.setParameter("unidadMedida", dto.getUnidadMedida());
            
            Ingrediente ingrediente = query.getResultList().stream().findFirst().orElse(null);
            
            if (ingrediente != null) {
                ProductoOcupaIngrediente relacion = new ProductoOcupaIngrediente();
                relacion.setProducto(producto);
                relacion.setIngrediente(ingrediente);
                relacion.setCantidad_necesaria(dto.getCantidadNecesariaProducto());

             
                producto.getProductos().add(relacion);

                entityManager.persist(relacion);
            }
        }
        
        entityManager.getTransaction().commit();
        return producto;
        
    } catch (Exception e) {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
        throw e;
    } finally {
        entityManager.close();
    }
}



    
    
    
    
    

}
