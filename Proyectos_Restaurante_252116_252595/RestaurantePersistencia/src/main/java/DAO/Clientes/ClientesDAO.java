/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Clientes;

import DTOS.Clientes.NuevoClienteDTO;
import Entidades.Clientes.Cliente;
import Entidades.Clientes.ClientesFrecuentes;
import ManejadorConexiones.ManejadorConexiones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Alberto Jimenez
 */
public class ClientesDAO implements IClientesDAO {

    @Override
    public Cliente registrarCliente(NuevoClienteDTO nuevoClienteDTO) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();

        Cliente cliente;

        switch (nuevoClienteDTO.getTipo()) {
            case 1: // ClienteFrecuente
                ClientesFrecuentes cf = new ClientesFrecuentes();
                cf.setTotalGastado(0.0);
                cf.setVisitas(0);
                cf.setPuntos(0);
                cliente = cf;
                break;

            case 2: // ClienteCorporativo
               

            default: // Cliente normal
                cliente = new Cliente();
        }

        cliente.setNombre(nuevoClienteDTO.getNombre());
        cliente.setNumTelefono(nuevoClienteDTO.getNumTelefono());
        cliente.setFechaRegistro(nuevoClienteDTO.getFechaRegistro());
        cliente.setCorreo(nuevoClienteDTO.getCorreo());

        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        return cliente;
    }

    @Override
    public List<Cliente> filtrarPorNombre(String filtroNombre) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpqlQuery = """
            SELECT c FROM Cliente c WHERE c.nombre LIKE :filtroNombre
                           """;
        TypedQuery<Cliente> query = entityManager.createQuery(jpqlQuery, Cliente.class);
        query.setParameter("filtroNombre", "%" + filtroNombre + "%");
        return query.getResultList();
    }

    @Override
    public List<Cliente> filtrarPorTelefono(Integer filtroNumero) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpqlQuery = """
            SELECT c FROM Cliente c WHERE c.numTelefono LIKE :filtroNumero
                           """;
        TypedQuery<Cliente> query = entityManager.createQuery(jpqlQuery, Cliente.class);
        query.setParameter("filtroNumero", "%" + filtroNumero + "%");
        return query.getResultList();
    }

    @Override
    public List<Cliente> filtrarPorCorreo(String filtroCorreo) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpqlQuery = """
            SELECT c FROM Cliente c WHERE c.correo LIKE :filtroCorreo
                           """;
        TypedQuery<Cliente> query = entityManager.createQuery(jpqlQuery, Cliente.class);
        query.setParameter("filtroCorreo", "%" + filtroCorreo + "%");
        return query.getResultList();
    }

    @Override
    public List<Cliente> mostrarListaClientes() {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpqlQuery = "SELECT c FROM Cliente c";
        TypedQuery<Cliente> query = entityManager.createQuery(jpqlQuery, Cliente.class);
        return query.getResultList();
    }
    
    @Override
    public Cliente buscarPorTelefono(Integer filtroNumero){
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
            Root<Cliente> root = query.from(Cliente.class);

            Predicate nombrePredicate = cb.equal(root.get("numTelefono"), filtroNumero);
            query.select(root).where(cb.and(nombrePredicate));

            return entityManager.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            return null; 
        } finally {
            entityManager.close();
        }
    }
}
