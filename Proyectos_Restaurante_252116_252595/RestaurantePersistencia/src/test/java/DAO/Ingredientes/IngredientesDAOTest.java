/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO.Ingredientes;

import DTOS.Ingredientes.NuevoIngredienteDTO;
import Entidades.Ingredientes.Ingrediente;
import ManejadorConexiones.ManejadorConexiones;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

/**
 *
 * @author PC Gamer
 */
public class IngredientesDAOTest {
//    
//    public IngredientesDAOTest() {
//    }
//    
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }
//
//    /**
//     * Test of registrarIngrediente method, of class IngredientesDAO.
//     */
//    @Test
//    public void testRegistrarIngrediente() {
//        IngredientesDAO ingredientesDAO = new IngredientesDAO();
//        NuevoIngredienteDTO nuevoIngrediente = new NuevoIngredienteDTO();
//        nuevoIngrediente.setNombre("Mantequilla");
//        nuevoIngrediente.setUnidad_medida("gr");
//        nuevoIngrediente.setStock(200);
//
//        Ingrediente ingredienteRegistrado = ingredientesDAO.registrarIngrediente(nuevoIngrediente);
//
//        assertNotNull(ingredienteRegistrado, "El ingrediente debe haberse registrado correctamente");
//        assertEquals("Mantequilla", ingredienteRegistrado.getNombre());
//        assertEquals("gr", ingredienteRegistrado.getUnidad_medida());
//        assertEquals(200, ingredienteRegistrado.getStock());
//    }
//
//    /**
//     * Test of eliminarIngrediente method, of class IngredientesDAO.
//     */
//    @Test
//    public void testEliminarIngrediente() {
//        // Crear ingrediente de prueba
//        EntityManager entityManager = ManejadorConexiones.getEntityManager();
//        IngredientesDAO ingredientesDAO = new IngredientesDAO();
//        
//        NuevoIngredienteDTO nuevoIngrediente = new NuevoIngredienteDTO();
//        nuevoIngrediente.setNombre("Harina");
//        nuevoIngrediente.setUnidad_medida("KG");
//        nuevoIngrediente.setStock(50);
//
//        ingredientesDAO.registrarIngrediente(nuevoIngrediente);
//
//
//        Ingrediente encontrado = ingredientesDAO.buscarIngredientePorNombreYUnidad("Harina", "KG");
//        assertNotNull(encontrado);
//
//
//        ingredientesDAO.eliminarIngrediente(encontrado);
//
//        Ingrediente eliminado = ingredientesDAO.buscarIngredientePorNombreYUnidad("Harina", "KG");
//        assertNull(eliminado);
//    }
//
//    /**
//     * Test of mostrarListaIngredientes method, of class IngredientesDAO.
//     */
//@Test
//public void testMostrarListaIngredientes() {
//   
//    IngredientesDAO ingredientesDAO = new IngredientesDAO();
//
//    NuevoIngredienteDTO dtoHarina = new NuevoIngredienteDTO();
//    dtoHarina.setNombre("Harina");
//    dtoHarina.setUnidad_medida("kg");
//    dtoHarina.setStock(50);
//    ingredientesDAO.registrarIngrediente(dtoHarina);
//
//    NuevoIngredienteDTO dtoAzucar = new NuevoIngredienteDTO();
//    dtoAzucar.setNombre("Azúcar");
//    dtoAzucar.setUnidad_medida("kg");
//    dtoAzucar.setStock(30);
//    ingredientesDAO.registrarIngrediente(dtoAzucar);
//
//   
//    List<Ingrediente> ingredientes = ingredientesDAO.mostrarListaIngredientes();
//
//  
//    assertNotNull(ingredientes, "La lista de ingredientes no debe ser nula");
//    assertTrue(ingredientes.size() >= 2, "La lista debe contener al menos los ingredientes registrados");
//
//  
//    boolean contieneHarina = ingredientes.stream().anyMatch(i -> i.getNombre().equals("Harina") && i.getUnidad_medida().equals("kg"));
//    boolean contieneAzucar = ingredientes.stream().anyMatch(i -> i.getNombre().equals("Azúcar") && i.getUnidad_medida().equals("kg"));
//
//    assertTrue(contieneHarina, "La lista debe contener Harina");
//    assertTrue(contieneAzucar, "La lista debe contener Azúcar");
//}
//
//
//    /**
//     * Test of actualizarIngrediente method, of class IngredientesDAO.
//     */
//    @Test
//    public void testActualizarIngrediente() {
//        IngredientesDAO ingredientesDAO = new IngredientesDAO();
//        
//
//        NuevoIngredienteDTO dtoHarina = new NuevoIngredienteDTO();
//        dtoHarina.setNombre("PRUEBASTOCK");
//        dtoHarina.setUnidad_medida("kg");
//        dtoHarina.setStock(50);
//        ingredientesDAO.registrarIngrediente(dtoHarina);
//        
//        ingredientesDAO.actualizarIngrediente(dtoHarina, 70.0);
//    Ingrediente harinaActualizada = ingredientesDAO.buscarIngredientePorNombreYUnidad("PRUEBASTOCK", "kg");
//    
//        assertNotNull(harinaActualizada, "El ingrediente Harina debe existir en la BD");
//        assertEquals(70.0, harinaActualizada.getStock(), "El stock de Harina debe ser exactamente 70 kg");
//        
//    ingredientesDAO.actualizarIngrediente(dtoHarina, 40.0);
//            harinaActualizada = ingredientesDAO.buscarIngredientePorNombreYUnidad("PRUEBASTOCK", "kg");
//
//        assertEquals(40.0, harinaActualizada.getStock(), "El stock de Harina debe ser exactamente 40 kg");
//        
//        ingredientesDAO.actualizarIngrediente(dtoHarina, -50.0); 
//        harinaActualizada = ingredientesDAO.buscarIngredientePorNombreYUnidad("PRUEBASTOCK", "kg");
//        
//        assertEquals(40.0, harinaActualizada.getStock(), "El stock de Harina no debe cambiar, sigue siendo 40 kg");
//    }
//    
//
//
//
//
//
//
//
//@Test
//public void testBuscarIngredientePorNombreYUnidad() {
//    // Arrange: Registrar un ingrediente antes de buscarlo
//    IngredientesDAO ingredientesDAO = new IngredientesDAO();
//
//    NuevoIngredienteDTO dtoHarina = new NuevoIngredienteDTO();
//    dtoHarina.setNombre("Harina");
//    dtoHarina.setUnidad_medida("kg");
//    dtoHarina.setStock(50);
//    ingredientesDAO.registrarIngrediente(dtoHarina);
//
//    // Act: Buscar el ingrediente registrado
//    Ingrediente harinaEncontrada = ingredientesDAO.buscarIngredientePorNombreYUnidad("Harina", "kg");
//
//    // Assert: Verificar que se encontró correctamente
//    assertNotNull(harinaEncontrada, "El ingrediente Harina debe existir en la BD");
//    assertEquals("Harina", harinaEncontrada.getNombre(), "El nombre del ingrediente debe ser Harina");
//    assertEquals("kg", harinaEncontrada.getUnidad_medida(), "La unidad de medida del ingrediente debe ser kg");
//    assertEquals(50, harinaEncontrada.getStock(), "El stock de Harina debe ser 50 kg");
//
//    // Act: Buscar un ingrediente inexistente
//    Ingrediente inexistente = ingredientesDAO.buscarIngredientePorNombreYUnidad("Mantequilla", "kg");
//
//    // Assert: Verificar que retorna null cuando el ingrediente no existe
//    assertNull(inexistente, "Si el ingrediente no existe, el metodo deberia retornar null");
//}


    
    
    
    

//@Test
//    public void testTieneRelacionesActivas_ConRelaciones() {
//        IngredientesDAO ingredientesDAO = new IngredientesDAO();
//        String nombre = "Cebolla";
//        String unidad = "kg";
//        
//        // Act (simular relaciones)
//    boolean resultado = ingredientesDAO.tieneRelacionesActivas(nombre, unidad);
//    
//        // Assert
//        assertTrue(resultado);
//    }

    
}
    
    

