/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO.Ingredientes;

import DTOS.Ingredientes.IngredienteConCantidadNecesariaDTO;
import Entidades.Ingredientes.Ingrediente;
import java.util.List;
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
//    IngredientesDAO ingredientesDAO = new IngredientesDAO();
//
//    NuevoIngredienteDTO dtoHarina = new NuevoIngredienteDTO();
//    dtoHarina.setNombre("Harina");
//    dtoHarina.setUnidad_medida("kg");
//    dtoHarina.setStock(50);
//    ingredientesDAO.registrarIngrediente(dtoHarina);
//
//    Ingrediente harinaEncontrada = ingredientesDAO.buscarIngredientePorNombreYUnidad("Harina", "kg");
//
//    assertNotNull(harinaEncontrada, "El ingrediente Harina debe existir en la BD");
//    assertEquals("Harina", harinaEncontrada.getNombre(), "El nombre del ingrediente debe ser Harina");
//    assertEquals("kg", harinaEncontrada.getUnidad_medida(), "La unidad de medida del ingrediente debe ser kg");
//    assertEquals(50, harinaEncontrada.getStock(), "El stock de Harina debe ser 50 kg");
//
//    Ingrediente inexistente = ingredientesDAO.buscarIngredientePorNombreYUnidad("Mantequilla", "kg");
//
//    assertNull(inexistente, "Si el ingrediente no existe, el metodo deberia retornar null");
//}


    
//        @Test
//    public void testObtenerIngredientesPorNombreProducto() {
//        
//        String nombreProducto = "Ensalada César";
//        IngredientesDAO ingredienteDAO = new IngredientesDAO();
//        List<Ingrediente> ingredientes = ingredienteDAO.obtenerIngredientesPorNombreProducto(nombreProducto);
//
//      
//        assertNotNull(ingredientes, "La lista de ingredientes no debería ser nula");
//
//       
//        assertFalse(ingredientes.isEmpty(), "El producto debería tener al menos un ingrediente relacionado");
//
//      
//        for (Ingrediente ing : ingredientes) {
//            System.out.println("Ingrediente: " + ing.getNombre() + " - Unidad: " + ing.getUnidad_medida());
//        }
//    }
    
    

//@Test
//    public void testTieneRelacionesActivas_ConRelaciones() {
//        IngredientesDAO ingredientesDAO = new IngredientesDAO();
//        String nombre = "Cebolla";
//        String unidad = "kg";
//        
//    boolean resultado = ingredientesDAO.tieneRelacionesActivas(nombre, unidad);
//    
//        assertTrue(resultado);
//    }

    
    
//    @Test
//    public void ProbarVerRelacionesDeUnProducto(){
//        IngredientesDAO ingredientesDAO = new IngredientesDAO();
//        String nombreProducto = "Flan";
//     List<IngredienteConCantidadNecesariaDTO> Lista = ingredientesDAO.obtenerIngredientesConCantidadPorProducto(nombreProducto);
//     
//        for (IngredienteConCantidadNecesariaDTO i : Lista) {
//            System.out.println(i.getNombreIngrediente() + " " + i.getUnidadMedida() + " " + i.getCantidadIngredienteNecesaria());
//        }
//    }
    
    
    
    
//    @Test
//    public static void generarTabla(){
//        IngredientesDAO ingredientesDAO = new IngredientesDAO();
//        List<Ingrediente> ingredientes = ingredientesDAO.mostrarListaIngredientes();
//    }
    
    
    
    
    
    
    
    
}
    
    

