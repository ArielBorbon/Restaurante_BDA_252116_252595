/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fabricas;

import BO.IngredienteBO.IngredienteBO;
import DAO.Ingredientes.IngredientesDAO;
import NegocioException.NegocioException;

/**
 *
 * @author rayoa
 */
public class FabricaIngredientes {
    
    public static IngredienteBO crearIngredienteBO() throws NegocioException{
        
        IngredientesDAO ingredienteDAO = new IngredientesDAO();
        IngredienteBO ingredienteBO = new IngredienteBO(ingredienteDAO);
        return ingredienteBO;
    }
}
