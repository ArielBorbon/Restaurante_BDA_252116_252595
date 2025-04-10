/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fabricas;

import BO.ComandasBO.ComandaBO;
import DAO.Clientes.ClientesDAO;
import DAO.Comandas.ComandasDAO;
import DAO.Mesas.MesasDAO;
import DAO.Productos.ProductosDAO;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class FabricaComandas {
    
    public static ComandaBO crearComandaBO(){
        
        ComandasDAO comandasDAO = new ComandasDAO();
        MesasDAO mesasDAO = new MesasDAO();
        ProductosDAO productosDAO = new ProductosDAO();
        ClientesDAO clientesDAO = new ClientesDAO();
        ComandaBO comandaBO = new ComandaBO(comandasDAO,productosDAO , clientesDAO, mesasDAO);
        
        return comandaBO;
    }
    
    
}
