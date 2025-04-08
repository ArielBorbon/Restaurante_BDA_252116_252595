/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BO.MesaBO;

import DTOS.Mesa.NuevaMesaDTO;
import Entidades.Mesa.Mesa;
import java.util.List;

/**
 *
 * @author PC Gamer
 */
public interface IMesaBO {
    
    Mesa crearMesaBO(NuevaMesaDTO nuevaMesaDTO);
    
    void eliminarMesaBO(NuevaMesaDTO nuevaMesaDTO);
    
    Mesa ocuparMesaBO(NuevaMesaDTO nuevaMesaDTO);
    
    Mesa disponibilizarMesaBO(NuevaMesaDTO nuevaMesaDTO);
    
    public List<Mesa> obtenerListaMesasDisponiblesBO();
    
    public List<Mesa> obtenerListaMesasOcupadasBO();
    
    public List<Mesa> obtenerListaMesasTodasBO();
    
    boolean verRelacionesMesaBO(int num_mesa);
    
    public Mesa crearMesaEnOrdenBO();
    
    public Mesa obtenerMesaPorNumMesaBO(int numeroMesa);
}
