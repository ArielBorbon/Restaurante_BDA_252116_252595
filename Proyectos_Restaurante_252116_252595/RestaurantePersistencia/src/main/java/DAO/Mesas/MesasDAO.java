/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Mesas;

import DTOS.Mesa.NuevaMesaDTO;
import Entidades.Mesa.EstadoMesa;
import Entidades.Mesa.Mesa;
import ManejadorConexiones.ManejadorConexiones;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author PC Gamer
 */
public class MesasDAO {
    
    

    public Mesa crearMesa(NuevaMesaDTO nuevaMesaDTO) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        try {

            Mesa mesa = new Mesa();
            mesa.setNum_mesa(nuevaMesaDTO.getNum_mesa());


            entityManager.persist(mesa);
            entityManager.getTransaction().commit();
            return mesa;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error al crear la mesa", e);
        } finally {
            entityManager.close();
        }
    }    
    
    

    public void eliminarMesa(NuevaMesaDTO nuevaMesaDTO) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        try {

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Mesa> cq = cb.createQuery(Mesa.class);
            Root<Mesa> root = cq.from(Mesa.class);
            cq.select(root).where(cb.equal(root.get("num_mesa"), nuevaMesaDTO.getNum_mesa()));
            Mesa mesa = entityManager.createQuery(cq).getSingleResult();


            entityManager.remove(mesa);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error al eliminar la mesa", e);
        } finally {
            entityManager.close();
        }
    }    
    
    
    
    
            public Mesa ocuparMesa(NuevaMesaDTO nuevaMesaDTO) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        try {

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Mesa> cq = cb.createQuery(Mesa.class);
            Root<Mesa> root = cq.from(Mesa.class);
            cq.select(root).where(cb.equal(root.get("num_mesa"), nuevaMesaDTO.getNum_mesa()));
            Mesa mesa = entityManager.createQuery(cq).getSingleResult();


            mesa.setEstado(EstadoMesa.OCUPADA);
            entityManager.merge(mesa);
            entityManager.getTransaction().commit();
            return mesa;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error al ocupar la mesa", e);
        } finally {
            entityManager.close();
        }
    }
    
    

            
            

    public Mesa disponibilizarMesa(NuevaMesaDTO nuevaMesaDTO) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Mesa> cq = cb.createQuery(Mesa.class);
            Root<Mesa> root = cq.from(Mesa.class);
            cq.select(root).where(cb.equal(root.get("num_mesa"), nuevaMesaDTO.getNum_mesa()));
            Mesa mesa = entityManager.createQuery(cq).getSingleResult();


            mesa.setEstado(EstadoMesa.DISPONIBLE);
            entityManager.merge(mesa);
            entityManager.getTransaction().commit();
            return mesa;
            
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error al disponibilizar la mesa", e);
        } finally {
            entityManager.close();
        }
    }      
    
    
    
    
            
    public Mesa obtenerMesaPorNumMesa(int numMesa) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Mesa> cq = cb.createQuery(Mesa.class);
            Root<Mesa> root = cq.from(Mesa.class);
            
            cq.select(root).where(cb.equal(root.get("num_mesa"), numMesa));
            
            Mesa mesa = entityManager.createQuery(cq).getSingleResult();
            return mesa;
        } catch (NoResultException e) {
            throw new RuntimeException("No se encontró la mesa con el número: " + numMesa, e);
        } finally {
            entityManager.close();
        }
    }
            
    
    
    
}
