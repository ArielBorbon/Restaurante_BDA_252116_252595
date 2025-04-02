/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Entidades.Productos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.Test;

/**
 *
 * @author PC Gamer
 */
public class ProductoTest {
    
    @Test
    public  void crearProducto() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_RestauranteDominio_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        
        Producto producto = new Producto();
        producto.setNombre("Pastel Chocolatoso");
        producto.setPrecio(250.0);
        producto.setTipo(Tipo_Producto.POSTRE);
        
        em.getTransaction().begin();
        em.persist(producto);
        em.getTransaction().commit();
        
        em.close();
        emf.close();
    }
}
