/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Clientes;

import DTOS.Clientes.NuevoClienteDTO;
import Entidades.Clientes.Cliente;
import InterfazDAO.Clientes.IClientesDAO;
import ManejadorConexiones.ManejadorConexiones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Alberto Jimenez
 */
public class ClientesDAO implements IClientesDAO {

    @Override
    public Cliente registrarCliente(NuevoClienteDTO nuevoClienteDTO) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        Cliente cliente = new Cliente();
        cliente.setNombre(nuevoClienteDTO.getNombre());
        cliente.setNumTelefono(nuevoClienteDTO.getNumTelefono());
        cliente.setFechaRegistro(nuevoClienteDTO.getFechaRegistro());
        if(nuevoClienteDTO.getCorreo() != null && !nuevoClienteDTO.getCorreo().isEmpty()){
            cliente.setCorreo(nuevoClienteDTO.getCorreo());
        }
        
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
            SELECT c FROM Cliente c WHERE c.numTelefono LIKE = :filtroNumero
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

}
