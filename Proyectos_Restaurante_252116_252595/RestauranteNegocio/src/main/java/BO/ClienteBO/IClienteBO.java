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
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public interface IClienteBO {
    public Cliente registrarClienteBO(NuevoClienteDTO nuevoClienteDTO) throws NegocioException;
    public Cliente buscarPorTelefono(String filtroNumero) throws NegocioException;
    public List<Cliente> obtenerListaClientesBO();
    List<Cliente> filtrarClientes(String nombre, String correo) throws NegocioException;
    List<Cliente> filtrarClientesReporte(String correo) throws NegocioException;
    List<Cliente> filtrarPorNombre(String nombre);
    Long obtenerIdPorNombreCliente(String nombre) throws NegocioException;
    Long obtenerIdClienteFrecuentePorNombreCliente(String nombreCliente) throws PersistenciaException;
}
