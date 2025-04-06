/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.Clientes;

import Entidades.Comandas.Comanda;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
/**
 *
 * @author Alberto Jimenez
 */
@Entity
@DiscriminatorValue("1")
public class ClientesFrecuentes extends Cliente implements Serializable{
    
    @Column (name = "Total_Gastado", nullable = false)
    private double totalGastado;
    
    @Column (name = "Visitas", nullable = false)
    private Integer visitas;
    
    @Column (name = "Puntos", nullable = false)
    private Integer puntos;
    
    @OneToMany(mappedBy = "clienteFrecuente", cascade = CascadeType.PERSIST)
    private List<Comanda> comandas = new ArrayList<>();


    public ClientesFrecuentes() {
    }

    public ClientesFrecuentes(double totalGastado, Integer visitas, Integer puntos) {
        this.totalGastado = totalGastado;
        this.visitas = visitas;
        this.puntos = puntos;
    }
    
    public ClientesFrecuentes(double totalGastado, Integer visitas, Integer puntos, String nombre, String correo, int numTelefono, Calendar fechaRegistro) {
        super(nombre, correo, numTelefono, fechaRegistro);
        this.totalGastado = totalGastado;
        this.visitas = visitas;
        this.puntos = puntos;
    }

    public ClientesFrecuentes(double totalGastado, Integer visitas, Integer puntos, String nombre, int numTelefono, Calendar fechaRegistro) {
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

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), totalGastado, visitas, puntos);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final ClientesFrecuentes other = (ClientesFrecuentes) obj;
        return Objects.equals(totalGastado, other.totalGastado) &&
               Objects.equals(visitas, other.visitas) &&
               Objects.equals(puntos, other.puntos);
    }
}
