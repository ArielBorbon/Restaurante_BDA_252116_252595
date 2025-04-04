/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Ingredientes;

import DTOS.Ingredientes.NuevoIngredienteDTO;
import DTOS.Productos.NuevoProductoDTO;
import Entidades.Ingredientes.Ingrediente;
import Entidades.Productos.Producto;
import ManejadorConexiones.ManejadorConexiones;
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
public class IngredientesDAO {
    
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
    
    
    public void eliminarIngrediente(NuevoIngredienteDTO nuevoIngrediente) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
    
        try {
            String jpql = """
                          SELECT i FROM Ingrediente i WHERE i.nombre = :nombre AND i.stock = :stock AND i.unidad_medida = :unidad_medida
            """;
            Ingrediente ingrediente = entityManager.createQuery(jpql, Ingrediente.class)
                    .setParameter("nombre", nuevoIngrediente.getNombre())
                    .setParameter("stock", nuevoIngrediente.getStock())
                    .setParameter("unidad_medida", nuevoIngrediente.getUnidad_medida())
                    .getSingleResult();
            
        
            entityManager.remove(ingrediente);
            entityManager.getTransaction().commit();
        
        } catch (NoResultException e) {
            System.out.println("No se encontró el Ingrediente para eliminar.");
            entityManager.getTransaction().rollback();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }
    
    
    

    public List<Ingrediente> mostrarListaIngredientes() {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpql = "SELECT i FROM Ingrediente i";
        TypedQuery<Ingrediente> query = entityManager.createQuery(jpql, Ingrediente.class);
        return query.getResultList();
    }
    
    
    
    
    public void actualizarIngrediente(NuevoIngredienteDTO nuevoIngredienteDTO, double cantidad) {
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
            double nuevoStock = ingrediente.getStock() + cantidad;
            if (nuevoStock >= 0) {
                
            
            ingrediente.setStock(nuevoStock);
            entityManager.merge(ingrediente);
        } else {
            System.out.println("No se puede restar más de lo disponible.");
            }
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
    
    
    
    
    
    
    
}
    

    
    
    
    
    
    
    
    
    

