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
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author PC Gamer
 */
public class ProductosDAO implements IProductosDAO {

    @Override
    public List<Producto> mostrarListaProductosDisponibles() {
     
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpql = "SELECT p FROM Producto p WHERE p.estado = :estado";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpql, Producto.class);
        query.setParameter("estado", Estado_Producto.HABILITADO);
        return query.getResultList();
}
    
    
    public List<Producto> mostrarListaProductosTodos() {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpql = "SELECT p FROM Producto p";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpql, Producto.class); 
        return query.getResultList();
    }
    
    
    
    
    
    @Override
    public List<Producto> filtrarPorNombreProductoDisponibles(String nombreFiltro) {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = "SELECT p FROM Producto p WHERE p.nombre LIKE :nombreFiltrador AND p.estado = :estado";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);
        query.setParameter("nombreFiltrador", "%" + nombreFiltro + "%");
        query.setParameter("estado", Estado_Producto.HABILITADO);
        return query.getResultList();
    }
    
    
    
    @Override
    public List<Producto> filtrarPorNombreProductoTodos(String nombreFiltro){
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = "SELECT p FROM Producto p WHERE p.nombre LIKE :nombreFiltrador";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);
        query.setParameter("nombreFiltrador", "%" + nombreFiltro + "%");
        return query.getResultList();
    }
    
    
    
    
    
    @Override
    public List<Producto> filtrarPorTipoProductoDisponibles(Tipo_Producto tipoFiltro) {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = "SELECT p FROM Producto p WHERE p.tipo = :tipoFiltrador AND p.estado = :estado";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);
        query.setParameter("tipoFiltrador", tipoFiltro);
        query.setParameter("estado", Estado_Producto.HABILITADO);
        return query.getResultList();
    }
    

    
        @Override
    public List<Producto> filtrarPorTipoProductoTodos(Tipo_Producto tipoFiltro) {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = "SELECT p FROM Producto p WHERE p.tipo = :tipoFiltrador";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);
        query.setParameter("tipoFiltrador", tipoFiltro);
        return query.getResultList();
    }
    
    
    
    
    @Override
    public List<Producto> filtrarPorNombreYTipoProductoTodos(String nombreFiltro, Tipo_Producto tipoFiltro) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = "SELECT p FROM Producto p WHERE p.nombre LIKE :nombreFiltrador AND p.tipo = :tipoFiltrador";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);
        query.setParameter("nombreFiltrador", "%" + nombreFiltro + "%");
        query.setParameter("tipoFiltrador", tipoFiltro);
        return query.getResultList();
    }
    
    
    
            @Override
    public List<Producto> filtrarPorNombreYTipoProductoDisponibles(String nombreFiltro, Tipo_Producto tipoFiltro) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = "SELECT p FROM Producto p WHERE p.nombre LIKE :nombreFiltrador AND p.tipo = :tipoFiltrador AND p.estado = :estado";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);
        query.setParameter("nombreFiltrador", "%" + nombreFiltro + "%");
        query.setParameter("tipoFiltrador", tipoFiltro);
        query.setParameter("estado", Estado_Producto.HABILITADO);
        return query.getResultList();
    }
    
    
    
    

    @Override
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
    
    
    
    
    
    @Override
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
    
            

    
    
    
    @Override
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
    
    @Override
    public Producto buscarProductoPorNombre(String nombre) {
    EntityManager entityManager = ManejadorConexiones.getEntityManager();
    
    String jpqlQuery = "SELECT p FROM Producto p WHERE p.nombre = :nombre AND p.estado = :estado";
    
    TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);
    query.setParameter("nombre", nombre);
    query.setParameter("estado", Estado_Producto.HABILITADO);
    
    try {
        return query.getSingleResult();
    } catch (NoResultException e) {

        return null;
    } catch (NonUniqueResultException e) {

        throw new IllegalStateException("Se encontraron múltiples productos con el mismo nombre: " + nombre);
    }
}
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public Producto modificarProducto(NuevoProductoDTO nuevoProductoDTO, List<NuevoProductoOcupaIngredienteDTO> nuevosIngredientesDTO) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            
            //Buscar el producto original CON EL NOMBRE PORQUE SON UNICOS
            String jpqlBuscarProducto = "SELECT p FROM Producto p WHERE p.nombre = :nombreProducto";
            TypedQuery<Producto> queryProducto = entityManager.createQuery(jpqlBuscarProducto, Producto.class);
            queryProducto.setParameter("nombreProducto", nuevoProductoDTO.getNombre());
            
            Producto producto = queryProducto.getResultList().stream().findFirst().orElse(null);
            if (producto == null) {
                throw new IllegalArgumentException("Producto no encontrado con nombre: " + nuevoProductoDTO.getNombre());
            }
            
            //Modificar atributos SOLAMENTE SI TRAEN ALGO
            if (nuevoProductoDTO.getPrecio() != 0) {
                producto.setPrecio(nuevoProductoDTO.getPrecio());
            }
            
            if (nuevoProductoDTO.getTipo() != null) {
                producto.setTipo(nuevoProductoDTO.getTipo());
            }
            
            if (nuevoProductoDTO.getNombre() != null && !nuevoProductoDTO.getNombre().isBlank()) {
                producto.setNombre(nuevoProductoDTO.getNombre());
            }
            
            if (nuevoProductoDTO.getEstado() != null) {
                producto.setEstado(nuevoProductoDTO.getEstado());
            }
            
            // Buscamos las relaciones Actuales y las que tendremos que eliminar en caso de dejar de considerarlas 
            List<ProductoOcupaIngrediente> relacionesActuales = producto.getProductos(); 
            List<ProductoOcupaIngrediente> relacionesAEliminar = new ArrayList<>(relacionesActuales);
            
            // Recorrer para los nuevos ingredientes y hacemos uniones para actualizar el producto
            for (NuevoProductoOcupaIngredienteDTO dto : nuevosIngredientesDTO) {
                String jpqlBuscarIngrediente = """
                    SELECT i FROM Ingrediente i 
                    WHERE i.nombre = :nombre AND i.unidad_medida = :unidad
                """;
                
                TypedQuery<Ingrediente> queryIngrediente = entityManager.createQuery(jpqlBuscarIngrediente, Ingrediente.class);
                queryIngrediente.setParameter("nombre", dto.getNombreIngrediente());
                queryIngrediente.setParameter("unidad", dto.getUnidadMedida());
                
                Ingrediente ingrediente = queryIngrediente.getResultList().stream().findFirst().orElse(null);
                if (ingrediente == null) {
                    throw new IllegalArgumentException("Ingrediente no encontrado: " + dto.getNombreIngrediente());
                }
                
                //checar si ya existe la relacion
                ProductoOcupaIngrediente relacionExistente = relacionesActuales.stream()
                    .filter(rel -> rel.getIngrediente().getNombre().equals(dto.getNombreIngrediente()) &&
                                   rel.getIngrediente().getUnidad_medida().equals(dto.getUnidadMedida()))
                    .findFirst()
                    .orElse(null);
                
                if (relacionExistente != null) {
                    // En caso de que exista actualizamos nomas la cantidad
                    if (relacionExistente.getCantidad_necesaria() != dto.getCantidadNecesariaProducto()) {
                        relacionExistente.setCantidad_necesaria(dto.getCantidadNecesariaProducto());
                        entityManager.merge(relacionExistente);
                    }
                    relacionesAEliminar.remove(relacionExistente);
                } else {
                // Aqui hacemos la nueva relacion
                    ProductoOcupaIngrediente nuevaRelacion = new ProductoOcupaIngrediente();
                    nuevaRelacion.setProducto(producto);
                nuevaRelacion.setIngrediente(ingrediente);
                    nuevaRelacion.setCantidad_necesaria(dto.getCantidadNecesariaProducto());
                    entityManager.persist(nuevaRelacion);
                    producto.getProductos().add(nuevaRelacion);
                }
            }
            
            // aqui eliminamos las relaciones que se dejaron de considerar
            for (ProductoOcupaIngrediente rel : relacionesAEliminar) {
                producto.getProductos().remove(rel);
                ProductoOcupaIngrediente managedRel = entityManager.merge(rel);
                entityManager.remove(managedRel);
            }
            
           entityManager.merge(producto);
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
