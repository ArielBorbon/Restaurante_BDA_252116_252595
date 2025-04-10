/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fabricas;

import BO.ClienteBO.ClienteBO;
import DAO.Clientes.ClientesDAO;
import NegocioException.NegocioException;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class FabricaClientes {
    public static ClienteBO crearClienteBO() throws NegocioException{
        ClientesDAO clientesDAO = new ClientesDAO();
        ClienteBO clienteBO = new ClienteBO(clientesDAO);
        return clienteBO;
    }
}
