/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO.Productos;

import DTOS.Productos.NuevoProductoDTO;
import DTOS.Productos.NuevoProductoOcupaIngredienteDTO;
import Entidades.Productos.Producto;
import Entidades.Productos.Tipo_Producto;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author rayoa
 */
public class ProductosDAOTest {
    
    public ProductosDAOTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    


    /**
     * Test of mostrarListaProductosDisponibles method, of class ProductosDAO.
     */
    @org.junit.jupiter.api.Test
    public void testMostrarListaProductosDisponibles() {
        System.out.println("mostrarListaProductosDisponibles");
        ProductosDAO instance = new ProductosDAO();
        List<Producto> expResult = null;
        List<Producto> result = instance.mostrarListaProductosDisponibles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of filtrarPorNombreProductoDisponibles method, of class ProductosDAO.
     */
    @org.junit.jupiter.api.Test
    public void testFiltrarPorNombreProductoDisponibles() {
        System.out.println("filtrarPorNombreProductoDisponibles");
        String nombreFiltro = "";
        ProductosDAO instance = new ProductosDAO();
        List<Producto> expResult = null;
        List<Producto> result = instance.filtrarPorNombreProductoDisponibles(nombreFiltro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of filtrarPorTipoProductoDisponibles method, of class ProductosDAO.
     */
    @org.junit.jupiter.api.Test
    public void testFiltrarPorTipoProductoDisponibles() {
        System.out.println("filtrarPorTipoProductoDisponibles");
        Tipo_Producto tipoFiltro = null;
        ProductosDAO instance = new ProductosDAO();
        List<Producto> expResult = null;
        List<Producto> result = instance.filtrarPorTipoProductoDisponibles(tipoFiltro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of filtrarPorNombreYTipoProductoDisponibles method, of class ProductosDAO.
     */
    @org.junit.jupiter.api.Test
    public void testFiltrarPorNombreYTipoProductoDisponibles() {
        System.out.println("filtrarPorNombreYTipoProductoDisponibles");
        String nombreFiltro = "";
        Tipo_Producto tipoFiltro = null;
        ProductosDAO instance = new ProductosDAO();
        List<Producto> expResult = null;
        List<Producto> result = instance.filtrarPorNombreYTipoProductoDisponibles(nombreFiltro, tipoFiltro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarProducto method, of class ProductosDAO.
     */
    @org.junit.jupiter.api.Test
    public void testRegistrarProducto() {
        System.out.println("registrarProducto");
        NuevoProductoDTO nuevoProducto = null;
        ProductosDAO instance = new ProductosDAO();
        Producto expResult = null;
        Producto result = instance.registrarProducto(nuevoProducto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deshabilitarProducto method, of class ProductosDAO.
     */
    @org.junit.jupiter.api.Test
    public void testDeshabilitarProducto() {
        System.out.println("deshabilitarProducto");
        NuevoProductoDTO nuevoProducto = null;
        ProductosDAO instance = new ProductosDAO();
        instance.deshabilitarProducto(nuevoProducto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarProductoConIngredientes method, of class ProductosDAO.
     */
    @org.junit.jupiter.api.Test
    public void testRegistrarProductoConIngredientes() {
        System.out.println("registrarProductoConIngredientes");
        NuevoProductoDTO nuevoProducto = null;
        List<NuevoProductoOcupaIngredienteDTO> listaProductoIngrediente = null;
        ProductosDAO instance = new ProductosDAO();
        Producto expResult = null;
        Producto result = instance.registrarProductoConIngredientes(nuevoProducto, listaProductoIngrediente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
