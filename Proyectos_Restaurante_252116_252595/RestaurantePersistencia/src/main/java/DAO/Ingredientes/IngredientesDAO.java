/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Ingredientes;

import DAO.Productos.ProductosDAO;
import DTOS.Ingredientes.IngredienteConCantidadNecesariaDTO;
import DTOS.Ingredientes.NuevoIngredienteDTO;
import Entidades.Ingredientes.Ingrediente;
import Entidades.Productos.Producto;
import Entidades.Productos.ProductoOcupaIngrediente;
import ManejadorConexiones.ManejadorConexiones;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author PC Gamer
 */
public class IngredientesDAO implements IIngredientesDAO {
    
        @Override
        public Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngrediente) {         
    EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre(nuevoIngrediente.getNombre());
        ingrediente.setStock(nuevoIngrediente.getStock());
        ingrediente.setUnidad_medida(nuevoIngrediente.getUnidad_medida());
        
        entityManager.persist(ingrediente);
        entityManager.getTransaction().commit();    
        return ingrediente;
    }
    
    
        @Override
        public void eliminarIngrediente(Ingrediente ingrediente) {
    EntityManager entityManager = ManejadorConexiones.getEntityManager();
    entityManager.getTransaction().begin();
    
    try {
        Ingrediente managedIngrediente = entityManager.merge(ingrediente);
        entityManager.remove(managedIngrediente);
        entityManager.getTransaction().commit();
        
    } catch (Exception e) {
        entityManager.getTransaction().rollback();
        throw e;
    } finally {
        entityManager.close();
    }
}
    
    
    

        @Override
    public List<Ingrediente> mostrarListaIngredientes() {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpql = "SELECT i FROM Ingrediente i";
        TypedQuery<Ingrediente> query = entityManager.createQuery(jpql, Ingrediente.class);
        return query.getResultList();
    }
    
    
    
    
        @Override
    public void actualizarIngrediente(NuevoIngredienteDTO nuevoIngredienteDTO, double nuevoStock) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Ingrediente> criteriaQuery = criteriaBuilder.createQuery(Ingrediente.class);
            Root<Ingrediente> ingredienteRoot = criteriaQuery.from(Ingrediente.class);
            
            criteriaQuery.select(ingredienteRoot)
                         .where(criteriaBuilder.and(
                             criteriaBuilder.equal(ingredienteRoot.get("nombre"), nuevoIngredienteDTO.getNombre()),
                             criteriaBuilder.equal(ingredienteRoot.get("unidad_medida"), nuevoIngredienteDTO.getUnidad_medida())
                         ));
            
            Ingrediente ingrediente = entityManager.createQuery(criteriaQuery)
                                                   .getResultList()
                                                   .stream()
                                                   .findFirst()
                                                   .orElse(null);
            
            if (ingrediente != null) {
                if (nuevoStock >= 0) {
                    ingrediente.setStock(nuevoStock);
                    entityManager.merge(ingrediente);
                } else {
                    System.out.println("El stock no puede ser negativo.");
                }
            } else {
                System.out.println("Ingrediente no encontrado para actualizar.");
            }
            
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
    
    
    
    
    
    
        @Override
        public Ingrediente buscarIngredientePorNombreYUnidad(String nombre, String unidadMedida) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Ingrediente> query = cb.createQuery(Ingrediente.class);
            Root<Ingrediente> root = query.from(Ingrediente.class);

            Predicate nombrePredicate = cb.equal(root.get("nombre"), nombre);
            Predicate unidadMedidaPredicate = cb.equal(root.get("unidad_medida"), unidadMedida);
            query.select(root).where(cb.and(nombrePredicate, unidadMedidaPredicate));

            return entityManager.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            return null; 
        } finally {
            entityManager.close();
        }
    }
    
    
        
        
        @Override
    public boolean tieneRelacionesActivas(String nombreIngrediente, String unidadMedida) {
            EntityManager entityManager = ManejadorConexiones.getEntityManager();
            try {
                String jpql = """
                    SELECT COUNT(poi) 
                    FROM ProductoOcupaIngrediente poi 
                    JOIN poi.ingrediente i 
                    WHERE i.nombre = :nombre 
                    AND i.unidad_medida = :unidad
                """;
            
                Long count = entityManager.createQuery(jpql, Long.class)
                                         .setParameter("nombre", nombreIngrediente)
                                         .setParameter("unidad", unidadMedida)
                                         .getSingleResult();
                                     
                return count > 0;
            
            } finally {
                entityManager.close();
            }
        }



        
        @Override
        public List<Ingrediente> obtenerIngredientesPorNombreProducto(String nombreProducto) {
    EntityManager entityManager = ManejadorConexiones.getEntityManager();

    try {
        String jpql = """
            SELECT poi.ingrediente
            FROM ProductoOcupaIngrediente poi
            WHERE poi.producto.nombre = :nombreProducto
        """;

        TypedQuery<Ingrediente> query = entityManager.createQuery(jpql, Ingrediente.class);
        query.setParameter("nombreProducto", nombreProducto);

        return query.getResultList();

    } finally {
        entityManager.close();
    }
}

    
    
        @Override
    public List<IngredienteConCantidadNecesariaDTO> obtenerIngredientesConCantidadPorProducto(String nombreProducto) {
    EntityManager em = ManejadorConexiones.getEntityManager();
    
    try {

        ProductosDAO productosDAO = new ProductosDAO();
        Producto producto = productosDAO.buscarProductoPorNombre(nombreProducto);

        if (producto == null) {
            throw new RuntimeException("Producto no encontrado: " + nombreProducto);
        }


        TypedQuery<ProductoOcupaIngrediente> query = em.createQuery(
            "SELECT poi FROM ProductoOcupaIngrediente poi WHERE poi.producto.id = :idProducto",
            ProductoOcupaIngrediente.class
        );
        query.setParameter("idProducto", producto.getId());
        List<ProductoOcupaIngrediente> relaciones = query.getResultList();

        List<IngredienteConCantidadNecesariaDTO> resultado = new ArrayList<>();

        for (ProductoOcupaIngrediente relacion : relaciones) {
            Ingrediente ingrediente = relacion.getIngrediente();

            IngredienteConCantidadNecesariaDTO dto = new IngredienteConCantidadNecesariaDTO();
            dto.setNombreIngrediente(ingrediente.getNombre());
            dto.setUnidadMedida(ingrediente.getUnidad_medida());
            dto.setCantidadIngredienteNecesaria(relacion.getCantidad_necesaria());

            resultado.add(dto);
        }

        return resultado;
    } catch (Exception e) {
        throw new RuntimeException("Error al obtener ingredientes del producto: " + nombreProducto, e);
    } finally {
        em.close();
    }
}
}

    

    
    
    
    
    
    
    
    
    

