/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO.Comandas;

import DAO.Clientes.ClientesDAO;
import DAO.Mesas.MesasDAO;
import DAO.Productos.ProductosDAO;
import DTOS.Clientes.NuevoClienteDTO;
import DTOS.Comandas.NuevaComandaDTO;
import DTOS.Comandas.NuevoDetalleComandaDTO;
import DTOS.Mesa.NuevaMesaDTO;
import DTOS.Productos.NuevoProductoDTO;
import Entidades.Comandas.Comanda;
import Entidades.Comandas.EstadoComanda;
import Entidades.Mesa.EstadoMesa;
import Entidades.Productos.Estado_Producto;
import Entidades.Productos.Tipo_Producto;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author PC Gamer
 */
public class ComandasDAOTest {

//    public ComandasDAOTest() {
//    }
//
//    @Test
//    public void testRegistrarComanda() {
//
//        try {
//            NuevoProductoDTO producto1 = new NuevoProductoDTO();
//            producto1.setNombre("Pizza Margherita");
//            producto1.setPrecio(150);
//            producto1.setTipo(Tipo_Producto.PLATO_FUERTE);
//            producto1.setEstado(Estado_Producto.HABILITADO);
//            ProductosDAO productosDAO = new ProductosDAO();
//
//            productosDAO.registrarProducto(producto1);
//
//            NuevoProductoDTO producto2 = new NuevoProductoDTO();
//            producto2.setNombre("Coca Cola");
//            producto2.setPrecio(300);
//            producto2.setTipo(Tipo_Producto.BEBIDA);
//            producto2.setEstado(Estado_Producto.HABILITADO);
//
//            productosDAO.registrarProducto(producto2);
//            ComandasDAO comandasDAO = new ComandasDAO();
//
//            NuevaMesaDTO mesa = new NuevaMesaDTO();
//            mesa.setNum_mesa(99);
//            mesa.setEstado(EstadoMesa.DISPONIBLE);
//            MesasDAO mesasDAO = new MesasDAO();
//            mesasDAO.crearMesa(mesa);
//
//            NuevoClienteDTO cliente = new NuevoClienteDTO();
//            cliente.setNombre("Juan Pérez");
//            cliente.setCorreo("juan@example.com");
//            cliente.setNumTelefono("123456789");
//            cliente.setFechaRegistro(Calendar.getInstance());
//
//            ClientesDAO clientesDAO = new ClientesDAO();
//            clientesDAO.registrarCliente(cliente);
//
//            NuevoDetalleComandaDTO detalleDTO1 = new NuevoDetalleComandaDTO();
//            detalleDTO1.setNombreProducto("Pizza Margherita");
//            detalleDTO1.setPrecioUnitario(150);
//            detalleDTO1.setCantidad(1);
//            detalleDTO1.setImporteTotal(comandasDAO.calcularTotalDetalleComanda(detalleDTO1));
//            detalleDTO1.setNotas_producto("Sin orégano");
//
//            NuevoDetalleComandaDTO detalleDTO2 = new NuevoDetalleComandaDTO();
//            detalleDTO2.setNombreProducto("Coca Cola");
//            detalleDTO2.setPrecioUnitario(300);
//            detalleDTO2.setCantidad(3);
//            detalleDTO2.setImporteTotal(comandasDAO.calcularTotalDetalleComanda(detalleDTO2));
//            detalleDTO2.setNotas_producto("Sin Insanidad");
//
//            List<NuevoDetalleComandaDTO> detallesDTO = new ArrayList<>();
//            detallesDTO.add(detalleDTO1);
//            detallesDTO.add(detalleDTO2);
//
//            NuevaComandaDTO comandaDTO = new NuevaComandaDTO();
//            comandaDTO.setFolio(comandasDAO.generarFolioComanda());
//            comandaDTO.setFecha_hora(Calendar.getInstance());
//            comandaDTO.setEstado_comanda(EstadoComanda.ABIERTA);
//            comandaDTO.setNum_mesa(99);
//            comandaDTO.setCorreoCliente("juan@example.com");
//            comandaDTO.setTotal(comandasDAO.calcularTotalComanda(detallesDTO));
//
//            Comanda comandaRegistrada = comandasDAO.registrarComanda(comandaDTO, detallesDTO);
//
//            assertNotNull(comandaRegistrada);
//            assertEquals("OB-20250406-001", comandaRegistrada.getFolio());
//            assertEquals(1050, comandaRegistrada.getTotal());
//            assertEquals(2, comandaRegistrada.getDetalles().size());
//            assertEquals("Pizza Margherita", comandaRegistrada.getDetalles().get(0).getProducto().getNombre());
//
//        } catch (Exception e) {
//            fail("Excepción durante la prueba de registrar comanda: " + e.getMessage());
//        }
//    }
//
//    @Test
//    public void PruebaGeneraTablas() {
//        NuevoClienteDTO cliente = new NuevoClienteDTO();
//        cliente.setNombre("Pepito Loco");
//        cliente.setCorreo("Pepito@example.com");
//        cliente.setNumTelefono("4646464646");
//        cliente.setFechaRegistro(Calendar.getInstance());
//
//        ClientesDAO clientesDAO = new ClientesDAO();
//        clientesDAO.registrarCliente(cliente);
//    }
//
//    @Test
//    public void pruebaModificarComanda() {
//        ComandasDAO comandasDAO = new ComandasDAO();
//        NuevoDetalleComandaDTO detalle1 = new NuevoDetalleComandaDTO();
//        detalle1.setCantidad(2);
//        detalle1.setNombreProducto("Nachos Con Queso");
//        detalle1.setNotas_producto("BIEN QUESOSOS");
//        detalle1.setPrecioUnitario(6);
//        detalle1.setImporteTotal(comandasDAO.calcularTotalDetalleComanda(detalle1));
//
//        NuevoDetalleComandaDTO detalle2 = new NuevoDetalleComandaDTO();
//        detalle2.setCantidad(5);
//        detalle2.setNombreProducto("Flan");
//        detalle2.setNotas_producto("BIEN FLANESOSO");
//        detalle2.setPrecioUnitario(4);
//        detalle2.setImporteTotal(comandasDAO.calcularTotalDetalleComanda(detalle2));
//
//        List<NuevoDetalleComandaDTO> detallesDTO = new ArrayList<>();
//        detallesDTO.add(detalle1);
//        detallesDTO.add(detalle2);
//
//        NuevaComandaDTO comandaDTO = new NuevaComandaDTO();
//        comandaDTO.setFolio("OB-20250406-001");
//        comandaDTO.setEstado_comanda(EstadoComanda.CANCELADA);
//        comandaDTO.setFecha_hora(Calendar.getInstance());
//        comandaDTO.setTotal(comandasDAO.calcularTotalComanda(detallesDTO));
//
//        Comanda comandaChida = comandasDAO.modificarComanda(comandaDTO, detallesDTO);
//        assertEquals(32, comandaChida.getTotal());
//
//    }
//
//    @Test
//    public void pruebaEliminarComanda() {
//
//        ComandasDAO comandasDAO = new ComandasDAO();
//        NuevaComandaDTO comandaDTO = new NuevaComandaDTO();
//        comandaDTO.setFolio("OB-20250406-001");
//        comandasDAO.eliminarComanda(comandaDTO);
//
//    }
//
//    @Test
//    public void pruebaEntregarComanda() {
//        ComandasDAO comandaDAO = new ComandasDAO();
//        Comanda comanda = new Comanda();
//        comandaDAO.cambiarEstadoComandaAEntregada("OB-20250406-001");
//    }
//
//    @Test
//    public void pruebaCancelarComanda() {
//        ComandasDAO comandaDAO = new ComandasDAO();
//        Comanda comanda = new Comanda();
//        comandaDAO.cambiarEstadoComandaACancelada("OB-20250406-001");
//    }

}
