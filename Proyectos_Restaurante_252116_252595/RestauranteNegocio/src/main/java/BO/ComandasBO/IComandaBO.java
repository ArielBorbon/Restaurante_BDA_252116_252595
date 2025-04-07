/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BO.ComandasBO;

import DTOS.Comandas.NuevaComandaDTO;
import DTOS.Comandas.NuevoDetalleComandaDTO;
import Entidades.Comandas.Comanda;
import java.util.List;

/**
 *
 * @author PC Gamer
 */

public interface IComandaBO {
    Comanda registrarComandaBO(NuevaComandaDTO comandaDTO, List<NuevoDetalleComandaDTO> detallesDTO);
    double calcularTotalDetalleComandaBO(NuevoDetalleComandaDTO detalleDTO);
    double calcularTotalComandaBO(List<NuevoDetalleComandaDTO> detallesDTO);
    String generarFolioComandaBO();
    void modificarComandaBO(NuevaComandaDTO comandaDTO, List<NuevoDetalleComandaDTO> detallesDTO);
    
    void eliminarComandaBO(NuevaComandaDTO comandaDTO);
    void cancelarComandaBO(String folio);
    void entregarComanda(String folio);
    boolean verificarStockNecesarioProductosBO(List<NuevoDetalleComandaDTO> detallesDTO);
    void restarStockIngredientesPorProductosComandaBO(List<NuevoDetalleComandaDTO> detallesDTO);
    
    void modificarNotaBO(NuevoDetalleComandaDTO detalleDTO, String nuevaNota);
    List<Comanda> mostrarComandasTodasBO();
    List<Comanda> mostrarComandasAbiertasBO();
}
