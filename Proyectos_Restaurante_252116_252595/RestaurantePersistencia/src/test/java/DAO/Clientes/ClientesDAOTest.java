/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO.Clientes;

import DTOS.Clientes.NuevoClienteDTO;
import Entidades.Clientes.Cliente;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Laboratorios
 */
public class ClientesDAOTest {
    
    public ClientesDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of registrarCliente method, of class ClientesDAO.
     */
    @Test
    public void testRegistrarClienteSinCorreo() {
        ClientesDAO clientesDAO = new ClientesDAO();
        Calendar fechaRegistro = new GregorianCalendar(2025, Calendar.APRIL, 3);
        NuevoClienteDTO nuevoClienteDTO = new NuevoClienteDTO(
                "Juanito", 
                644552492 ,
                fechaRegistro
        );
        Cliente cliente = clientesDAO.registrarCliente(nuevoClienteDTO);
        assertNotNull(cliente.getId());
        assertEquals(nuevoClienteDTO.getNombre(),cliente.getNombre());
        assertEquals(nuevoClienteDTO.getNumTelefono(), cliente.getNumTelefono());
        assertEquals(nuevoClienteDTO.getFechaRegistro(), cliente.getFechaRegistro());
    }
    
    @Test
    public void testRegistrarClienteConCorreo() {
        ClientesDAO clientesDAO = new ClientesDAO();
        Calendar fechaRegistro = new GregorianCalendar(2025, Calendar.APRIL, 3);
        NuevoClienteDTO nuevoClienteDTO = new NuevoClienteDTO(
                "Pepe",
                "pepelocochon123@hotmail.com",
                644498212 ,
                fechaRegistro
        );
        Cliente cliente = clientesDAO.registrarCliente(nuevoClienteDTO);
        assertNotNull(cliente.getId());
        assertEquals(nuevoClienteDTO.getNombre(),cliente.getNombre());
        assertEquals(nuevoClienteDTO.getCorreo(),cliente.getCorreo());
        assertEquals(nuevoClienteDTO.getNumTelefono(), cliente.getNumTelefono());
        assertEquals(nuevoClienteDTO.getFechaRegistro(), cliente.getFechaRegistro());
    }

//    /**
//     * Test of filtrarPorNombre method, of class ClientesDAO.
//     */
//    @Test
//    public void testFiltrarPorNombre() {
//        ClientesDAO clientesDAO = new ClientesDAO();
//        String filtroNombre = "Juanito";
//        int NUMERO_DE_CLIENTES_ESPERADOS = 1;
//        List<Cliente> clientesConsultados = clientesDAO.filtrarPorNombre(filtroNombre);
//        assertNotNull(clientesConsultados);
//        assertEquals(NUMERO_DE_CLIENTES_ESPERADOS, clientesConsultados.size());
//    }
//
//    /**
//     * Test of filtrarPorTelefono method, of class ClientesDAO.
//     */
//    @Test
//    public void testFiltrarPorTelefono() {
//        ClientesDAO clientesDAO = new ClientesDAO();
//        Integer filtroNumero = 644498212;
//        int NUMERO_DE_CLIENTES_ESPERADOS = 1;
//        List<Cliente> clientesConsultados = clientesDAO.filtrarPorTelefono(filtroNumero);
//        assertNotNull(clientesConsultados);
//        assertEquals(NUMERO_DE_CLIENTES_ESPERADOS, clientesConsultados.size());
//    }
//
//    /**
//     * Test of filtrarPorCorreo method, of class ClientesDAO.
//     */
//    @Test
//    public void testFiltrarPorCorreo() {
//        ClientesDAO clientesDAO = new ClientesDAO();
//        String filtroCorreo = "pepelocochon123@hotmail.com";
//        int NUMERO_DE_CLIENTES_ESPERADOS = 1;
//        List<Cliente> clientesConsultados = clientesDAO.filtrarPorCorreo(filtroCorreo);
//        assertNotNull(clientesConsultados);
//        assertEquals(NUMERO_DE_CLIENTES_ESPERADOS, clientesConsultados.size());
//    }

    /**
     * Test of mostrarListaClientes method, of class ClientesDAO.
     */
    @Test
    public void testMostrarListaClientes() {
    }
    
}
