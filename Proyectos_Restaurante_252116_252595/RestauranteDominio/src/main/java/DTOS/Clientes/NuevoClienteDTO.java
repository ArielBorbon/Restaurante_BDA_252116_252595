/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS.Clientes;

import java.util.Calendar;

/**
 * Clase DTO para clientes
 * 
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class NuevoClienteDTO {
    private String nombre;
    private String correo;
    private String numTelefono;
    private Calendar fechaRegistro;
    private int tipo = 1;
    
    public NuevoClienteDTO() {
    }

    public NuevoClienteDTO(String nombre, String correo, String numTelefono, Calendar fechaRegistro) {
        this.nombre = nombre;
        this.correo = correo;
        this.numTelefono = numTelefono;
        this.fechaRegistro = fechaRegistro;
    }

    public NuevoClienteDTO(String nombre, String numTelefono, Calendar fechaRegistro) {
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
    
}
