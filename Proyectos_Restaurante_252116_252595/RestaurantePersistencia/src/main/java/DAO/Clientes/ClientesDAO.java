/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Clientes;

import DTOS.Clientes.NuevoClienteDTO;
import DTOS.Clientes.NuevoClienteFrecuenteDTO;
import Entidades.Clientes.Cliente;
import Entidades.Clientes.ClientesFrecuentes;
import ManejadorConexiones.ManejadorConexiones;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
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
        cliente.setFechaRegistro(Calendar.getInstance());
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
    public List<Cliente> filtrarPorTelefono(String filtroNumero) {
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
    public Cliente buscarPorTelefono(String filtroNumero) {
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

    @Override
    public List<Cliente> filtrarClientes(String nombre, String telefono, String correo) {
        EntityManager em = ManejadorConexiones.getEntityManager();

        StringBuilder jpql = new StringBuilder("SELECT c FROM Cliente c WHERE 1=1");

        if (nombre != null && !nombre.trim().isEmpty()) {
            jpql.append(" AND LOWER(c.nombre) LIKE LOWER(:nombre)");
        }
        if (telefono != null) {
            jpql.append(" AND c.numTelefono LIKE :telefono");
        }
        if (correo != null && !correo.trim().isEmpty()) {
            jpql.append(" AND LOWER(c.correo) LIKE LOWER(:correo)");
        }

        TypedQuery<Cliente> query = em.createQuery(jpql.toString(), Cliente.class);

        if (nombre != null && !nombre.trim().isEmpty()) {
            query.setParameter("nombre", "%" + nombre.trim() + "%");
        }
        if (telefono != null) {
            query.setParameter("telefono", "%" + telefono.trim() + "%");
        }
        if (correo != null && !correo.trim().isEmpty()) {
            query.setParameter("correo", "%" + correo.trim() + "%");
        }

        return query.getResultList();
    }

    public void actualizarClienteFrecuente(int idCliente, int puntos, int visitas, double totalGastado) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();

        ClientesFrecuentes clienteFrecuente = entityManager.find(ClientesFrecuentes.class, idCliente);

        if (clienteFrecuente != null) {
            clienteFrecuente.setPuntos(clienteFrecuente.getPuntos() + puntos);
            clienteFrecuente.setVisitas(clienteFrecuente.getVisitas() + visitas);
            clienteFrecuente.setTotalGastado(clienteFrecuente.getTotalGastado() + totalGastado);
            entityManager.merge(clienteFrecuente);
        }
 
        entityManager.getTransaction().commit();
    }

    public List<NuevoClienteFrecuenteDTO> obtenerClientesFrecuentes() {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpqlQuery = "SELECT c FROM ClienteFrecuente c";

        TypedQuery<ClientesFrecuentes> query = entityManager.createQuery(jpqlQuery, ClientesFrecuentes.class);
        List<ClientesFrecuentes> clientesFrecuentes = query.getResultList();

        List<NuevoClienteFrecuenteDTO> clientesDTO = new ArrayList<>();
        for (ClientesFrecuentes c : clientesFrecuentes) {
            NuevoClienteFrecuenteDTO clienteDTO = new NuevoClienteFrecuenteDTO(
                    c.getId(),
                    c.getTotalGastado(),
                    c.getVisitas(),
                    c.getPuntos(),
                    c.getNombre(),
                    c.getNumTelefono(),
                    c.getFechaRegistro()
            );
            clientesDTO.add(clienteDTO);
        }

        return clientesDTO;
    }

    @Override
    public ClientesFrecuentes obtenerPorCorreo(String Correo) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            String jpql = "SELECT c FROM Cliente c WHERE c.correo = :correo";      
            return entityManager.createQuery(jpql, ClientesFrecuentes.class)
                    .setParameter("correo", Correo)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            throw new IllegalStateException("Error de integridad: Correo duplicado detectado", e);
        }
    }

    public List<Cliente> filtrarClientesReporte(String telefono, String correo) {
        EntityManager em = ManejadorConexiones.getEntityManager();

        StringBuilder jpql = new StringBuilder("SELECT c FROM Cliente c WHERE 1=1");

        if (telefono != null) {
            jpql.append(" AND c.numTelefono LIKE :telefono");
        }
        if (correo != null && !correo.trim().isEmpty()) {
            jpql.append(" AND LOWER(c.correo) LIKE LOWER(:correo)");
        }

        TypedQuery<Cliente> query = em.createQuery(jpql.toString(), Cliente.class);

        if (telefono != null) {
            query.setParameter("telefono", "%" + telefono.trim() + "%");
        }
        if (correo != null && !correo.trim().isEmpty()) {
            query.setParameter("correo", "%" + correo.trim() + "%");
        }

        return query.getResultList();
    }
}
