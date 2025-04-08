/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO.ComandasBO;

import DAO.Clientes.ClientesDAO;
import DAO.Comandas.ComandasDAO;
import DAO.Mesas.MesasDAO;
import DAO.Productos.ProductosDAO;
import DTOS.Comandas.NuevaComandaDTO;
import DTOS.Comandas.NuevoDetalleComandaDTO;
import Entidades.Comandas.Comanda;
import Entidades.Comandas.DetalleComanda;
import Entidades.Productos.Producto;
import Excepciones.PersistenciaException;
import NegocioException.NegocioException;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author PC Gamer
 */
public class ComandaBO implements IComandaBO {

    private ComandasDAO comandasDAO;
    private ProductosDAO productosDAO;
    private ClientesDAO clientesDAO;
    private MesasDAO mesasDAO;

    public ComandaBO(ComandasDAO comandasDAO, ProductosDAO productosDAO, ClientesDAO clientesDAO, MesasDAO mesasDAO) {
        this.comandasDAO = comandasDAO;
        this.productosDAO = productosDAO;
        this.clientesDAO = clientesDAO;
        this.mesasDAO = mesasDAO;
    }

    /**
     * Registra una comanda y sus detalles, validando previamente si hay stock
     * suficiente.
     *
     * @param comandaDTO
     * @param detallesDTO
     * @return
     */
    @Override
    public Comanda registrarComandaBO(NuevaComandaDTO comandaDTO, List<NuevoDetalleComandaDTO> detallesDTO) {
        if (detallesDTO == null || detallesDTO.isEmpty()) {
            throw new IllegalArgumentException("La comanda debe tener al menos un producto.");
        }

        boolean stockSuficiente = comandasDAO.verificarStockNecesarioProductos(detallesDTO);
        if (!stockSuficiente) {
            throw new RuntimeException("No hay stock suficiente para realizar la comanda.");
        }

        for (NuevoDetalleComandaDTO detalle : detallesDTO) {
            ComandasDAO comandaDAO = new ComandasDAO();
            double totalDetalle = comandaDAO.calcularTotalDetalleComanda(detalle);
            detalle.setImporteTotal(totalDetalle);
        }
        ComandasDAO comandaDAO = new ComandasDAO();
        double totalComanda = comandaDAO.calcularTotalComanda(detallesDTO);
        comandaDTO.setTotal(totalComanda);

        if (comandaDTO.getFolio() == null || comandaDTO.getFolio().isBlank()) {
            comandaDTO.setFolio(comandasDAO.generarFolioComanda());
        }

        return comandasDAO.registrarComanda(comandaDTO, detallesDTO);
    }

    /**
     * Calcula el total de un detalle (cantidad x precio del producto).
     *
     * @param detalleDTO
     * @return
     */
    @Override
    public double calcularTotalDetalleComandaBO(NuevoDetalleComandaDTO detalleDTO) {
        Producto producto = productosDAO.buscarProductoPorNombre(detalleDTO.getNombreProducto());
        return producto.getPrecio() * detalleDTO.getCantidad();
    }

    /**
     * Suma los importes totales de todos los detalles.
     *
     * @param detallesDTO
     * @return
     */
    @Override
    public double calcularTotalComandaBO(List<NuevoDetalleComandaDTO> detallesDTO) {
        return detallesDTO.stream()
                .mapToDouble(NuevoDetalleComandaDTO::getImporteTotal)
                .sum();
    }

    @Override
    public String generarFolioComandaBO() {
        return comandasDAO.generarFolioComanda();
    }

    @Override
    public void modificarComandaBO(NuevaComandaDTO comandaDTO, List<NuevoDetalleComandaDTO> detallesDTO) {
        if (comandaDTO == null || comandaDTO.getFolio() == null || comandaDTO.getFolio().isBlank()) {
            throw new IllegalArgumentException("El folio de la comanda no puede ser nulo o vacío.");
        }

        if (detallesDTO == null || detallesDTO.isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos un detalle de comanda.");
        }

        ComandasDAO comandasDAO = new ComandasDAO();
        boolean hayStock = comandasDAO.verificarStockNecesarioProductos(detallesDTO);
        if (!hayStock) {
            throw new RuntimeException("No hay stock suficiente para modificar la comanda.");
        }

        for (NuevoDetalleComandaDTO detalle : detallesDTO) {
            ComandasDAO comandaDAO = new ComandasDAO();
            double totalDetalle = comandaDAO.calcularTotalDetalleComanda(detalle);
            detalle.setImporteTotal(totalDetalle);
        }
        ComandasDAO comandaDAO = new ComandasDAO();
        double totalComanda = comandaDAO.calcularTotalComanda(detallesDTO);
        comandaDTO.setTotal(totalComanda);

        comandasDAO.modificarComanda(comandaDTO, detallesDTO);
    }

    @Override
    public void eliminarComandaBO(NuevaComandaDTO comandaDTO) {
        if (comandaDTO == null || comandaDTO.getFolio() == null || comandaDTO.getFolio().isBlank()) {
            throw new IllegalArgumentException("Se requiere un folio válido para eliminar la comanda.");
        }

        ComandasDAO comandasDAO = new ComandasDAO();
        comandasDAO.eliminarComanda(comandaDTO);
    }

    @Override
    public void cancelarComandaBO(String folio) {
        if (folio == null || folio.isBlank()) {
            throw new IllegalArgumentException("Folio inválido. No se puede cancelar la comanda.");
        }

        ComandasDAO comandasDAO = new ComandasDAO();
        comandasDAO.cambiarEstadoComandaACancelada(folio);
    }

    @Override
    public void entregarComanda(String folio) {
        if (folio == null || folio.isBlank()) {
            throw new IllegalArgumentException("Folio inválido. No se puede entregar la comanda.");
        }

        ComandasDAO comandasDAO = new ComandasDAO();
        comandasDAO.cambiarEstadoComandaAEntregada(folio);
    }

    @Override
    public boolean verificarStockNecesarioProductosBO(List<NuevoDetalleComandaDTO> detallesDTO) {
        if (detallesDTO == null || detallesDTO.isEmpty()) {
            throw new IllegalArgumentException("La lista de detalles de comanda no puede estar vacía.");
        }

        ComandasDAO comandasDAO = new ComandasDAO();
        return comandasDAO.verificarStockNecesarioProductos(detallesDTO);
    }

    @Override
    public void restarStockIngredientesPorProductosComandaBO(List<NuevoDetalleComandaDTO> detallesDTO) {
        if (detallesDTO == null || detallesDTO.isEmpty()) {
            throw new IllegalArgumentException("La lista de detalles de comanda no puede estar vacía.");
        }

        ComandasDAO comandasDAO = new ComandasDAO();
        comandasDAO.restarStockIngredientesPorProductosComanda(detallesDTO);
    }

    @Override
    public void modificarNotaBO(NuevoDetalleComandaDTO detalleDTO, String nuevaNota) {
        if (detalleDTO == null || detalleDTO.getFolioComanda() == null || detalleDTO.getNombreProducto() == null) {
            throw new IllegalArgumentException("El detalle de comanda debe contener el folio y nombre del producto.");
        }

        if (nuevaNota == null) {
            throw new IllegalArgumentException("La nueva nota no puede ser nula.");
        }

        ComandasDAO comandasDAO = new ComandasDAO();
        comandasDAO.modificarNota(detalleDTO, nuevaNota);
    }

    @Override
    public List<Comanda> mostrarComandasTodasBO() {
        ComandasDAO comandasDAO = new ComandasDAO();
        return comandasDAO.mostrarComandasTodas();
    }

    @Override
    public List<Comanda> mostrarComandasAbiertasBO() {
        ComandasDAO comandasDAO = new ComandasDAO();
        return comandasDAO.mostrarComandasAbiertas();
    }

    @Override
    public List<Comanda> filtrarPorFecha(Calendar fechaInicio, Calendar fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin no pueden ser nulas.");
        }

        if (fechaInicio.after(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin.");
        }

        List<Comanda> comandas = comandasDAO.filtrarPorFecha(fechaInicio, fechaFin);

        if (comandas.isEmpty()) {
            throw new RuntimeException("No se encontraron comandas en el rango de fechas especificado.");
        }

        return comandas; 
    }
    
    
    
    
    
    
        public Comanda obtenerComandaPorFolioBO(String folio) throws NegocioException {
        try {
            return comandasDAO.obtenerComandaPorFolio(folio);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error en la lógica al buscar la comanda por folio", e);
        }
    }
    
    
        public List<DetalleComanda> obtenerListaDetallesComandaBO(Comanda comanda) throws NegocioException {
        try {
            return comandasDAO.obtenerListaDetallesComanda(comanda);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al recuperar detalles de la comanda", e);
        }
    }

    
    
    
    

}
