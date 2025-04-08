/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Comandas;

import DTOS.Comandas.NuevaComandaDTO;
import DTOS.Comandas.NuevoDetalleComandaDTO;
import Entidades.Comandas.Comanda;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author PC Gamer
 */

public interface IComandasDAO {
    Comanda registrarComanda(NuevaComandaDTO comandaDTO, List<NuevoDetalleComandaDTO> detallesDTO);
    
    String generarFolioComanda();
    
    double calcularTotalDetalleComanda(NuevoDetalleComandaDTO detalleDTO);
    
    double calcularTotalComanda(List<NuevoDetalleComandaDTO> detallesDTO);
    
    Comanda modificarComanda(NuevaComandaDTO comandaDTO, List<NuevoDetalleComandaDTO> nuevosDetallesDTO);
    
    void eliminarComanda(NuevaComandaDTO comandaDTO);
    
    void cambiarEstadoComandaACancelada(String folio);
    
    void cambiarEstadoComandaAEntregada(String folio);
    
    boolean verificarStockNecesarioProductos(List<NuevoDetalleComandaDTO> detallesDTO);
    
    void restarStockIngredientesPorProductosComanda(List<NuevoDetalleComandaDTO> detallesDTO);
    
    List<Comanda> mostrarComandasTodas();
    
    void modificarNota(NuevoDetalleComandaDTO detalleDTO, String nuevaNota);
    
    List<Comanda> mostrarComandasAbiertas();
    
    List<Comanda> filtrarPorFecha(Calendar fechaInicio, Calendar fechaFin);
}
