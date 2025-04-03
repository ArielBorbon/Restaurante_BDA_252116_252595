/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.Clientes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.totalGastado);
        hash = 97 * hash + Objects.hashCode(this.visitas);
        hash = 97 * hash + Objects.hashCode(this.puntos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClientesFrecuentes other = (ClientesFrecuentes) obj;
        if (!Objects.equals(this.totalGastado, other.totalGastado)) {
            return false;
        }
        if (!Objects.equals(this.visitas, other.visitas)) {
            return false;
        }
        return Objects.equals(this.puntos, other.puntos);
    }
}
