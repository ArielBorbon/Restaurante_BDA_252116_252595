/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Clientes;

import DTOS.Clientes.NuevoClienteDTO;
import DTOS.Clientes.NuevoClienteFrecuenteDTO;
import Entidades.Clientes.Cliente;
import Entidades.Clientes.ClientesFrecuentes;
import Excepciones.PersistenciaException;
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
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class ClientesDAO implements IClientesDAO {

    @Override
    // Metodo para registrar cliente en la base de datos
    public Cliente registrarCliente(NuevoClienteDTO nuevoClienteDTO) {
        // Se obtiene el EntityManager para manejar la conexión con la base de datos
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        // Se inicia una transacción para realizar operaciones en la base de datos
        entityManager.getTransaction().begin();

        Cliente cliente;

        // Se determina el tipo de cliente a registrar
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

        // Se asigna el nombre
        cliente.setNombre(nuevoClienteDTO.getNombre());
        // Intenta encriptar el número de teléfono antes de guardarlo
        try {
            String telefonoEncriptado = Encriptador.encriptar(nuevoClienteDTO.getNumTelefono());
            cliente.setNumTelefono(telefonoEncriptado);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Se asigna la fecha actual como fecha de registro
        cliente.setFechaRegistro(Calendar.getInstance());
        // Se asigna el correo (puede ser null si es opcional)
        cliente.setCorreo(nuevoClienteDTO.getCorreo());

        // Se persiste el cliente en la base de datos
        entityManager.persist(cliente);
        // Se confirma la transacción
        entityManager.getTransaction().commit();
        return cliente;
    }

    @Override
    // Metodo para filtrar por nombre
    public List<Cliente> filtrarPorNombre(String filtroNombre) {
        // Se obtiene el EntityManager para acceder a la base de datos
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        // Consulta JPQL que busca clientes que el nombre tenga el texto parecido
        String jpqlQuery = """
        SELECT c FROM Cliente c WHERE c.nombre LIKE :filtroNombre
                           """;
        // Se crea la consulta tipada a partir del JPQL, indicando que devolverá objetos de tipo Cliente
        TypedQuery<Cliente> query = entityManager.createQuery(jpqlQuery, Cliente.class);
        // Esto asegura que busque coincidencias
        query.setParameter("filtroNombre", "%" + filtroNombre + "%");
        return query.getResultList();
    }

    @Override
    // Filtra clientes cuyo número de teléfono contenga el texto especificado
    public List<Cliente> filtrarPorTelefono(String filtroNumero) {
        // Se obtiene el EntityManager para ejecutar la consulta JPQL
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        // Consulta JPQL que busca coincidencias en el número de teléfono
        String jpqlQuery = """
            SELECT c FROM Cliente c WHERE c.numTelefono LIKE :filtroNumero
                           """;
        
        // Se crea y configura la consulta, agregando el comodín '%' para búsqueda parcial
        TypedQuery<Cliente> query = entityManager.createQuery(jpqlQuery, Cliente.class);
        query.setParameter("filtroNumero", "%" + filtroNumero + "%");
        return query.getResultList();
    }

    @Override
    // Filtra clientes cuyo correo contenga el texto especificado
    public List<Cliente> filtrarPorCorreo(String filtroCorreo) {
        // Se obtiene el EntityManager para ejecutar la consulta JPQL
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        // Consulta JPQL que busca coincidencias parciales en el campo correo
        String jpqlQuery = """
            SELECT c FROM Cliente c WHERE c.correo LIKE :filtroCorreo
                           """;
        // Se crea y configura la consulta, agregando el comodín '%' para búsqueda
        TypedQuery<Cliente> query = entityManager.createQuery(jpqlQuery, Cliente.class);
        query.setParameter("filtroCorreo", "%" + filtroCorreo + "%");
        return query.getResultList();
    }

    @Override
    // Metodo para dar una lista de clientes 
    public List<Cliente> mostrarListaClientes() {
        // Se obtiene el EntityManager para interactuar con la base de datos
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        // Consulta JPQL para seleccionar todos los clientes
        String jpqlQuery = "SELECT c FROM Cliente c";
        // Se crea la consulta tipada indicando que se espera una lista de objetos Cliente
        TypedQuery<Cliente> query = entityManager.createQuery(jpqlQuery, Cliente.class);
        return query.getResultList();
    }

    @Override
    // Busca un cliente por su número de teléfono exacto utilizando la API de Criteria.
    public Cliente buscarPorTelefono(String filtroNumero) {
        // Se obtiene un EntityManager para gestionar la conexión a la base de datos
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            // Se crea un CriteriaBuilder para construir consultas de manera programática
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            // Se crea una consulta tipada para la entidad Cliente
            CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
            // Se define la raíz (FROM Cliente c)
            Root<Cliente> root = query.from(Cliente.class);
            
            // Se construye la condición WHERE c.numTelefono = :filtroNumero
            Predicate nombrePredicate = cb.equal(root.get("numTelefono"), filtroNumero);
            // Se completa la consulta con el WHERE
            query.select(root).where(cb.and(nombrePredicate));

            return entityManager.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            // Si no se encuentra ningún cliente con el número especificado, se retorna null
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    // Metodo para filtrar clientes por nombre y correo
    public List<Cliente> filtrarClientes(String nombre, String correo) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        
        // Se utiliza un StringBuilder para construir dinámicamente la consulta JPQL
        StringBuilder jpql = new StringBuilder("SELECT c FROM Cliente c WHERE 1=1");
        
         // Si se proporciona un nombre, se agrega una condición para filtrar por nombre
        if (nombre != null && !nombre.trim().isEmpty()) {
            jpql.append(" AND LOWER(c.nombre) LIKE LOWER(:nombre)");
        }
        
        // Si se proporciona un correo, se agrega una condición para filtrar por correo
        if (correo != null && !correo.trim().isEmpty()) {
            jpql.append(" AND LOWER(c.correo) LIKE LOWER(:correo)");
        }
        
        // Se crea la consulta usando el JPQL generado
        TypedQuery<Cliente> query = em.createQuery(jpql.toString(), Cliente.class);
        
        // Se asignan los parámetros a la consulta solo si fueron proporcionados
        if (nombre != null && !nombre.trim().isEmpty()) {
            query.setParameter("nombre", "%" + nombre.trim() + "%");
        }
        
        if (correo != null && !correo.trim().isEmpty()) {
            query.setParameter("correo", "%" + correo.trim() + "%");
        }

        return query.getResultList();
    }

    @Override
    // Actualiza los datos acumulativos (puntos, visitas y total gastado) de un cliente frecuente
    public void actualizarClienteFrecuente(Long idCliente, int puntos, int visitas, double totalGastado) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        
        // Busca el cliente frecuente por ID
        ClientesFrecuentes clienteFrecuente = entityManager.find(ClientesFrecuentes.class, idCliente);
        
        // Si se encuentra, actualiza sus datos acumulativos
        if (clienteFrecuente != null) {
            clienteFrecuente.setPuntos(clienteFrecuente.getPuntos() + puntos);
            clienteFrecuente.setVisitas(clienteFrecuente.getVisitas() + visitas);
            clienteFrecuente.setTotalGastado(clienteFrecuente.getTotalGastado() + totalGastado);
            entityManager.merge(clienteFrecuente);
        }

        entityManager.getTransaction().commit();
    }

    @Override
    // Obtiene todos los clientes frecuentes y los convierte en DTOs para su presentación
    public List<NuevoClienteFrecuenteDTO> obtenerClientesFrecuentes() {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpqlQuery = "SELECT c FROM ClienteFrecuente c";

        TypedQuery<ClientesFrecuentes> query = entityManager.createQuery(jpqlQuery, ClientesFrecuentes.class);
        List<ClientesFrecuentes> clientesFrecuentes = query.getResultList();
        
        // Convierte cada cliente frecuente en un DTO
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
    // Obtiene un cliente frecuente por su correo exacto
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
    
    @Override
    // filtra los clientes por correo para el reporte
    public List<Cliente> filtrarClientesReporte(String correo) {
        EntityManager em = ManejadorConexiones.getEntityManager();

        StringBuilder jpql = new StringBuilder("SELECT c FROM Cliente c WHERE 1=1");
        
        // Añade condición si se proporciona correo
        if (correo != null && !correo.trim().isEmpty()) {
            jpql.append(" AND LOWER(c.correo) LIKE LOWER(:correo)");
        }

        TypedQuery<Cliente> query = em.createQuery(jpql.toString(), Cliente.class);
        
        // Establece parámetro si se aplica filtro
        if (correo != null && !correo.trim().isEmpty()) {
            query.setParameter("correo", "%" + correo.trim() + "%");
        }

        return query.getResultList();
    }
    
    @Override
    // Obtiene el ID del primer cliente cuyo nombre contiene el valor proporcionado
    public Long obtenerIdPorNombre(String nombre) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpqlQuery = "SELECT c.id FROM Cliente c WHERE c.nombre LIKE :nombre";
        TypedQuery<Long> query = entityManager.createQuery(jpqlQuery, Long.class);
        query.setParameter("nombre", "%" + nombre + "%");
        List<Long> results = query.getResultList();
        if (results.isEmpty()) {
            return null; // Si no se encuentra el cliente
        }
        return results.get(0); // Devuelve el primer ID encontrado
    }

    @Override
    // Obtiene el ID de un cliente frecuente por nombre (coincidencia parcial)
    public Long obtenerIdClienteFrecuentePorNombreCliente(String nombreCliente) throws PersistenciaException {
        try {
            EntityManager entityManager = ManejadorConexiones.getEntityManager();
            String jpql = "SELECT c.id FROM ClientesFrecuentes c WHERE c.nombre LIKE :nombreCliente";
            TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
            query.setParameter("nombreCliente", "%" + nombreCliente + "%");  
            return query.getSingleResult();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener idClienteFrecuente por nombre", e);
        }
    }
}
