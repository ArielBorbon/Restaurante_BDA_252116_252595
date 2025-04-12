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
 * Clase DAO de mesas
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class MesasDAO implements IMesasDAO {

    /**
     * Constructor de mesas DAO
     */
    public MesasDAO() {
    }

    /**
     * Este método permite crear una nueva mesa en el sistema utilizando la
     * información proporcionada en un objeto `NuevaMesaDTO`.1.Se obtiene un
     * `EntityManager` a través de `ManejadorConexiones`, que gestiona la
     * conexión a la base de datos.
     *
     * 2. Se inicia una transacción utilizando
     * `entityManager.getTransaction().begin()`, lo que permite agrupar las
     * operaciones de base de datos que se realizarán. 3. Se crea una nueva
     * instancia de `Mesa` y se establecen sus propiedades utilizando los datos
     * del objeto `nuevaMesaDTO`, que incluye el número de mesa y el estado de
     * la mesa. 4. Se persiste la nueva mesa en la base de datos mediante
     * `entityManager.persist(mesa)`. 5. Se confirma la transacción con
     * `entityManager.getTransaction().commit()`, asegurando que los cambios se
     * guarden de manera permanente. 6. Si ocurre cualquier excepción durante el
     * proceso, se realiza un rollback de la transacción y se lanza una
     * `RuntimeException` con un mensaje que indica que hubo un error al crear
     * la mesa. 7. Finalmente, se cierra el `EntityManager` en el bloque
     * `finally` para liberar recursos.
     *
     * Este método es útil para agregar nuevas mesas al sistema, permitiendo a
     * los administradores gestionar el espacio disponible en el establecimiento
     * de manera efectiva.
     *
     * @param nuevaMesaDTO
     * @return
     */
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

    /**
     * Este método permite eliminar una mesa del sistema utilizando la
     * información proporcionada en un objeto `NuevaMesaDTO`.1.
     *
     * Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que
     * gestiona la conexión a la base de datos. 2. Se inicia una transacción
     * utilizando `entityManager.getTransaction().begin()`, lo que permite
     * agrupar las operaciones de base de datos que se realizarán. 3. Se utiliza
     * el `CriteriaBuilder` para construir una consulta que busca una mesa
     * (`Mesa`) en la base de datos cuyo número de mesa coincida con el valor
     * proporcionado en el objeto `nuevaMesaDTO`. - Se crea un `CriteriaQuery`
     * para la entidad `Mesa`. - Se define la raíz de la consulta (`Root Mesa`)
     * y se establece la condición de búsqueda utilizando `cb.equal()`. 4. Se
     * ejecuta la consulta y se obtiene la mesa correspondiente utilizando
     * `entityManager.createQuery(cq).getSingleResult()`. 5. Se elimina la mesa
     * de la base de datos mediante `entityManager.remove(mesa)`. 6. Se confirma
     * la transacción con `entityManager.getTransaction().commit()`, asegurando
     * que los cambios se guarden de manera permanente. 7. Si ocurre cualquier
     * excepción durante el proceso, se realiza un rollback de la transacción y
     * se lanza una `RuntimeException` con un mensaje que indica que hubo un
     * error al eliminar la mesa. 8. Finalmente, se cierra el `EntityManager` en
     * el bloque `finally` para liberar recursos.
     *
     * Este método es útil para eliminar mesas del sistema, permitiendo a los
     * administradores gestionar el espacio disponible en el establecimiento de
     * manera efectiva.
     *
     * @param nuevaMesaDTO
     */
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

    /**
     * Este método permite ocupar una mesa en el sistema utilizando la
     * información proporcionada en un objeto `NuevaMesaDTO`.1.Se obtiene un
     * `EntityManager` a través de `ManejadorConexiones`, que gestiona la
     * conexión a la base de datos.
     *
     * 2. Se inicia una transacción utilizando
     * `entityManager.getTransaction().begin()`, lo que permite agrupar las
     * operaciones de base de datos que se realizarán. 3. Se utiliza el
     * `CriteriaBuilder` para construir una consulta que busca una mesa (`Mesa`)
     * en la base de datos cuyo número de mesa coincida con el valor
     * proporcionado en el objeto `nuevaMesaDTO`. - Se crea un `CriteriaQuery`
     * para la entidad `Mesa`. - Se define la raíz de la consulta (`Root Mesa `)
     * y se establece la condición de búsqueda utilizando `cb.equal()`. 4. Se
     * ejecuta la consulta y se obtiene la mesa correspondiente utilizando
     * `entityManager.createQuery(cq).getSingleResult()`. 5. Se cambia el estado
     * de la mesa a `OCUPADA` utilizando `mesa.setEstado(EstadoMesa.OCUPADA)`.
     * 6. Se actualiza la mesa en la base de datos mediante
     * `entityManager.merge(mesa)`. 7. Se confirma la transacción con
     * `entityManager.getTransaction().commit()`, asegurando que los cambios se
     * guarden de manera permanente. 8. Si ocurre cualquier excepción durante el
     * proceso, se realiza un rollback de la transacción y se lanza una
     * `RuntimeException` con un mensaje que indica que hubo un error al ocupar
     * la mesa. 9. Finalmente, se cierra el `EntityManager` en el bloque
     * `finally` para liberar recursos.
     *
     * Este método es útil para marcar una mesa como ocupada en el sistema,
     * permitiendo a los administradores y al personal gestionar el estado de
     * las mesas de manera efectiva en el establecimiento.
     *
     * @param nuevaMesaDTO
     * @return
     */
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

    /**
     * Este método permite disponibilizar una mesa en el sistema utilizando la
     * información proporcionada en un objeto `NuevaMesaDTO`.1.Se obtiene un
     * `EntityManager` a través de `ManejadorConexiones`, que gestiona la
     * conexión a la base de datos.
     *
     * 2. Se inicia una transacción utilizando
     * `entityManager.getTransaction().begin()`, lo que permite agrupar las
     * operaciones de base de datos que se realizarán. 3. Se utiliza el
     * `CriteriaBuilder` para construir una consulta que busca una mesa (`Mesa`)
     * en la base de datos cuyo número de mesa coincida con el valor
     * proporcionado en el objeto `nuevaMesaDTO`. - Se crea un `CriteriaQuery`
     * para la entidad `Mesa`. - Se define la raíz de la consulta (`Root Mesa`)
     * y se establece la condición de búsqueda utilizando `cb.equal()`. 4. Se
     * ejecuta la consulta y se obtiene la mesa correspondiente utilizando
     * `entityManager.createQuery(cq).getSingleResult()`. 5. Se cambia el estado
     * de la mesa a `DISPONIBLE` utilizando
     * `mesa.setEstado(EstadoMesa.DISPONIBLE)`. 6. Se actualiza la mesa en la
     * base de datos mediante `entityManager.merge(mesa)`. 7. Se confirma la
     * transacción con `entityManager.getTransaction().commit()`, asegurando que
     * los cambios se guarden de manera permanente. 8. Si ocurre cualquier
     * excepción durante el proceso, se realiza un rollback de la transacción y
     * se lanza una `RuntimeException` con un mensaje que indica que hubo un
     * error al disponibilizar la mesa. 9. Finalmente, se cierra el
     * `EntityManager` en el bloque `finally` para liberar recursos.
     *
     * Este método es útil para marcar una mesa como disponible en el sistema,
     * permitiendo a los administradores y al personal gestionar el estado de
     * las mesas de manera efectiva en el establecimiento.
     *
     * @param nuevaMesaDTO
     * @return
     */
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

    /**
     * Este método permite verificar si una mesa tiene relaciones con alguna
     * comanda en el sistema, utilizando el número de mesa proporcionado como
     * parámetro.1.Se obtiene un `EntityManager` a través de
     * `ManejadorConexiones`, que gestiona la conexión a la base de datos.
     *
     * 2. Se utiliza el `CriteriaBuilder` para construir una consulta que cuenta
     * el número de comandas (`Comanda`) asociadas a la mesa cuyo número
     * coincide con el valor proporcionado en el parámetro `num_mesa`. - Se crea
     * un `CriteriaQuery` que devuelve un valor de tipo `Long`. - Se define la
     * raíz de la consulta (`Root Comanda`) y se establece la condición de
     * búsqueda utilizando `cb.equal()`, que compara el número de mesa de la
     * comanda con el número de mesa proporcionado. 3. Se ejecuta la consulta y
     * se obtiene el conteo de comandas asociadas a la mesa utilizando
     * `em.createQuery(cq).getSingleResult()`. 4. Se devuelve `true` si el
     * conteo es mayor que 0, lo que indica que hay al menos una comanda
     * asociada a la mesa; de lo contrario, se devuelve `false`. 5. Si ocurre
     * cualquier excepción durante el proceso, se lanza una `RuntimeException`
     * con un mensaje que indica que hubo un error al verificar las relaciones
     * de la mesa con las comandas. 6. Finalmente, se cierra el `EntityManager`
     * en el bloque `finally` para liberar recursos.
     *
     * Este método es útil para determinar si una mesa está actualmente en uso o
     * tiene relaciones activas con comandas, lo que puede ser importante para
     * la gestión de mesas en un establecimiento.
     *
     * @param num_mesa
     * @return
     */
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

    /**
     * Este método permite obtener una mesa del sistema utilizando su número de
     * mesa.1.Se obtiene un `EntityManager` a través de `ManejadorConexiones`,
     * que gestiona la conexión a la base de datos.
     *
     * 2. Se utiliza el `CriteriaBuilder` para construir una consulta que busca
     * una mesa (`Mesa`) en la base de datos cuyo número de mesa coincida con el
     * valor proporcionado en el parámetro `numMesa`. - Se crea un
     * `CriteriaQuery` para la entidad `Mesa`. - Se define la raíz de la
     * consulta (`Root Mesa`) y se establece la condición de búsqueda utilizando
     * `cb.equal()`. 3. Se ejecuta la consulta y se obtiene la mesa
     * correspondiente utilizando
     * `entityManager.createQuery(cq).getSingleResult()`. 4. Si se encuentra la
     * mesa, se devuelve. Si no se encuentra, se lanza una excepción
     * `NoResultException`, que se captura y se lanza una `RuntimeException` con
     * un mensaje que indica que no se encontró la mesa con el número
     * especificado. 5. Finalmente, se cierra el `EntityManager` en el bloque
     * `finally` para liberar recursos.
     *
     * Este método es útil para acceder a la información de una mesa específica
     * en el sistema, permitiendo a los administradores y al personal gestionar
     * las mesas de manera efectiva.
     *
     * @param numMesa
     * @return
     */
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

    /**
     * Este método permite obtener una lista de mesas que están disponibles en
     * el sistema.1.
     *
     * Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que
     * gestiona la conexión a la base de datos. 2. Se utiliza el
     * `CriteriaBuilder` para construir una consulta que busca todas las mesas
     * (`Mesa`) en la base de datos cuyo estado sea `DISPONIBLE`. - Se crea un
     * `CriteriaQuery` para la entidad `Mesa`. - Se define la raíz de la
     * consulta (`Root Mesa`) y se establece la condición de búsqueda utilizando
     * `cb.equal()`, que compara el estado de la mesa con
     * `EstadoMesa.DISPONIBLE`. 3. Se ejecuta la consulta y se obtiene la lista
     * de mesas disponibles utilizando
     * `entityManager.createQuery(cq).getResultList()`. 4. Finalmente, se cierra
     * el `EntityManager` en el bloque `finally` para liberar recursos.
     *
     * Este método es útil para que los administradores y el personal puedan ver
     * rápidamente qué mesas están disponibles para ser ocupadas, facilitando la
     * gestión del espacio en el establecimiento.
     *
     * @return
     */
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

    /**
     * Este método permite obtener una lista de mesas que están ocupadas en el
     * sistema.1.
     *
     * Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que
     * gestiona la conexión a la base de datos. 2. Se utiliza el
     * `CriteriaBuilder` para construir una consulta que busca todas las mesas
     * (`Mesa`) en la base de datos cuyo estado sea `OCUPADA`. - Se crea un
     * `CriteriaQuery` para la entidad `Mesa`. - Se define la raíz de la
     * consulta (`Root Mesa`) y se establece la condición de búsqueda utilizando
     * `cb.equal()`, que compara el estado de la mesa con `EstadoMesa.OCUPADA`.
     * 3. Se ejecuta la consulta y se obtiene la lista de mesas ocupadas
     * utilizando `em.createQuery(cq).getResultList()`. 4. Finalmente, se cierra
     * el `EntityManager` en el bloque `finally` para liberar recursos.
     *
     * Este método es útil para que los administradores y el personal puedan ver
     * rápidamente qué mesas están ocupadas, facilitando la gestión del espacio
     * en el establecimiento y ayudando a coordinar el servicio a los clientes.
     *
     * @return
     */
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

    /**
     * Este método permite obtener una lista de todas las mesas en el sistema,
     * independientemente de su estado.1.
     *
     * Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que
     * gestiona la conexión a la base de datos. 2. Se utiliza el
     * `CriteriaBuilder` para construir una consulta que busca todas las mesas
     * (`Mesa`) en la base de datos. - Se crea un `CriteriaQuery` para la
     * entidad `Mesa`. - Se define la raíz de la consulta (`Root Mesa`) y se
     * seleccionan todas las mesas sin aplicar ningún filtro. 3. Se ejecuta la
     * consulta y se obtiene la lista de todas las mesas utilizando
     * `entityManager.createQuery(cq).getResultList()`. 4. Finalmente, se cierra
     * el `EntityManager` en el bloque `finally` para liberar recursos.
     *
     * Este método es útil para que los administradores y el personal puedan ver
     * un listado completo de todas las mesas disponibles en el establecimiento,
     * lo que facilita la gestión y planificación del espacio.
     *
     * @return
     */
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

    /**
     * Este método permite crear una nueva mesa en el sistema y asignarle un
     * número de mesa único.1.
     *
     * Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que
     * gestiona la conexión a la base de datos. 2. Se inicia una transacción
     * utilizando `tx.begin()`, lo que permite agrupar las operaciones de base
     * de datos que se realizarán. 3. Se utiliza el `CriteriaBuilder` para
     * construir una consulta que obtiene el número máximo de mesa (`num_mesa`)
     * existente en la base de datos. - Se crea un `CriteriaQuery` que devuelve
     * un valor de tipo `Integer`. - Se define la raíz de la consulta (`Root
     * Mesa`) y se selecciona el valor máximo del campo `num_mesa` utilizando
     * `cb.max()`. 4. Se ejecuta la consulta y se obtiene el número máximo de
     * mesa. Si no hay mesas existentes, se establece `maxNumMesa` como `null`,
     * y se asigna `nuevoNumMesa` como 1 (o el siguiente número disponible). 5.
     * Se crea una nueva instancia de `Mesa` y se le asigna el número de mesa
     * (`nuevoNumMesa`) y el estado `DISPONIBLE`. 6. Se persiste la nueva mesa
     * en la base de datos utilizando `em.persist(nuevaMesa)`. 7. Se confirma la
     * transacción con `tx.commit()`, asegurando que los cambios se guarden de
     * manera permanente. 8. Si ocurre cualquier excepción durante el proceso,
     * se verifica si la transacción está activa y se realiza un rollback.
     * Luego, se lanza la excepción original. 9. Finalmente, se cierra el
     * `EntityManager` en el bloque `finally` para liberar recursos.
     *
     * Este método es útil para agregar nuevas mesas al sistema de manera
     * ordenada, asegurando que cada mesa tenga un número único y que su estado
     * inicial sea disponible.
     *
     * @return
     */
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

    /**
     * Este método permite verificar si existen mesas en el sistema.1.
     *
     * Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que
     * gestiona la conexión a la base de datos. 2. Se ejecuta una consulta JPQL
     * (Java Persistence Query Language) que cuenta el número total de mesas
     * (`Mesa`) en la base de datos. La consulta es: `SELECT COUNT(m) FROM Mesa
     * m`. 3. Se obtiene el resultado de la consulta, que es un valor de tipo
     * `Long`, representando la cantidad de mesas. 4. Se devuelve `true` si la
     * cantidad es mayor que 0, lo que indica que hay al menos una mesa en el
     * sistema; de lo contrario, se devuelve `false`. 5. Finalmente, se cierra
     * el `EntityManager` en el bloque `finally` para liberar recursos.
     *
     * Este método es útil para determinar si hay mesas registradas en el
     * sistema, lo que puede ser importante para la gestión y operación del
     * establecimiento.
     *
     * @return
     */
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
