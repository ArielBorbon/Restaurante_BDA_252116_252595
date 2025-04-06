/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS.Comandas;

/**
 *
 * @author PC Gamer
 */
public class NuevoDetalleComandaDTO {
    private String nombreProducto;
    private double precioUnitario;
    private String notas_producto;
    private int cantidad;
    private double importeTotal;
    private String folioComanda;

    public NuevoDetalleComandaDTO() {
    }

    public NuevoDetalleComandaDTO(String nombreProducto, double precioUnitario, String notas_producto, int cantidad, double importeTotal, String folioComanda) {
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.notas_producto = notas_producto;
        this.cantidad = cantidad;
        this.importeTotal = importeTotal;
        this.folioComanda = folioComanda;
    }
    
    
    
    

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getNotas_producto() {
        return notas_producto;
    }

    public void setNotas_producto(String notas_producto) {
        this.notas_producto = notas_producto;
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

    public String getFolioComanda() {
        return folioComanda;
    }

    public void setFolioComanda(String folioComanda) {
        this.folioComanda = folioComanda;
    }


    
    
    
}
