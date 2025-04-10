/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO.ProductoBO;

import DAO.Productos.ProductosDAO;
import DTOS.Productos.NuevoProductoDTO;
import DTOS.Productos.NuevoProductoOcupaIngredienteDTO;
import Entidades.Ingredientes.Ingrediente;
import Entidades.Productos.Estado_Producto;
import Entidades.Productos.Producto;
import Entidades.Productos.Tipo_Producto;
import NegocioException.NegocioException;
import java.util.List;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class ProductoBO implements IProductoBO {

    private ProductosDAO productoDAO;

    public ProductoBO(ProductosDAO productoDAO) throws NegocioException {
                if (productoDAO == null) {
            throw new NegocioException("El DAO no puede ser null");
        }
        this.productoDAO = productoDAO;
    }
    
    
    
    
    /*
Este método se encarga de recuperar una lista de productos que están disponibles en el sistema.

1. **Método**: `mostrarListaProductosDisponiblesBO()`
2. **Tipo de retorno**: `List<Producto>`
3. **Funcionalidad**:
   - Llama al método `mostrarListaProductosDisponibles` del `productoDAO`, que se espera que devuelva una lista de objetos `Producto` que están actualmente disponibles.
   - Este método no incluye validaciones ni manejo de excepciones, lo que implica que se asume que el `productoDAO` está correctamente configurado para realizar la consulta.

Este método es útil para obtener una lista de productos que están disponibles para la venta, lo que es esencial en aplicaciones de gestión de inventarios, restaurantes o cualquier sistema que requiera la administración de productos.
*/
    
    
    @Override
    public List<Producto> mostrarListaProductosDisponiblesBO(){
        return productoDAO.mostrarListaProductosDisponibles();
    }
    
    
    /*
Este método se encarga de filtrar la lista de productos disponibles por su nombre.

1. **Método**: `filtrarPorNombreProductoDisponiblesBO(String nombreFiltro)`
2. **Parámetro**: 
   - `nombreFiltro`: Un `String` que representa el nombre o parte del nombre del producto que se desea filtrar.
3. **Tipo de retorno**: `List<Producto>`
4. **Funcionalidad**:
   - Llama al método `filtrarPorNombreProductoDisponibles` del `productoDAO`, pasando el `nombreFiltro` para obtener una lista de productos que coincidan con el filtro.
   - Este método no incluye validaciones ni manejo de excepciones.

Este método es útil para buscar productos específicos en el inventario, facilitando la localización de artículos por su nombre en aplicaciones de gestión de productos.
*/
    
    
    @Override
    public List<Producto> filtrarPorNombreProductoDisponiblesBO(String nombreFiltro) {
        return productoDAO.filtrarPorNombreProductoDisponibles(nombreFiltro);
    }
    
    
    
    /*
Este método se encarga de filtrar la lista de productos disponibles por su tipo.

1. **Método**: `filtrarPorTipoProductoDisponiblesBO(Tipo_Producto tipoFiltro)`
2. **Parámetro**: 
   - `tipoFiltro`: Un objeto de tipo `Tipo_Producto` que representa el tipo de producto que se desea filtrar.
3. **Tipo de retorno**: `List<Producto>`
4. **Funcionalidad**:
   - Llama al método `filtrarPorTipoProductoDisponibles` del `productoDAO`, pasando el `tipoFiltro` para obtener una lista de productos que coincidan con el tipo especificado.
   - Este método no incluye validaciones ni manejo de excepciones.

Este método es útil para agrupar productos por su tipo, facilitando la búsqueda y gestión de productos en aplicaciones de inventario.
*/
    
    
    @Override
    public List<Producto> filtrarPorTipoProductoDisponiblesBO(Tipo_Producto tipoFiltro) {
        return productoDAO.filtrarPorTipoProductoDisponibles(tipoFiltro);
    }
    
    
    
    
    /*
Este método se encarga de filtrar la lista de productos disponibles por su nombre y tipo.

1. **Método**: `filtrarPorNombreYTipoProductoDisponiblesBO(String nombreFiltro, Tipo_Producto tipoFiltro)`
2. **Parámetros**: 
   - `nombreFiltro`: Un `String` que representa el nombre o parte del nombre del producto.
   - `tipoFiltro`: Un objeto de tipo `Tipo_Producto` que representa el tipo de producto.
3. **Tipo de retorno**: `List<Producto>`
4. **Funcionalidad**:
   - Llama al método `filtrarPorNombreYTipoProductoDisponibles` del `productoDAO`, pasando ambos filtros para obtener una lista de productos que coincidan con los criterios especificados.
   - Este método no incluye validaciones ni manejo de excepciones.

Este método es útil para realizar búsquedas más específicas en el inventario, permitiendo a los usuarios encontrar productos que cumplan con múltiples criterios.
*/
    
    @Override
    public List<Producto> filtrarPorNombreYTipoProductoDisponiblesBO(String nombreFiltro, Tipo_Producto tipoFiltro) {
        return productoDAO.filtrarPorNombreYTipoProductoDisponibles(nombreFiltro, tipoFiltro);
    }
    
    
    
    /*
Este método se encarga de recuperar una lista de todos los productos en el sistema, independientemente de su estado.

1. **Método**: `mostrarListaProductosTodosBO()`
2. **Tipo de retorno**: `List<Producto>`
3. **Funcionalidad**:
   - Llama al método `mostrarListaProductosTodos` del `productoDAO`, que se espera que devuelva una lista de todos los objetos `Producto` registrados en el sistema.
   - Este método no incluye validaciones ni manejo de excepciones.

Este método es útil para obtener una visión completa de todos los productos disponibles en el sistema, lo que puede ser esencial para la gestión y planificación en aplicaciones de inventario o ventas.
*/
    @Override
    public List<Producto> mostrarListaProductosTodosBO(){
        return productoDAO.mostrarListaProductosTodos();
    }
    
    
    /*
Este método se encarga de registrar un nuevo producto en el sistema.

1. **Método**: `registrarProductoBO(NuevoProductoDTO nuevoProducto)`
2. **Parámetro**: 
   - `nuevoProducto`: Un objeto de tipo `NuevoProductoDTO` que contiene la información del nuevo producto a registrar.
3. **Tipo de retorno**: `Producto`
4. **Excepciones**: 
   - Lanza `NegocioException` en los siguientes casos:
     - Si `nuevoProducto` es `null`.
     - Si el nombre del producto es `null` o está vacío.
     - Si el precio del producto es menor o igual a 0.
     - Si el tipo de producto es `null`.
     - Si el estado del producto es `null`.
     - Si ya existe un producto con el mismo nombre en el sistema.

5. **Funcionalidad**:
   - Se realizan varias validaciones sobre el objeto `nuevoProducto` para asegurar que todos los campos requeridos estén correctamente establecidos.
   - Se verifica si ya existe un producto con el mismo nombre utilizando el método `buscarProductoPorNombre` del `productoDAO`. Si se encuentra un producto existente, se lanza una `NegocioException`.
   - Si todas las validaciones son exitosas, se llama al método `registrarProducto` del `productoDAO` para registrar el nuevo producto en la base de datos y se devuelve el objeto `Producto` correspondiente.

Este método es esencial para garantizar que solo se registren productos válidos en el sistema, promoviendo la integridad de los datos y evitando duplicados.
*/
    
    @Override
    public Producto registrarProductoBO(NuevoProductoDTO nuevoProducto) throws NegocioException {

 
        if (nuevoProducto == null) {
            throw new NegocioException("El DTO del producto no puede ser nulo.");
        }

        if (nuevoProducto.getNombre() == null || nuevoProducto.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del producto no puede estar vacío.");
        }

        if (nuevoProducto.getPrecio() <= 0) {
            throw new NegocioException("El precio del producto debe ser mayor a 0.");
        }

        if (nuevoProducto.getTipo() == null) {
            throw new NegocioException("El tipo de producto no puede ser nulo.");
        }

        if (nuevoProducto.getEstado() == null) {
            throw new NegocioException("El estado del producto no puede ser nulo.");
        }


        Producto existente = productoDAO.buscarProductoPorNombre(nuevoProducto.getNombre());
        if (existente != null) {
            throw new NegocioException("Ya existe un producto con ese nombre.");
        }


        return productoDAO.registrarProducto(nuevoProducto);
    }
    
    
    /*
Este método se encarga de deshabilitar un producto en el sistema.

1. **Método**: `deshabilitarProductoBO(NuevoProductoDTO productoAEliminar)`
2. **Parámetro**: 
   - `productoAEliminar`: Un objeto de tipo `NuevoProductoDTO` que contiene la información del producto que se desea deshabilitar.
3. **Excepciones**: 
   - Lanza `NegocioException` en los siguientes casos:
     - Si `productoAEliminar` es `null`.
     - Si el nombre del producto es `null` o está vacío.
     - Si el precio del producto es menor o igual a 0.
     - Si el tipo de producto es `null`.
     - Si ocurre un error al intentar deshabilitar el producto en el DAO.

4. **Funcionalidad**:
   - Se realizan varias validaciones sobre el objeto `productoAEliminar` para asegurar que todos los campos requeridos estén correctamente establecidos.
   - Se llama al método `deshabilitarProducto` del `productoDAO` para deshabilitar el producto en la base de datos.
   - Si ocurre una excepción durante la operación de deshabilitación, se captura y se lanza una nueva `NegocioException` con un mensaje que incluye la causa del error.

Este método es esencial para gestionar el estado de los productos en el sistema, permitiendo deshabilitar productos que ya no están disponibles para la venta o que deben ser retirados del inventario.
*/
    
    
    @Override
    public void deshabilitarProductoBO(NuevoProductoDTO productoAEliminar) throws NegocioException {
        if (productoAEliminar == null) {
            throw new NegocioException("El DTO del producto no puede ser nulo.");
        }
        
        if (productoAEliminar.getNombre() == null || productoAEliminar.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del producto no puede estar vacío.");
        }
        
        if (productoAEliminar.getPrecio() <= 0) {
            throw new NegocioException("El precio del producto debe ser mayor a 0.");
        }
        
        if (productoAEliminar.getTipo() == null) {
            throw new NegocioException("El tipo de producto no puede ser nulo.");
        }
        
        
        try {
            productoDAO.deshabilitarProducto(productoAEliminar);
        } catch (Exception e) {
            throw new NegocioException("Error al intentar deshabilitar el producto: " + e.getMessage(), e);
        }
    }

    
    
    
    
    
    /*
Este método se encarga de registrar un nuevo producto junto con sus ingredientes en el sistema.

1. **Método**: `registrarProductoConIngredientesBO(NuevoProductoDTO nuevoProducto, List<NuevoProductoOcupaIngredienteDTO> listaProductoIngrediente)`
2. **Parámetros**: 
   - `nuevoProducto`: Un objeto de tipo `NuevoProductoDTO` que contiene la información del nuevo producto a registrar.
   - `listaProductoIngrediente`: Una lista de objetos de tipo `NuevoProductoOcupaIngredienteDTO` que representan los ingredientes asociados al producto.
3. **Tipo de retorno**: `Producto`
4. **Excepciones**: 
   - Lanza `NegocioException` en los siguientes casos:
     - Si `nuevoProducto` es `null`.
     - Si el nombre del producto es `null` o está vacío.
     - Si el precio del producto es menor o igual a 0.
     - Si el tipo de producto es `null`.
     - Si el estado del producto es `null`, se establece por defecto a `Estado_Producto.HABILITADO`.
     - Si `listaProductoIngrediente` es `null` o está vacía, indicando que el producto debe tener al menos un ingrediente asociado.
     - Si ocurre un error al intentar registrar el producto con ingredientes en el DAO.

5. **Funcionalidad**:
   - Se realizan varias validaciones sobre el objeto `nuevoProducto` y la lista de ingredientes para asegurar que todos los campos requeridos estén correctamente establecidos.
   - Se llama al método `registrarProductoConIngredientes` del `productoDAO` para registrar el nuevo producto junto con sus ingredientes en la base de datos.
   - Si ocurre una excepción durante la operación de registro, se captura y se lanza una nueva `NegocioException` con un mensaje que incluye la causa del error.

Este método es esencial para gestionar la creación de productos que requieren ingredientes, como en el caso de platos en un menú, asegurando que se registren correctamente en el sistema.
*/
    
    
        @Override
    public Producto registrarProductoConIngredientesBO(NuevoProductoDTO nuevoProducto, 
            List<NuevoProductoOcupaIngredienteDTO> listaProductoIngrediente) throws NegocioException {
        
        if (nuevoProducto == null) {
            throw new NegocioException("El producto no puede ser nulo.");
        }
        
        if (nuevoProducto.getNombre() == null || nuevoProducto.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del producto no puede estar vacío.");
        }
        
        if (nuevoProducto.getPrecio() <= 0) {
            throw new NegocioException("El precio del producto debe ser mayor a 0.");
        }
        
        if (nuevoProducto.getTipo() == null) {
            throw new NegocioException("El tipo de producto no puede ser nulo.");
        }
        
        if (nuevoProducto.getEstado() == null) {
            nuevoProducto.setEstado(Estado_Producto.HABILITADO);
        }
        
        if (listaProductoIngrediente == null || listaProductoIngrediente.isEmpty()) {
            throw new NegocioException("El producto debe tener al menos un ingrediente asociado.");
        }
        
        try {
            return productoDAO.registrarProductoConIngredientes(nuevoProducto, listaProductoIngrediente);
        } catch (Exception e) {
            throw new NegocioException("Error al registrar producto con ingredientes: " + e.getMessage(), e);
        }
    }
    
    
    
    
    





/*
Este método se encarga de modificar un producto existente en el sistema, así como sus ingredientes asociados.

1. **Método**: `modificarProductoBO(NuevoProductoDTO nuevoProductoDTO, List<NuevoProductoOcupaIngredienteDTO> nuevosIngredientesDTO)`
2. **Parámetros**: 
   - `nuevoProductoDTO`: Un objeto de tipo `NuevoProductoDTO` que contiene la información actualizada del producto a modificar.
   - `nuevosIngredientesDTO`: Una lista de objetos de tipo `NuevoProductoOcupaIngredienteDTO` que representan los nuevos ingredientes asociados al producto.
3. **Tipo de retorno**: `Producto`
4. **Excepciones**: 
   - Lanza `NegocioException` en los siguientes casos:
     - Si `nuevoProductoDTO` es `null`.
     - Si el nombre del producto es `null` o está vacío.
     - Si `nuevosIngredientesDTO` es `null`.

5. **Funcionalidad**:
   - Se realizan varias validaciones sobre el objeto `nuevoProductoDTO` y la lista de nuevos ingredientes para asegurar que todos los campos requeridos estén correctamente establecidos.
   - Se llama al método `modificarProducto` del `productoDAO` para actualizar el producto y sus ingredientes en la base de datos.
   - Se manejan excepciones específicas, como `IllegalArgumentException`, y se lanza una `NegocioException` con un mensaje que incluye la causa del error. También se captura cualquier otra excepción inesperada y se lanza una `NegocioException` genérica.

Este método es esencial para permitir la actualización de productos en el sistema, asegurando que se mantenga la integridad de los datos y que los cambios se reflejen correctamente en la base de datos.
*/

        @Override
    public Producto modificarProductoBO(NuevoProductoDTO nuevoProductoDTO, List<NuevoProductoOcupaIngredienteDTO> nuevosIngredientesDTO) throws NegocioException {
        if (nuevoProductoDTO == null) {
            throw new NegocioException("El DTO del producto no puede ser nulo.");
        }
        
        if (nuevoProductoDTO.getNombre() == null || nuevoProductoDTO.getNombre().isBlank()) {
            throw new NegocioException("El nombre del producto no puede estar vacío.");
        }
        
        if (nuevosIngredientesDTO == null) {
            throw new NegocioException("La lista de ingredientes no puede ser nula.");
        }
        
        try {
            return productoDAO.modificarProducto(nuevoProductoDTO, nuevosIngredientesDTO);
        } catch (IllegalArgumentException e) {
            throw new NegocioException("Error al modificar producto: " + e.getMessage(), e);
        } catch (Exception e) {
        throw new NegocioException("Error inesperado al modificar producto.", e);
        }   
    }

    /*
Este método se encarga de buscar un producto en el sistema por su nombre.

1. **Método**: `buscarProductoPorNombreBO(String nombre)`
2. **Parámetro**: 
   - `nombre`: Un `String` que representa el nombre del producto que se desea buscar.
3. **Tipo de retorno**: `Producto`
4. **Funcionalidad**:
   - Llama al método `buscarProductoPorNombre` del `productoDAO`, que se espera que devuelva el objeto `Producto` correspondiente al nombre proporcionado.
   - Este método no incluye validaciones ni manejo de excepciones, lo que implica que se asume que el `productoDAO` está correctamente configurado para realizar la consulta.

Este método es útil para localizar un producto específico en el sistema, facilitando la gestión y consulta de productos.
*/
    
    @Override
    public Producto buscarProductoPorNombreBO(String nombre) {
        return productoDAO.buscarProductoPorNombre(nombre);
    }

    
    
    
    /*
Este método se encarga de filtrar la lista de todos los productos por su nombre.

1. **Método**: `filtrarPorNombreProductoTodosBO(String nombreFiltro)`
2. **Parámetro**: 
   - `nombreFiltro`: Un `String` que representa el nombre o parte del nombre del producto que se desea filtrar.
3. **Tipo de retorno**: `List<Producto>`
4. **Funcionalidad**:
   - Llama al método `filtrarPorNombreProductoTodos` del `productoDAO`, pasando el `nombreFiltro` para obtener una lista de productos que coincidan con el filtro.
   - Este método no incluye validaciones ni manejo de excepciones.

Este método es útil para buscar productos específicos en el inventario, facilitando la localización de artículos por su nombre en aplicaciones de gestión de productos.
*/
    @Override
    public List<Producto> filtrarPorNombreProductoTodosBO(String nombreFiltro) {
        return productoDAO.filtrarPorNombreProductoTodos(nombreFiltro);
    }
    
    
    
    
    /*
Este método se encarga de filtrar la lista de todos los productos por su tipo.

1. **Método**: `filtrarPorTipoProductoTodosBO(Tipo_Producto tipoFiltro)`
2. **Parámetro**: 
   - `tipoFiltro`: Un objeto de tipo `Tipo_Producto` que representa el tipo de producto que se desea filtrar.
3. **Tipo de retorno**: `List<Producto>`
4. **Funcionalidad**:
   - Llama al método `filtrarPorTipoProductoTodos` del `productoDAO`, pasando el `tipoFiltro` para obtener una lista de productos que coincidan con el tipo especificado.
   - Este método no incluye validaciones ni manejo de excepciones.

Este método es útil para agrupar productos por su tipo, facilitando la búsqueda y gestión de productos en aplicaciones de inventario.
*/

    @Override
    public List<Producto> filtrarPorTipoProductoTodosBO(Tipo_Producto tipoFiltro) {
        return productoDAO.filtrarPorTipoProductoTodos(tipoFiltro);
    }
    
    
    
    
    
    /*
Este método se encarga de filtrar la lista de todos los productos por su nombre y tipo.

1. **Método**: `filtrarPorNombreYTipoProductoTodosBO(String nombreFiltro, Tipo_Producto tipoFiltro)`
2. **Parámetros**: 
   - `nombreFiltro`: Un `String` que representa el nombre o parte del nombre del producto.
   - `tipoFiltro`: Un objeto de tipo `Tipo_Producto` que representa el tipo de producto.
3. **Tipo de retorno**: `List<Producto>`
4. **Funcionalidad**:
   - Llama al método `filtrarPorNombreYTipoProductoTodos` del `productoDAO`, pasando ambos filtros para obtener una lista de productos que coincidan con los criterios especificados.
   - Este método no incluye validaciones ni manejo de excepciones.

Este método es útil para realizar búsquedas más específicas en el inventario, permitiendo a los usuarios encontrar productos que cumplan con múltiples criterios.
*/

    @Override
    public List<Producto> filtrarPorNombreYTipoProductoTodosBO(String nombreFiltro, Tipo_Producto tipoFiltro) {
        return productoDAO.filtrarPorNombreYTipoProductoTodos(nombreFiltro, tipoFiltro);
    }

    
    
    
    /*
Este método se encarga de habilitar un producto en el sistema.

1. **Método**: `habilitarProductoBO(NuevoProductoDTO productoAHabilitar)`
2. **Parámetro**: 
   - `productoAHabilitar`: Un objeto de tipo `NuevoProductoDTO` que contiene la información del producto que se desea habilitar.
3. **Tipo de retorno**: `void`
4. **Funcionalidad**:
   - Llama al método `habilitarProducto` del `productoDAO` para habilitar el producto en la base de datos.
   - Este método no incluye validaciones ni manejo de excepciones, lo que implica que se asume que el `productoDAO` está correctamente configurado para realizar la operación.

Este método es esencial para gestionar el estado de los productos en el sistema, permitiendo reactivar productos que han sido deshabilitados.
*/
    
    
    
    @Override
    public void habilitarProductoBO(NuevoProductoDTO productoAHabilitar) {
         productoDAO.habilitarProducto(productoAHabilitar);
    }
    
    

    
    
}

    
    
    

