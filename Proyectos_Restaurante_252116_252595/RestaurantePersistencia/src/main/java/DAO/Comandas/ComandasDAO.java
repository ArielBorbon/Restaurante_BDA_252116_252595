/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Comandas;

import DAO.Clientes.ClientesDAO;
import DAO.Ingredientes.IngredientesDAO;
import DAO.Mesas.MesasDAO;
import DAO.Productos.ProductosDAO;
import DTOS.Comandas.NuevaComandaDTO;
import DTOS.Comandas.NuevoDetalleComandaDTO;
import DTOS.Ingredientes.IngredienteConCantidadNecesariaDTO;
import Entidades.Clientes.ClientesFrecuentes;
import Entidades.Comandas.Comanda;
import Entidades.Comandas.DetalleComanda;
import Entidades.Comandas.EstadoComanda;
import Entidades.Ingredientes.Ingrediente;
import Entidades.Ingredientes.Unidad_Medida;
import Entidades.Mesa.Mesa;
import Entidades.Productos.Producto;
import Excepciones.PersistenciaException;
import ManejadorConexiones.ManejadorConexiones;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
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
public class ComandasDAO implements IComandasDAO {

    public ComandasDAO() {
    }

    /*
Este método permite registrar una nueva comanda en el sistema, incluyendo los detalles de la comanda y la mesa asociada.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos, y se inicia una transacción con `em.getTransaction().begin()`.
2. Se crea una instancia de `MesasDAO` para obtener la mesa correspondiente al número de mesa proporcionado en el objeto `comandaDTO`.
3. Se verifica si se ha proporcionado un correo electrónico del cliente. Si es así, se crea una instancia de `ClientesDAO` para buscar al cliente frecuente asociado a ese correo.
4. Se crea una nueva instancia de `Comanda` y se establecen sus propiedades utilizando los datos del objeto `comandaDTO`, incluyendo el folio, fecha y hora, estado, total, mesa y cliente frecuente.
5. Se itera sobre la lista de detalles de la comanda (`detallesDTO`):
   - Para cada detalle, se crea una instancia de `ProductosDAO` para buscar el producto correspondiente por su nombre.
   - Se crea una nueva instancia de `DetalleComanda` y se establecen sus propiedades, incluyendo el producto, precio unitario, notas especiales, cantidad, importe total y la comanda a la que pertenece.
   - Se agrega el detalle a la lista de detalles de la comanda.
6. Se persiste la comanda en la base de datos utilizando `em.persist(comanda)`.
7. Se confirma la transacción con `em.getTransaction().commit()`, asegurando que todos los cambios se guarden de manera permanente.
8. Si ocurre cualquier excepción durante el proceso, se realiza un rollback de la transacción y se lanza una `RuntimeException` con un mensaje que indica que hubo un error al registrar la comanda.
9. Finalmente, se cierra el `EntityManager` en el bloque `finally` para liberar recursos.

Este método es útil para registrar una nueva comanda en el sistema, asegurando que todos los detalles y relaciones se manejen correctamente, lo que permite un seguimiento adecuado de las órdenes de los clientes.
     */
    @Override
    public Comanda registrarComanda(NuevaComandaDTO comandaDTO, List<NuevoDetalleComandaDTO> detallesDTO) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        em.getTransaction().begin();

        try {
            MesasDAO mesasDAO = new MesasDAO();
            Mesa mesa = mesasDAO.obtenerMesaPorNumMesa(comandaDTO.getNum_mesa());
            ClientesFrecuentes cliente = null;

            if (comandaDTO.getCorreoCliente() != null && !comandaDTO.getCorreoCliente().isBlank()) {
                ClientesDAO clientesDAO = new ClientesDAO();
                cliente = clientesDAO.obtenerPorCorreo(comandaDTO.getCorreoCliente());
            }

            Comanda comanda = new Comanda();
            comanda.setFolio(comandaDTO.getFolio());
            comanda.setFechaHora(comandaDTO.getFecha_hora());
            comanda.setEstado(comandaDTO.getEstado_comanda());
            comanda.setTotal(comandaDTO.getTotal());
            comanda.setMesa(mesa);
            comanda.setClienteFrecuente(cliente);

            for (NuevoDetalleComandaDTO detalleDTO : detallesDTO) {

                ProductosDAO productosDAO = new ProductosDAO();
                Producto producto = productosDAO.buscarProductoPorNombre(detalleDTO.getNombreProducto());

                DetalleComanda detalle = new DetalleComanda();
                detalle.setProducto(producto);
                detalle.setPrecioUnitario(detalleDTO.getPrecioUnitario());
                detalle.setNotasEspeciales(detalleDTO.getNotas_producto());
                detalle.setCantidad(detalleDTO.getCantidad());
                detalle.setImporteTotal(detalleDTO.getImporteTotal());
                detalle.setComanda(comanda);

                comanda.getDetalles().add(detalle);
            }

            em.persist(comanda);
            em.getTransaction().commit();
            return comanda;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al registrar la comanda", e);
        } finally {
            em.close();
        }
    }

    /*
Este método genera un nuevo folio para una comanda, asegurando que sea único y esté basado en la fecha actual.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos.
2. Se obtiene la fecha actual utilizando `Calendar` y se formatea en el formato `yyyyMMdd` utilizando `SimpleDateFormat`. Esto genera una cadena que representa la fecha en el formato deseado.
3. Se utiliza el `CriteriaBuilder` para construir una consulta que cuenta cuántas comandas se han registrado en la misma fecha.
   - Se crea un `CriteriaQuery` que devuelve un valor de tipo `Long`.
   - Se define la raíz de la consulta (`Root<Comanda>`) y se establece un `Predicate` que compara la fecha de la comanda (`fechaHora`) con la fecha actual, utilizando la función `DATE` para asegurarse de que solo se comparen las fechas sin considerar la hora.
4. Se ejecuta la consulta y se obtiene el conteo de comandas registradas en la fecha actual.
5. Se genera un número consecutivo para la comanda, que es el conteo más uno, formateado como un número de tres dígitos (por ejemplo, "001", "002", etc.) utilizando `String.format()`.
6. Se construye el folio de la comanda en el formato "OB-yyyyMMdd-consecutivo" y se devuelve como resultado.

Este método es útil para asegurar que cada comanda tenga un folio único y fácilmente identificable, lo que facilita el seguimiento y la gestión de las órdenes en el sistema.
     */
    @Override
    public String generarFolioComanda() {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            Calendar hoy = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String fechaFormateada = sdf.format(hoy.getTime());

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Comanda> root = cq.from(Comanda.class);

            Predicate mismoDia = cb.equal(cb.function("DATE", Date.class, root.get("fechaHora")),
                    cb.function("DATE", Date.class, cb.literal(hoy.getTime())));
            cq.select(cb.count(root)).where(mismoDia);
            Long conteo = em.createQuery(cq).getSingleResult();

            String consecutivo = String.format("%03d", conteo + 1);
            return "OB-" + fechaFormateada + "-" + consecutivo;
        } finally {
            em.close();
        }

    }

    /*
Este método calcula el total de un detalle de comanda basado en la cantidad de productos y el precio unitario del producto.

1. Se crea una instancia de `ProductosDAO` para acceder a la información de los productos en la base de datos.
2. Se busca el producto correspondiente al nombre proporcionado en el objeto `detalleDTO` utilizando el método `buscarProductoPorNombre()`.
3. Se verifica si el producto fue encontrado. Si no se encuentra, se lanza una `RuntimeException` con un mensaje que indica que el producto no fue encontrado, incluyendo el nombre del producto.
4. Si el producto es encontrado, se calcula el total multiplicando la cantidad del producto (`detalleDTO.getCantidad()`) por el precio del producto (`producto.getPrecio()`).
5. Se devuelve el total calculado.

Este método es útil para determinar el importe total de un detalle de comanda, asegurando que se utilicen los precios actualizados de los productos y que se manejen adecuadamente los casos en los que un producto no se encuentra en el sistema.
     */
    @Override
    public double calcularTotalDetalleComanda(NuevoDetalleComandaDTO detalleDTO) {
        ProductosDAO productosDAO = new ProductosDAO();
        Producto producto = productosDAO.buscarProductoPorNombre(detalleDTO.getNombreProducto());

        if (producto == null) {
            throw new RuntimeException("Producto no encontrado: " + detalleDTO.getNombreProducto());
        }

        return detalleDTO.getCantidad() * producto.getPrecio();
    }

    /*
Este método calcula el total de una comanda sumando los totales de cada uno de los detalles de la comanda.

1. Se inicializa una variable `total` en 0, que se utilizará para acumular el total de la comanda.
2. Se itera sobre la lista de detalles de la comanda (`detallesDTO`):
   - Para cada detalle, se llama al método `calcularTotalDetalleComanda(detalle)`, que calcula el total para ese detalle específico.
   - El resultado de cada cálculo se suma a la variable `total`.
3. Al finalizar la iteración, se devuelve el total acumulado.

Este método es útil para obtener el importe total de una comanda, asegurando que se consideren todos los detalles y sus respectivos totales, lo que permite una gestión precisa de los costos en el sistema.
     */
    @Override
    public double calcularTotalComanda(List<NuevoDetalleComandaDTO> detallesDTO) {
        double total = 0;

        for (NuevoDetalleComandaDTO detalle : detallesDTO) {
            total += calcularTotalDetalleComanda(detalle);
        }

        return total;
    }

    /*
Este método permite modificar una comanda existente en el sistema, actualizando sus detalles y propiedades.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos, y se inicia una transacción con `em.getTransaction().begin()`.
2. Se crea una consulta `TypedQuery` para buscar la comanda correspondiente al folio proporcionado en el objeto `comandaDTO`. Se establece el parámetro `folio` con el valor correspondiente.
3. Se obtiene la comanda utilizando `query.getSingleResult()`. Si no se encuentra la comanda, se lanza una `RuntimeException` con un mensaje que indica que no se encontró la comanda con el folio especificado.
4. Se crea una consulta `Query` para eliminar todos los detalles de la comanda actual. Se establece el parámetro `comanda` con la comanda obtenida y se ejecuta la consulta para eliminar los detalles existentes.
5. Se crea una instancia de `ProductosDAO` para acceder a la información de los productos.
6. Se itera sobre la lista de nuevos detalles de la comanda (`nuevosDetallesDTO`):
   - Para cada detalle, se busca el producto correspondiente por su nombre utilizando `productosDAO.buscarProductoPorNombre()`.
   - Se crea una nueva instancia de `DetalleComanda` y se establecen sus propiedades, incluyendo el producto, precio unitario, notas especiales, cantidad, importe total y la comanda a la que pertenece.
   - Se persiste el nuevo detalle en la base de datos utilizando `em.persist(detalle)` y se agrega a la lista de detalles de la comanda.
7. Se actualizan las propiedades de la comanda con los nuevos valores del objeto `comandaDTO`, incluyendo el estado, fecha y hora, y total.
8. Se utiliza `em.merge(comanda)` para actualizar la comanda en la base de datos con los nuevos valores.
9. Se confirma la transacción con `em.getTransaction().commit()`, asegurando que todos los cambios se guarden de manera permanente.
10. Si ocurre cualquier excepción durante el proceso, se realiza un rollback de la transacción y se lanza una `RuntimeException` con un mensaje que indica que hubo un error al modificar la comanda.
11. Finalmente, se cierra el `EntityManager` en el bloque `finally` para liberar recursos.

Este método es útil para actualizar una comanda existente, permitiendo modificar tanto sus detalles como sus propiedades generales, lo que facilita la gestión de las órdenes en el sistema.
     */
    @Override
    public Comanda modificarComanda(NuevaComandaDTO comandaDTO, List<NuevoDetalleComandaDTO> nuevosDetallesDTO) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        em.getTransaction().begin();

        try {

            TypedQuery<Comanda> query = em.createQuery(
                    "SELECT c FROM Comanda c WHERE c.folio = :folio", Comanda.class);
            query.setParameter("folio", comandaDTO.getFolio());
            Comanda comanda = query.getSingleResult();

            if (comanda == null) {
                throw new RuntimeException("Comanda no encontrada con el folio: " + comandaDTO.getFolio());
            }

            Query eliminarDetalles = em.createQuery(
                    "DELETE FROM DetalleComanda d WHERE d.comanda = :comanda");
            eliminarDetalles.setParameter("comanda", comanda);
            eliminarDetalles.executeUpdate();

            ProductosDAO productosDAO = new ProductosDAO();
            for (NuevoDetalleComandaDTO detalleDTO : nuevosDetallesDTO) {
                Producto producto = productosDAO.buscarProductoPorNombre(detalleDTO.getNombreProducto());

                DetalleComanda detalle = new DetalleComanda();
                detalle.setProducto(producto);
                detalle.setPrecioUnitario(detalleDTO.getPrecioUnitario());
                detalle.setNotasEspeciales(detalleDTO.getNotas_producto());
                detalle.setCantidad(detalleDTO.getCantidad());
                detalle.setImporteTotal(detalleDTO.getImporteTotal());
                detalle.setComanda(comanda);

                em.persist(detalle);
                comanda.getDetalles().add(detalle);
            }

            comanda.setEstado(comandaDTO.getEstado_comanda());
            comanda.setFechaHora(comandaDTO.getFecha_hora());
            comanda.setTotal(comandaDTO.getTotal());

            em.merge(comanda);
            em.getTransaction().commit();
            return comanda;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al modificar la comanda", e);
        } finally {
            em.close();
        }
    }

    /*
Este método permite eliminar una comanda existente en el sistema, junto con sus detalles asociados.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos, y se inicia una transacción con `em.getTransaction().begin()`.
2. Se crea una consulta `TypedQuery` para buscar la comanda correspondiente al folio proporcionado en el objeto `comandaDTO`. Se establece el parámetro `folio` con el valor correspondiente.
3. Se obtiene la comanda utilizando `query.getSingleResult()`. Si no se encuentra la comanda, se lanza una `RuntimeException` con un mensaje que indica que no se encontró la comanda con el folio especificado.
4. Se crea una consulta `Query` para eliminar todos los detalles de la comanda actual. Se establece el parámetro `comanda` con la comanda obtenida y se ejecuta la consulta para eliminar los detalles existentes.
5. Se elimina la comanda de la base de datos utilizando `em.remove(comanda)`.
6. Se confirma la transacción con `em.getTransaction().commit()`, asegurando que todos los cambios se guarden de manera permanente.
7. Si ocurre cualquier excepción durante el proceso, se realiza un rollback de la transacción y se lanza una `RuntimeException` con un mensaje que indica que hubo un error al eliminar la comanda.
8. Finalmente, se cierra el `EntityManager` en el bloque `finally` para liberar recursos.

Este método es útil para eliminar una comanda y todos sus detalles del sistema, lo que permite una gestión adecuada de las órdenes y la capacidad de corregir errores o cancelar pedidos según sea necesario.
     */
    @Override
    public void eliminarComanda(NuevaComandaDTO comandaDTO) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        em.getTransaction().begin();

        try {

            TypedQuery<Comanda> query = em.createQuery(
                    "SELECT c FROM Comanda c WHERE c.folio = :folio", Comanda.class);
            query.setParameter("folio", comandaDTO.getFolio());
            Comanda comanda = query.getSingleResult();

            if (comanda == null) {
                throw new RuntimeException("Comanda no encontrada con el folio: " + comandaDTO.getFolio());
            }

            Query eliminarDetalles = em.createQuery(
                    "DELETE FROM DetalleComanda d WHERE d.comanda = :comanda");
            eliminarDetalles.setParameter("comanda", comanda);
            eliminarDetalles.executeUpdate();

            em.remove(comanda);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al eliminar la comanda", e);
        } finally {
            em.close();
        }
    }

    /*
Este método cambia el estado de una comanda a "CANCELADA" en el sistema.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos, y se inicia una transacción con `em.getTransaction().begin()`.
2. Se crea una consulta `TypedQuery` para buscar la comanda correspondiente al folio proporcionado. Se establece el parámetro `folio` con el valor correspondiente.
3. Se obtiene la comanda utilizando `query.getSingleResult()`.
4. Se actualiza el estado de la comanda a `EstadoComanda.CANCELADA` utilizando el método `setEstado()`.
5. Se confirma la transacción con `em.getTransaction().commit()`, asegurando que el cambio se guarde de manera permanente.
6. Si ocurre cualquier excepción durante el proceso, se realiza un rollback de la transacción y se lanza una `RuntimeException` con un mensaje que indica que hubo un error al cambiar el estado de la comanda.
7. Finalmente, se cierra el `EntityManager` en el bloque `finally` para liberar recursos.

Este método es útil para gestionar el estado de las comandas, permitiendo marcar una comanda como cancelada, lo que es esencial para el control de pedidos y la atención al cliente en el sistema.
     */
    @Override
    public void cambiarEstadoComandaACancelada(String folio) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        em.getTransaction().begin();

        try {
            TypedQuery<Comanda> query = em.createQuery(
                    "SELECT c FROM Comanda c WHERE c.folio = :folio", Comanda.class);
            query.setParameter("folio", folio);
            Comanda comanda = query.getSingleResult();

            comanda.setEstado(EstadoComanda.CANCELADA);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al cambiar el estado de la comanda a CANCELADA", e);
        } finally {
            em.close();
        }
    }

    /*
Este método cambia el estado de una comanda a "ENTREGADA" en el sistema.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos, y se inicia una transacción con `em.getTransaction().begin()`.
2. Se crea una consulta `TypedQuery` para buscar la comanda correspondiente al folio proporcionado. Se establece el parámetro `folio` con el valor correspondiente.
3. Se obtiene la comanda utilizando `query.getSingleResult()`.
4. Se actualiza el estado de la comanda a `EstadoComanda.ENTREGADA` utilizando el método `setEstado()`.
5. Se confirma la transacción con `em.getTransaction().commit()`, asegurando que el cambio se guarde de manera permanente.
6. Si ocurre cualquier excepción durante el proceso, se realiza un rollback de la transacción y se lanza una `RuntimeException` con un mensaje que indica que hubo un error al cambiar el estado de la comanda.
7. Finalmente, se cierra el `EntityManager` en el bloque `finally` para liberar recursos.

Este método es útil para gestionar el estado de las comandas, permitiendo marcar una comanda como entregada, lo que es esencial para el control de pedidos y la atención al cliente en el sistema.
     */
    @Override
    public void cambiarEstadoComandaAEntregada(String folio) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        em.getTransaction().begin();

        try {
            TypedQuery<Comanda> query = em.createQuery(
                    "SELECT c FROM Comanda c WHERE c.folio = :folio", Comanda.class);
            query.setParameter("folio", folio);
            Comanda comanda = query.getSingleResult();

            comanda.setEstado(EstadoComanda.ENTREGADA);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al cambiar el estado de la comanda a ENTREGADA", e);
        } finally {
            em.close();
        }
    }

    /*
Este método verifica si hay suficiente stock de los ingredientes necesarios para preparar los productos especificados en una lista de detalles de comanda.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones` para gestionar la conexión a la base de datos.
2. Se crean instancias de `IngredientesDAO` y `ProductosDAO` para acceder a la información de ingredientes y productos.
3. Se inicializa un `Map<String, Double>` llamado `ingredientesNecesarios` para almacenar la cantidad total de cada ingrediente necesario, utilizando una clave que combina el nombre del ingrediente y su unidad de medida.
4. Se itera sobre la lista de detalles de la comanda (`detallesDTO`):
   - Para cada detalle, se obtiene el nombre del producto y la cantidad de platillos solicitados.
   - Se obtienen los ingredientes necesarios para el producto utilizando `ingredientesDAO.obtenerIngredientesConCantidadPorProducto(nombreProducto)`.
   - Se itera sobre la lista de ingredientes del producto:
     - Se calcula la cantidad requerida de cada ingrediente multiplicando la cantidad necesaria por la cantidad de platillos.
     - Se utiliza `merge` para acumular la cantidad requerida de cada ingrediente en el mapa `ingredientesNecesarios`, utilizando una clave que combina el nombre del ingrediente y su unidad de medida.
5. Se itera sobre las entradas del mapa `ingredientesNecesarios`:
   - Para cada entrada, se separa la clave en el nombre del ingrediente y la unidad de medida.
   - Se busca el ingrediente en la base de datos utilizando `ingredientesDAO.buscarIngredientePorNombreYUnidad(nombreIngrediente, unidadString)`.
   - Se verifica si el ingrediente existe y si su stock es suficiente para cubrir la cantidad necesaria. Si no hay suficiente stock, se devuelve `false`.
6. Si se verifica que todos los ingredientes tienen suficiente stock, se devuelve `true`.
7. Si ocurre cualquier excepción durante el proceso, se lanza una `RuntimeException` con un mensaje que indica que hubo un error al verificar el stock de ingredientes.
8. Finalmente, se cierra el `EntityManager` en el bloque `finally` para liberar recursos.

Este método es útil para garantizar que se cuente con los ingredientes necesarios antes de procesar una comanda, lo que ayuda a evitar problemas de falta de stock durante la preparación de los platillos.
     */
    @Override
    public boolean verificarStockNecesarioProductos(List<NuevoDetalleComandaDTO> detallesDTO) {
        EntityManager em = ManejadorConexiones.getEntityManager();

        try {
            IngredientesDAO ingredientesDAO = new IngredientesDAO();
            ProductosDAO productosDAO = new ProductosDAO();

            Map<String, Double> ingredientesNecesarios = new HashMap<>();

            for (NuevoDetalleComandaDTO detalleDTO : detallesDTO) {
                String nombreProducto = detalleDTO.getNombreProducto();
                int cantidadPlatillos = detalleDTO.getCantidad();

                List<IngredienteConCantidadNecesariaDTO> ingredientesProducto
                        = ingredientesDAO.obtenerIngredientesConCantidadPorProducto(nombreProducto);

                for (IngredienteConCantidadNecesariaDTO ingredienteDTO : ingredientesProducto) {
                    String nombreIngrediente = ingredienteDTO.getNombreIngrediente();
                    String unidadMedida = ingredienteDTO.getUnidadMedida();

                    double cantidadRequerida = ingredienteDTO.getCantidadIngredienteNecesaria() * cantidadPlatillos;

                    String clave = nombreIngrediente + "|" + unidadMedida.toString();

                    ingredientesNecesarios.merge(clave, cantidadRequerida, Double::sum);
                }
            }

            for (Map.Entry<String, Double> entrada : ingredientesNecesarios.entrySet()) {
                String clave = entrada.getKey();
                double cantidadNecesaria = entrada.getValue();

                String[] partes = clave.split("\\|");
                String nombreIngrediente = partes[0];
                Unidad_Medida unidad = Unidad_Medida.valueOf(partes[1]);
                String unidadString = unidad.toString();
                Ingrediente ingrediente = ingredientesDAO.buscarIngredientePorNombreYUnidad(nombreIngrediente, unidadString);

                if (ingrediente == null || ingrediente.getStock() < cantidadNecesaria) {
                    return false;
                }
            }

            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error al verificar stock de ingredientes necesarios", e);
        } finally {
            em.close();
        }
    }

    /*
Este método se encarga de restar el stock de los ingredientes necesarios para los productos de una comanda, basándose en los detalles de la comanda proporcionados.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones` para gestionar la conexión a la base de datos y se inicia una transacción con `em.getTransaction().begin()`.
2. Se crean instancias de `IngredientesDAO` y `ProductosDAO` para acceder a la información de ingredientes y productos.
3. Se inicializa un `Map<String, Double>` llamado `ingredientesPorRestar` para almacenar la cantidad total de cada ingrediente que se debe restar, utilizando una clave que combina el nombre del ingrediente y su unidad de medida.
4. Se itera sobre la lista de detalles de la comanda (`detallesDTO`):
   - Para cada detalle, se busca el producto correspondiente utilizando `productosDAO.buscarProductoPorNombre(detalle.getNombreProducto())`.
   - Se obtiene la cantidad de platillos solicitados.
   - Se obtienen los ingredientes necesarios para el producto utilizando `ingredientesDAO.obtenerIngredientesConCantidadPorProducto(producto.getNombre())`.
   - Se itera sobre la lista de ingredientes del producto:
     - Se calcula la cantidad total de cada ingrediente necesaria multiplicando la cantidad requerida por la cantidad de platillos.
     - Se utiliza `merge` para acumular la cantidad total de cada ingrediente en el mapa `ingredientesPorRestar`, utilizando una clave que combina el nombre del ingrediente y su unidad de medida.
5. Se itera sobre las entradas del mapa `ingredientesPorRestar`:
   - Para cada entrada, se separa la clave en el nombre del ingrediente y la unidad de medida.
   - Se busca el ingrediente en la base de datos utilizando `ingredientesDAO.buscarIngredientePorNombreYUnidad(nombre, unidad.toString())`.
   - Si el ingrediente no se encuentra, se lanza una `RuntimeException` indicando que el ingrediente no fue encontrado.
   - Se calcula el nuevo stock restando la cantidad a restar del stock actual del ingrediente. Si el nuevo stock es menor que 0, se lanza una `RuntimeException` indicando que hay stock insuficiente.
   - Si hay suficiente stock, se actualiza el stock del ingrediente y se persiste el cambio en la base de datos utilizando `em.merge(ingrediente)`.
6. Se confirma la transacción con `em.getTransaction().commit()`, asegurando que todos los cambios se guarden de manera permanente.
7. Si ocurre cualquier excepción durante el proceso, se realiza un rollback de la transacción y se lanza una `RuntimeException` con un mensaje que indica que hubo un error al restar el stock de ingredientes.
8. Finalmente, se cierra el `EntityManager` en el bloque `finally` para liberar recursos.

Este método es esencial para mantener la integridad del stock de ingredientes en el sistema, asegurando que se actualice correctamente cada vez que se procesa una comanda.
     */
    @Override
    public void restarStockIngredientesPorProductosComanda(List<NuevoDetalleComandaDTO> detallesDTO) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        em.getTransaction().begin();

        try {
            IngredientesDAO ingredientesDAO = new IngredientesDAO();
            ProductosDAO productosDAO = new ProductosDAO();

            Map<String, Double> ingredientesPorRestar = new HashMap<>();

            for (NuevoDetalleComandaDTO detalle : detallesDTO) {
                Producto producto = productosDAO.buscarProductoPorNombre(detalle.getNombreProducto());
                int cantidadPlatillos = detalle.getCantidad();

                List<IngredienteConCantidadNecesariaDTO> ingredientes
                        = ingredientesDAO.obtenerIngredientesConCantidadPorProducto(producto.getNombre());

                for (IngredienteConCantidadNecesariaDTO ingredienteDTO : ingredientes) {
                    String nombre = ingredienteDTO.getNombreIngrediente();
                    String unidad = ingredienteDTO.getUnidadMedida();
                    double cantidadTotal = ingredienteDTO.getCantidadIngredienteNecesaria() * cantidadPlatillos;

                    String clave = nombre + "|" + unidad.toString();

                    ingredientesPorRestar.merge(clave, cantidadTotal, Double::sum);
                }
            }

            for (Map.Entry<String, Double> entry : ingredientesPorRestar.entrySet()) {
                String[] partes = entry.getKey().split("\\|");
                String nombre = partes[0];
                Unidad_Medida unidad = Unidad_Medida.valueOf(partes[1]);
                double cantidadARestar = entry.getValue();

                Ingrediente ingrediente = ingredientesDAO.buscarIngredientePorNombreYUnidad(nombre, unidad.toString());

                if (ingrediente == null) {
                    throw new RuntimeException("Ingrediente no encontrado: " + nombre + " (" + unidad + ")");
                }

                double nuevoStock = ingrediente.getStock() - cantidadARestar;
                if (nuevoStock < 0) {
                    throw new RuntimeException("Stock insuficiente al restar: " + nombre + " (" + unidad + ")");
                }

                ingrediente.setStock(nuevoStock);
                em.merge(ingrediente);
            }

            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al restar stock de ingredientes", e);
        } finally {
            em.close();
        }
    }

    /*
Este método se encarga de recuperar y mostrar todas las comandas almacenadas en la base de datos.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos.
2. Se define una consulta JPQL (Java Persistence Query Language) que selecciona todas las instancias de la entidad `Comanda` mediante la instrucción `SELECT c FROM Comanda c`.
3. Se crea un `TypedQuery<Comanda>` utilizando el `EntityManager` y la consulta definida.
4. Se ejecuta la consulta con `query.getResultList()`, que devuelve una lista de todas las comandas encontradas en la base de datos.
5. Finalmente, se cierra el `EntityManager` en el bloque `finally` para liberar recursos.

Este método es útil para obtener un listado completo de todas las comandas, lo que puede ser utilizado para mostrar información en una interfaz de usuario o para realizar análisis sobre las órdenes procesadas en el sistema.
     */
    @Override
    public List<Comanda> mostrarComandasTodas() {
        EntityManager em = ManejadorConexiones.getEntityManager();

        try {
            String jpql = "SELECT c FROM Comanda c";
            TypedQuery<Comanda> query = em.createQuery(jpql, Comanda.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /*
Este método se encarga de modificar la nota especial de un detalle de comanda específico en el sistema.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones` para gestionar la conexión a la base de datos.
2. Se inicia una transacción con `em.getTransaction().begin()`.
3. Se crea una instancia de `ProductosDAO` para acceder a la información de los productos y se busca el producto correspondiente utilizando `productosDAO.buscarProductoPorNombre(detalleDTO.getNombreProducto())`.
4. Se define una consulta JPQL (Java Persistence Query Language) para buscar el `DetalleComanda` que coincide con el folio de la comanda, el producto, la cantidad y el precio unitario. La consulta se define como un bloque de texto (string multilinea).
5. Se crea un `TypedQuery<DetalleComanda>` utilizando el `EntityManager` y la consulta definida, y se establecen los parámetros necesarios.
6. Se ejecuta la consulta y se obtiene el primer resultado utilizando `query.getResultStream().findFirst()`. Si no se encuentra el detalle, se lanza una `RuntimeException` con un mensaje de error.
7. Si se encuentra el detalle, se actualiza la nota especial del detalle utilizando `detalle.setNotasEspeciales(nuevaNota)`.
8. Se persiste el cambio en la base de datos utilizando `em.merge(detalle)`.
9. Se confirma la transacción con `em.getTransaction().commit()`, asegurando que el cambio se guarde de manera permanente.
10. Si ocurre cualquier excepción durante el proceso, se verifica si la transacción está activa y se realiza un rollback. Luego, se lanza una `RuntimeException` con un mensaje que indica que hubo un error al modificar la nota del detalle de comanda.
11. Finalmente, se cierra el `EntityManager` en el bloque `finally` para liberar recursos.

Este método es útil para actualizar las notas especiales de un detalle de comanda, permitiendo a los usuarios realizar cambios en las instrucciones o comentarios asociados a un producto específico en una comanda.
     */
    @Override
    public void modificarNota(NuevoDetalleComandaDTO detalleDTO, String nuevaNota) {
        EntityManager em = ManejadorConexiones.getEntityManager();

        try {
            em.getTransaction().begin();

            ProductosDAO productosDAO = new ProductosDAO();
            Producto producto = productosDAO.buscarProductoPorNombre(detalleDTO.getNombreProducto());

            String jpqlDetalle = """
            SELECT d FROM DetalleComanda d
            WHERE d.comanda.folio = :folio
            AND d.producto = :producto
            AND d.cantidad = :cantidad
            AND d.precioUnitario = :precioUnitario
        """;

            TypedQuery<DetalleComanda> query = em.createQuery(jpqlDetalle, DetalleComanda.class);
            query.setParameter("folio", detalleDTO.getFolioComanda());
            query.setParameter("producto", producto);
            query.setParameter("cantidad", detalleDTO.getCantidad());
            query.setParameter("precioUnitario", detalleDTO.getPrecioUnitario());

            DetalleComanda detalle = query.getResultStream().findFirst()
                    .orElseThrow(() -> new RuntimeException("No se encontró el detalle de comanda para el folio y producto dados."));

            detalle.setNotasEspeciales(nuevaNota);
            em.merge(detalle);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al modificar la nota del detalle de comanda", e);
        } finally {
            em.close();
        }
    }

    /*
Este método se encarga de recuperar y mostrar todas las comandas que están en estado "ABIERTO" en la base de datos.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos.
2. Se define una consulta JPQL (Java Persistence Query Language) que selecciona todas las instancias de la entidad `Comanda` donde el estado es igual a `EstadoComanda.ABIERTA`.
3. Se crea un `TypedQuery<Comanda>` utilizando el `EntityManager` y la consulta definida.
4. Se establece el parámetro `estado` con el valor `EstadoComanda.ABIERTA` para filtrar las comandas que están abiertas.
5. Se ejecuta la consulta con `query.getResultList()`, que devuelve una lista de todas las comandas abiertas encontradas en la base de datos.
6. Finalmente, se cierra el `EntityManager` en el bloque `finally` para liberar recursos.

Este método es útil para obtener un listado de todas las comandas que están actualmente abiertas, lo que puede ser utilizado para la gestión de pedidos en curso y para facilitar el seguimiento de las órdenes en el sistema.
     */
    @Override
    public List<Comanda> mostrarComandasAbiertas() {
        EntityManager em = ManejadorConexiones.getEntityManager();

        try {
            String jpql = "SELECT c FROM Comanda c WHERE c.estado = :estado";
            TypedQuery<Comanda> query = em.createQuery(jpql, Comanda.class);
            query.setParameter("estado", EstadoComanda.ABIERTA);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /*
Este método se encarga de filtrar y recuperar las comandas que se encuentran dentro de un rango de fechas específico.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos.
2. Se define una consulta JPQL (Java Persistence Query Language) que selecciona todas las instancias de la entidad `Comanda` donde la fecha y hora de la comanda (`fechaHora`) se encuentra entre dos fechas: `fechaInicio` y `fechaFin`.
3. Se crea un `TypedQuery<Comanda>` utilizando el `EntityManager` y la consulta definida.
4. Se establecen los parámetros `fechaInicio` y `fechaFin` en la consulta para filtrar las comandas según el rango de fechas proporcionado.
5. Se ejecuta la consulta con `query.getResultList()`, que devuelve una lista de todas las comandas que cumplen con el criterio de fecha especificado.

Este método es útil para obtener un listado de comandas que fueron creadas dentro de un rango de fechas determinado, lo que puede ser útil para informes, análisis de ventas o auditorías en el sistema.
     */
    @Override
    public List<Comanda> filtrarPorFecha(Calendar fechaInicio, Calendar fechaFin) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpql = "SELECT c FROM Comanda c WHERE c.fechaHora BETWEEN :fechaInicio AND :fechaFin";

        TypedQuery<Comanda> query = entityManager.createQuery(jpql, Comanda.class);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);

        return query.getResultList();
    }

    /*
Este método se encarga de recuperar una comanda específica a partir de su folio.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos.
2. Se define una consulta JPQL (Java Persistence Query Language) que selecciona la instancia de la entidad `Comanda` donde el folio coincide con el folio proporcionado.
3. Se crea un `TypedQuery<Comanda>` utilizando el `EntityManager` y la consulta definida.
4. Se establece el parámetro `folio` en la consulta para filtrar la comanda deseada.
5. Se ejecuta la consulta con `query.getSingleResult()`, que intenta recuperar una única comanda que coincida con el folio.
6. Si no se encuentra ninguna comanda que coincida con el folio, se captura la excepción `NoResultException` y se devuelve `null`.
7. Si ocurre cualquier otra excepción durante el proceso, se lanza una `PersistenciaException` con un mensaje que indica que hubo un error al obtener la comanda por folio, junto con la excepción original.

Este método es útil para buscar y recuperar una comanda específica en el sistema utilizando su folio, lo que permite acceder a la información de la comanda de manera eficiente.
     */
    @Override
    public Comanda obtenerComandaPorFolio(String folio) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            TypedQuery<Comanda> query = em.createQuery(
                    "SELECT c FROM Comanda c WHERE c.folio = :folio", Comanda.class);
            query.setParameter("folio", folio);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener la comanda por folio", e);
        }
    }

    /*
Este método se encarga de recuperar la lista de detalles de una comanda específica.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos.
2. Se define una consulta JPQL (Java Persistence Query Language) que selecciona todas las instancias de la entidad `DetalleComanda` donde el ID de la comanda coincide con el ID de la comanda proporcionada como parámetro.
3. Se crea un `TypedQuery<DetalleComanda>` utilizando el `EntityManager` y la consulta definida.
4. Se establece el parámetro `idComanda` en la consulta utilizando el ID de la comanda proporcionada (`comanda.getId()`).
5. Se ejecuta la consulta con `query.getResultList()`, que devuelve una lista de todos los detalles de la comanda que coinciden con el ID especificado.
6. Si ocurre cualquier excepción durante el proceso, se lanza una `PersistenciaException` con un mensaje que indica que hubo un error al obtener los detalles de la comanda, junto con la excepción original.

Este método es útil para acceder a la información detallada de los productos y servicios incluidos en una comanda específica, lo que permite gestionar y visualizar los elementos de la comanda de manera efectiva.
     */
    @Override
    public List<DetalleComanda> obtenerListaDetallesComanda(Comanda comanda) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            TypedQuery<DetalleComanda> query = em.createQuery(
                    "SELECT d FROM DetalleComanda d WHERE d.comanda.id = :idComanda", DetalleComanda.class);
            query.setParameter("idComanda", comanda.getId());
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener los detalles de la comanda", e);
        }
    }

    @Override
    public double calcularTotalDeTodasLasComandas() throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            String jpql = "SELECT SUM(c.total) FROM Comanda c";
            TypedQuery<Double> query = em.createQuery(jpql, Double.class);
            Double total = query.getSingleResult();
            return total != null ? total : 0.0;
        } finally {
            em.close();
        }
    }

    @Override
    public double calcularTotalDeComandasPorFechas(Date fechaInicio, Date fechaFin) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            String jpql = "SELECT SUM(c.total) FROM Comanda c WHERE c.fechaHora BETWEEN :fechaInicio AND :fechaFin";
            TypedQuery<Double> query = em.createQuery(jpql, Double.class);
            query.setParameter("fechaInicio", fechaInicio, TemporalType.TIMESTAMP);
            query.setParameter("fechaFin", fechaFin, TemporalType.TIMESTAMP);

            Double total = query.getSingleResult();
            return total != null ? total : 0.0;
        } finally {
            em.close();
        }
    }
}
