/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO.Clientes;

import DTOS.Clientes.NuevoClienteDTO;
import Entidades.Clientes.Cliente;
import java.util.List;

/**
 *
 * @author Alberto Jimenez
 */
public interface IClientesDAO {
    public abstract Cliente registrarCliente(NuevoClienteDTO nuevoClienteDTO);
    public abstract List<Cliente> filtrarPorNombre(String filtroNombre);
    public abstract List<Cliente> filtrarPorTelefono(Integer filtroNumero);
    public abstract List<Cliente> filtrarPorCorreo(String filtroCorreo);
    public abstract List<Cliente> mostrarListaClientes();
    public abstract Cliente buscarPorTelefono(Integer filtroNumero);
    public abstract List<Cliente> filtrarClientes(String nombre, Integer telefono, String correo);
}
