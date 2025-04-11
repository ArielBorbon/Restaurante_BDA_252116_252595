/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BO.ComandasBO;

import DTOS.Comandas.NuevaComandaDTO;
import DTOS.Comandas.NuevoDetalleComandaDTO;
import Entidades.Comandas.Comanda;
import Entidades.Comandas.DetalleComanda;
import NegocioException.NegocioException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Interfaz de ComandaBO
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public interface IComandaBO {

    /**
     * Registra una nueva comanda y sus detalles.
     *
     * @param comandaDTO Objeto con los datos generales de la comanda.
     * @param detallesDTO Lista de detalles (productos) incluidos en la comanda.
     * @return La comanda registrada.
     */
    Comanda registrarComandaBO(NuevaComandaDTO comandaDTO, List<NuevoDetalleComandaDTO> detallesDTO);

    /**
     * Calcula el total de un detalle (cantidad * precio del producto).
     *
     * @param detalleDTO Detalle de la comanda.
     * @return Total del detalle.
     */
    double calcularTotalDetalleComandaBO(NuevoDetalleComandaDTO detalleDTO);

    /**
     * Suma los importes totales de todos los detalles de una comanda.
     *
     * @param detallesDTO Lista de detalles de la comanda.
     * @return Total acumulado de la comanda.
     */
    double calcularTotalComandaBO(List<NuevoDetalleComandaDTO> detallesDTO);

    /**
     * Genera un folio único para una nueva comanda.
     *
     * @return Folio generado.
     */
    String generarFolioComandaBO();

    /**
     * Modifica una comanda existente y sus detalles.
     *
     * @param comandaDTO Comanda a modificar.
     * @param detallesDTO Nuevos detalles de la comanda.
     */
    void modificarComandaBO(NuevaComandaDTO comandaDTO, List<NuevoDetalleComandaDTO> detallesDTO);

    /**
     * Elimina una comanda del sistema.
     *
     * @param comandaDTO Comanda a eliminar.
     */
    void eliminarComandaBO(NuevaComandaDTO comandaDTO);

    /**
     * Marca una comanda como cancelada.
     *
     * @param folio Folio de la comanda a cancelar.
     */
    void cancelarComandaBO(String folio);

    /**
     * Marca una comanda como entregada.
     *
     * @param folio Folio de la comanda.
     */
    void entregarComanda(String folio);

    /**
     * Verifica si hay stock suficiente para los productos solicitados.
     *
     * @param detallesDTO Lista de productos requeridos.
     * @return {@code true} si hay stock suficiente, {@code false} en caso
     * contrario.
     */
    boolean verificarStockNecesarioProductosBO(List<NuevoDetalleComandaDTO> detallesDTO);

    /**
     * Descuenta del inventario los ingredientes requeridos por los productos de
     * la comanda.
     *
     * @param detallesDTO Lista de productos de la comanda.
     */
    void restarStockIngredientesPorProductosComandaBO(List<NuevoDetalleComandaDTO> detallesDTO);

    /**
     * Modifica la nota de un detalle de comanda.
     *
     * @param detalleDTO Detalle de comanda a modificar.
     * @param nuevaNota Nueva nota del producto.
     */
    void modificarNotaBO(NuevoDetalleComandaDTO detalleDTO, String nuevaNota);

    /**
     * Retorna todas las comandas registradas.
     *
     * @return Lista de todas las comandas.
     */
    List<Comanda> mostrarComandasTodasBO();

    /**
     * Retorna las comandas que aún están abiertas.
     *
     * @return Lista de comandas abiertas.
     */
    List<Comanda> mostrarComandasAbiertasBO();

    /**
     * Filtra comandas entre dos fechas dadas.
     *
     * @param fechaInicio Fecha de inicio.
     * @param fechaFin Fecha de fin.
     * @return Lista de comandas en ese rango.
     */
    List<Comanda> filtrarPorFecha(Calendar fechaInicio, Calendar fechaFin);

    /**
     * Obtiene una comanda por su folio.
     *
     * @param folio Folio de la comanda.
     * @return La comanda correspondiente.
     * @throws NegocioException Si la comanda no existe o ocurre un error.
     */
    Comanda obtenerComandaPorFolioBO(String folio) throws NegocioException;

    /**
     * Obtiene la lista de detalles de una comanda.
     *
     * @param comanda Comanda de la que se obtendrán los detalles.
     * @return Lista de detalles asociados.
     * @throws NegocioException Si ocurre un error al obtener los detalles.
     */
    List<DetalleComanda> obtenerListaDetallesComandaBO(Comanda comanda) throws NegocioException;

    /**
     * Actualiza el estado de cliente frecuente en base al total gastado.
     *
     * @param idCliente ID del cliente.
     * @param totalGastado Monto total acumulado.
     */
    void actualizarClienteFrecuente(Long idCliente, double totalGastado);

    /**
     * Calcula el total de todas las comandas registradas.
     *
     * @return Total acumulado.
     * @throws NegocioException Si ocurre un error en el cálculo.
     */
    double calcularTotalDeTodasLasComandas() throws NegocioException;

    /**
     * Calcula el total de comandas entre dos fechas.
     *
     * @param fechaInicio Fecha de inicio.
     * @param fechaFin Fecha de fin.
     * @return Total acumulado del periodo.
     * @throws NegocioException Si ocurre un error en el cálculo.
     */
    double calcularTotalDeComandasPorFechas(Date fechaInicio, Date fechaFin) throws NegocioException;
}
