/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ModuloComandas;

import BO.ComandasBO.ComandaBO;
import Entidades.Comandas.Comanda;
import Fabricas.FabricaComandas;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class FormTablaComandaCompleta extends javax.swing.JPanel {

    /**
     * Creates new form FormTablaComandaCompleta
     */
    public FormTablaComandaCompleta() {
        initComponents();
        llenarTablaComandas();
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
        tblComandas = new javax.swing.JTable();

        tblComandas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblComandas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    /*
Este método se encarga de llenar una tabla con la información de todas las comandas registradas en el sistema.

1. **Método**: `llenarTablaComandas()`
2. **Tipo de retorno**: `void`
3. **Funcionalidad**:
   - Se crea una instancia de `ComandaBO` utilizando la fábrica `FabricaComandas`.
   - Se obtiene una lista de todas las comandas llamando al método `mostrarComandasTodasBO` del objeto `comandasBO`.
   - Se define un arreglo de `String` llamado `columnas`, que contiene los nombres de las columnas que se mostrarán en la tabla: "Folio", "Mesa", "Estado", "Total", "Fecha" y "Cliente".
   - Se crea un modelo de tabla (`DefaultTableModel`) utilizando las columnas definidas, inicializándolo con 0 filas.
   - Se itera sobre cada comanda en la lista de comandas:
     - Se extraen los datos relevantes de cada comanda, como el folio, número de mesa, estado, total, fecha y nombre del cliente.
     - Se maneja la posibilidad de que algunos valores sean `null`, proporcionando valores predeterminados como "N/A" o "Sin Fecha" cuando sea necesario.
     - Se crea un arreglo de objetos (`Object[]`) que representa una fila de la tabla con los datos extraídos.
     - Se agrega la fila al modelo de la tabla.
   - Finalmente, se establece el modelo de la tabla (`tblComandas`) con el modelo creado, actualizando así la visualización de la tabla con la información de las comandas.

Este método es esencial para presentar de manera visual y organizada la información de las comandas en la interfaz de usuario, facilitando la consulta y gestión de datos.
*/
    
    public void llenarTablaComandas() {
        ComandaBO comandasBO = FabricaComandas.crearComandaBO();
        List<Comanda> comandas = comandasBO.mostrarComandasTodasBO();

        String[] columnas = {"Folio", "Mesa", "Estado", "Total", "Fecha", "Cliente"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        for (Comanda c : comandas) {
            Date fechaComanda = c.getFechaHora() != null ? c.getFechaHora().getTime() : null;
            
            String folio = c.getFolio();
            int numeroMesa = c.getMesa() != null ? c.getMesa().getNum_mesa() : -1;
            String estado = c.getEstado() != null ? c.getEstado().toString() : "N/A";
            double total = c.getTotal();
            String fecha = sdf.format(fechaComanda);

            String cliente = (c.getClienteFrecuente() != null)
                    ? c.getClienteFrecuente().getNombre()
                    : "Cliente General";

            Object[] fila = {folio, numeroMesa, estado, total, fecha, cliente};
            modelo.addRow(fila);
        }

        tblComandas.setModel(modelo);
    }

    public JTable getTblComandas() {
        return tblComandas;
    }
    
    
    
    /*
Este método se encarga de llenar una tabla con la información de las comandas registradas en el sistema que se encuentran dentro de un rango de fechas específico.

1. **Método**: `llenarTablaComandasConFiltro(Date fechaInicio, Date fechaFin)`
2. **Parámetros**: 
   - `fechaInicio`: Un objeto de tipo `Date` que representa la fecha de inicio del rango para filtrar las comandas.
   - `fechaFin`: Un objeto de tipo `Date` que representa la fecha de fin del rango para filtrar las comandas.
3. **Tipo de retorno**: `void`
4. **Funcionalidad**:
   - Se crea una instancia de `ComandaBO` utilizando la fábrica `FabricaComandas`.
   - Se obtiene una lista de todas las comandas llamando al método `mostrarComandasTodasBO` del objeto `comandasBO`.
   - Se define un arreglo de `String` llamado `columnas`, que contiene los nombres de las columnas que se mostrarán en la tabla: "Folio", "Mesa", "Estado", "Total", "Fecha" y "Cliente".
   - Se crea un modelo de tabla (`DefaultTableModel`) utilizando las columnas definidas, inicializándolo con 0 filas.
   - Se utiliza un objeto `SimpleDateFormat` para formatear las fechas en el formato "yyyy-MM-dd".
   - Se itera sobre cada comanda en la lista de comandas:
     - Se extrae la fecha de la comanda, manejando el caso en que pueda ser `null`.
     - Se verifica si la fecha de la comanda está dentro del rango especificado (entre `fechaInicio` y `fechaFin`).
     - Si la fecha está dentro del rango, se extraen los datos relevantes de la comanda, como el folio, número de mesa, estado, total y nombre del cliente.
     - Se crea un arreglo de objetos (`Object[]`) que representa una fila de la tabla con los datos extraídos.
     - Se agrega la fila al modelo de la tabla.
   - Finalmente, se establece el modelo de la tabla (`tblComandas`) con el modelo creado, actualizando así la visualización de la tabla con la información filtrada de las comandas.

Este método es esencial para presentar de manera visual y organizada la información de las comandas que cumplen con el criterio de fecha en la interfaz de usuario, facilitando la consulta y gestión de datos.
*/
    
    public void llenarTablaComandasConFiltro(Date fechaInicio, Date fechaFin) {
        ComandaBO comandasBO = FabricaComandas.crearComandaBO();
        List<Comanda> comandas = comandasBO.mostrarComandasTodasBO();

        String[] columnas = {"Folio", "Mesa", "Estado", "Total", "Fecha", "Cliente"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        for (Comanda c : comandas) {
            Date fechaComanda = c.getFechaHora() != null ? c.getFechaHora().getTime() : null;

            // Verificar si la fecha de la comanda está dentro del rango
            if (fechaComanda != null && !fechaComanda.before(fechaInicio) && !fechaComanda.after(fechaFin)) {
                String folio = c.getFolio();
                int numeroMesa = c.getMesa() != null ? c.getMesa().getNum_mesa() : -1;
                String estado = c.getEstado() != null ? c.getEstado().toString() : "N/A";
                double total = c.getTotal();
                String fecha = sdf.format(fechaComanda);

                String cliente = (c.getClienteFrecuente() != null)
                        ? c.getClienteFrecuente().getNombre()
                        : "Cliente General";

                Object[] fila = {folio, numeroMesa, estado, total, fecha, cliente};
                modelo.addRow(fila);
            }
        }

        tblComandas.setModel(modelo);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblComandas;
    // End of variables declaration//GEN-END:variables
}
