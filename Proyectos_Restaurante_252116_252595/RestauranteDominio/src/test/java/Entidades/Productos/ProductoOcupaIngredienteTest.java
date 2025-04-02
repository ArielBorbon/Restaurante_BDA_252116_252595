/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Entidades.Productos;

import Entidades.Ingredientes.Ingrediente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.Test;

/**
 *
 * @author PC Gamer
 */
public class ProductoOcupaIngredienteTest {
    
    @Test
    public  void crearProductoOcupaIngrediente() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_RestauranteDominio_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        // Obtener ingredientes y productos existentes
        Ingrediente ingrediente = em.find(Ingrediente.class, 1L); // Asumimos que el ingrediente con ID 1 existe
        Producto producto = em.find(Producto.class, 1L); // Asumimos que el producto con ID 1 existe
        
        if (ingrediente != null && producto != null) {
            ProductoOcupaIngrediente relacion = new ProductoOcupaIngrediente();
            relacion.setCantidad_necesaria(2.5);
            relacion.setIngrediente(ingrediente);
            relacion.setProducto(producto);
            
            em.persist(relacion);
        }
        
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
    
    
     @Test
    public void relacionarProductoConTresIngredientes() {
        // Crear la factoría y el EntityManager utilizando la unidad de persistencia configurada
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_RestauranteDominio_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Recuperar el producto con id 1
            Producto producto = em.find(Producto.class, 2L);

            // Recuperar los tres ingredientes (IDs 1, 2 y 3)
            Ingrediente ingrediente1 = em.find(Ingrediente.class, 2L);
            Ingrediente ingrediente2 = em.find(Ingrediente.class, 3L);
            Ingrediente ingrediente3 = em.find(Ingrediente.class, 4L);

            // Verificar que se encontraron todos los elementos necesarios
            if (producto == null || ingrediente1 == null || ingrediente2 == null || ingrediente3 == null) {
                throw new RuntimeException("No se encontró alguno de los elementos requeridos (producto o ingredientes)");
            }

            // Crear y persistir las relaciones entre el producto y cada ingrediente
            ProductoOcupaIngrediente rel1 = new ProductoOcupaIngrediente();
            rel1.setProducto(producto);
            rel1.setIngrediente(ingrediente1);
            rel1.setCantidad_necesaria(1.5); // cantidad necesaria para el ingrediente 1

            ProductoOcupaIngrediente rel2 = new ProductoOcupaIngrediente();
            rel2.setProducto(producto);
            rel2.setIngrediente(ingrediente2);
            rel2.setCantidad_necesaria(2.0); // cantidad necesaria para el ingrediente 2

            ProductoOcupaIngrediente rel3 = new ProductoOcupaIngrediente();
            rel3.setProducto(producto);
            rel3.setIngrediente(ingrediente3);
            rel3.setCantidad_necesaria(0.75); // cantidad necesaria para el ingrediente 3

            // Persistir las relaciones
            em.persist(rel1);
            em.persist(rel2);
            em.persist(rel3);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
    
    
    
    
    
    
    
    
    
    
    
    

