/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO.Mesas;

import DTOS.Mesa.NuevaMesaDTO;
import Entidades.Mesa.Mesa;
import java.util.List;

/**
 *
 * @author PC Gamer
 */
public interface IMesasDAO {
    
    Mesa crearMesa(NuevaMesaDTO nuevaMesaDTO);
    
    void eliminarMesa(NuevaMesaDTO nuevaMesaDTO);
    
    Mesa ocuparMesa(NuevaMesaDTO nuevaMesaDTO);
    
    Mesa disponibilizarMesa(NuevaMesaDTO nuevaMesaDTO);
    
    Mesa obtenerMesaPorNumMesa(int numMesa);
    
    List<Mesa> obtenerListaMesasDisponibles();
    
    List<Mesa> obtenerListaMesasOcupadas();
    
    List<Mesa> obtenerListaMesasTodas();
    
    Mesa crearMesaEnOrden();
}
