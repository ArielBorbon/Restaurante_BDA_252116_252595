/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManejadorConexiones;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author PC Gamer
 */


public class ManejadorConexiones {
    
    private static final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("com.mycompany_RestauranteDominio_jar_1.0-SNAPSHOTPU");
    
    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }
    

}
