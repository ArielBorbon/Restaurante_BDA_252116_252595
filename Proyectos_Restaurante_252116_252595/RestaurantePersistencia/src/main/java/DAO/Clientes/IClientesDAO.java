/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO.Clientes;

import DTOS.Clientes.NuevoClienteDTO;
import DTOS.Clientes.NuevoClienteFrecuenteDTO;
import Entidades.Clientes.Cliente;
import Excepciones.PersistenciaException;
import java.util.List;

/**
 * Interfaz de clientes DAO
 * 
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public interface IClientesDAO {
    public abstract Cliente registrarCliente(NuevoClienteDTO nuevoClienteDTO);
    public abstract List<Cliente> filtrarPorNombre(String filtroNombre);
    public abstract List<Cliente> filtrarPorTelefono(String filtroNumero);
    public abstract List<Cliente> filtrarPorCorreo(String filtroCorreo);
    public abstract List<Cliente> mostrarListaClientes();
    public abstract Cliente buscarPorTelefono(String filtroNumero);
    public abstract List<Cliente> filtrarClientes(String nombre, String correo);
    public void actualizarClienteFrecuente(Long idCliente, int puntos, int visitas, double totalGastado);
    public abstract List<NuevoClienteFrecuenteDTO> obtenerClientesFrecuentes();
    public abstract Cliente obtenerPorCorreo(String Correo);
    public abstract List<Cliente> filtrarClientesReporte(String correo);
    public abstract Long obtenerIdPorNombre(String nombre);
    public abstract Long obtenerIdClienteFrecuentePorNombreCliente(String nombreCliente) throws PersistenciaException;
}
