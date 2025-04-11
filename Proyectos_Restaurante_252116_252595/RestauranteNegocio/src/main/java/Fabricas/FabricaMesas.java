/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fabricas;

import BO.MesaBO.MesaBO;
import DAO.Mesas.MesasDAO;

/**
 * Fabrica de Mesas
 * 
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class FabricaMesas {
    
    /**
     * 
     * @return 
     */
    public static MesaBO crearMesaBO (){
        MesasDAO mesasDAO = new MesasDAO();
        MesaBO mesaBO = new MesaBO(mesasDAO);
        return mesaBO;
    }
}
