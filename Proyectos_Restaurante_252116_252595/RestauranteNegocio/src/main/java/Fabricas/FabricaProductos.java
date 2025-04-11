/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fabricas;

import BO.ProductoBO.ProductoBO;
import DAO.Productos.ProductosDAO;
import NegocioException.NegocioException;

/**
 * Fabrica de Productos
 * 
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class FabricaProductos {
    
    /**
     * 
     * @return
     * @throws NegocioException 
     */
    public static ProductoBO crearProductoBO() throws NegocioException{
         ProductosDAO productosDAO = new ProductosDAO();
        ProductoBO productoBO = new ProductoBO(productosDAO);
        return productoBO;
    }
    
    
}
