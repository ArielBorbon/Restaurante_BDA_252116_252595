/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO.IngredienteBO;

import DAO.Ingredientes.IngredientesDAO;
import DTOS.Ingredientes.IngredienteConCantidadNecesariaDTO;
import DTOS.Ingredientes.NuevoIngredienteDTO;
import Entidades.Ingredientes.Ingrediente;
import NegocioException.NegocioException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase BO de Ingrediente
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class IngredienteBO implements IIngredienteBO {

    private IngredientesDAO ingredienteDAO;

    /**
     * Este es el constructor de la clase `IngredienteBO`, que se encarga de
     * inicializar una instancia de la clase y asegurar que se le proporcione un
     * objeto `IngredientesDAO` válido.
     *
     * 1. **Constructor**: `IngredienteBO(IngredientesDAO ingredienteDAO)` 2.
     * **Parámetro**: - `ingredienteDAO`: Una instancia de `IngredientesDAO`,
     * que se espera que no sea nula. 3. **Funcionalidad**: - Se verifica si el
     * parámetro `ingredienteDAO` es `null`. Si es así, se lanza una excepción
     * de tipo `NegocioException` con un mensaje que indica que el DAO no puede
     * ser nulo. Esto es una medida de validación para asegurar que la clase
     * `IngredienteBO` siempre tenga un objeto DAO válido para trabajar. - Si el
     * `ingredienteDAO` no es nulo, se asigna a la variable de instancia
     * `this.ingredienteDAO`, que se utilizará en otros métodos de la clase para
     * realizar operaciones relacionadas con los ingredientes.
     *
     * Este constructor es útil para garantizar que la clase `IngredienteBO`
     * esté correctamente configurada antes de ser utilizada, promoviendo así un
     * diseño robusto y evitando errores en tiempo de ejecución relacionados con
     * la falta de un objeto DAO necesario para la lógica de negocio.
     *
     * @param ingredienteDAO
     * @throws NegocioException
     */
    public IngredienteBO(IngredientesDAO ingredienteDAO) throws NegocioException {
        if (ingredienteDAO == null) {
            throw new NegocioException("El DAO no puede ser null");
        }
        this.ingredienteDAO = ingredienteDAO;
    }

    /**
     * Este método se encarga de registrar un nuevo ingrediente en el sistema,
     * validando la información proporcionada en un objeto `NuevoIngredienteDTO`
     * antes de proceder con la operación de registro.
     *
     * 1. **Método**: `registrarIngredienteBO(NuevoIngredienteDTO
     * nuevoIngrediente)` 2. **Parámetro**: - `nuevoIngrediente`: Un objeto de
     * tipo `NuevoIngredienteDTO` que contiene la información del ingrediente a
     * registrar. 3. **Excepciones**: - Lanza `NegocioException` si se detectan
     * problemas con los datos proporcionados.
     *
     * 4. **Funcionalidad**: - Se verifica si `nuevoIngrediente` es `null`. Si
     * es así, se lanza una `NegocioException` con un mensaje que indica que el
     * DTO no puede ser nulo. - Se valida que el nombre del ingrediente no sea
     * nulo ni esté vacío. Si es nulo o vacío, se lanza una `NegocioException`
     * correspondiente. - Se valida que la unidad de medida no sea nula ni esté
     * vacía. Si es nula o vacía, se lanza una `NegocioException`
     * correspondiente. - Se valida que el stock del ingrediente no sea
     * negativo. Si es negativo, se lanza una `NegocioException`
     * correspondiente. - Se busca un ingrediente existente en la base de datos
     * utilizando el método `buscarIngredientePorNombreYUnidad` del
     * `ingredienteDAO`. Si se encuentra un ingrediente con el mismo nombre y
     * unidad de medida, se lanza una `NegocioException` indicando que el
     * ingrediente ya existe. - Si todas las validaciones son satisfactorias y
     * no se encuentra un ingrediente existente, se llama al método
     * `registrarIngrediente` del `ingredienteDAO` para registrar el nuevo
     * ingrediente en la base de datos.
     *
     * Este método es útil para asegurar que solo se registren ingredientes
     * válidos y únicos en el sistema, promoviendo la integridad de los datos y
     * evitando duplicados en la base de datos.
     *
     * @param nuevoIngrediente
     * @return
     * @throws Exception
     */
    @Override
    public Ingrediente registrarIngredienteBO(NuevoIngredienteDTO nuevoIngrediente) throws Exception {
        if (nuevoIngrediente == null) {
            throw new NegocioException("El DTO del ingrediente no puede ser nulo.");
        }
        if (nuevoIngrediente.getNombre() == null || nuevoIngrediente.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del ingrediente no puede estar vacío.");
        }
        if (nuevoIngrediente.getUnidad_medida() == null || nuevoIngrediente.getUnidad_medida().trim().isEmpty()) {
            throw new NegocioException("La unidad de medida no puede estar vacía.");
        }
        if (nuevoIngrediente.getStock() < 0) {
            throw new NegocioException("El stock del ingrediente no puede ser negativo.");
        }

        Ingrediente ingredienteExistente = ingredienteDAO.buscarIngredientePorNombreYUnidad(
                nuevoIngrediente.getNombre(), nuevoIngrediente.getUnidad_medida()
        );

        if (ingredienteExistente != null) {
            throw new NegocioException("El ingrediente ya existe en la base de datos.");
        }

        return ingredienteDAO.registrarIngrediente(nuevoIngrediente);
    }

    /**
     * Este método se encarga de eliminar un ingrediente del sistema, validando
     * la información proporcionada en un objeto `NuevoIngredienteDTO` antes de
     * proceder con la operación de eliminación.1.**Método**:
     * `eliminarIngredienteBO(NuevoIngredienteDTO nuevoIngrediente)` 2.
     *
     * **Parámetro**: - `nuevoIngrediente`: Un objeto de tipo
     * `NuevoIngredienteDTO` que contiene la información del ingrediente a
     * eliminar. 3. **Excepciones**: - Lanza `NegocioException` si se detectan
     * problemas con los datos proporcionados o si no se puede realizar la
     * eliminación.
     *
     * 4. **Funcionalidad**: - Se verifica si `nuevoIngrediente` es `null`. Si
     * es así, se lanza una `NegocioException` con un mensaje que indica que el
     * DTO no puede ser nulo. - Se valida que el nombre del ingrediente no sea
     * nulo ni esté vacío. Si es nulo o vacío, se lanza una `NegocioException`
     * correspondiente. - Se valida que la unidad de medida no sea nula ni esté
     * vacía. Si es nula o vacía, se lanza una `NegocioException`
     * correspondiente. - Se verifica si el ingrediente tiene relaciones activas
     * en la base de datos utilizando el método `tieneRelacionesActivas` del
     * `ingredienteDAO`. Si el ingrediente está en uso por productos, se lanza
     * una `NegocioException` indicando que no se puede eliminar. - Se busca un
     * ingrediente existente en la base de datos utilizando el método
     * `buscarIngredientePorNombreYUnidad` del `ingredienteDAO`. Si no se
     * encuentra el ingrediente, se lanza una `NegocioException` indicando que
     * no se encontró el ingrediente en la base de datos. - Si todas las
     * validaciones son satisfactorias y se encuentra el ingrediente, se llama
     * al método `eliminarIngrediente` del `ingredienteDAO` para eliminar el
     * ingrediente de la base de datos.
     *
     * Este método es útil para asegurar que solo se eliminen ingredientes que
     * no están en uso y que se proporciona un manejo adecuado de errores,
     * promoviendo la integridad de los datos en el sistema.
     *
     * @param nuevoIngrediente
     * @throws java.lang.Exception
     */
    @Override
    public void eliminarIngredienteBO(NuevoIngredienteDTO nuevoIngrediente) throws Exception {
        if (nuevoIngrediente == null) {
            throw new NegocioException("El DTO del ingrediente no puede ser nulo.");
        }

        if (nuevoIngrediente.getNombre() == null || nuevoIngrediente.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del ingrediente no puede estar vacío.");
        }
        if (nuevoIngrediente.getUnidad_medida() == null || nuevoIngrediente.getUnidad_medida().trim().isEmpty()) {
            throw new NegocioException("La unidad de medida no puede estar vacía.");
        }

        if (ingredienteDAO.tieneRelacionesActivas(
                nuevoIngrediente.getNombre(),
                nuevoIngrediente.getUnidad_medida()
        )) {
            throw new NegocioException("No se puede eliminar: el ingrediente está en uso por productos.");
        }

        Ingrediente ingredienteExistente = ingredienteDAO.buscarIngredientePorNombreYUnidad(
                nuevoIngrediente.getNombre(), nuevoIngrediente.getUnidad_medida()
        );

        if (ingredienteExistente == null) {
            throw new NegocioException("No se encontró el ingrediente en la base de datos.");
        }

        ingredienteDAO.eliminarIngrediente(ingredienteExistente);
    }

    /**
     * Este método se encarga de recuperar una lista de ingredientes desde el
     * sistema, utilizando el objeto `ingredienteDAO`.1.
     *
     * **Método**: `obtenerListaIngredientesBO()` 2. **Tipo de retorno**:
     * `List<Ingrediente>` 3. **Funcionalidad**: - Llama al método
     * `mostrarListaIngredientes()` del `ingredienteDAO`, que se espera que
     * devuelva una lista de objetos `Ingrediente` que representan todos los
     * ingredientes almacenados en la base de datos. - No se realizan
     * validaciones ni excepciones en este método, ya que se asume que el
     * `ingredienteDAO` está correctamente configurado y que la operación de
     * recuperación de datos se ejecutará sin problemas.
     *
     * Este método es útil para obtener una lista completa de ingredientes,
     * permitiendo a otras partes de la aplicación acceder a la información de
     * los ingredientes de manera sencilla y eficiente. Es común que este tipo
     * de métodos se utilicen en la lógica de negocio para presentar datos a la
     * capa de presentación o para realizar operaciones adicionales sobre la
     * lista de ingredientes recuperada.
     *
     * @return
     */
    @Override
    public List<Ingrediente> obtenerListaIngredientesBO() {
        return ingredienteDAO.mostrarListaIngredientes();
    }

    /**
     * Este método se encarga de actualizar la información de un ingrediente en
     * el sistema, validando la información proporcionada en un objeto
     * `NuevoIngredienteDTO` y el nuevo stock antes de proceder con la operación
     * de actualización.1.**Método**:
     * `actualizarIngredienteBO(NuevoIngredienteDTO nuevoIngredienteDTO, double
     * nuevoStock)` 2.**Parámetros**: - `nuevoIngredienteDTO`: Un objeto de tipo
     * `NuevoIngredienteDTO` que contiene la información del ingrediente a
     * actualizar.
     *
     * - `nuevoStock`: Un valor `double` que representa el nuevo stock del
     * ingrediente. 3. Excepciones**: - Lanza `NegocioException` si se detectan
     * problemas con los datos proporcionados o si no se puede realizar la
     * actualización.
     *
     * 4. **Funcionalidad**: - Se verifica si `nuevoIngredienteDTO` es `null`.
     * Si es así, se lanza una `NegocioException` con un mensaje que indica que
     * el DTO no puede ser nulo. - Se valida que el nombre del ingrediente no
     * sea nulo ni esté vacío. Si es nulo o vacío, se lanza una
     * `NegocioException` correspondiente. - Se valida que la unidad de medida
     * no sea nula ni esté vacía. Si es nula o vacía, se lanza una
     * `NegocioException` correspondiente. - Se valida que el nuevo stock no sea
     * negativo. Si es negativo, se lanza una `NegocioException`
     * correspondiente. - Se busca el ingrediente existente en la base de datos
     * utilizando el método `buscarIngredientePorNombreYUnidad` del
     * `ingredienteDAO`. Si no se encuentra el ingrediente, se lanza una
     * `NegocioException` indicando que no se encontró el ingrediente con el
     * nombre y unidad de medida especificados. - Si todas las validaciones son
     * satisfactorias y se encuentra el ingrediente, se llama al método
     * `actualizarIngrediente` del `ingredienteDAO`, pasando el
     * `nuevoIngredienteDTO` y el `nuevoStock` para realizar la actualización en
     * la base de datos.
     *
     * Este método es útil para asegurar que solo se actualicen ingredientes
     * válidos y que se manejen adecuadamente los errores, promoviendo la
     * integridad de los datos en el sistema y permitiendo la modificación de la
     * información de los ingredientes de manera controlada.
     *
     * @param nuevoIngredienteDTO
     * @param nuevoStock
     * @throws java.lang.Exception
     */
    @Override
    public void actualizarIngredienteBO(NuevoIngredienteDTO nuevoIngredienteDTO, double nuevoStock) throws Exception {

        if (nuevoIngredienteDTO == null) {
            throw new NegocioException("El DTO del ingrediente no puede ser nulo.");
        }
        if (nuevoIngredienteDTO.getNombre() == null || nuevoIngredienteDTO.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del ingrediente no puede estar vacío.");
        }
        if (nuevoIngredienteDTO.getUnidad_medida() == null || nuevoIngredienteDTO.getUnidad_medida().trim().isEmpty()) {
            throw new NegocioException("La unidad de medida del ingrediente no puede estar vacía.");
        }

        if (nuevoStock < 0) {
            throw new NegocioException("El stock no puede ser negativo.");
        }

        Ingrediente ingrediente = ingredienteDAO.buscarIngredientePorNombreYUnidad(
                nuevoIngredienteDTO.getNombre(),
                nuevoIngredienteDTO.getUnidad_medida()
        );

        if (ingrediente == null) {
            throw new NegocioException("No se encontró el ingrediente con el nombre y unidad de medida especificados.");
        }

        ingredienteDAO.actualizarIngrediente(nuevoIngredienteDTO, nuevoStock);
    }

    /**
     * Este método se encarga de buscar un ingrediente en el sistema utilizando
     * su nombre y unidad de medida, validando la información proporcionada
     * antes de realizar la búsqueda.1.**Método**:
     * `buscarIngredientePorNombreYUnidadBO(String nombre, String unidadMedida)`
     * 2.**Parámetros**: - `nombre`: Un `String` que representa el nombre del
     * ingrediente a buscar.- `unidadMedida`: Un `String` que representa la
     * unidad de medida del ingrediente a buscar.
     *
     * 3. Excepciones**: - Lanza `NegocioException` si se detectan problemas con
     * los datos proporcionados o si no se encuentra el ingrediente.
     *
     * 4. **Funcionalidad**: - Se valida que el `nombre` no sea nulo ni esté
     * vacío. Si es nulo o vacío, se lanza una `NegocioException` con un mensaje
     * correspondiente. - Se valida que la `unidadMedida` no sea nula ni esté
     * vacía. Si es nula o vacía, se lanza una `NegocioException` con un mensaje
     * correspondiente. - Se busca el ingrediente en la base de datos utilizando
     * el método `buscarIngredientePorNombreYUnidad` del `ingredienteDAO`. - Si
     * no se encuentra el ingrediente, se lanza una `NegocioException` indicando
     * que no se encontró el ingrediente con el nombre y unidad de medida
     * especificados. - Si se encuentra el ingrediente, se devuelve la instancia
     * del mismo.
     *
     * Este método es útil para permitir la búsqueda de ingredientes de manera
     * controlada, asegurando que se manejen adecuadamente los errores y que
     * solo se realicen búsquedas con datos válidos. Además, promueve la
     * integridad de los datos al garantizar que se notifique al usuario si el
     * ingrediente no existe en el sistema.
     *
     * @param nombre
     * @param unidadMedida
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public Ingrediente buscarIngredientePorNombreYUnidadBO(String nombre, String unidadMedida) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NegocioException("El nombre del ingrediente no puede estar vacío.");
        }
        if (unidadMedida == null || unidadMedida.trim().isEmpty()) {
            throw new NegocioException("La unidad de medida del ingrediente no puede estar vacía.");
        }

        Ingrediente ingrediente = ingredienteDAO.buscarIngredientePorNombreYUnidad(nombre, unidadMedida);

        if (ingrediente == null) {
            throw new NegocioException("No se encontró el ingrediente con el nombre '" + nombre + "' y unidad de medida '" + unidadMedida + "'.");
        }

        return ingrediente;
    }

    /**
     * Este método se encarga de verificar si un ingrediente tiene relaciones
     * activas en el sistema, utilizando su nombre y unidad de medida como
     * parámetros de búsqueda.1.**Método**: `tieneRelacionesActivasBO(String
     * nombre, String unidadMedida)` 2.**Parámetros**: - `nombre`: Un `String`
     * que representa el nombre del ingrediente a verificar.- `unidadMedida`: Un
     * `String` que representa la unidad de medida del ingrediente a verificar.
     *
     * 3. Excepciones**: - Lanza `NegocioException` si se detectan problemas con
     * los datos proporcionados.
     *
     * 4. **Funcionalidad**: - Se valida que el `nombre` no sea nulo ni esté
     * vacío. Si es nulo o vacío, se lanza una `NegocioException` con un mensaje
     * correspondiente. - Se valida que la `unidadMedida` no sea nula ni esté
     * vacía. Si es nula o vacía, se lanza una `NegocioException` con un mensaje
     * correspondiente. - (El código que sigue a estas validaciones no está
     * incluido, pero se espera que el método continúe con la lógica para
     * verificar si el ingrediente tiene relaciones activas en la base de
     * datos).
     *
     * Este método es útil para determinar si un ingrediente puede ser eliminado
     * o modificado, asegurando que no se realicen operaciones en ingredientes
     * que están en uso por otros productos o entidades en el sistema. Al
     * manejar adecuadamente las validaciones y excepciones, se promueve la
     * integridad de los datos y se evita la eliminación accidental de
     * ingredientes que son necesarios para otras operaciones.
     *
     * @param nombre
     * @param unidadMedida
     * @return
     * @throws NegocioException.NegocioException
     */
    @Override
    public boolean tieneRelacionesActivasBO(String nombre, String unidadMedida) throws NegocioException {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NegocioException("El nombre del ingrediente no puede estar vacío.");
        }
        if (unidadMedida == null || unidadMedida.trim().isEmpty()) {
            throw new NegocioException("La unidad de medida no puede estar vacía.");
        }

        try {

            return ingredienteDAO.tieneRelacionesActivas(nombre, unidadMedida);

        } catch (Exception e) {
            Logger.getLogger(IngredienteBO.class.getName())
                    .log(Level.SEVERE, "Error al verificar relaciones del ingrediente", e);
            throw new NegocioException("No se pudo verificar si el ingrediente está en uso.");
        }
    }

    /**
     * Este método se encarga de recuperar una lista de ingredientes asociados a
     * un producto específico, utilizando el nombre del producto como
     * parámetro.1.**Método**: `obtenerIngredientesPorNombreProductoBO(String
     * nombreProducto)` 2.
     *
     * **Parámetro**: - `nombreProducto`: Un `String` que representa el nombre
     * del producto del cual se desean obtener los ingredientes. 3. **Tipo de
     * retorno**: `List<Ingrediente>` 4. **Funcionalidad**: - Llama al método
     * `obtenerIngredientesPorNombreProducto` del `ingredienteDAO`, que se
     * espera que devuelva una lista de objetos `Ingrediente` asociados al
     * producto especificado por `nombreProducto`. - Este método no incluye
     * validaciones ni manejo de excepciones, lo que implica que se asume que el
     * `nombreProducto` es válido y que el `ingredienteDAO` está correctamente
     * configurado para realizar la consulta.
     *
     * Este método es útil para obtener los ingredientes que componen un
     * producto específico, permitiendo a otras partes de la aplicación acceder
     * a la información de manera sencilla. Es común que este tipo de métodos se
     * utilicen en la lógica de negocio para facilitar la gestión de recetas o
     * listas de ingredientes en aplicaciones de cocina, restaurantes o gestión
     * de inventarios.
     *
     * @param nombreProducto
     * @return
     */
    @Override
    public List<Ingrediente> obtenerIngredientesPorNombreProductoBO(String nombreProducto) {
        return ingredienteDAO.obtenerIngredientesPorNombreProducto(nombreProducto);
    }

    /**
     * Este método se encarga de recuperar una lista de ingredientes junto con
     * la cantidad necesaria para un producto específico, utilizando el nombre
     * del producto como parámetro.1.**Método**:
     * `obtenerIngredientesConCantidadPorProductoBO(String nombreProducto)` 2.
     *
     * **Parámetro**: - `nombreProducto`: Un `String` que representa el nombre
     * del producto del cual se desean obtener los ingredientes y sus cantidades
     * necesarias. 3. **Tipo de retorno**:
     * `List<IngredienteConCantidadNecesariaDTO>` 4. **Funcionalidad**: - Llama
     * al método `obtenerIngredientesConCantidadPorProducto` del
     * `ingredienteDAO`, que se espera que devuelva una lista de objetos
     * `IngredienteConCantidadNecesariaDTO` asociados al producto especificado
     * por `nombreProducto`. - Este método no incluye validaciones ni manejo de
     * excepciones, lo que implica que se asume que el `nombreProducto` es
     * válido y que el `ingredienteDAO` está correctamente configurado para
     * realizar la consulta.
     *
     * Este método es útil para obtener no solo los ingredientes que componen un
     * producto específico, sino también la cantidad necesaria de cada uno de
     * ellos. Esto es especialmente valioso en aplicaciones de cocina,
     * restaurantes o gestión de inventarios, donde es fundamental conocer las
     * cantidades exactas de ingredientes requeridos para la preparación de un
     * producto. Facilita la planificación y gestión de recursos en la cocina o
     * en el manejo de inventarios.
     *
     * @param nombreProducto
     * @return
     */
    @Override
    public List<IngredienteConCantidadNecesariaDTO> obtenerIngredientesConCantidadPorProductoBO(String nombreProducto) {
        return ingredienteDAO.obtenerIngredientesConCantidadPorProducto(nombreProducto);
    }

}
