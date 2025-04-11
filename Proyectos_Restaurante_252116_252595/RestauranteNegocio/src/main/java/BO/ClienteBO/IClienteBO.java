/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BO.ClienteBO;

import DTOS.Clientes.NuevoClienteDTO;
import Entidades.Clientes.Cliente;
import Excepciones.PersistenciaException;
import NegocioException.NegocioException;
import java.util.List;

/**
 * Interfaz de ClienteBO
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public interface IClienteBO {

    /**
     * Registra un nuevo cliente en el sistema a partir de los datos
     * proporcionados.
     *
     * @param nuevoClienteDTO Objeto que contiene la información del nuevo
     * cliente.
     * @return El cliente registrado con su información completa.
     * @throws NegocioException Si ocurre un error en la lógica de negocio
     * durante el registro.
     */
    public Cliente registrarClienteBO(NuevoClienteDTO nuevoClienteDTO) throws NegocioException;

    /**
     * Busca un cliente utilizando su número de teléfono como filtro.
     *
     * @param filtroNumero Número de teléfono del cliente a buscar.
     * @return El cliente que coincide con el número proporcionado.
     * @throws NegocioException Si ocurre un error durante la búsqueda.
     */
    public Cliente buscarPorTelefono(String filtroNumero) throws NegocioException;

    /**
     * Obtiene la lista completa de clientes registrados.
     *
     * @return Lista de todos los clientes registrados en el sistema.
     */
    public List<Cliente> obtenerListaClientesBO();

    /**
     * Filtra la lista de clientes por nombre y/o correo electrónico.
     *
     * @param nombre Nombre del cliente a filtrar.
     * @param correo Correo electrónico del cliente a filtrar.
     * @return Lista de clientes que coinciden con los filtros proporcionados.
     * @throws NegocioException Si ocurre un error durante el filtrado.
     */
    List<Cliente> filtrarClientes(String nombre, String correo) throws NegocioException;

    /**
     * Filtra los clientes por correo electrónico, usado específicamente para
     * reportes.
     *
     * @param correo Correo electrónico a utilizar como filtro.
     * @return Lista de clientes que coinciden con el correo proporcionado.
     * @throws NegocioException Si ocurre un error durante la operación.
     */
    List<Cliente> filtrarClientesReporte(String correo) throws NegocioException;

    /**
     * Filtra los clientes por nombre.
     *
     * @param nombre Nombre del cliente a buscar.
     * @return Lista de clientes que coinciden con el nombre proporcionado.
     */
    List<Cliente> filtrarPorNombre(String nombre);

    /**
     * Obtiene el ID de un cliente a partir de su nombre.
     *
     * @param nombre Nombre del cliente.
     * @return ID del cliente si se encuentra.
     * @throws NegocioException Si el cliente no existe o hay un problema en la
     * búsqueda.
     */
    Long obtenerIdPorNombreCliente(String nombre) throws NegocioException;

    /**
     * Obtiene el ID del cliente frecuente usando su nombre como identificador.
     *
     * @param nombreCliente Nombre del cliente frecuente.
     * @return ID del cliente frecuente si existe.
     * @throws PersistenciaException Si ocurre un error al acceder a los datos
     * persistidos.
     */
    Long obtenerIdClienteFrecuentePorNombreCliente(String nombreCliente) throws PersistenciaException;
}
