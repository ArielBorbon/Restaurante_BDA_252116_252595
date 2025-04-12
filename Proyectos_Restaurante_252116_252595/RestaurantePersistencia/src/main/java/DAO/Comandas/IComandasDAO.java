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
     * Registra una comanda
     *
     * @param comandaDTO comanda DTO
     * @param detallesDTO detalles comanda DTO
     * @return regresa una nueva comanda
     */
    Comanda registrarComanda(NuevaComandaDTO comandaDTO, List<NuevoDetalleComandaDTO> detallesDTO);

    /**
     * Genera un folio para una comanda
     *
     * @return regresa un folio
     */
    String generarFolioComanda();

    /**
     * Calcula el total de detalles de una comanda
     *
     * @param detalleDTO detalles DTO
     * @return regresa el total de una comanda
     */
    double calcularTotalDetalleComanda(NuevoDetalleComandaDTO detalleDTO);

    /**
     * Calcula el total de una nueva comanda
     *
     * @param detallesDTO lista de detalles de una comanda
     * @return regresa el total de una comanda
     */
    double calcularTotalComanda(List<NuevoDetalleComandaDTO> detallesDTO);

    /**
     * Modifica una comanda existente
     *
     * @param comandaDTO comanda
     * @param nuevosDetallesDTO lista de los detalles de una comanda
     * @return regresa una comanda modificada
     */
    Comanda modificarComanda(NuevaComandaDTO comandaDTO, List<NuevoDetalleComandaDTO> nuevosDetallesDTO);

    /**
     * Elimina una comanda
     *
     * @param comandaDTO comanda DTO
     */
    void eliminarComanda(NuevaComandaDTO comandaDTO);

    /**
     * Cambia el estado de una comanda a Cancelada
     *
     * @param folio manda un folio
     */
    void cambiarEstadoComandaACancelada(String folio);

    /**
     * Cambia el estado de una comanda a Entregada
     *
     * @param folio manda un folio
     */
    void cambiarEstadoComandaAEntregada(String folio);

    /**
     * Verifica el stock necesario de productos
     * 
     * @param detallesDTO detalles de una comanda
     * @return regresa la verificacion si necesita un producto
     */
    boolean verificarStockNecesarioProductos(List<NuevoDetalleComandaDTO> detallesDTO);

    /**
     * Resta el stock de ingredientes que tenga un producto al ser incluido en
     * una comanda
     *
     * @param detallesDTO manda los detalles de una comanda
     */
    void restarStockIngredientesPorProductosComanda(List<NuevoDetalleComandaDTO> detallesDTO);

    /**
     * Lista de todas las comandas
     *
     * @return regresa una lista de comandas
     */
    List<Comanda> mostrarComandasTodas();

    /**
     * Modifica las notas de una comanda
     *
     * @param detalleDTO manda los detalles de una comanda
     * @param nuevaNota manda un nueva nota
     */
    void modificarNota(NuevoDetalleComandaDTO detalleDTO, String nuevaNota);

    /**
     * Lista de las comandas con estado Abierta
     *
     * @return regresa una lista de comandas abiertas
     */
    List<Comanda> mostrarComandasAbiertas();

    /**
     * Lista de comandas filtradas en un rango de fechas
     *
     * @param fechaInicio manda una fecha de inicio
     * @param fechaFin manda una fecha de fin
     * @return regresa una lista de comandas en ese rango de fechas
     */
    List<Comanda> filtrarPorFecha(Calendar fechaInicio, Calendar fechaFin);

    /**
     * Obtiene una comanda buscando por su folio
     * 
     * @param folio manda un folio
     * @return regresa una comanda
     * @throws PersistenciaException Errores de persistencia
     */
    Comanda obtenerComandaPorFolio(String folio) throws PersistenciaException;

    /**
     * Lista de los detalles de una comanda
     *
     * @param comanda manda una comanda
     * @return regresa una lista de los detalles de una comanda
     * @throws PersistenciaException Error persistencias
     */
    List<DetalleComanda> obtenerListaDetallesComanda(Comanda comanda) throws PersistenciaException;

    /**
     * Calcula el total de todas las comandas que existan
     *
     * @return regresa el total de todas las comandas
     * @throws PersistenciaException Error persistencias
     */
    double calcularTotalDeTodasLasComandas() throws PersistenciaException;

    /**
     * Calcula el total de todas las comandas que existan en un rango de fechas
     *
     * @param fechaInicio fecha de inicio
     * @param fechaFin fecha de fin
     * @return regresa el total de las comanda que esten en ese rango de fechas
     * @throws PersistenciaException Error de persistencias
     */
    double calcularTotalDeComandasPorFechas(Date fechaInicio, Date fechaFin) throws PersistenciaException;
}
