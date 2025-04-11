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
 * Clase BO de Clientes
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class ClienteBO implements IClienteBO {

    private ClientesDAO clientesDAO;

    /**
     *
     * @param clientesDAO
     * @throws NegocioException
     */
    public ClienteBO(ClientesDAO clientesDAO) throws NegocioException {
        if (clientesDAO == null) {
            throw new NegocioException("El DAO no puede ser nulo");
        }
        this.clientesDAO = clientesDAO;
    }

    /**
     * Registra un nuevo cliente validando previamente los datos necesarios
     *
     * @param nuevoClienteDTO
     * @return
     * @throws NegocioException.NegocioException
     */
    @Override
    public Cliente registrarClienteBO(NuevoClienteDTO nuevoClienteDTO) throws NegocioException {
        // Validaciones del DTO y sus campos obligatorios
        if (nuevoClienteDTO == null) {
            throw new NegocioException("El DTO no puede ser nulo");
        }

        if (nuevoClienteDTO.getNombre() == null) {
            throw new NegocioException("El nombre no puede ser nulo");
        }

        if (nuevoClienteDTO.getNumTelefono() == null) {
            throw new NegocioException("El numero de telefono no puede ser nulo");
        }

        if (nuevoClienteDTO.getFechaRegistro() == null) {
            throw new NegocioException("La fecha de registro no puede ser nulo");
        }

        // Verifica que el número de teléfono contenga solo dígitos
        if (!String.valueOf(nuevoClienteDTO.getNumTelefono()).matches("\\d+")) {
            throw new NegocioException("El numero de telefono solo debe contener dígitos");
        }

        // Se valida que el número de teléfono no esté ya registrado
        Cliente telefonoExistente = clientesDAO.buscarPorTelefono(nuevoClienteDTO.getNumTelefono());

        if (telefonoExistente != null) {
            throw new NegocioException("El numero de telefono ya esta asignado a un cliene");
        }

        return clientesDAO.registrarCliente(nuevoClienteDTO);
    }

    /**
     * Busca un cliente por su número de teléfono, validando que sea un número
     * válido
     *
     * @param filtroNumero
     * @return
     * @throws NegocioException.NegocioException
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
     * Obtiene la lista completa de clientes registrados
     *
     * @return
     */
    @Override
    public List<Cliente> obtenerListaClientesBO() {
        return clientesDAO.mostrarListaClientes();
    }

    /**
     * Filtra clientes por nombre y/o correo.Delegado al DAO
     *
     * @param nombre
     * @param correo
     * @return
     * @throws NegocioException.NegocioException
     */
    @Override
    public List<Cliente> filtrarClientes(String nombre, String correo) throws NegocioException {
        return clientesDAO.filtrarClientes(nombre, correo);
    }

    /**
     * Filtra clientes por correo, pensado para reportes
     *
     * @param correo
     * @return
     * @throws NegocioException.NegocioException
     */
    @Override
    public List<Cliente> filtrarClientesReporte(String correo) throws NegocioException {
        return clientesDAO.filtrarClientesReporte(correo);
    }

    /**
     * Filtra clientes cuyo nombre contenga el texto dado
     *
     * @param nombre
     * @return
     */
    @Override
    public List<Cliente> filtrarPorNombre(String nombre) {
        ClientesDAO clienteDAO = new ClientesDAO(); // Crea una nueva instancia local
        return clienteDAO.filtrarPorNombre(nombre);
    }

    /**
     * Obtiene el ID de un cliente a partir de su nombre
     *
     * @param nombre
     * @return
     * @throws NegocioException.NegocioException
     */
    @Override
    public Long obtenerIdPorNombreCliente(String nombre) throws NegocioException {
        return clientesDAO.obtenerIdPorNombre(nombre);
    }

    /**
     * Obtiene el ID de un cliente frecuente a partir del nombre del cliente
     *
     * @param nombreCliente
     * @return
     * @throws Excepciones.PersistenciaException
     */
    @Override
    public Long obtenerIdClienteFrecuentePorNombreCliente(String nombreCliente) throws PersistenciaException {
        try {
            return clientesDAO.obtenerIdClienteFrecuentePorNombreCliente(nombreCliente);
        } catch (PersistenciaException e) {
            // Se vuelve a lanzar la excepción con un mensaje personalizado
            throw new PersistenciaException("Error al obtener idClienteFrecuente por nombre", e);
        }
    }
}
