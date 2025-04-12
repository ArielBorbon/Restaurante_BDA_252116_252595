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
     * Registrador de clientes
     * 
     * @param nuevoClienteDTO manda un cliente DTO
     * @return regresa un cliente
     */
    public abstract Cliente registrarCliente(NuevoClienteDTO nuevoClienteDTO);
    
    /**
     * Filtrador por nombres
     * 
     * @param filtroNombre manda un nombre
     * @return regresa una lista de clientes
     */
    public abstract List<Cliente> filtrarPorNombre(String filtroNombre);
    
    /**
     * filtrador por telefono
     * 
     * @param filtroNumero manda un numero
     * @return regresa una lista de clientes
     */
    public abstract List<Cliente> filtrarPorTelefono(String filtroNumero);
    
    /**
     * filtrador por correo
     * 
     * @param filtroCorreo manda un correo
     * @return regresa una lista de clientes
     */
    public abstract List<Cliente> filtrarPorCorreo(String filtroCorreo);
    
    /**
     * Metodo para mostrar una lista de clientes
     * 
     * @return regresa una lista de clientes
     */
    public abstract List<Cliente> mostrarListaClientes();
    
    /**
     * busqueda de clientes por telefono
     * 
     * @param filtroNumero manda un filtro para telefono
     * @return regresa un solo cliente
     */
    public abstract Cliente buscarPorTelefono(String filtroNumero);

    /**
     * filtra a los clientes por su nombre y/o correo
     * 
     * @param nombre manda un nombre
     * @param correo manda un corre
     * @return regresa una lista de clientes con esos filtros
     */
    public abstract List<Cliente> filtrarClientes(String nombre, String correo);
    
    /**
     * Actualiza los puntos, visitas y total gastado de un cliente
     * 
     * @param idCliente id cliente
     * @param puntos puntos
     * @param visitas visitas
     * @param totalGastado total gastado
     */
    public void actualizarClienteFrecuente(Long idCliente, int puntos, int visitas, double totalGastado);
    
    /**
     * Lista de clientes frecuentes existentes
     * 
     * @return regresa una lista de clientes frecuentes 
     */
    public abstract List<NuevoClienteFrecuenteDTO> obtenerClientesFrecuentes();
    
    /**
     * Obtiene un cliente por su correo
     * 
     * @param Correo manda un correo
     * @return regresa un solo cliente
     */
    public abstract Cliente obtenerPorCorreo(String Correo);
    
    /**
     * Lista de clientes para almacenar en el reporte
     * 
     * @param correo manda un correo
     * @return regresa una lista de clientes para el reporte
     */
    public abstract List<Cliente> filtrarClientesReporte(String correo);
    
    /**
     * Obtiene el Id de un cliente buscandolo con su nombre
     * 
     * @param nombre manda un nombre
     * @return regresa el id de un cliente
     */
    public abstract Long obtenerIdPorNombre(String nombre);
    
    /**
     * Obtiene el id de un cliente frecuente por su nombre
     * 
     * @param nombreCliente manda un nombre
     * @return regresa el id de un cliente frecuente
     * @throws PersistenciaException Error de persistencias
     */
    public abstract Long obtenerIdClienteFrecuentePorNombreCliente(String nombreCliente) throws PersistenciaException;
}
