/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Ingredientes;

import DAO.Productos.ProductosDAO;
import DTOS.Ingredientes.IngredienteConCantidadNecesariaDTO;
import DTOS.Ingredientes.NuevoIngredienteDTO;
import Entidades.Ingredientes.Ingrediente;
import Entidades.Productos.Producto;
import Entidades.Productos.ProductoOcupaIngrediente;
import ManejadorConexiones.ManejadorConexiones;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Clase DAO de Ingredientes
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class IngredientesDAO implements IIngredientesDAO {

    /**
     * Constructor default de Ingredientes DAO
     */
    public IngredientesDAO() {
    }

    /**
     * Método que implementa la funcionalidad para registrar un nuevo
     * ingrediente en el sistema.
     *
     * Recibe un objeto DTO con los datos del nuevo ingrediente y realiza las
     * siguientes acciones: 1. Obtiene una instancia del EntityManager para
     * gestionar la persistencia 2. Inicia una transacción en la base de datos
     * 3. Crea una nueva instancia de la entidad Ingrediente 4. Establece las
     * propiedades del ingrediente (nombre, stock y unidad de medida) a partir
     * de los datos del DTO 5. Persiste el nuevo ingrediente en la base de datos
     * 6. Confirma la transacción 7. Retorna el objeto Ingrediente creado
     *
     * @param nuevoIngrediente DTO que contiene la información del ingrediente a
     * registrar
     * @return El objeto Ingrediente creado y persistido en la base de datos
     */
    @Override
    public Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngrediente) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre(nuevoIngrediente.getNombre());
        ingrediente.setStock(nuevoIngrediente.getStock());
        ingrediente.setUnidad_medida(nuevoIngrediente.getUnidad_medida());

        entityManager.persist(ingrediente);
        entityManager.getTransaction().commit();
        return ingrediente;
    }

    /**
     * Método que implementa la funcionalidad para eliminar un ingrediente del
     * sistema.Recibe un objeto Ingrediente y realiza las siguientes acciones:
     * 1.
     *
     * Obtiene una instancia del EntityManager para gestionar la persistencia 2.
     * Inicia una transacción en la base de datos 3. Dentro de un bloque
     * try-catch-finally: - Fusiona el objeto ingrediente con el contexto de
     * persistencia actual usando merge() - Elimina el ingrediente gestionado de
     * la base de datos - Confirma la transacción si todo es exitoso - En caso
     * de error, revierte la transacción y relanza la excepción - Finalmente,
     * cierra el EntityManager para liberar recursos
     *
     * @param ingrediente El objeto Ingrediente que se desea eliminar del
     * sistema
     */
    @Override
    public void eliminarIngrediente(Ingrediente ingrediente) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();

        try {
            Ingrediente managedIngrediente = entityManager.merge(ingrediente);
            entityManager.remove(managedIngrediente);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Método que implementa la funcionalidad para recuperar la lista completa
     * de ingredientes del sistema.
     *
     * Realiza las siguientes acciones: 1. Obtiene una instancia del
     * EntityManager para gestionar la persistencia 2. Define una consulta JPQL
     * que selecciona todos los registros de la entidad Ingrediente 3. Crea un
     * objeto TypedQuery tipado a la clase Ingrediente 4. Ejecuta la consulta y
     * retorna la lista completa de ingredientes
     *
     * @return Una lista que contiene todos los ingredientes almacenados en la
     * base de datos
     */
    @Override
    public List<Ingrediente> mostrarListaIngredientes() {

        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpql = "SELECT i FROM Ingrediente i";
        TypedQuery<Ingrediente> query = entityManager.createQuery(jpql, Ingrediente.class);
        return query.getResultList();
    }

    /**
     * Método que implementa la funcionalidad para actualizar el stock de un
     * ingrediente existente.
     *
     * Recibe un DTO con los datos del ingrediente y el nuevo valor de stock,
     * realizando las siguientes acciones: 1. Obtiene una instancia del
     * EntityManager para gestionar la persistencia 2. Dentro de un bloque
     * try-catch-finally: - Inicia una transacción en la base de datos - Utiliza
     * Criteria API para construir una consulta que busca el ingrediente
     * específico basándose en el nombre y la unidad de medida - Busca el primer
     * ingrediente que coincida con los criterios especificados - Si encuentra
     * el ingrediente: - Verifica que el nuevo stock no sea negativo - Actualiza
     * el stock si es válido, o muestra un mensaje de error si es negativo - Si
     * no encuentra el ingrediente, muestra un mensaje indicándolo - Confirma la
     * transacción si todo es exitoso - En caso de error, revierte la
     * transacción si está activa e imprime la traza de la excepción -
     * Finalmente, cierra el EntityManager para liberar recursos
     *
     * @param nuevoIngredienteDTO DTO que contiene la información del
     * ingrediente a actualizar
     * @param nuevoStock El nuevo valor de stock que se asignará al ingrediente
     */
    @Override
    public void actualizarIngrediente(NuevoIngredienteDTO nuevoIngredienteDTO, double nuevoStock) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            entityManager.getTransaction().begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Ingrediente> criteriaQuery = criteriaBuilder.createQuery(Ingrediente.class);
            Root<Ingrediente> ingredienteRoot = criteriaQuery.from(Ingrediente.class);

            criteriaQuery.select(ingredienteRoot)
                    .where(criteriaBuilder.and(
                            criteriaBuilder.equal(ingredienteRoot.get("nombre"), nuevoIngredienteDTO.getNombre()),
                            criteriaBuilder.equal(ingredienteRoot.get("unidad_medida"), nuevoIngredienteDTO.getUnidad_medida())
                    ));

            Ingrediente ingrediente = entityManager.createQuery(criteriaQuery)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null);

            if (ingrediente != null) {
                if (nuevoStock >= 0) {
                    ingrediente.setStock(nuevoStock);
                    entityManager.merge(ingrediente);
                } else {
                    System.out.println("El stock no puede ser negativo.");
                }
            } else {
                System.out.println("Ingrediente no encontrado para actualizar.");
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    /**
     * Método que implementa la funcionalidad para buscar un ingrediente
     * específico según su nombre y unidad de medida.
     *
     * Realiza las siguientes acciones: 1. Obtiene una instancia del
     * EntityManager para gestionar la persistencia 2. Dentro de un bloque
     * try-catch-finally: - Utiliza Criteria API para construir una consulta con
     * dos predicados: uno para el nombre y otro para la unidad de medida -
     * Combina ambos predicados con una operación lógica AND - Ejecuta la
     * consulta esperando un único resultado - Si no encuentra ningún resultado,
     * captura la excepción NoResultException y retorna null para indicar que no
     * existe el ingrediente buscado - Finalmente, cierra el EntityManager para
     * liberar recursos
     *
     * @param nombre El nombre del ingrediente a buscar
     * @param unidadMedida La unidad de medida del ingrediente a buscar
     * @return El objeto Ingrediente encontrado o null si no existe
     */
    @Override
    public Ingrediente buscarIngredientePorNombreYUnidad(String nombre, String unidadMedida) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Ingrediente> query = cb.createQuery(Ingrediente.class);
            Root<Ingrediente> root = query.from(Ingrediente.class);

            Predicate nombrePredicate = cb.equal(root.get("nombre"), nombre);
            Predicate unidadMedidaPredicate = cb.equal(root.get("unidad_medida"), unidadMedida);
            query.select(root).where(cb.and(nombrePredicate, unidadMedidaPredicate));

            return entityManager.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Método que verifica si un ingrediente específico está siendo utilizado en
     * algún producto.
     *
     * Este método comprueba las relaciones activas de un ingrediente mediante
     * las siguientes acciones: 1. Obtiene una instancia del EntityManager para
     * gestionar la persistencia 2. Dentro de un bloque try-finally: - Define
     * una consulta JPQL que cuenta las ocurrencias del ingrediente en la tabla
     * de relación ProductoOcupaIngrediente, filtrando por nombre y unidad de
     * medida - Establece los parámetros de la consulta con los valores
     * proporcionados - Ejecuta la consulta y obtiene el resultado como un valor
     * Long - Retorna true si el conteo es mayor que cero (existe al menos una
     * relación) o false en caso contrario (no hay relaciones) - Finalmente,
     * cierra el EntityManager para liberar recursos
     *
     * @param nombreIngrediente El nombre del ingrediente a verificar
     * @param unidadMedida La unidad de medida del ingrediente a verificar
     * @return true si el ingrediente está siendo utilizado en algún producto,
     * false si no tiene relaciones
     */
    @Override
    public boolean tieneRelacionesActivas(String nombreIngrediente, String unidadMedida) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            String jpql = """
                    SELECT COUNT(poi) 
                    FROM ProductoOcupaIngrediente poi 
                    JOIN poi.ingrediente i 
                    WHERE i.nombre = :nombre 
                    AND i.unidad_medida = :unidad
                """;

            Long count = entityManager.createQuery(jpql, Long.class)
                    .setParameter("nombre", nombreIngrediente)
                    .setParameter("unidad", unidadMedida)
                    .getSingleResult();

            return count > 0;

        } finally {
            entityManager.close();
        }
    }

    /**
     * Método que recupera todos los ingredientes utilizados en un producto
     * específico.
     *
     * Realiza las siguientes acciones: 1. Obtiene una instancia del
     * EntityManager para gestionar la persistencia 2. Dentro de un bloque
     * try-finally: - Define una consulta JPQL que selecciona los ingredientes
     * relacionados con un producto a través de la tabla de relación
     * ProductoOcupaIngrediente - Crea un TypedQuery tipado a la clase
     * Ingrediente - Establece el parámetro de la consulta con el nombre del
     * producto proporcionado - Ejecuta la consulta y retorna la lista de
     * ingredientes del producto - Finalmente, cierra el EntityManager para
     * liberar recursos
     *
     * @param nombreProducto El nombre del producto del cual se desean obtener
     * los ingredientes
     * @return Una lista con todos los ingredientes utilizados en el producto
     * especificado
     */
    @Override
    public List<Ingrediente> obtenerIngredientesPorNombreProducto(String nombreProducto) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        try {
            String jpql = """
            SELECT poi.ingrediente
            FROM ProductoOcupaIngrediente poi
            WHERE poi.producto.nombre = :nombreProducto
        """;

            TypedQuery<Ingrediente> query = entityManager.createQuery(jpql, Ingrediente.class);
            query.setParameter("nombreProducto", nombreProducto);

            return query.getResultList();

        } finally {
            entityManager.close();
        }
    }

    /**
     * Método que obtiene los ingredientes y sus cantidades necesarias para
     * preparar un producto específico.
     *
     * Realiza las siguientes acciones: 1. Obtiene una instancia del
     * EntityManager para gestionar la persistencia 2. Dentro de un bloque
     * try-catch-finally: - Utiliza el ProductosDAO para buscar el producto por
     * su nombre - Verifica si el producto existe, lanzando una excepción si no
     * se encuentra - Crea una consulta tipada para obtener todas las relaciones
     * ProductoOcupaIngrediente asociadas al ID del producto encontrado - Para
     * cada relación encontrada: - Obtiene el ingrediente asociado - Crea un
     * objeto DTO con el nombre del ingrediente, unidad de medida y cantidad
     * necesaria - Agrega el DTO a la lista de resultados - Retorna la lista
     * completa de DTOs con la información solicitada - Si ocurre algún error,
     * lanza una excepción con un mensaje descriptivo - Finalmente, cierra el
     * EntityManager para liberar recursos
     *
     * @param nombreProducto El nombre del producto del cual se desean obtener
     * los ingredientes con sus cantidades
     * @return Una lista de DTOs que contienen el nombre, unidad de medida y
     * cantidad necesaria de cada ingrediente
     * @throws RuntimeException Si el producto no existe o si ocurre un error
     * durante la consulta
     */
    @Override
    public List<IngredienteConCantidadNecesariaDTO> obtenerIngredientesConCantidadPorProducto(String nombreProducto) {
        EntityManager em = ManejadorConexiones.getEntityManager();

        try {

            ProductosDAO productosDAO = new ProductosDAO();
            Producto producto = productosDAO.buscarProductoPorNombre(nombreProducto);

            if (producto == null) {
                throw new RuntimeException("Producto no encontrado: " + nombreProducto);
            }

            TypedQuery<ProductoOcupaIngrediente> query = em.createQuery(
                    "SELECT poi FROM ProductoOcupaIngrediente poi WHERE poi.producto.id = :idProducto",
                    ProductoOcupaIngrediente.class
            );
            query.setParameter("idProducto", producto.getId());
            List<ProductoOcupaIngrediente> relaciones = query.getResultList();

            List<IngredienteConCantidadNecesariaDTO> resultado = new ArrayList<>();

            for (ProductoOcupaIngrediente relacion : relaciones) {
                Ingrediente ingrediente = relacion.getIngrediente();

                IngredienteConCantidadNecesariaDTO dto = new IngredienteConCantidadNecesariaDTO();
                dto.setNombreIngrediente(ingrediente.getNombre());
                dto.setUnidadMedida(ingrediente.getUnidad_medida());
                dto.setCantidadIngredienteNecesaria(relacion.getCantidad_necesaria());

                resultado.add(dto);
            }

            return resultado;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener ingredientes del producto: " + nombreProducto, e);
        } finally {
            em.close();
        }
    }
}
