/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS.Comandas;

import Entidades.Comandas.EstadoComanda;
import java.util.Calendar;

/**
 *
 * @author PC Gamer
 */
public class NuevaComandaDTO {
    private String folio;
    private Calendar fecha_hora;
    private int num_mesa; 
    private EstadoComanda estado_comanda;
    private double total;
    private String correoCliente;

    public NuevaComandaDTO() {
    }

    public NuevaComandaDTO(String folio, Calendar fecha_hora, int num_mesa, EstadoComanda estado_comanda, double total, String CorreoCliente) {
        this.folio = folio;
        this.fecha_hora = fecha_hora;
        this.num_mesa = num_mesa;
        this.estado_comanda = estado_comanda;
        this.total = total;
        this.correoCliente = CorreoCliente;
    }

    public NuevaComandaDTO(String folio, Calendar fecha_hora, int num_mesa, EstadoComanda estado_comanda, double total) {
        this.folio = folio;
        this.fecha_hora = fecha_hora;
        this.num_mesa = num_mesa;
        this.estado_comanda = estado_comanda;
        this.total = total;
    }
    
    

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Calendar getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Calendar fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public int getNum_mesa() {
        return num_mesa;
    }

    public void setNum_mesa(int num_mesa) {
        this.num_mesa = num_mesa;
    }

    public EstadoComanda getEstado_comanda() {
        return estado_comanda;
    }

    public void setEstado_comanda(EstadoComanda estado_comanda) {
        this.estado_comanda = estado_comanda;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String CorreoCliente) {
        this.correoCliente = CorreoCliente;
    }
    
    
}
