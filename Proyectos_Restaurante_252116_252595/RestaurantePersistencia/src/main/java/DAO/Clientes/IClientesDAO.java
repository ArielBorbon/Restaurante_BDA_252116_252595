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
    
    /**
     * 
     * @param nuevoClienteDTO manda un cliente DTO
     * @return regresa un cliente
     */
    public abstract Cliente registrarCliente(NuevoClienteDTO nuevoClienteDTO);
    
    /**
     * 
     * @param filtroNombre manda un nombre
     * @return regresa una lista de clientes
     */
    public abstract List<Cliente> filtrarPorNombre(String filtroNombre);
    
    /**
     * 
     * @param filtroNumero manda un numero
     * @return regresa una lista de clientes
     */
    public abstract List<Cliente> filtrarPorTelefono(String filtroNumero);
    
    /**
     * 
     * @param filtroCorreo manda un correo
     * @return regresa una lista de clientes
     */
    public abstract List<Cliente> filtrarPorCorreo(String filtroCorreo);
    
    /**
     * 
     * @return regresa una lista de clientes
     */
    public abstract List<Cliente> mostrarListaClientes();
    
    /**
     * 
     * @param filtroNumero manda un filtro para telefono
     * @return regresa un solo cliente
     */
    public abstract Cliente buscarPorTelefono(String filtroNumero);

    /**
     * 
     * @param nombre manda un nombre
     * @param correo manda un corre
     * @return regresa una lista de clientes con esos filtros
     */
    public abstract List<Cliente> filtrarClientes(String nombre, String correo);
    
    /**
     * 
     * @param idCliente id cliente
     * @param puntos puntos
     * @param visitas visitas
     * @param totalGastado total gastado
     */
    public void actualizarClienteFrecuente(Long idCliente, int puntos, int visitas, double totalGastado);
    
    /**
     * 
     * @return regresa una lista de clientes frecuentes 
     */
    public abstract List<NuevoClienteFrecuenteDTO> obtenerClientesFrecuentes();
    
    /**
     * 
     * @param Correo manda un correo
     * @return regresa un solo cliente
     */
    public abstract Cliente obtenerPorCorreo(String Correo);
    
    /**
     * 
     * @param correo manda un correo
     * @return regresa una lista de clientes para el reporte
     */
    public abstract List<Cliente> filtrarClientesReporte(String correo);
    
    /**
     * 
     * @param nombre manda un nombre
     * @return regresa el id de un cliente
     */
    public abstract Long obtenerIdPorNombre(String nombre);
    
    /**
     * 
     * @param nombreCliente manda un nombre
     * @return regresa el id de un cliente frecuente
     * @throws PersistenciaException Error de persistencias
     */
    public abstract Long obtenerIdClienteFrecuentePorNombreCliente(String nombreCliente) throws PersistenciaException;
}
