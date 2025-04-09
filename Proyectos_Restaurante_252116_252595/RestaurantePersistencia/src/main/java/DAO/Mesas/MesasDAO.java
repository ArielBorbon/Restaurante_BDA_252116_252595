/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Mesas;

import DTOS.Mesa.NuevaMesaDTO;
import Entidades.Comandas.Comanda;
import Entidades.Mesa.EstadoMesa;
import Entidades.Mesa.Mesa;
import ManejadorConexiones.ManejadorConexiones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author PC Gamer
 */
public class MesasDAO implements IMesasDAO {
    
    

    @Override
    public Mesa crearMesa(NuevaMesaDTO nuevaMesaDTO) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        try {

            Mesa mesa = new Mesa();
            mesa.setNum_mesa(nuevaMesaDTO.getNum_mesa());
            mesa.setEstado(nuevaMesaDTO.getEstado());


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
    
    

    @Override
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
    
    
    
    
    @Override
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
    
    

            
            

    @Override
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
    
    
    public boolean verRelacionesMesa(int num_mesa) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Comanda> root = cq.from(Comanda.class);
        
            cq.select(cb.count(root)).where(cb.equal(root.get("mesa").get("num_mesa"), num_mesa));
            Long count = em.createQuery(cq).getSingleResult();
        
            return count > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error al verificar relaciones de la mesa con comandas", e);
        } finally {
            em.close();
        }
    }

    
    
    
    
    
            
    @Override
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
          
    
    @Override
    public List<Mesa> obtenerListaMesasDisponibles() {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Mesa> cq = cb.createQuery(Mesa.class);
            Root<Mesa> root = cq.from(Mesa.class);
            cq.select(root).where(cb.equal(root.get("estado"), EstadoMesa.DISPONIBLE));
        
            return entityManager.createQuery(cq).getResultList();
        } finally {
            entityManager.close();
        }
    }

    
    @Override
    public List<Mesa> obtenerListaMesasOcupadas() {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Mesa> cq = cb.createQuery(Mesa.class);
            Root<Mesa> root = cq.from(Mesa.class);
            cq.select(root).where(cb.equal(root.get("estado"), EstadoMesa.OCUPADA));
        
            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }

    
    
    
    
    @Override
    public List<Mesa> obtenerListaMesasTodas() {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Mesa> cq = cb.createQuery(Mesa.class);
            Root<Mesa> root = cq.from(Mesa.class);
            cq.select(root);
        
            return entityManager.createQuery(cq).getResultList();
        } finally {
            entityManager.close();
        }
    }
    
    
    @Override
    public Mesa crearMesaEnOrden() {
        EntityManager em = ManejadorConexiones.getEntityManager();
        EntityTransaction tx = em.getTransaction();
    
        try {
            tx.begin();
        
         
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
            Root<Mesa> root = cq.from(Mesa.class);
            cq.select(cb.max(root.get("num_mesa")));
        
            Integer maxNumMesa = em.createQuery(cq).getSingleResult();
            int nuevoNumMesa = (maxNumMesa != null ? maxNumMesa : 0) + 1;
         
            
            Mesa nuevaMesa = new Mesa();
            nuevaMesa.setNum_mesa(nuevoNumMesa);
            nuevaMesa.setEstado(EstadoMesa.DISPONIBLE);
        
            em.persist(nuevaMesa);
            tx.commit();
        
            return nuevaMesa;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
}   

    
    
    
    
    
    @Override
    public boolean existenMesas() {
    EntityManager em = ManejadorConexiones.getEntityManager();
    try {
        Long cantidad = em.createQuery("SELECT COUNT(m) FROM Mesa m", Long.class).getSingleResult();
        return cantidad > 0;
    } finally {
        em.close();
    }
}

    
    
    
    
    
    
    
    
}
