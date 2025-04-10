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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name= "Clientes_Tipo", discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "Cliente")
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
    private String numTelefono;
    
    @Temporal (TemporalType.DATE)
    @Column (name = "Fecha_Registro", nullable = false)
    private Calendar fechaRegistro;
    
    @Column(name = "Clientes_Tipo", insertable = false, updatable = false)
    private Integer tipoCliente;
    
    public Cliente() {
    }

    public Cliente(String nombre, String correo, String numTelefono, Calendar fechaRegistro) {
        this.nombre = nombre;
        this.correo = correo;
        this.numTelefono = numTelefono;
        this.fechaRegistro = fechaRegistro;
    }

    public Cliente(String nombre, String numTelefono, Calendar fechaRegistro) {
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

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public Calendar getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Calendar fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Integer tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cliente)) return false;
        Cliente other = (Cliente) obj;
        return Objects.equals(this.id, other.id);
    }
}