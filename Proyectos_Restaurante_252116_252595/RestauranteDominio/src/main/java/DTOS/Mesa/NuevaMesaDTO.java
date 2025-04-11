/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS.Mesa;

import Entidades.Mesa.EstadoMesa;

/**
 * Clase DTO para mesas
 * 
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class NuevaMesaDTO {
    private int num_mesa; //probablemente usemos esta con un DAO para identificar cual es la verdadera mesa, DAO. identificarMesaporNum
    private EstadoMesa estado;

    public NuevaMesaDTO() {
    }

    public NuevaMesaDTO(int num_mesa) {
        this.num_mesa = num_mesa;
    }

    public NuevaMesaDTO(int num_mesa, EstadoMesa estado) {
        this.num_mesa = num_mesa;
        this.estado = estado;
    }
    
    

    public NuevaMesaDTO(EstadoMesa estado) {
        this.estado = estado;
    }

    
    
    
    
    public EstadoMesa getEstado() {
        return estado;
    }

    public void setEstado(EstadoMesa estado) {
        this.estado = estado;
    }
    
    
    

    public int getNum_mesa() {
        return num_mesa;
    }

    public void setNum_mesa(int num_mesa) {
        this.num_mesa = num_mesa;
    }
    
    
    
}
