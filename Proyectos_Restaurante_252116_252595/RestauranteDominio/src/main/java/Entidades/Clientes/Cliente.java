/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.Clientes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author PC Gamer
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name= "Clientes_Tipo", discriminatorType = DiscriminatorType.INTEGER)
public class Cliente implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_cliente")
    private Long id;
    
    @Column (name = "Nombre", length = 255, nullable = false)
    private String nombre;
    
    @Column (name = "Correo_Electronico", length = 50, nullable = true)
    private String correo;
    
    @Column (name = "Numero_Telefono", unique = true, nullable = false)
    private Integer numTelefono;
    
    @Temporal (TemporalType.DATE)
    @Column (name = "Fecha_Registro", nullable = false)
    private Calendar fechaRegistro;

    public Cliente() {
    }

    public Cliente(String nombre, String correo, Integer numTelefono, Calendar fechaRegistro) {
        this.nombre = nombre;
        this.correo = correo;
        this.numTelefono = numTelefono;
        this.fechaRegistro = fechaRegistro;
    }

    public Cliente(String nombre, Integer numTelefono, Calendar fechaRegistro) {
        this.nombre = nombre;
        this.numTelefono = numTelefono;
        this.fechaRegistro = fechaRegistro;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(Integer numTelefono) {
        this.numTelefono = numTelefono;
    }

    public Calendar getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Calendar fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.nombre);
        hash = 53 * hash + Objects.hashCode(this.correo);
        hash = 53 * hash + this.numTelefono;
        hash = 53 * hash + Objects.hashCode(this.fechaRegistro);
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
        final Cliente other = (Cliente) obj;
        if (this.numTelefono != other.numTelefono) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.fechaRegistro, other.fechaRegistro);
    }
}