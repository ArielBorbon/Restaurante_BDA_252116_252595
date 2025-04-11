/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO.ClienteBO;

import DAO.Clientes.ClientesDAO;
import DTOS.Clientes.NuevoClienteDTO;
import NegocioException.NegocioException;
import Entidades.Clientes.Cliente;
import Excepciones.PersistenciaException;
import java.util.List;

/**
 * Clase BO (Business Object) que implementa la lógica de negocio para la
 * gestión de clientes. Se encarga de realizar validaciones antes de delegar las
 * operaciones a la capa DAO.
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class ClienteBO implements IClienteBO {

    private ClientesDAO clientesDAO;

    /**
     * Constructor que inicializa la clase con un DAO de clientes.
     *
     * @param clientesDAO Objeto DAO para acceder a la capa de persistencia de
     * clientes.
     * @throws NegocioException Si el DAO es nulo.
     */
    public ClienteBO(ClientesDAO clientesDAO) throws NegocioException {
        if (clientesDAO == null) {
            throw new NegocioException("El DAO no puede ser nulo");
        }
        this.clientesDAO = clientesDAO;
    }

    /**
     * Registra un nuevo cliente validando previamente los datos requeridos.
     *
     * @param nuevoClienteDTO Objeto con la información del nuevo cliente.
     * @return El cliente registrado.
     * @throws NegocioException Si los datos son inválidos o el número ya
     * existe.
     */
    @Override
    public Cliente registrarClienteBO(NuevoClienteDTO nuevoClienteDTO) throws NegocioException {
        if (nuevoClienteDTO == null) {
            throw new NegocioException("El DTO no puede ser nulo");
        }

        if (nuevoClienteDTO.getNombre() == null) {
            throw new NegocioException("El nombre no puede ser nulo");
        }

        if (nuevoClienteDTO.getNumTelefono() == null) {
            throw new NegocioException("El número de teléfono no puede ser nulo");
        }

        if (nuevoClienteDTO.getFechaRegistro() == null) {
            throw new NegocioException("La fecha de registro no puede ser nula");
        }

        if (!String.valueOf(nuevoClienteDTO.getNumTelefono()).matches("\\d+")) {
            throw new NegocioException("El número de teléfono solo debe contener dígitos");
        }

        Cliente telefonoExistente = clientesDAO.buscarPorTelefono(nuevoClienteDTO.getNumTelefono());

        if (telefonoExistente != null) {
            throw new NegocioException("El número de teléfono ya está asignado a un cliente");
        }

        return clientesDAO.registrarCliente(nuevoClienteDTO);
    }

    /**
     * Busca un cliente por su número de teléfono, validando que el número sea
     * correcto.
     *
     * @param filtroNumero Número de teléfono a buscar.
     * @return Cliente encontrado.
     * @throws NegocioException Si el número es inválido o el cliente no existe.
     */
    @Override
    public Cliente buscarPorTelefono(String filtroNumero) throws NegocioException {
        if (filtroNumero == null) {
            throw new NegocioException("El número de teléfono no puede ser nulo.");
        }

        if (!String.valueOf(filtroNumero).matches("\\d+")) {
            throw new NegocioException("El número de teléfono solo debe contener dígitos.");
        }

        Cliente cliente = clientesDAO.buscarPorTelefono(filtroNumero);

        if (cliente == null) {
            throw new NegocioException("No se encontró ningún cliente con ese número de teléfono.");
        }

        return cliente;
    }

    /**
     * Obtiene la lista completa de clientes registrados.
     *
     * @return Lista de clientes.
     */
    @Override
    public List<Cliente> obtenerListaClientesBO() {
        return clientesDAO.mostrarListaClientes();
    }

    /**
     * Filtra clientes por nombre y/o correo.
     *
     * @param nombre Nombre del cliente.
     * @param correo Correo electrónico del cliente.
     * @return Lista de clientes que coinciden con los filtros.
     * @throws NegocioException Si ocurre un error durante el filtrado.
     */
    @Override
    public List<Cliente> filtrarClientes(String nombre, String correo) throws NegocioException {
        return clientesDAO.filtrarClientes(nombre, correo);
    }

    /**
     * Filtra clientes por correo electrónico, utilizado para reportes.
     *
     * @param correo Correo electrónico a filtrar.
     * @return Lista de clientes que coinciden.
     * @throws NegocioException Si ocurre un error durante el filtrado.
     */
    @Override
    public List<Cliente> filtrarClientesReporte(String correo) throws NegocioException {
        return clientesDAO.filtrarClientesReporte(correo);
    }

    /**
     * Filtra clientes cuyo nombre contenga el texto dado.
     *
     * @param nombre Nombre del cliente a buscar.
     * @return Lista de clientes que coinciden parcialmente con el nombre.
     */
    @Override
    public List<Cliente> filtrarPorNombre(String nombre) {
        ClientesDAO clienteDAO = new ClientesDAO(); // Crea una nueva instancia local
        return clienteDAO.filtrarPorNombre(nombre);
    }

    /**
     * Obtiene el ID de un cliente a partir de su nombre.
     *
     * @param nombre Nombre del cliente.
     * @return ID del cliente.
     * @throws NegocioException Si el cliente no se encuentra.
     */
    @Override
    public Long obtenerIdPorNombreCliente(String nombre) throws NegocioException {
        return clientesDAO.obtenerIdPorNombre(nombre);
    }

    /**
     * Obtiene el ID de un cliente frecuente a partir del nombre del cliente.
     *
     * @param nombreCliente Nombre del cliente frecuente.
     * @return ID del cliente frecuente.
     * @throws PersistenciaException Si ocurre un error en la capa de
     * persistencia.
     */
    @Override
    public Long obtenerIdClienteFrecuentePorNombreCliente(String nombreCliente) throws PersistenciaException {
        try {
            return clientesDAO.obtenerIdClienteFrecuentePorNombreCliente(nombreCliente);
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al obtener idClienteFrecuente por nombre", e);
        }
    }
}
