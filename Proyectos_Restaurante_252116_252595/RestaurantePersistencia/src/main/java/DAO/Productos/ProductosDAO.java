
package DAO.Productos;

import DTOS.Productos.NuevoProductoDTO;
import DTOS.Productos.NuevoProductoOcupaIngredienteDTO;
import Entidades.Ingredientes.Ingrediente;
import Entidades.Productos.Estado_Producto;
import Entidades.Productos.Producto;
import Entidades.Productos.ProductoOcupaIngrediente;
import Entidades.Productos.Tipo_Producto;
import ManejadorConexiones.ManejadorConexiones;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class ProductosDAO implements IProductosDAO {

    
    /*
Este método es una implementación de un método que devuelve una lista de productos disponibles en el sistema.  

2. Se define una consulta JPQL (Java Persistence Query Language) que selecciona todos los productos (`Producto`) cuyo estado es `HABILITADO`.
3. Se crea una `TypedQuery` utilizando la consulta JPQL y se establece el parámetro `estado` con el valor `Estado_Producto.HABILITADO`.
4. Finalmente, se ejecuta la consulta y se devuelve la lista de productos que cumplen con el criterio especificado.

Este método es útil para obtener solo aquellos productos que están activos y disponibles para ser seleccionados en las comandas.
*/
    
    
    @Override
    public List<Producto> mostrarListaProductosDisponibles() {
     
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpql = "SELECT p FROM Producto p WHERE p.estado = :estado";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpql, Producto.class);
        query.setParameter("estado", Estado_Producto.HABILITADO);
        return query.getResultList();
}
    
    
    
    
    /*
Este método, `mostrarListaProductosTodos`, tiene como objetivo recuperar una lista de todos los productos registrados en el sistema, sin filtrar por estado.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que permite gestionar la conexión a la base de datos.
2. Se define una consulta JPQL (Java Persistence Query Language) que selecciona todos los productos (`Producto`) sin ninguna condición adicional.
3. Se crea una `TypedQuery` utilizando la consulta JPQL, especificando que el resultado será de tipo `Producto`.
4. Finalmente, se ejecuta la consulta y se devuelve la lista completa de productos que están almacenados en la base de datos.

Este método es útil para obtener un listado completo de productos, independientemente de su estado, lo que puede ser útil para la administración o auditoría de los productos disponibles en el sistema.
*/
    
    public List<Producto> mostrarListaProductosTodos() {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpql = "SELECT p FROM Producto p";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpql, Producto.class); 
        return query.getResultList();
    }
    
    
    
    /*
Este método, `filtrarPorNombreProductoDisponibles`, permite filtrar la lista de productos disponibles en el sistema según un nombre específico proporcionado como parámetro.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos.
2. Se define una consulta JPQL que selecciona productos (`Producto`) cuyo nombre coincide parcialmente con el valor de `nombreFiltro` y cuyo estado es `HABILITADO`. El uso de `LIKE` permite realizar una búsqueda que no es exacta, utilizando el símbolo de porcentaje (`%`) para indicar que puede haber cualquier carácter antes o después del texto filtrado.
3. Se crea una `TypedQuery` utilizando la consulta JPQL, especificando que el resultado será de tipo `Producto`.
4. Se establecen los parámetros de la consulta: `nombreFiltrador` se establece con el valor de `nombreFiltro` rodeado de porcentajes para permitir coincidencias parciales, y `estado` se establece en `Estado_Producto.HABILITADO`.
5. Finalmente, se ejecuta la consulta y se devuelve la lista de productos que cumplen con los criterios de búsqueda.

Este método es útil para que los usuarios puedan buscar productos específicos en el menú que estén disponibles, mejorando la experiencia de selección durante la creación de comandas.
*/
    
    @Override
    public List<Producto> filtrarPorNombreProductoDisponibles(String nombreFiltro) {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = "SELECT p FROM Producto p WHERE p.nombre LIKE :nombreFiltrador AND p.estado = :estado";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);
        query.setParameter("nombreFiltrador", "%" + nombreFiltro + "%");
        query.setParameter("estado", Estado_Producto.HABILITADO);
        return query.getResultList();
    }
    
    
    
    
    /*
Este método, `filtrarPorNombreProductoTodos`, tiene como objetivo filtrar la lista de todos los productos registrados en el sistema según un nombre específico proporcionado como parámetro, sin considerar el estado de los productos.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos.
2. Se define una consulta JPQL que selecciona productos (`Producto`) cuyo nombre coincide parcialmente con el valor de `nombreFiltro`. Al igual que en el método anterior, se utiliza `LIKE` para permitir coincidencias parciales, rodeando el valor de `nombreFiltro` con el símbolo de porcentaje (`%`).
3. Se crea una `TypedQuery` utilizando la consulta JPQL, especificando que el resultado será de tipo `Producto`.
4. Se establece el parámetro de la consulta `nombreFiltrador` con el valor de `nombreFiltro` rodeado de porcentajes para permitir coincidencias parciales.
5. Finalmente, se ejecuta la consulta y se devuelve la lista de productos que cumplen con el criterio de búsqueda.

Este método es útil para obtener un listado de productos que coinciden con el nombre proporcionado, sin filtrar por su estado, lo que puede ser útil para la administración o auditoría de productos en el sistema.
*/
    
    @Override
    public List<Producto> filtrarPorNombreProductoTodos(String nombreFiltro){
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = "SELECT p FROM Producto p WHERE p.nombre LIKE :nombreFiltrador";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);
        query.setParameter("nombreFiltrador", "%" + nombreFiltro + "%");
        return query.getResultList();
    }
    
    
    /*
Este método permite filtrar la lista de productos disponibles en el sistema según un tipo específico proporcionado como parámetro.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos.
2. Se define una consulta JPQL que selecciona productos (`Producto`) cuyo tipo coincide con el valor de `tipoFiltro` y cuyo estado es `HABILITADO`.
3. Se crea una `TypedQuery` utilizando la consulta JPQL, especificando que el resultado será de tipo `Producto`.
4. Se establecen los parámetros de la consulta: `tipoFiltrador` se establece con el valor de `tipoFiltro`, y `estado` se establece en `Estado_Producto.HABILITADO`.
5. Finalmente, se ejecuta la consulta y se devuelve la lista de productos que cumplen con los criterios de filtrado.

Este método es útil para que los usuarios puedan buscar productos específicos en el menú que estén disponibles y que pertenezcan a un tipo determinado, mejorando la experiencia de selección durante la creación de comandas.
*/
    
    
    @Override
    public List<Producto> filtrarPorTipoProductoDisponibles(Tipo_Producto tipoFiltro) {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = "SELECT p FROM Producto p WHERE p.tipo = :tipoFiltrador AND p.estado = :estado";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);
        query.setParameter("tipoFiltrador", tipoFiltro);
        query.setParameter("estado", Estado_Producto.HABILITADO);
        return query.getResultList();
    }
    
/*
Este método permite filtrar la lista de todos los productos registrados en el sistema según un tipo específico proporcionado como parámetro, sin considerar el estado de los productos.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos.
2. Se define una consulta JPQL que selecciona productos (`Producto`) cuyo tipo coincide con el valor de `tipoFiltro`.
3. Se crea una `TypedQuery` utilizando la consulta JPQL, especificando que el resultado será de tipo `Producto`.
4. Se establece el parámetro de la consulta `tipoFiltrador` con el valor de `tipoFiltro`.
5. Finalmente, se ejecuta la consulta y se devuelve la lista de productos que cumplen con el criterio de filtrado.

Este método es útil para obtener un listado de productos que pertenecen a un tipo específico, sin filtrar por su estado, lo que puede ser útil para la administración o auditoría de productos en el sistema.
*/
    
        @Override
    public List<Producto> filtrarPorTipoProductoTodos(Tipo_Producto tipoFiltro) {
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = "SELECT p FROM Producto p WHERE p.tipo = :tipoFiltrador";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);
        query.setParameter("tipoFiltrador", tipoFiltro);
        return query.getResultList();
    }
    
    
    
    /*
Este método permite filtrar la lista de todos los productos registrados en el sistema según un nombre y un tipo específicos proporcionados como parámetros, sin considerar el estado de los productos.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos.
2. Se define una consulta JPQL que selecciona productos (`Producto`) cuyo nombre coincide parcialmente con el valor de `nombreFiltro` y cuyo tipo coincide con el valor de `tipoFiltro`. Se utiliza `LIKE` para permitir coincidencias parciales en el nombre.
3. Se crea una `TypedQuery` utilizando la consulta JPQL, especificando que el resultado será de tipo `Producto`.
4. Se establecen los parámetros de la consulta: `nombreFiltrador` se establece con el valor de `nombreFiltro` rodeado de porcentajes para permitir coincidencias parciales, y `tipoFiltrador` se establece con el valor de `tipoFiltro`.
5. Finalmente, se ejecuta la consulta y se devuelve la lista de productos que cumplen con los criterios de filtrado.

Este método es útil para obtener un listado de productos que coinciden con un nombre y un tipo específicos, lo que puede ser útil para la administración o auditoría de productos en el sistema.
*/
    @Override
    public List<Producto> filtrarPorNombreYTipoProductoTodos(String nombreFiltro, Tipo_Producto tipoFiltro) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = "SELECT p FROM Producto p WHERE p.nombre LIKE :nombreFiltrador AND p.tipo = :tipoFiltrador";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);
        query.setParameter("nombreFiltrador", "%" + nombreFiltro + "%");
        query.setParameter("tipoFiltrador", tipoFiltro);
        return query.getResultList();
    }
    
    /*
Este método permite filtrar la lista de productos disponibles en el sistema según un nombre y un tipo específicos proporcionados como parámetros.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos.
2. Se define una consulta JPQL que selecciona productos (`Producto`) cuyo nombre coincide parcialmente con el valor de `nombreFiltro`, cuyo tipo coincide con el valor de `tipoFiltro`, y cuyo estado es `HABILITADO`.
3. Se crea una `TypedQuery` utilizando la consulta JPQL, especificando que el resultado será de tipo `Producto`.
4. Se establecen los parámetros de la consulta: `nombreFiltrador` se establece con el valor de `nombreFiltro` rodeado de porcentajes para permitir coincidencias parciales, `tipoFiltrador` se establece con el valor de `tipoFiltro`, y `estado` se establece en `Estado_Producto.HABILITADO`.
5. Finalmente, se ejecuta la consulta y se devuelve la lista de productos que cumplen con los criterios de filtrado.

Este método es útil para que los usuarios puedan buscar productos específicos en el menú que estén disponibles y que pertenezcan a un tipo determinado, mejorando la experiencia de selección durante la creación de comandas.
*/
    
            @Override
    public List<Producto> filtrarPorNombreYTipoProductoDisponibles(String nombreFiltro, Tipo_Producto tipoFiltro) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        String jpqlQuery = "SELECT p FROM Producto p WHERE p.nombre LIKE :nombreFiltrador AND p.tipo = :tipoFiltrador AND p.estado = :estado";
        
        TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);
        query.setParameter("nombreFiltrador", "%" + nombreFiltro + "%");
        query.setParameter("tipoFiltrador", tipoFiltro);
        query.setParameter("estado", Estado_Producto.HABILITADO);
        return query.getResultList();
    }
    
    
    
    /*
Este método permite registrar un nuevo producto en el sistema utilizando la información proporcionada en un objeto `NuevoProductoDTO`.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos.
2. Se inicia una transacción utilizando `entityManager.getTransaction().begin()`, lo que permite agrupar las operaciones de base de datos que se realizarán.
3. Se crea una nueva instancia de `Producto` y se establecen sus propiedades utilizando los datos del objeto `nuevoProducto`, que incluye el nombre, precio, tipo y estado del producto.
4. Se persiste el nuevo producto en la base de datos mediante `entityManager.persist(producto)`, lo que agrega el objeto a la base de datos.
5. Se confirma la transacción con `entityManager.getTransaction().commit()`, lo que asegura que los cambios se guarden de manera permanente.
6. Finalmente, se devuelve el objeto `producto` que ha sido registrado.

Este método es útil para agregar nuevos productos al sistema, permitiendo a los administradores o usuarios autorizados gestionar el inventario de manera efectiva.
*/

    @Override
    public Producto registrarProducto(NuevoProductoDTO nuevoProducto) {

        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        Producto producto = new Producto();
        producto.setNombre(nuevoProducto.getNombre());
        producto.setPrecio(nuevoProducto.getPrecio());
        producto.setTipo(nuevoProducto.getTipo());
        producto.setEstado(nuevoProducto.getEstado());

        entityManager.persist(producto);
        entityManager.getTransaction().commit();
        return producto;
    }
    
    
    
    
    /*
Este método permite deshabilitar un producto en el sistema utilizando la información proporcionada en un objeto `NuevoProductoDTO`.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos.
2. Se inicia una transacción utilizando `entityManager.getTransaction().begin()`, lo que permite agrupar las operaciones de base de datos que se realizarán.
3. Se define una consulta JPQL que busca un producto (`Producto`) en la base de datos que coincida con el nombre, precio y tipo proporcionados en el objeto `productoAEliminar`.
4. Se ejecuta la consulta y se obtiene el producto correspondiente utilizando `getSingleResult()`. Si no se encuentra ningún producto, se lanza una excepción `NoResultException`.
5. Si se encuentra el producto, se cambia su estado a `DESHABILITADO` y se actualiza en la base de datos mediante `entityManager.merge(producto)`.
6. Se confirma la transacción con `entityManager.getTransaction().commit()`, asegurando que los cambios se guarden de manera permanente.
7. Si ocurre una `NoResultException`, se imprime un mensaje indicando que no se encontró el producto y se realiza un rollback de la transacción.
8. En caso de cualquier otra excepción, se realiza un rollback de la transacción y se vuelve a lanzar la excepción.
9. Finalmente, se cierra el `EntityManager` en el bloque `finally` para liberar recursos.

Este método es útil para deshabilitar productos en el sistema, permitiendo a los administradores gestionar el inventario de manera efectiva y asegurando que los productos no disponibles no sean accesibles para los usuarios.
*/
    @Override
    public void deshabilitarProducto(NuevoProductoDTO productoAEliminar) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
        
        try {
            String jpql = """
                          SELECT p FROM Producto p WHERE p.nombre = :nombre 
                          AND p.precio = :precio AND p.tipo = :tipo
                          """;
            Producto producto = entityManager.createQuery(jpql, Producto.class)
                    .setParameter("nombre", productoAEliminar.getNombre())
                    .setParameter("precio", productoAEliminar.getPrecio())
                    .setParameter("tipo", productoAEliminar.getTipo())
                    .getSingleResult();
            
            if (producto != null) {
                producto.setEstado(Estado_Producto.DESHABILITADO); 
                entityManager.merge(producto); // Actualizar en la BD
            }
            
            entityManager.getTransaction().commit();
            
        } catch (NoResultException e) {
            System.out.println("No se encontró el producto para deshabilitar.");
            entityManager.getTransaction().rollback();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }
    
          
    
    
    
    /*
Este método permite habilitar un producto en el sistema utilizando la información proporcionada en un objeto `NuevoProductoDTO`.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos.
2. Se inicia una transacción utilizando `entityManager.getTransaction().begin()`, lo que permite agrupar las operaciones de base de datos que se realizarán.
3. Se define una consulta JPQL que busca un producto (`Producto`) en la base de datos que coincida con el nombre, precio y tipo proporcionados en el objeto `productoAHabilitar`.
4. Se ejecuta la consulta y se obtiene el producto correspondiente utilizando `getSingleResult()`. Si no se encuentra ningún producto, se lanza una excepción `NoResultException`.
5. Si se encuentra el producto, se cambia su estado a `HABILITADO` y se actualiza en la base de datos mediante `entityManager.merge(producto)`.
6. Se confirma la transacción con `entityManager.getTransaction().commit()`, asegurando que los cambios se guarden de manera permanente.
7. Si ocurre una `NoResultException`, se imprime un mensaje indicando que no se encontró el producto y se realiza un rollback de la transacción.
8. En caso de cualquier otra excepción, se realiza un rollback de la transacción y se vuelve a lanzar la excepción.
9. Finalmente, se cierra el `EntityManager` en el bloque `finally` para liberar recursos.

Este método es útil para habilitar productos en el sistema, permitiendo a los administradores gestionar el inventario de manera efectiva y asegurando que los productos disponibles sean accesibles para los usuarios.
*/
    
        @Override
    public void habilitarProducto(NuevoProductoDTO productoAHabilitar) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        entityManager.getTransaction().begin();
    
        try {
            String jpql = """
                          SELECT p FROM Producto p WHERE p.nombre = :nombre 
                          AND p.precio = :precio AND p.tipo = :tipo
                          """;
            Producto producto = entityManager.createQuery(jpql, Producto.class)
                    .setParameter("nombre", productoAHabilitar.getNombre())
                    .setParameter("precio", productoAHabilitar.getPrecio())
                    .setParameter("tipo", productoAHabilitar.getTipo())
                    .getSingleResult();
        
            if (producto != null) {
                producto.setEstado(Estado_Producto.HABILITADO); 
                entityManager.merge(producto);
            }
        
            entityManager.getTransaction().commit();
        
        } catch (NoResultException e) {
            System.out.println("No se encontró el producto para habilitar.");
            entityManager.getTransaction().rollback();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }


    
    
    /*
Este método permite registrar un nuevo producto en el sistema junto con sus ingredientes asociados, utilizando la información proporcionada en un objeto `NuevoProductoDTO` y una lista de objetos `NuevoProductoOcupaIngredienteDTO`.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos.
2. Se inicia una transacción utilizando `entityManager.getTransaction().begin()`, lo que permite agrupar las operaciones de base de datos que se realizarán.
3. Se crea una nueva instancia de `Producto` y se establecen sus propiedades utilizando los datos del objeto `nuevoProducto`, que incluye el nombre, precio, tipo y estado del producto.
4. Se inicializa la lista de relaciones de ingredientes (`productos`) del producto si es `null`.
5. Se persiste el nuevo producto en la base de datos mediante `entityManager.persist(producto)`.
6. Se itera sobre la lista de ingredientes (`listaProductoIngrediente`). Para cada ingrediente:
   - Se define una consulta JPQL que busca un ingrediente (`Ingrediente`) en la base de datos que coincida con el nombre y la unidad de medida proporcionados en el objeto `dto`.
   - Se ejecuta la consulta y se obtiene el ingrediente correspondiente. Si no se encuentra, se asigna `null`.
   - Si se encuentra el ingrediente, se crea una nueva relación (`ProductoOcupaIngrediente`) que asocia el producto con el ingrediente y la cantidad necesaria.
   - Se agrega la relación a la lista de relaciones del producto y se persiste la relación en la base de datos.
7. Se confirma la transacción con `entityManager.getTransaction().commit()`, asegurando que los cambios se guarden de manera permanente.
8. Si ocurre cualquier excepción durante el proceso, se realiza un rollback de la transacción si está activa y se vuelve a lanzar la excepción.
9. Finalmente, se cierra el `EntityManager` en el bloque `finally` para liberar recursos.

Este método es útil para agregar nuevos productos al sistema junto con sus ingredientes, permitiendo a los administradores gestionar el inventario de manera efectiva y asegurando que la información de los productos sea completa.
*/
    @Override
    public Producto registrarProductoConIngredientes(NuevoProductoDTO nuevoProducto, 
        List<NuevoProductoOcupaIngredienteDTO> listaProductoIngrediente) {
    
    EntityManager entityManager = ManejadorConexiones.getEntityManager();
    
    try {
        entityManager.getTransaction().begin();
        
        Producto producto = new Producto();
        producto.setNombre(nuevoProducto.getNombre());
        producto.setPrecio(nuevoProducto.getPrecio());
        producto.setTipo(nuevoProducto.getTipo());
        producto.setEstado(nuevoProducto.getEstado());


        if (producto.getProductos() == null) {
            producto.setProductos(new ArrayList<>());
        }

        entityManager.persist(producto);
        
        for (NuevoProductoOcupaIngredienteDTO dto : listaProductoIngrediente) {
            String jpql = """
                SELECT i FROM Ingrediente i 
                WHERE i.nombre = :nombreIngrediente 
                AND i.unidad_medida = :unidadMedida
                """;
            
            TypedQuery<Ingrediente> query = entityManager.createQuery(jpql, Ingrediente.class);
            query.setParameter("nombreIngrediente", dto.getNombreIngrediente());
            query.setParameter("unidadMedida", dto.getUnidadMedida());
            
            Ingrediente ingrediente = query.getResultList().stream().findFirst().orElse(null);
            
            if (ingrediente != null) {
                ProductoOcupaIngrediente relacion = new ProductoOcupaIngrediente();
                relacion.setProducto(producto);
                relacion.setIngrediente(ingrediente);
                relacion.setCantidad_necesaria(dto.getCantidadNecesariaProducto());

             
                producto.getProductos().add(relacion);

                entityManager.persist(relacion);
            }
        }
        
        entityManager.getTransaction().commit();
        return producto;
        
    } catch (Exception e) {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
        throw e;
    } finally {
        entityManager.close();
    }
}
    
    
    
    
    
    /*
Este método permite buscar un producto en el sistema utilizando su nombre.

1. Se obtiene un `EntityManager` a través de `ManejadorConexiones`, que gestiona la conexión a la base de datos.
2. Se define una consulta JPQL que selecciona un producto (`Producto`) en la base de datos cuyo nombre coincide con el valor proporcionado en el parámetro `nombre`.
3. Se crea una `TypedQuery` utilizando la consulta JPQL, especificando que el resultado será de tipo `Producto`.
4. Se establece el parámetro de la consulta `nombre` con el valor proporcionado.
5. Se intenta obtener un único resultado de la consulta utilizando `query.getSingleResult()`.
   - Si se encuentra un producto, se devuelve.
   - Si no se encuentra ningún producto, se lanza una excepción `NoResultException`, que se captura y se devuelve `null`.
   - Si se encuentran múltiples productos con el mismo nombre, se lanza una excepción `NonUniqueResultException`, que se captura y se lanza una `IllegalStateException` con un mensaje que indica que se encontraron múltiples productos.

Este método es útil para buscar productos específicos en el sistema por su nombre, permitiendo a los usuarios o administradores acceder rápidamente a la información de un producto en particular.
*/
    @Override
    public Producto buscarProductoPorNombre(String nombre) {
    EntityManager entityManager = ManejadorConexiones.getEntityManager();
    
    String jpqlQuery = "SELECT p FROM Producto p WHERE p.nombre = :nombre";
    
    TypedQuery<Producto> query = entityManager.createQuery(jpqlQuery, Producto.class);
    query.setParameter("nombre", nombre);
    
    try {
        return query.getSingleResult();
    } catch (NoResultException e) {

        return null;
    } catch (NonUniqueResultException e) {

        throw new IllegalStateException("Se encontraron múltiples productos con el mismo nombre: " + nombre);
    }
    
    
    
    
}
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public Producto modificarProducto(NuevoProductoDTO nuevoProductoDTO, List<NuevoProductoOcupaIngredienteDTO> nuevosIngredientesDTO) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            
            //Buscar el producto original CON EL NOMBRE PORQUE SON UNICOS
            String jpqlBuscarProducto = "SELECT p FROM Producto p WHERE p.nombre = :nombreProducto";
            TypedQuery<Producto> queryProducto = entityManager.createQuery(jpqlBuscarProducto, Producto.class);
            queryProducto.setParameter("nombreProducto", nuevoProductoDTO.getNombre());
            
            Producto producto = queryProducto.getResultList().stream().findFirst().orElse(null);
            if (producto == null) {
                throw new IllegalArgumentException("Producto no encontrado con nombre: " + nuevoProductoDTO.getNombre());
            }
            
            //Modificar atributos SOLAMENTE SI TRAEN ALGO
            if (nuevoProductoDTO.getPrecio() != 0) {
                producto.setPrecio(nuevoProductoDTO.getPrecio());
            }
            
            if (nuevoProductoDTO.getTipo() != null) {
                producto.setTipo(nuevoProductoDTO.getTipo());
            }
            
            if (nuevoProductoDTO.getNombre() != null && !nuevoProductoDTO.getNombre().isBlank()) {
                producto.setNombre(nuevoProductoDTO.getNombre());
            }
            
            if (nuevoProductoDTO.getEstado() != null) {
                producto.setEstado(nuevoProductoDTO.getEstado());
            }
            
            // Buscamos las relaciones Actuales y las que tendremos que eliminar en caso de dejar de considerarlas 
            List<ProductoOcupaIngrediente> relacionesActuales = producto.getProductos(); 
            List<ProductoOcupaIngrediente> relacionesAEliminar = new ArrayList<>(relacionesActuales);
            
            // Recorrer para los nuevos ingredientes y hacemos uniones para actualizar el producto
            for (NuevoProductoOcupaIngredienteDTO dto : nuevosIngredientesDTO) {
                String jpqlBuscarIngrediente = """
                    SELECT i FROM Ingrediente i 
                    WHERE i.nombre = :nombre AND i.unidad_medida = :unidad
                """;
                
                TypedQuery<Ingrediente> queryIngrediente = entityManager.createQuery(jpqlBuscarIngrediente, Ingrediente.class);
                queryIngrediente.setParameter("nombre", dto.getNombreIngrediente());
                queryIngrediente.setParameter("unidad", dto.getUnidadMedida());
                
                Ingrediente ingrediente = queryIngrediente.getResultList().stream().findFirst().orElse(null);
                if (ingrediente == null) {
                    throw new IllegalArgumentException("Ingrediente no encontrado: " + dto.getNombreIngrediente());
                }
                
                //checar si ya existe la relacion
                ProductoOcupaIngrediente relacionExistente = relacionesActuales.stream()
                    .filter(rel -> rel.getIngrediente().getNombre().equals(dto.getNombreIngrediente()) &&
                                   rel.getIngrediente().getUnidad_medida().equals(dto.getUnidadMedida()))
                    .findFirst()
                    .orElse(null);
                
                if (relacionExistente != null) {
                    // En caso de que exista actualizamos nomas la cantidad
                    if (relacionExistente.getCantidad_necesaria() != dto.getCantidadNecesariaProducto()) {
                        relacionExistente.setCantidad_necesaria(dto.getCantidadNecesariaProducto());
                        entityManager.merge(relacionExistente);
                    }
                    relacionesAEliminar.remove(relacionExistente);
                } else {
                // Aqui hacemos la nueva relacion
                    ProductoOcupaIngrediente nuevaRelacion = new ProductoOcupaIngrediente();
                    nuevaRelacion.setProducto(producto);
                nuevaRelacion.setIngrediente(ingrediente);
                    nuevaRelacion.setCantidad_necesaria(dto.getCantidadNecesariaProducto());
                    entityManager.persist(nuevaRelacion);
                    producto.getProductos().add(nuevaRelacion);
                }
            }
            
            // aqui eliminamos las relaciones que se dejaron de considerar
            for (ProductoOcupaIngrediente rel : relacionesAEliminar) {
                producto.getProductos().remove(rel);
                ProductoOcupaIngrediente managedRel = entityManager.merge(rel);
                entityManager.remove(managedRel);
            }
            
           entityManager.merge(producto);
            entityManager.getTransaction().commit();
            return producto;
            
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
        }
            throw e;
        } finally {
            entityManager.close();
    }
}   
    
    

}
