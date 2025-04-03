/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Productos;

import DTOS.Productos.NuevoProductoDTO;
import Entidades.Productos.Producto;
import Entidades.Productos.Tipo_Producto;
import ManejadorConexiones.ManejadorConexiones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author PC Gamer
 */
public class ProductosDAO {

    public List<Producto> filtrarPorNombreProducto(String nombreFiltro) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpqlQuery
                = """
    SELECT p FROM Producto p WHERE p.nombre LIKE :nombreFiltrador
    """;
        TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);
        query.setParameter("nombreFiltrador", "%" + nombreFiltro + "%");
        return query.getResultList();
    }

    public List<Producto> filtrarPorTipoProducto(Tipo_Producto tipoFiltro) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpqlQuery
                = """                  
        SELECT p FROM Producto p WHERE p.tipo = :tipoFiltrador;
        """;
        TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);

        query.setParameter("tipoFiltrador", tipoFiltro);
        return query.getResultList();

    }

    public List<Producto> filtrarPorNombreYTipoProducto(String nombreFiltro, Tipo_Producto tipoFiltro) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpqlQuery
                = """
        SELECT p FROM Producto p WHERE p.nombre LIKE :nombreFiltrador AND p.tipo = :tipoFiltrador
        """;

        TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);

        query.setParameter("nombreFiltrador", "%" + nombreFiltro + "%");
        query.setParameter("tipoFiltrador", tipoFiltro);

        return query.getResultList();

    }

    public Producto registrarProducto(NuevoProductoDTO nuevoProducto) {

        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        Producto producto = new Producto();
        producto.setNombre(nuevoProducto.getNombre());
        producto.setPrecio(nuevoProducto.getPrecio());
        producto.setTipo(nuevoProducto.getTipo());

        entityManager.persist(producto);
        entityManager.getTransaction().commit();
        return producto;
    }

    public void eliminarProducto(NuevoProductoDTO nuevoProducto) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();

        try {
            String jpql = """
                          SELECT p FROM Producto p WHERE p.nombre = :nombre AND p.precio = :precio AND p.tipo = :tipo
                          """;
            Producto producto = entityManager.createQuery(jpql, Producto.class)
                    .setParameter("nombre", nuevoProducto.getNombre())
                    .setParameter("precio", nuevoProducto.getPrecio())
                    .setParameter("tipo", nuevoProducto.getTipo())
                    .getSingleResult();

            entityManager.remove(producto);
            entityManager.getTransaction().commit();

        } catch (NoResultException e) {
            System.out.println("No se encontró el producto para eliminar.");
            entityManager.getTransaction().rollback();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public List<Producto> mostrarListaProductos() {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpql = "SELECT p FROM Producto p";
        TypedQuery<Producto> query = entityManager.createQuery(jpql, Producto.class);
        return query.getResultList();
    }
}
