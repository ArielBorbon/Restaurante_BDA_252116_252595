/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Comandas;

import DTOS.Comandas.NuevaComandaDTO;
import DTOS.Comandas.NuevoDetalleComandaDTO;
import Entidades.Comandas.Comanda;
import Entidades.Comandas.DetalleComanda;
import Excepciones.PersistenciaException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Interfaz de comandas DAO
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public interface IComandasDAO {

    /**
     *
     * @param comandaDTO comanda DTO
     * @param detallesDTO detalles comanda DTO
     * @return regresa una nueva comanda
     */
    Comanda registrarComanda(NuevaComandaDTO comandaDTO, List<NuevoDetalleComandaDTO> detallesDTO);

    /**
     *
     * @return regresa un folio
     */
    String generarFolioComanda();

    /**
     *
     * @param detalleDTO detalles DTO
     * @return regresa el total de una comanda
     */
    double calcularTotalDetalleComanda(NuevoDetalleComandaDTO detalleDTO);

    /**
     *
     * @param detallesDTO lista de detalles de una comanda
     * @return regresa el total de una comanda
     */
    double calcularTotalComanda(List<NuevoDetalleComandaDTO> detallesDTO);

    /**
     *
     * @param comandaDTO comanda
     * @param nuevosDetallesDTO lista de los detalles de una comanda
     * @return regresa una comanda modificada
     */
    Comanda modificarComanda(NuevaComandaDTO comandaDTO, List<NuevoDetalleComandaDTO> nuevosDetallesDTO);

    /**
     *
     * @param comandaDTO comanda DTO
     */
    void eliminarComanda(NuevaComandaDTO comandaDTO);

    /**
     *
     * @param folio manda un folio
     */
    void cambiarEstadoComandaACancelada(String folio);

    /**
     *
     * @param folio manda un folio
     */
    void cambiarEstadoComandaAEntregada(String folio);

    /**
     *
     * @param detallesDTO detalles de una comanda
     * @return regresa la verificacion si necesita un producto
     */
    boolean verificarStockNecesarioProductos(List<NuevoDetalleComandaDTO> detallesDTO);

    /**
     *
     * @param detallesDTO manda los detalles de una comanda
     */
    void restarStockIngredientesPorProductosComanda(List<NuevoDetalleComandaDTO> detallesDTO);

    /**
     *
     * @return regresa una lista de comandas
     */
    List<Comanda> mostrarComandasTodas();

    /**
     *
     * @param detalleDTO manda los detalles de una comanda
     * @param nuevaNota manda un nueva nota
     */
    void modificarNota(NuevoDetalleComandaDTO detalleDTO, String nuevaNota);

    /**
     *
     * @return regresa una lista de comandas abiertas
     */
    List<Comanda> mostrarComandasAbiertas();

    /**
     *
     * @param fechaInicio manda una fecha de inicio
     * @param fechaFin manda una fecha de fin
     * @return regresa una lista de comandas en ese rango de fechas
     */
    List<Comanda> filtrarPorFecha(Calendar fechaInicio, Calendar fechaFin);

    /**
     *
     * @param folio manda un folio
     * @return regresa una comanda
     * @throws PersistenciaException Errores de persistencia
     */
    Comanda obtenerComandaPorFolio(String folio) throws PersistenciaException;

    /**
     *
     * @param comanda manda una comanda
     * @return regresa una lista de los detalles de una comanda
     * @throws PersistenciaException Error persistencias
     */
    List<DetalleComanda> obtenerListaDetallesComanda(Comanda comanda) throws PersistenciaException;

    /**
     *
     * @return regresa el total de todas las comandas
     * @throws PersistenciaException Error persistencias
     */
    double calcularTotalDeTodasLasComandas() throws PersistenciaException;

    /**
     *
     * @param fechaInicio fecha de inicio
     * @param fechaFin fecha de fin
     * @return regresa el total de las comanda que esten en ese rango de fechas
     * @throws PersistenciaException Error de persistencias
     */
    double calcularTotalDeComandasPorFechas(Date fechaInicio, Date fechaFin) throws PersistenciaException;
}
