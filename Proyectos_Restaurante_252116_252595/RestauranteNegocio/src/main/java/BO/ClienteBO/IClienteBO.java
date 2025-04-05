/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BO.ClienteBO;

import DTOS.Clientes.NuevoClienteDTO;
import Entidades.Clientes.Cliente;
import NegocioException.NegocioException;
/**
 *
 * @author Alberto Jimenez
 */
public interface IClienteBO {
    public Cliente registrarClienteBO(NuevoClienteDTO nuevoClienteDTO) throws NegocioException;
    public Cliente buscarPorTelefono(Integer filtroNumero) throws NegocioException;
}
