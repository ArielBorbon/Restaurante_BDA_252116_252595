/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS.Clientes;

import java.util.Calendar;

/**
 *
 * @author Alberto Jimenez
 */
public class NuevoClienteDTO {
    private String nombre;
    private String correo;
    private Integer numTelefono;
    private Calendar fechaRegistro;

    public NuevoClienteDTO() {
    }

    public NuevoClienteDTO(String nombre, String correo, Integer numTelefono, Calendar fechaRegistro) {
        this.nombre = nombre;
        this.correo = correo;
        this.numTelefono = numTelefono;
        this.fechaRegistro = fechaRegistro;
    }

    public NuevoClienteDTO(String nombre, Integer numTelefono, Calendar fechaRegistro) {
        this.nombre = nombre;
        this.numTelefono = numTelefono;
        this.fechaRegistro = fechaRegistro;
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

    public Integer getNumTelefono() {
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
    
    
}
