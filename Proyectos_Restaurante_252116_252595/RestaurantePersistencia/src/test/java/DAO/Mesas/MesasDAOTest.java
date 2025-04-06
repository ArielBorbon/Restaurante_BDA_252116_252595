/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO.Mesas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


/**
 *
 * @author PC Gamer
 */

public class MesasDAOTest {

    private MesasDAO mesasDAO = new MesasDAO();

//
//
//    @Test
//    public void testCrearMesa() {
//        NuevaMesaDTO mesaDTO = new NuevaMesaDTO();
//        mesaDTO.setNum_mesa(5 );
//        mesaDTO.setEstado(EstadoMesa.DISPONIBLE);
//
//       Mesa mesa = mesasDAO.crearMesa(mesaDTO); 
//
//        Mesa mesaCreada = mesasDAO.obtenerMesaPorNumMesa(5);
//        assertNotNull(mesaCreada, "La mesa debe haberse creado correctamente");
//        assertEquals(5, mesaCreada.getNum_mesa(), "El número de mesa debe ser 5");
//    }
//
//
//    @Test
//    public void testEliminarMesa() {
// 
//        NuevaMesaDTO mesaDTO = new NuevaMesaDTO();
//        mesaDTO.setNum_mesa(780);
//        mesaDTO.setEstado(EstadoMesa.DISPONIBLE);
//
//        mesasDAO.crearMesa(mesaDTO); 
//
//        mesasDAO.eliminarMesa(mesaDTO);
//
//    assertThrows(RuntimeException.class, () -> {
//        mesasDAO.obtenerMesaPorNumMesa(mesaDTO.getNum_mesa()); }
//                                        , "Se esperaba que se lanzara RuntimeException porque la mesa fue eliminada");
//    }
//    
//    @Test
//    public void prueba(){
//        NuevaMesaDTO mesaDTO = new NuevaMesaDTO();
//        mesaDTO.setNum_mesa(70);
//        mesaDTO.setEstado(EstadoMesa.DISPONIBLE);
//        //mesasDAO.crearMesa(mesaDTO);
//        mesasDAO.eliminarMesa(mesaDTO);
//        
//        
//    }
//    
//    
//
//    @Test
//    public void testOcuparMesa() {
//        NuevaMesaDTO mesaDTO = new NuevaMesaDTO();
//        mesaDTO.setNum_mesa(10);
//        Mesa mesachila = mesasDAO.obtenerMesaPorNumMesa(mesaDTO.getNum_mesa());
//        
//        mesaDTO.setEstado(mesachila.getEstado());
//        Mesa mesa = mesasDAO.ocuparMesa(mesaDTO);
//        assertEquals(EstadoMesa.OCUPADA, mesa.getEstado(), "La mesa debe estar ocupada");
//        
//    }
//
//    @Test
//    public void testDisponibilizarMesa() {
//        NuevaMesaDTO mesaDTO = new NuevaMesaDTO();
//        mesaDTO.setNum_mesa(10); // a esta altura estarian obtenidos el estado gracias al metodo obtenerporNumMesa
//        mesaDTO.setEstado(EstadoMesa.OCUPADA);
//        
//        Mesa mesa = mesasDAO.disponibilizarMesa(mesaDTO);
//
//        assertEquals(EstadoMesa.DISPONIBLE, mesa.getEstado(), "La mesa debe estar disponible");
//    }
//
//    
//    @Test
//    public void testObtenerMesaPorNumMesa() {
//        NuevaMesaDTO mesaDTO = new NuevaMesaDTO();
//        mesaDTO.setNum_mesa(5);
//
//        Mesa mesaRecuperada = mesasDAO.obtenerMesaPorNumMesa(5);
//
//        assertNotNull(mesaRecuperada, "La mesa debe ser recuperada");
//        assertEquals(5, mesaRecuperada.getNum_mesa(), "El número de la mesa debe ser 5");
//    }
//    
//    
    
//    @Test
//    public void testListasDeMesas () {
//        
//        List<Mesa> mesasDisponibles = mesasDAO.obtenerListaMesasDisponibles();
//        List<Mesa> mesasOcupadas = mesasDAO.obtenerListaMesasOcupadas();
//        List<Mesa> mesasTodas = mesasDAO.obtenerListaMesasTodas();
//        
//        
//        System.out.println("MESAS DISPONIBLES:");
//        for (Mesa i : mesasDisponibles) {
//            System.out.println(i.getEstado() + " " + i.getNum_mesa());
//        }
//        
//        System.out.println("");
//        System.out.println("MESAS OCUPADAS");
//        for (Mesa i : mesasOcupadas) {
//            System.out.println(i.getEstado() + " " + i.getNum_mesa());
//        }
//        
//        System.out.println("");
//        System.out.println("MESAS TODAS");
//        for (Mesa i : mesasTodas) {
//            System.out.println(i.getEstado() + " " + i.getNum_mesa());
//        }
        
        
    
    
    
    //    @Test
    //    public void testCrearMesaEnOrden(){
    //        mesasDAO.crearMesaEnOrden();
    //        
    //    }
    
    
    
//    @Test
//    public void verRelacionesMesaTest(){
//        
//        
//        boolean resultado1 = mesasDAO.verRelacionesMesa(1);
//        assertEquals (false , resultado1);
//        boolean resultado2 = mesasDAO.verRelacionesMesa(2);
//        assertEquals(true,resultado2);
//   }     
    
    
    
    
    }
    
    
    
    
    
    

