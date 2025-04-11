/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Comandas;

import DTOS.Comandas.NuevaComandaDTO;
import DTOS.Comandas.NuevoDetalleComandaDTO;
import Entidades.Comandas.Comanda;
import Entidades.Comandas.DetalleComanda;
import Excepciones.PersistenciaException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Interfaz de comandas DAO
 * 
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
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
    
    Comanda obtenerComandaPorFolio(String folio) throws PersistenciaException;
    
    List<DetalleComanda> obtenerListaDetallesComanda(Comanda comanda) throws PersistenciaException;
    
    double calcularTotalDeTodasLasComandas() throws PersistenciaException;
    
    double calcularTotalDeComandasPorFechas(Date fechaInicio, Date fechaFin) throws PersistenciaException;
}
