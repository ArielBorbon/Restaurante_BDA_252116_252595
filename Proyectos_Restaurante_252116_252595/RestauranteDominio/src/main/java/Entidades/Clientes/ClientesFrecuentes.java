/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.Clientes;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Alberto Jimenez
 */
@Entity
@Table (name = "Clientes Frecuentes")
public class ClientesFrecuentes extends Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "Id")
    private Long id;
    
    @Column (name = "Total Gastado", nullable = false)
    private Long totalGastado;
    
    @Column (name = "Visitas", nullable = false)
    private Integer visitas;
    
    @Column (name = "Puntos", nullable = false)
    private Integer puntos;

    public ClientesFrecuentes() {
    }

    public ClientesFrecuentes(Long totalGastado, Integer visitas, Integer puntos, String nombre, String correo, int numTelefono, Calendar fechaRegistro) {
        super(nombre, correo, numTelefono, fechaRegistro);
        this.totalGastado = totalGastado;
        this.visitas = visitas;
        this.puntos = puntos;
    }

    public ClientesFrecuentes(Long totalGastado, Integer visitas, Integer puntos, String nombre, int numTelefono, Calendar fechaRegistro) {
        super(nombre, numTelefono, fechaRegistro);
        this.totalGastado = totalGastado;
        this.visitas = visitas;
        this.puntos = puntos;
    }
    
    
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(Long totalGastado) {
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
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ClientesFrecuentes)) {
            return false;
        }
        ClientesFrecuentes other = (ClientesFrecuentes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Clientes.ClientesFrecuentes[ id=" + id + " ]";
    }
    
}
