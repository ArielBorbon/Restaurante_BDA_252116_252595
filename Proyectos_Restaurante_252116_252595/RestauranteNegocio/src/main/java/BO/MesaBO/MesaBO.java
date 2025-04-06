/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO.MesaBO;

import DAO.Mesas.MesasDAO;
import DTOS.Mesa.NuevaMesaDTO;
import Entidades.Mesa.EstadoMesa;
import Entidades.Mesa.Mesa;
import java.util.List;

/**
 *
 * @author PC Gamer
 */
public class MesaBO implements IMesaBO  {
    private MesasDAO mesaDAO;

    public MesaBO(MesasDAO mesaDAO) {
        this.mesaDAO = mesaDAO;
    }
    
    
    
    
    @Override
    public Mesa crearMesaBO(NuevaMesaDTO nuevaMesaDTO) {
        if (nuevaMesaDTO == null) {
            throw new IllegalArgumentException("El DTO de nueva mesa no puede ser null");
        }

        if (nuevaMesaDTO.getNum_mesa() <= 0) {
            throw new IllegalArgumentException("El número de mesa debe ser mayor a cero");
        }

        if (nuevaMesaDTO.getEstado() == null) {
            throw new IllegalArgumentException("El estado de la mesa no puede ser null");
        }

        Mesa existente = mesaDAO.obtenerMesaPorNumMesa(nuevaMesaDTO.getNum_mesa());
        
        if (existente != null) {
            throw new IllegalArgumentException("Ya existe una mesa con el número proporcionado");
        }
        
        return mesaDAO.crearMesa(nuevaMesaDTO);
    }
    
    
    @Override
    public void eliminarMesaBO(NuevaMesaDTO nuevaMesaDTO) {
        if (nuevaMesaDTO == null) {
            throw new IllegalArgumentException("El DTO de nueva mesa no puede ser null");
        }

        if (nuevaMesaDTO.getNum_mesa() <= 0) {
            throw new IllegalArgumentException("El número de mesa debe ser mayor a cero");
        }

        Mesa mesa = mesaDAO.obtenerMesaPorNumMesa(nuevaMesaDTO.getNum_mesa());
        if (mesa == null) {
            throw new IllegalArgumentException("No existe una mesa con ese número");
        }

        mesaDAO.eliminarMesa(nuevaMesaDTO);
    }    
    
    
    
    
    
    
    @Override
    public Mesa ocuparMesaBO(NuevaMesaDTO nuevaMesaDTO) {
        if (nuevaMesaDTO == null) {
            throw new IllegalArgumentException("El DTO de nueva mesa no puede ser null");
        }
    
        if (nuevaMesaDTO.getNum_mesa() <= 0) {
            throw new IllegalArgumentException("El número de mesa debe ser mayor a cero");
        }
    
        Mesa mesa = mesaDAO.obtenerMesaPorNumMesa(nuevaMesaDTO.getNum_mesa());
        if (mesa == null) {
            throw new IllegalArgumentException("No existe una mesa con ese número");
        }
    
        if (mesa.getEstado() == EstadoMesa.OCUPADA) {
            throw new IllegalStateException("La mesa ya se encuentra ocupada");
        }
    
        return mesaDAO.ocuparMesa(nuevaMesaDTO);
    }    
    
    
    
    @Override
    public Mesa disponibilizarMesaBO(NuevaMesaDTO nuevaMesaDTO) {
        if (nuevaMesaDTO == null) {
            throw new IllegalArgumentException("El DTO de nueva mesa no puede ser null");
        }
    
        if (nuevaMesaDTO.getNum_mesa() <= 0) {
            throw new IllegalArgumentException("El número de mesa debe ser mayor a cero");
        }
    
        Mesa mesa = mesaDAO.obtenerMesaPorNumMesa(nuevaMesaDTO.getNum_mesa());
        if (mesa == null) {
            throw new IllegalArgumentException("No existe una mesa con ese número");
        }
    
        if (mesa.getEstado() == EstadoMesa.DISPONIBLE) {
            throw new IllegalStateException("La mesa ya está disponible");
        }
    
        return mesaDAO.disponibilizarMesa(nuevaMesaDTO);
    }
    
    
    
    @Override
    public boolean verRelacionesMesaBO(int num_mesa) {
        if (num_mesa <= 0) {
            throw new IllegalArgumentException("El número de mesa debe ser mayor a cero.");
        }
    
        Mesa mesa = mesaDAO.obtenerMesaPorNumMesa(num_mesa);
        if (mesa == null) {
            throw new IllegalArgumentException("No existe una mesa con ese número.");
        }
    
        return mesaDAO.verRelacionesMesa(num_mesa);
    }

    
    @Override
    public List<Mesa> obtenerListaMesasDisponiblesBO() {
        return mesaDAO.obtenerListaMesasDisponibles();
    }
    
    
    @Override
    public List<Mesa> obtenerListaMesasOcupadasBO() {
        return mesaDAO.obtenerListaMesasOcupadas();
    }
    
    
    @Override
    public List<Mesa> obtenerListaMesasTodasBO() {
        return mesaDAO.obtenerListaMesasTodas();
    }
    
    @Override
    public Mesa crearMesaEnOrdenBO(){
        return mesaDAO.crearMesaEnOrden();
        
    }
    

    
    

           
    
    
}
