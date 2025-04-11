/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ModuloProductos;

import BO.ProductoBO.ProductoBO;
import Entidades.Productos.Producto;
import Fabricas.FabricaProductos;
import NegocioException.NegocioException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Tabla de Productos
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class FormProductosTablaTodos extends javax.swing.JPanel {

    /**
     * Creates new form FormProductosTablaTodos
     *
     * @throws NegocioException.NegocioException
     */
    public FormProductosTablaTodos() throws NegocioException {
        initComponents();
        cargarProductosEnTablaTodos();

    }

    /**
     * Este método se encarga de cargar la lista de todos los productos en una
     * tabla, mostrando su nombre, precio, tipo y estado de disponibilidad.
     *
     * 1. **Método**: `cargarProductosEnTablaTodos()` 2. **Tipo de retorno**:
     * `void` 3. **Excepciones**: - Lanza `NegocioException` si ocurre un error
     * al obtener la lista de productos.
     *
     * 4. **Funcionalidad**: - Se crea una instancia de `ProductoBO` utilizando
     * la fábrica `FabricaProductos`. - Se define un arreglo de `String` llamado
     * `columnas`, que contiene los nombres de las columnas que se mostrarán en
     * la tabla: "Nombre", "Precio", "Tipo" y "Disponible". - Se crea un modelo
     * de tabla (`DefaultTableModel`) utilizando las columnas definidas,
     * inicializándolo con 0 filas. Se sobrescribe el método `isCellEditable`
     * para hacer que las celdas de la tabla no sean editables. - Se obtiene la
     * lista de productos llamando al método `mostrarListaProductosTodosBO` del
     * objeto `productoBO`. - Se itera sobre cada producto en la lista: - Se
     * crea un arreglo de objetos (`Object[]`) que representa una fila de la
     * tabla con los datos del producto (nombre, precio, tipo y estado de
     * disponibilidad). - Se agrega la fila al modelo de la tabla. - Finalmente,
     * se establece el modelo de la tabla (`tblProductos`) con el modelo creado,
     * actualizando así la visualización de los productos.
     *
     * Este método es esencial para presentar de manera clara y organizada la
     * información de todos los productos en la interfaz de usuario, facilitando
     * la consulta de datos sobre los productos disponibles.
     *
     * @throws NegocioException
     */
    private void cargarProductosEnTablaTodos() throws NegocioException {

        ProductoBO productoBO = FabricaProductos.crearProductoBO();
        String[] columnas = {"Nombre", "Precio", "Tipo", "Disponible"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Producto> productos = productoBO.mostrarListaProductosTodosBO();

        for (Producto pro : productos) {
            Object[] fila = {
                pro.getNombre(),
                pro.getPrecio(),
                pro.getTipo(),
                pro.getEstado()
            };
            modeloTabla.addRow(fila);
        }

        tblProductos.setModel(modeloTabla);
    }

    /**
     * Este método se encarga de cargar todos los productos en la tabla,
     * actuando como un envoltorio para el método
     * `cargarProductosEnTablaTodos`.1.
     *
     * **Método**: `cargarProductosEnTablaTodosExterno()` 2. **Tipo de
     * retorno**: `void` 3. **Excepciones**: - Lanza `NegocioException` si
     * ocurre un error al cargar la lista de productos.
     *
     * 4. **Funcionalidad**: - Llama al método `cargarProductosEnTablaTodos()`,
     * que se encarga de obtener la lista de todos los productos y llenar la
     * tabla correspondiente con esta información.
     *
     * Este método es útil para proporcionar una interfaz más clara o para ser
     * llamado desde otros contextos donde se necesite recargar la tabla de
     * productos, asegurando que la tabla muestre siempre la información más
     * actualizada.
     *
     * @throws NegocioException.NegocioException
     */
    public void cargarProductosEnTablaTodosExterno() throws NegocioException {
        cargarProductosEnTablaTodos();
    }

    /**
     * Este método se encarga de llenar una tabla con una lista filtrada de
     * productos, mostrando su nombre, precio, tipo y estado de
     * disponibilidad.1.
     *
     * **Método**: `llenarTablaFiltrada(List<Producto> productos)` 2. **Tipo de
     * retorno**: `void` 3. **Parámetros**: - `List<Producto> productos`: Lista
     * de productos que se utilizará para llenar la tabla.
     *
     * 4. **Funcionalidad**: - Se define un arreglo de `String` llamado
     * `columnas`, que contiene los nombres de las columnas que se mostrarán en
     * la tabla: "Nombre", "Precio", "Tipo" y "Disponible". - Se crea un modelo
     * de tabla (`DefaultTableModel`) utilizando las columnas definidas,
     * inicializándolo con 0 filas. Se sobrescribe el método `isCellEditable`
     * para hacer que las celdas de la tabla no sean editables. - Se itera sobre
     * cada producto en la lista proporcionada: - Se crea un arreglo de objetos
     * (`Object[]`) que representa una fila de la tabla con los datos del
     * producto (nombre, precio, tipo y estado de disponibilidad). - Se agrega
     * la fila al modelo de la tabla. - Finalmente, se establece el modelo de la
     * tabla (`tblProductos`) con el modelo creado, actualizando así la
     * visualización de los productos filtrados.
     *
     * Este método es esencial para mostrar de manera clara y organizada una
     * lista específica de productos en la interfaz de usuario, permitiendo a
     * los usuarios ver solo aquellos productos que cumplen con ciertos
     * criterios de filtrado.
     *
     * @param productos
     */
    public void llenarTablaFiltrada(List<Producto> productos) {
        String[] columnas = {"Nombre", "Precio", "Tipo", "Disponible"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Producto pro : productos) {
            Object[] fila = {
                pro.getNombre(),
                pro.getPrecio(),
                pro.getTipo(),
                pro.getEstado()
            };
            modeloTabla.addRow(fila);
        }

        tblProductos.setModel(modeloTabla);
    }

    /**
     * 
     * @return 
     */
    public JTable getTblProductos() {
        return tblProductos;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JScrollPane = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        JScrollPane.setViewportView(tblProductos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JScrollPane)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPane;
    public javax.swing.JTable tblProductos;
    // End of variables declaration//GEN-END:variables
}
