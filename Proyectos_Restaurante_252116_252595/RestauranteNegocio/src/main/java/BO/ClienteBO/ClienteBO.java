/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO.ClienteBO;

import DAO.Clientes.ClientesDAO;
import DTOS.Clientes.NuevoClienteDTO;
import NegocioException.NegocioException;
import Entidades.Clientes.Cliente;
import java.util.List;

/**
 *
 * @author Alberto Jimenez
 */
public class ClienteBO implements IClienteBO {

    private ClientesDAO clientesDAO;

    public ClienteBO(ClientesDAO clientesDAO) throws NegocioException {
        if (clientesDAO == null) {
            throw new NegocioException("El DAO no puede ser nulo");
        }
        this.clientesDAO = clientesDAO;
    }

    @Override
    public Cliente registrarClienteBO(NuevoClienteDTO nuevoClienteDTO) throws NegocioException {
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

        if (!String.valueOf(nuevoClienteDTO.getNumTelefono()).matches("\\d+")) {
            throw new NegocioException("El numero de telefono solo debe contener dígitos");
        }

        Cliente telefonoExistente = clientesDAO.buscarPorTelefono(nuevoClienteDTO.getNumTelefono());

        if (telefonoExistente != null) {
            throw new NegocioException("El numero de telefono ya esta asignado a un cliene");
        }

        return clientesDAO.registrarCliente(nuevoClienteDTO);
    }

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

    @Override
    public List<Cliente> obtenerListaClientesBO() {
        return clientesDAO.mostrarListaClientes();
    }

    public List<Cliente> filtrarClientes(String nombre, String correo) throws NegocioException {
        return clientesDAO.filtrarClientes(nombre, correo);
    }

    public List<Cliente> filtrarClientesReporte(String correo) throws NegocioException {
        return clientesDAO.filtrarClientesReporte(correo);
    }
}
