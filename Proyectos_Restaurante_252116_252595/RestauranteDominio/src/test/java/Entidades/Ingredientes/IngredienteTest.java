/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Entidades.Ingredientes;

import Entidades.Productos.ProductoOcupaIngrediente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author PC Gamer
 */
public class IngredienteTest {
    @Test
    public void crearIngrediente() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_RestauranteDominio_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre("Choco");
        ingrediente.setUnidad_medida("kg");
        ingrediente.setStock(50.0);
        
        em.getTransaction().begin();
        em.persist(ingrediente);
        em.getTransaction().commit();
        
        em.close();
        emf.close();
    }
}
