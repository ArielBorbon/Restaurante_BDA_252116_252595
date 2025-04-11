/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.Comandas;

import Entidades.Productos.Producto;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidad Detalle Comandas
 * 
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
@Entity
@Table(name = "Detalles_Comanda")
public class DetalleComanda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalleComanda")
    private Long id;

    @Column(name = "precio_unitario", nullable = false)
    private double precioUnitario;

    @Column(name = "notas_especiales", length = 255)
    private String notasEspeciales;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "importe_total", nullable = false)
    private double importeTotal;

    // Relación  Producto (N 1)
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    // Relación  Comanda (N 1)
    @ManyToOne
    @JoinColumn(name = "id_comanda", nullable = false)
    private Comanda comanda;

    public DetalleComanda() {
    }

    public DetalleComanda(Long id, double precioUnitario, String notasEspeciales, int cantidad, double importeTotal, Producto producto, Comanda comanda) {
        this.id = id;
        this.precioUnitario = precioUnitario;
        this.notasEspeciales = notasEspeciales;
        this.cantidad = cantidad;
        this.importeTotal = importeTotal;
        this.producto = producto;
        this.comanda = comanda;
    }

    public DetalleComanda(double precioUnitario, String notasEspeciales, int cantidad, double importeTotal, Producto producto, Comanda comanda) {
        this.precioUnitario = precioUnitario;
        this.notasEspeciales = notasEspeciales;
        this.cantidad = cantidad;
        this.importeTotal = importeTotal;
        this.producto = producto;
        this.comanda = comanda;
    }

    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getNotasEspeciales() {
        return notasEspeciales;
    }

    public void setNotasEspeciales(String notasEspeciales) {
        this.notasEspeciales = notasEspeciales;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }
}

