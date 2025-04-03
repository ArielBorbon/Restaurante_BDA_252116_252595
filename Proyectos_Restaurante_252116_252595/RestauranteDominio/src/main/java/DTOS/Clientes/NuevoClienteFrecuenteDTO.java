/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS.Clientes;

import Entidades.Clientes.Cliente;
import java.util.Calendar;

/**
 *
 * @author Alberto Jimenez
 */
public class NuevoClienteFrecuenteDTO extends Cliente{
    private double totalGastado;
    private Integer visitas;
    private Integer puntos;

    public NuevoClienteFrecuenteDTO() {
    }

    public NuevoClienteFrecuenteDTO(double totalGastado, Integer visitas, Integer puntos) {
        this.totalGastado = totalGastado;
        this.visitas = visitas;
        this.puntos = puntos;
    }

    public NuevoClienteFrecuenteDTO(double totalGastado, Integer visitas, Integer puntos, String nombre, String correo, Integer numTelefono, Calendar fechaRegistro) {
        super(nombre, correo, numTelefono, fechaRegistro);
        this.totalGastado = totalGastado;
        this.visitas = visitas;
        this.puntos = puntos;
    }

    public NuevoClienteFrecuenteDTO(double totalGastado, Integer visitas, Integer puntos, String nombre, Integer numTelefono, Calendar fechaRegistro) {
        super(nombre, numTelefono, fechaRegistro);
        this.totalGastado = totalGastado;
        this.visitas = visitas;
        this.puntos = puntos;
    }

    public double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(double totalGastado) {
        this.totalGastado = totalGastado;
    }

    public Integer getVisitas() {
        return visitas;
    }

    public void setVisitas(Integer visitas) {
        this.visitas = visitas;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }
}
