/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ModuloProductos.AñadirProducto;

import BO.IngredienteBO.IngredienteBO;
import BO.ProductoBO.ProductoBO;
import Entidades.Ingredientes.Ingrediente;
import Entidades.Productos.Producto;
import Fabricas.FabricaIngredientes;
import Fabricas.FabricaProductos;
import NegocioException.NegocioException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Tabla de productos
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class FormProductosTablaDisponibles extends javax.swing.JPanel {

    /**
     * Creates new form FormProductosTabla
     *
     * @throws NegocioException.NegocioException
     */
    public FormProductosTablaDisponibles() throws NegocioException {
        initComponents();
        cargarProductosEnTablaDisponibles();
    }

    /**
     * Este método se encarga de cargar en una tabla la lista de productos
     * disponibles, mostrando su nombre, precio y tipo.
     *
     * 1. **Método**: `cargarProductosEnTablaDisponibles()` 2. **Tipo de
     * retorno**: `void` 3. **Excepciones**: - Lanza `NegocioException` si
     * ocurre un error al obtener la lista de productos.
     *
     * 4. **Funcionalidad**: - Se crea una instancia de `ProductoBO` utilizando
     * la fábrica `FabricaProductos`. - Se define un arreglo de `String` llamado
     * `columnas`, que contiene los nombres de las columnas que se mostrarán en
     * la tabla: "Nombre", "Precio" y "Tipo". - Se crea un modelo de tabla
     * (`DefaultTableModel`) utilizando las columnas definidas, inicializándolo
     * con 0 filas. Se sobrescribe el método `isCellEditable` para hacer que las
     * celdas de la tabla no sean editables. - Se obtiene la lista de productos
     * disponibles llamando al método `filtrarPorNombreProductoDisponiblesBO`
     * del objeto `productoBO`, pasando una cadena vacía como argumento para
     * obtener todos los productos disponibles. - Se itera sobre cada producto
     * en la lista: - Se crea un arreglo de objetos (`Object[]`) que representa
     * una fila de la tabla con los datos del producto (nombre, precio y tipo).
     * - Se agrega la fila al modelo de la tabla. - Finalmente, se establece el
     * modelo de la tabla (`tblProductos`) con el modelo creado, actualizando
     * así la visualización de los productos disponibles.
     *
     * Este método es esencial para presentar de manera clara y organizada la
     * información de los productos disponibles en la interfaz de usuario,
     * facilitando la consulta de datos sobre los productos que se pueden
     * ofrecer.
     *
     * @throws NegocioException
     */
    private void cargarProductosEnTablaDisponibles() throws NegocioException {

        ProductoBO productoBO = FabricaProductos.crearProductoBO();
        String[] columnas = {"Nombre", "Precio", "Tipo"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Producto> productos = productoBO.filtrarPorNombreProductoDisponiblesBO("");

        for (Producto pro : productos) {
            Object[] fila = {
                pro.getNombre(),
                pro.getPrecio(),
                pro.getTipo()
            };
            modeloTabla.addRow(fila);
        }

        tblProductos.setModel(modeloTabla);
    }

    /**
     * Este método se encarga de cargar la lista de productos disponibles en la
     * tabla, actuando como un envoltorio para el método
     * `cargarProductosEnTablaDisponibles`.1.
     *
     * **Método**: `cargarProductosEnTablaDisponiblesExterno()` 2. **Tipo de
     * retorno**: `void` 3. **Excepciones**: - Lanza `NegocioException` si
     * ocurre un error al cargar la lista de productos disponibles.
     *
     * 4. **Funcionalidad**: - Llama al método
     * `cargarProductosEnTablaDisponibles()`, que se encarga de obtener la lista
     * de productos disponibles y llenar la tabla correspondiente con esta
     * información.
     *
     * Este método es útil para proporcionar una interfaz más clara o para ser
     * llamado desde otros contextos donde se necesite recargar la tabla de
     * productos disponibles, asegurando que la tabla muestre siempre la
     * información más actualizada.
     *
     * @throws NegocioException.NegocioException
     */
    public void cargarProductosEnTablaDisponiblesExterno() throws NegocioException {
        cargarProductosEnTablaDisponibles();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Precio", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProductos);
        if (tblProductos.getColumnModel().getColumnCount() > 0) {
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(120);
            tblProductos.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProductos;
    // End of variables declaration//GEN-END:variables
}
