/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fabricas;

import BO.MesaBO.MesaBO;
import DAO.Mesas.MesasDAO;

/**
 *
 * @author PC Gamer
 */
public class FabricaMesas {
    
    
    public static MesaBO crearMesaBO (){
        MesasDAO mesasDAO = new MesasDAO();
        MesaBO mesaBO = new MesaBO(mesasDAO);
        return mesaBO;
    }
}
