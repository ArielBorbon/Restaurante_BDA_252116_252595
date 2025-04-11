/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO.ComandasBO;

import DAO.Clientes.ClientesDAO;
import DAO.Comandas.ComandasDAO;
import DAO.Mesas.MesasDAO;
import DAO.Productos.ProductosDAO;
import DTOS.Comandas.NuevaComandaDTO;
import DTOS.Comandas.NuevoDetalleComandaDTO;
import Entidades.Comandas.Comanda;
import Entidades.Comandas.DetalleComanda;
import Entidades.Productos.Producto;
import Excepciones.PersistenciaException;
import NegocioException.NegocioException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase BO de comanda
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class ComandaBO implements IComandaBO {
    
    private ComandasDAO comandasDAO;
    private ProductosDAO productosDAO;
    private ClientesDAO clientesDAO;
    private MesasDAO mesasDAO;

    /**
     * Este es el constructor de la clase `ComandaBO`, que se encarga de
     * inicializar una instancia de la clase y establecer las dependencias
     * necesarias para su funcionamiento.
     *
     * 1. **Constructor**: `ComandaBO(ComandasDAO comandasDAO, ProductosDAO
     * productosDAO, ClientesDAO clientesDAO, MesasDAO mesasDAO)` 2.
     * **Parámetros**: - `comandasDAO`: Un objeto de tipo `ComandasDAO` que se
     * utilizará para realizar operaciones relacionadas con las comandas en la
     * base de datos. - `productosDAO`: Un objeto de tipo `ProductosDAO` que se
     * utilizará para realizar operaciones relacionadas con los productos en la
     * base de datos. - `clientesDAO`: Un objeto de tipo `ClientesDAO` que se
     * utilizará para realizar operaciones relacionadas con los clientes en la
     * base de datos. - `mesasDAO`: Un objeto de tipo `MesasDAO` que se
     * utilizará para realizar operaciones relacionadas con las mesas en la base
     * de datos.
     *
     * 3. **Funcionalidad**: - Este constructor asigna los objetos DAO
     * proporcionados a las variables de instancia correspondientes
     * (`this.comandasDAO`, `this.productosDAO`, `this.clientesDAO`,
     * `this.mesasDAO`), permitiendo que la clase `ComandaBO` utilice estos DAOs
     * para realizar operaciones relacionadas con las comandas, productos,
     * clientes y mesas.
     *
     * Este constructor es fundamental para garantizar que la clase `ComandaBO`
     * esté correctamente configurada antes de ser utilizada, promoviendo la
     * integridad y la robustez del sistema al asegurar que todas las
     * dependencias necesarias estén disponibles.
     *
     * @param comandasDAO
     * @param productosDAO
     * @param clientesDAO
     * @param mesasDAO
     */
    public ComandaBO(ComandasDAO comandasDAO, ProductosDAO productosDAO, ClientesDAO clientesDAO, MesasDAO mesasDAO) {
        this.comandasDAO = comandasDAO;
        this.productosDAO = productosDAO;
        this.clientesDAO = clientesDAO;
        this.mesasDAO = mesasDAO;
    }

    /**
     * Registra una comanda y sus detalles, validando previamente si hay stock
     * suficiente.
     *
     * @param comandaDTO
     * @param detallesDTO
     * @return
     */
    @Override
    public Comanda registrarComandaBO(NuevaComandaDTO comandaDTO, List<NuevoDetalleComandaDTO> detallesDTO) {
        if (detallesDTO == null || detallesDTO.isEmpty()) {
            throw new IllegalArgumentException("La comanda debe tener al menos un producto.");
        }

        boolean stockSuficiente = comandasDAO.verificarStockNecesarioProductos(detallesDTO);
        if (!stockSuficiente) {
            throw new RuntimeException("No hay stock suficiente para realizar la comanda.");
        }

        for (NuevoDetalleComandaDTO detalle : detallesDTO) {
            ComandasDAO comandaDAO = new ComandasDAO();
            double totalDetalle = comandaDAO.calcularTotalDetalleComanda(detalle);
            detalle.setImporteTotal(totalDetalle);
        }
        ComandasDAO comandaDAO = new ComandasDAO();
        double totalComanda = comandaDAO.calcularTotalComanda(detallesDTO);
        comandaDTO.setTotal(totalComanda);

        if (comandaDTO.getFolio() == null || comandaDTO.getFolio().isBlank()) {
            comandaDTO.setFolio(comandasDAO.generarFolioComanda());
        }

        return comandasDAO.registrarComanda(comandaDTO, detallesDTO);
    }

    /**
     * Calcula el total de un detalle (cantidad x precio del producto).
     *
     * @param detalleDTO
     * @return
     */
    @Override
    public double calcularTotalDetalleComandaBO(NuevoDetalleComandaDTO detalleDTO) {
        Producto producto = productosDAO.buscarProductoPorNombre(detalleDTO.getNombreProducto());
        return producto.getPrecio() * detalleDTO.getCantidad();
    }

    /**
     * Suma los importes totales de todos los detalles.
     *
     * @param detallesDTO
     * @return
     */
    @Override
    public double calcularTotalComandaBO(List<NuevoDetalleComandaDTO> detallesDTO) {
        return detallesDTO.stream()
                .mapToDouble(NuevoDetalleComandaDTO::getImporteTotal)
                .sum();
    }

    /**
     * Este método se encarga de generar un nuevo folio para una comanda.
     *
     * 1. **Método**: `generarFolioComandaBO()` 2. **Tipo de retorno**: `String`
     * 3. **Funcionalidad**: - Llama al método `generarFolioComanda` del
     * `comandasDAO` para obtener un nuevo folio que se utilizará para
     * identificar la comanda de manera única. - Este método no incluye
     * validaciones ni manejo de excepciones, ya que se asume que el
     * `comandasDAO` está correctamente configurado para realizar la operación.
     *
     * Este método es esencial para asegurar que cada comanda tenga un
     * identificador único, facilitando su seguimiento y gestión dentro del
     * sistema.
     *
     * @return
     */
    @Override
    public String generarFolioComandaBO() {
        return comandasDAO.generarFolioComanda();
    }

    /**
     * Este método se encarga de modificar una comanda existente en el sistema,
     * actualizando su información y detalles.
     *
     * 1. **Método**: `modificarComandaBO(NuevaComandaDTO comandaDTO,
     * List<NuevoDetalleComandaDTO> detallesDTO)` 2. **Parámetros**: -
     * `comandaDTO`: Un objeto de tipo `NuevaComandaDTO` que contiene la
     * información de la comanda a modificar, incluyendo su folio. -
     * `detallesDTO`: Una lista de objetos de tipo `NuevoDetalleComandaDTO` que
     * representan los detalles de los productos incluidos en la comanda. 3.
     * **Tipo de retorno**: `void` 4. **Excepciones**: - Lanza
     * `IllegalArgumentException` si `comandaDTO` es `null`, o si el folio de la
     * comanda es `null` o está vacío. - Lanza `IllegalArgumentException` si
     * `detallesDTO` es `null` o está vacío, indicando que debe haber al menos
     * un detalle de comanda. - Lanza `RuntimeException` si no hay suficiente
     * stock para modificar la comanda.
     *
     * 5. **Funcionalidad**: - Se verifica que el objeto `comandaDTO` no sea
     * `null` y que su folio no esté vacío. Si alguna de estas condiciones no se
     * cumple, se lanza una excepción. - Se verifica que la lista de detalles no
     * esté vacía. Si lo está, se lanza una excepción. - Se llama al método
     * `verificarStockNecesarioProductos` del `comandasDAO` para comprobar si
     * hay suficiente stock para los productos en los detalles de la comanda. Si
     * no hay suficiente stock, se lanza una excepción. - Se itera sobre cada
     * detalle de la comanda para calcular el total de cada detalle utilizando
     * el método `calcularTotalDetalleComanda` del `comandasDAO`, y se establece
     * el importe total en cada detalle. - Se calcula el total de la comanda
     * utilizando el método `calcularTotalComanda` del `comandasDAO` y se
     * establece en el objeto `comandaDTO`. - Finalmente, se llama al método
     * `modificarComanda` del `comandasDAO` para actualizar la comanda y sus
     * detalles en la base de datos.
     *
     * Este método es esencial para gestionar la modificación de una comanda
     * existente, asegurando que se cumplan las condiciones necesarias antes de
     * realizar la actualización en el sistema.
     *
     * @param comandaDTO
     * @param detallesDTO
     */
    @Override
    public void modificarComandaBO(NuevaComandaDTO comandaDTO, List<NuevoDetalleComandaDTO> detallesDTO) {
        if (comandaDTO == null || comandaDTO.getFolio() == null || comandaDTO.getFolio().isBlank()) {
            throw new IllegalArgumentException("El folio de la comanda no puede ser nulo o vacío.");
        }

        if (detallesDTO == null || detallesDTO.isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos un detalle de comanda.");
        }

        ComandasDAO comandasDAO = new ComandasDAO();
        boolean hayStock = comandasDAO.verificarStockNecesarioProductos(detallesDTO);
        if (!hayStock) {
            throw new RuntimeException("No hay stock suficiente para modificar la comanda.");
        }

        for (NuevoDetalleComandaDTO detalle : detallesDTO) {
            ComandasDAO comandaDAO = new ComandasDAO();
            double totalDetalle = comandaDAO.calcularTotalDetalleComanda(detalle);
            detalle.setImporteTotal(totalDetalle);
        }
        ComandasDAO comandaDAO = new ComandasDAO();
        double totalComanda = comandaDAO.calcularTotalComanda(detallesDTO);
        comandaDTO.setTotal(totalComanda);

        comandasDAO.modificarComanda(comandaDTO, detallesDTO);
    }

    /**
     * Este método se encarga de eliminar una comanda existente en el sistema.
     *
     * 1. **Método**: `eliminarComandaBO(NuevaComandaDTO comandaDTO)` 2.
     * **Parámetro**: - `comandaDTO`: Un objeto de tipo `NuevaComandaDTO` que
     * contiene la información de la comanda a eliminar, incluyendo su folio. 3.
     * **Tipo de retorno**: `void` 4. **Excepciones**: - Lanza
     * `IllegalArgumentException` si `comandaDTO` es `null`, o si el folio de la
     * comanda es `null` o está vacío, indicando que se requiere un folio válido
     * para realizar la eliminación.
     *
     * 5. **Funcionalidad**: - Se verifica que el objeto `comandaDTO` no sea
     * `null` y que su folio no esté vacío. Si alguna de estas condiciones no se
     * cumple, se lanza una excepción. - Se llama al método `eliminarComanda`
     * del `comandasDAO` para eliminar la comanda de la base de datos.
     *
     * Este método es esencial para gestionar la eliminación de una comanda en
     * el sistema, asegurando que se proporcione un identificador válido antes
     * de proceder con la operación.
     *
     * @param comandaDTO
     */
    @Override
    public void eliminarComandaBO(NuevaComandaDTO comandaDTO) {
        if (comandaDTO == null || comandaDTO.getFolio() == null || comandaDTO.getFolio().isBlank()) {
            throw new IllegalArgumentException("Se requiere un folio válido para eliminar la comanda.");
        }

        ComandasDAO comandasDAO = new ComandasDAO();
        comandasDAO.eliminarComanda(comandaDTO);
    }

    /**
     * Este método se encarga de cancelar una comanda existente en el sistema,
     * cambiando su estado a "cancelada".
     *
     * 1. **Método**: `cancelarComandaBO(String folio)` 2. **Parámetro**: -
     * `folio`: Un `String` que representa el folio de la comanda que se desea
     * cancelar. 3. **Tipo de retorno**: `void` 4. **Excepciones**: - Lanza
     * `IllegalArgumentException` si el `folio` es `null` o está vacío,
     * indicando que se requiere un folio válido para realizar la cancelación.
     *
     * 5. **Funcionalidad**: - Se verifica que el `folio` no sea `null` y que no
     * esté vacío. Si alguna de estas condiciones no se cumple, se lanza una
     * excepción. - Se llama al método `cambiarEstadoComandaACancelada` del
     * `comandasDAO`, pasando el `folio` para cambiar el estado de la comanda a
     * "cancelada".
     *
     * Este método es esencial para gestionar la cancelación de una comanda en
     * el sistema, asegurando que se proporcione un identificador válido antes
     * de proceder con la operación.
     *
     * @param folio
     */
    @Override
    public void cancelarComandaBO(String folio) {
        if (folio == null || folio.isBlank()) {
            throw new IllegalArgumentException("Folio inválido. No se puede cancelar la comanda.");
        }

        ComandasDAO comandasDAO = new ComandasDAO();
        comandasDAO.cambiarEstadoComandaACancelada(folio);
    }

    /**
     * Este método se encarga de marcar una comanda como entregada en el
     * sistema, cambiando su estado a "entregada".
     *
     * 1. **Método**: `entregarComanda(String folio)` 2. **Parámetro**: -
     * `folio`: Un `String` que representa el folio de la comanda que se desea
     * marcar como entregada. 3. **Tipo de retorno**: `void` 4. **Excepciones**:
     * - Lanza `IllegalArgumentException` si el `folio` es `null` o está vacío,
     * indicando que se requiere un folio válido para realizar la entrega.
     *
     * 5. **Funcionalidad**: - Se verifica que el `folio` no sea `null` y que no
     * esté vacío. Si alguna de estas condiciones no se cumple, se lanza una
     * excepción. - Se llama al método `cambiarEstadoComandaAEntregada` del
     * `comandasDAO`, pasando el `folio` para cambiar el estado de la comanda a
     * "entregada".
     *
     * Este método es esencial para gestionar la entrega de una comanda en el
     * sistema, asegurando que se proporcione un identificador válido antes de
     * proceder con la operación.
     *
     * @param folio
     */
    @Override
    public void entregarComanda(String folio) {
        if (folio == null || folio.isBlank()) {
            throw new IllegalArgumentException("Folio inválido. No se puede entregar la comanda.");
        }

        ComandasDAO comandasDAO = new ComandasDAO();
        comandasDAO.cambiarEstadoComandaAEntregada(folio);
    }

    /**
     * Este método se encarga de verificar si hay suficiente stock disponible
     * para los productos incluidos en los detalles de una comanda.
     *
     * 1. **Método**:
     * `verificarStockNecesarioProductosBO(List<NuevoDetalleComandaDTO>
     * detallesDTO)` 2. **Parámetro**: - `detallesDTO`: Una lista de objetos de
     * tipo `NuevoDetalleComandaDTO` que representan los detalles de los
     * productos cuya disponibilidad de stock se desea verificar. 3. **Tipo de
     * retorno**: `boolean` 4. **Excepciones**: - Lanza
     * `IllegalArgumentException` si `detallesDTO` es `null` o está vacía,
     * indicando que la lista de detalles de comanda no puede estar vacía.
     *
     * 5. **Funcionalidad**: - Se verifica que la lista de detalles no sea
     * `null` y que no esté vacía. Si alguna de estas condiciones no se cumple,
     * se lanza una excepción. - Se llama al método
     * `verificarStockNecesarioProductos` del `comandasDAO`, pasando la lista de
     * detalles para comprobar si hay suficiente stock disponible para todos los
     * productos.
     *
     * Este método es esencial para garantizar que se pueda realizar una comanda
     * sin problemas de disponibilidad de stock, ayudando a prevenir errores en
     * el proceso de registro de comandas.
     *
     * @param detallesDTO
     * @return
     */
    @Override
    public boolean verificarStockNecesarioProductosBO(List<NuevoDetalleComandaDTO> detallesDTO) {
        if (detallesDTO == null || detallesDTO.isEmpty()) {
            throw new IllegalArgumentException("La lista de detalles de comanda no puede estar vacía.");
        }

        ComandasDAO comandasDAO = new ComandasDAO();
        return comandasDAO.verificarStockNecesarioProductos(detallesDTO);
    }

    /**
     * Este método se encarga de restar el stock de los ingredientes necesarios
     * para los productos incluidos en los detalles de una comanda.
     *
     * 1. **Método**:
     * `restarStockIngredientesPorProductosComandaBO(List<NuevoDetalleComandaDTO>
     * detallesDTO)` 2. **Parámetro**: - `detallesDTO`: Una lista de objetos de
     * tipo `NuevoDetalleComandaDTO` que representan los detalles de los
     * productos para los cuales se desea restar el stock de ingredientes. 3.
     * **Tipo de retorno**: `void` 4. **Excepciones**: - Lanza
     * `IllegalArgumentException` si `detallesDTO` es `null` o está vacía,
     * indicando que la lista de detalles de comanda no puede estar vacía.
     *
     * 5. **Funcionalidad**: - Se verifica que la lista de detalles no sea
     * `null` y que no esté vacía. Si alguna de estas condiciones no se cumple,
     * se lanza una excepción. - Se llama al método
     * `restarStockIngredientesPorProductosComanda` del `comandasDAO`, pasando
     * la lista de detalles para restar el stock de los ingredientes necesarios
     * para los productos de la comanda.
     *
     * Este método es esencial para gestionar el inventario de ingredientes,
     * asegurando que se actualice correctamente el stock después de que se haya
     * procesado una comanda.
     *
     * @param detallesDTO
     */
    @Override
    public void restarStockIngredientesPorProductosComandaBO(List<NuevoDetalleComandaDTO> detallesDTO) {
        if (detallesDTO == null || detallesDTO.isEmpty()) {
            throw new IllegalArgumentException("La lista de detalles de comanda no puede estar vacía.");
        }

        ComandasDAO comandasDAO = new ComandasDAO();
        comandasDAO.restarStockIngredientesPorProductosComanda(detallesDTO);
    }

    /**
     * Este método se encarga de modificar la nota asociada a un detalle de
     * comanda específico.
     *
     * 1. **Método**: `modificarNotaBO(NuevoDetalleComandaDTO detalleDTO, String
     * nuevaNota)` 2. **Parámetros**: - `detalleDTO`: Un objeto de tipo
     * `NuevoDetalleComandaDTO` que representa el detalle de la comanda que se
     * desea modificar. Debe contener el folio de la comanda y el nombre del
     * producto. - `nuevaNota`: Un `String` que representa la nueva nota que se
     * desea asignar al detalle de la comanda. 3. **Tipo de retorno**: `void` 4.
     * **Excepciones**: - Lanza `IllegalArgumentException` si `detalleDTO` es
     * `null`, o si el folio de la comanda o el nombre del producto son `null`,
     * indicando que el detalle de comanda debe contener esta información. -
     * Lanza `IllegalArgumentException` si `nuevaNota` es `null`, indicando que
     * la nueva nota no puede ser nula.
     *
     * 5. **Funcionalidad**: - Se verifica que el objeto `detalleDTO` no sea
     * `null` y que contenga tanto el folio de la comanda como el nombre del
     * producto. Si alguna de estas condiciones no se cumple, se lanza una
     * excepción. - Se verifica que `nuevaNota` no sea `null`. Si lo es, se
     * lanza una excepción. - Se llama al método `modificarNota` del
     * `comandasDAO`, pasando el `detalleDTO` y la `nuevaNota` para actualizar
     * la nota en la base de datos.
     *
     * Este método es esencial para permitir la actualización de notas en los
     * detalles de las comandas, asegurando que se mantenga la información
     * correcta y actualizada.
     *
     * @param detalleDTO
     * @param nuevaNota
     */
    @Override
    public void modificarNotaBO(NuevoDetalleComandaDTO detalleDTO, String nuevaNota) {
        if (detalleDTO == null || detalleDTO.getFolioComanda() == null || detalleDTO.getNombreProducto() == null) {
            throw new IllegalArgumentException("El detalle de comanda debe contener el folio y nombre del producto.");
        }

        if (nuevaNota == null) {
            throw new IllegalArgumentException("La nueva nota no puede ser nula.");
        }

        ComandasDAO comandasDAO = new ComandasDAO();
        comandasDAO.modificarNota(detalleDTO, nuevaNota);
    }

    /**
     * Este método se encarga de recuperar y mostrar todas las comandas
     * existentes en el sistema.
     *
     * 1. **Método**: `mostrarComandasTodasBO()` 2. **Tipo de retorno**:
     * `List<Comanda>` 3. **Funcionalidad**: - Se crea una instancia de
     * `ComandasDAO`. - Se llama al método `mostrarComandasTodas` del
     * `comandasDAO`, que devuelve una lista de todas las comandas registradas
     * en el sistema.
     *
     * Este método es esencial para obtener un listado completo de todas las
     * comandas, permitiendo a los usuarios ver toda la información disponible.
     *
     * @return
     */
    @Override
    public List<Comanda> mostrarComandasTodasBO() {
        ComandasDAO comandasDAO = new ComandasDAO();
        return comandasDAO.mostrarComandasTodas();
    }

    /**
     * Este método se encarga de recuperar y mostrar todas las comandas que
     * están actualmente abiertas en el sistema.
     *
     * 1. **Método**: `mostrarComandasAbiertasBO()` 2. **Tipo de retorno**:
     * `List<Comanda>` 3. **Funcionalidad**: - Se crea una instancia de
     * `ComandasDAO`. - Se llama al método `mostrarComandasAbiertas` del
     * `comandasDAO`, que devuelve una lista de todas las comandas que están en
     * estado abierto.
     *
     * Este método es esencial para obtener un listado de las comandas que aún
     * no han sido cerradas, permitiendo a los usuarios gestionar las comandas
     * activas.
     *
     * @return
     */
    @Override
    public List<Comanda> mostrarComandasAbiertasBO() {
        ComandasDAO comandasDAO = new ComandasDAO();
        return comandasDAO.mostrarComandasAbiertas();
    }

    /**
     * Este método se encarga de filtrar y recuperar las comandas que se
     * encuentran dentro de un rango de fechas especificado.
     *
     * 1. **Método**: `filtrarPorFecha(Calendar fechaInicio, Calendar fechaFin)`
     * 2. **Parámetros**: - `fechaInicio`: Un objeto de tipo `Calendar` que
     * representa la fecha de inicio del rango para filtrar las comandas. -
     * `fechaFin`: Un objeto de tipo `Calendar` que representa la fecha de fin
     * del rango para filtrar las comandas. 3. **Tipo de retorno**:
     * `List<Comanda>` 4. **Excepciones**: - Lanza `IllegalArgumentException` si
     * `fechaInicio` o `fechaFin` son `null`, indicando que ambas fechas son
     * necesarias para realizar el filtrado. - Lanza `IllegalArgumentException`
     * si `fechaInicio` es posterior a `fechaFin`, indicando que el rango de
     * fechas es inválido. - Lanza `RuntimeException` si no se encuentran
     * comandas en el rango de fechas especificado.
     *
     * 5. **Funcionalidad**: - Se verifica que las fechas de inicio y fin no
     * sean `null`. Si alguna de estas condiciones no se cumple, se lanza una
     * excepción. - Se verifica que `fechaInicio` no sea posterior a `fechaFin`.
     * Si lo es, se lanza una excepción. - Se llama al método `filtrarPorFecha`
     * del `comandasDAO`, pasando las fechas para obtener la lista de comandas
     * que se encuentran dentro del rango especificado. - Si la lista de
     * comandas resultante está vacía, se lanza una excepción indicando que no
     * se encontraron comandas en el rango de fechas.
     *
     * Este método es esencial para permitir la búsqueda de comandas en un rango
     * de fechas específico, facilitando la gestión y consulta de datos en el
     * sistema.
     *
     * @param fechaInicio
     * @param fechaFin
     * @return
     */
    @Override
    public List<Comanda> filtrarPorFecha(Calendar fechaInicio, Calendar fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin no pueden ser nulas.");
        }

        if (fechaInicio.after(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin.");
        }

        List<Comanda> comandas = comandasDAO.filtrarPorFecha(fechaInicio, fechaFin);

        if (comandas.isEmpty()) {
            throw new RuntimeException("No se encontraron comandas en el rango de fechas especificado.");
        }

        return comandas;
    }

    /**
     * Este método se encarga de obtener una comanda específica a partir de su
     * folio.
     *
     * 1. **Método**: `obtenerComandaPorFolioBO(String folio)` 2. **Parámetro**:
     * - `folio`: Un `String` que representa el folio de la comanda que se desea
     * obtener. 3. **Tipo de retorno**: `Comanda` 4. **Excepciones**: - Lanza
     * `NegocioException` si ocurre un error en la lógica al buscar la comanda,
     * específicamente si se produce una `PersistenciaException` durante la
     * operación de búsqueda.
     *
     * 5. **Funcionalidad**: - Se llama al método `obtenerComandaPorFolio` del
     * `comandasDAO`, pasando el `folio` para recuperar la comanda
     * correspondiente. - Si se produce una `PersistenciaException` durante la
     * búsqueda, se captura la excepción y se lanza una `NegocioException` con
     * un mensaje descriptivo y la excepción original como causa.
     *
     * Este método es esencial para permitir la recuperación de una comanda
     * específica en el sistema, manejando adecuadamente las excepciones que
     * puedan surgir durante el proceso de búsqueda.
     *
     * @param folio
     * @return
     * @throws NegocioException
     */
    @Override
    public Comanda obtenerComandaPorFolioBO(String folio) throws NegocioException {
        try {
            return comandasDAO.obtenerComandaPorFolio(folio);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error en la lógica al buscar la comanda por folio", e);
        }
    }

    /**
     * Este método se encarga de obtener la lista de detalles de una comanda
     * específica.
     *
     * 1. **Método**: `obtenerListaDetallesComandaBO(Comanda comanda)` 2.
     * **Parámetro**: - `comanda`: Un objeto de tipo `Comanda` que representa la
     * comanda de la cual se desea obtener la lista de detalles. 3. **Tipo de
     * retorno**: `List<DetalleComanda>` 4. **Excepciones**: - Lanza
     * `NegocioException` si ocurre un error al recuperar los detalles de la
     * comanda, específicamente si se produce una `PersistenciaException`
     * durante la operación de recuperación.
     *
     * 5. **Funcionalidad**: - Se llama al método `obtenerListaDetallesComanda`
     * del `comandasDAO`, pasando la `comanda` para recuperar la lista de
     * detalles asociados a ella. - Si se produce una `PersistenciaException`
     * durante la recuperación, se captura la excepción y se lanza una
     * `NegocioException` con un mensaje descriptivo y la excepción original
     * como causa.
     *
     * Este método es esencial para permitir la obtención de los detalles de una
     * comanda en el sistema, manejando adecuadamente las excepciones que puedan
     * surgir durante el proceso de recuperación.
     *
     * @param comanda
     * @return
     * @throws NegocioException
     */
    @Override
    public List<DetalleComanda> obtenerListaDetallesComandaBO(Comanda comanda) throws NegocioException {
        try {
            return comandasDAO.obtenerListaDetallesComanda(comanda);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al recuperar detalles de la comanda", e);
        }
    }

    /**
     * Este método se encarga de actualizar la información de un cliente
     * frecuente en el sistema, basándose en el total gastado por el cliente.
     *
     * 1. **Método**: `actualizarClienteFrecuente(Long idCliente, double
     * totalGastado)` 2. **Parámetros**: - `idCliente`: Un `Long` que representa
     * el identificador único del cliente que se desea actualizar. -
     * `totalGastado`: Un `double` que representa el total de dinero gastado por
     * el cliente, que se utilizará para calcular los puntos acumulados. 3.
     * **Tipo de retorno**: `void` 4. **Funcionalidad**: - Se calcula el número
     * de puntos acumulados dividiendo el `totalGastado` entre 20. Cada 20
     * unidades monetarias gastadas equivalen a 1 punto. - Se establece el
     * número de visitas a 1, asumiendo que esta actualización corresponde a una
     * nueva visita del cliente. - Se crea una instancia de `ClientesDAO`. - Se
     * llama al método `actualizarClienteFrecuente` del `clientesDAO`, pasando
     * el `idCliente`, los `puntos`, el número de `visitas` y el `totalGastado`
     * para actualizar la información del cliente en la base de datos.
     *
     * Este método es esencial para mantener actualizada la información de los
     * clientes frecuentes, permitiendo gestionar sus puntos y visitas de manera
     * efectiva.
     * 
     * @param idCliente
     * @param totalGastado 
     */
    @Override
    public void actualizarClienteFrecuente(Long idCliente, double totalGastado) {
        int puntos = (int) (totalGastado / 20);
        int visitas = 1;

        ClientesDAO clientesDAO = new ClientesDAO();
        clientesDAO.actualizarClienteFrecuente(idCliente, puntos, visitas, totalGastado);
    }
    
    /**
     * Este metodo se encarga de calcular el total de todas las comandas
     * existentes, basandose en el total de cada una de las comandas
     * 
     * @return
     * @throws NegocioException 
     */
    @Override
    public double calcularTotalDeTodasLasComandas() throws NegocioException {
        try {
            return comandasDAO.calcularTotalDeTodasLasComandas();
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al calcular el total de todas las comandas", e);
        }
    }
    
    /**
     * Este metodo se encarga de calcular el total de todas las comandas
     * existentes, basandose en el total de cada una de las comandas
     * 
     * @param fechaInicio
     * @param fechaFin
     * @return
     * @throws NegocioException 
     */
    @Override
    public double calcularTotalDeComandasPorFechas(Date fechaInicio, Date fechaFin) throws NegocioException {
        try {
            return comandasDAO.calcularTotalDeComandasPorFechas(fechaInicio, fechaFin);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al calcular el total de todas las comandas", e);
        }
    }
}
