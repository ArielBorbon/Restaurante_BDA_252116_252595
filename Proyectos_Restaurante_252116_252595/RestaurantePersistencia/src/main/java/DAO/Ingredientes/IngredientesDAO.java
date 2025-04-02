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
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

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
}
