/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BO.MesaBO;

import DTOS.Mesa.NuevaMesaDTO;
import Entidades.Mesa.Mesa;
import java.util.List;

/**
 * Interfaz de Mesa BO
 * 
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public interface IMesaBO {
    
    /**
     * 
     * @param nuevaMesaDTO
     * @return 
     */
    Mesa crearMesaBO(NuevaMesaDTO nuevaMesaDTO);
    
    /**
     * 
     * @param nuevaMesaDTO 
     */
    void eliminarMesaBO(NuevaMesaDTO nuevaMesaDTO);
    
    /**
     * 
     * @param nuevaMesaDTO
     * @return 
     */
    Mesa ocuparMesaBO(NuevaMesaDTO nuevaMesaDTO);
    
    /**
     * 
     * @param nuevaMesaDTO
     * @return 
     */
    Mesa disponibilizarMesaBO(NuevaMesaDTO nuevaMesaDTO);
    
    /**
     * 
     * @return 
     */
    List<Mesa> obtenerListaMesasDisponiblesBO();
    
    /**
     * 
     * @return 
     */
    List<Mesa> obtenerListaMesasOcupadasBO();
    
    /**
     * 
     * @return 
     */
    List<Mesa> obtenerListaMesasTodasBO();
    
    /**
     * 
     * @param num_mesa
     * @return 
     */
    boolean verRelacionesMesaBO(int num_mesa);
    
    /**
     * 
     * @return 
     */
    Mesa crearMesaEnOrdenBO();
    
    /**
     * 
     * @param numeroMesa
     * @return 
     */
    Mesa obtenerMesaPorNumMesaBO(int numeroMesa);
    
    /**
     * 
     * @return 
     */
    boolean existenMesasBO();
}
