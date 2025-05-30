/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloComandas.AñadirComanda;

import BO.ProductoBO.ProductoBO;
import Entidades.Productos.Producto;
import Fabricas.FabricaProductos;
import ModuloProductos.AñadirProducto.FormProductosTablaDisponibles;
import NegocioException.NegocioException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Seleccionador de productos nuevos
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class SeleccionarProductoNuevo extends javax.swing.JFrame {

    private final FormProductosTablaDisponibles formtablaProductos = new FormProductosTablaDisponibles();
    JTable tablaProductosClienteHastaElMomento;

    /**
     * Creates new form SeleccionarProductoNuevo
     *
     * @param tablaProductosClienteHastaElMomento
     * @throws NegocioException.NegocioException
     */
    public SeleccionarProductoNuevo(JTable tablaProductosClienteHastaElMomento) throws NegocioException {
        initComponents();
        pnlTabla.add(formtablaProductos);
        formtablaProductos.cargarProductosEnTablaDisponiblesExterno();
        formtablaProductos.setVisible(true);
        this.tablaProductosClienteHastaElMomento = tablaProductosClienteHastaElMomento;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTabla = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Seleccionar producto");

        pnlTabla.setLayout(new java.awt.BorderLayout());

        btnAgregar.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        btnAgregar.setText("Agregar Producto");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTabla, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAgregar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * 
     * @param evt 
     */
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            int filaSeleccionada = formtablaProductos.getTblProductos().getSelectedRow();

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Por favor selecciona un producto de la tabla.");
                return;
            }

            String nombreProducto = formtablaProductos.getTblProductos().getValueAt(filaSeleccionada, 0).toString();

            String inputCantidad = JOptionPane.showInputDialog(this,
                    "Selecciona la cantidad de \"" + nombreProducto + "\" que deseas agregar a la comanda:");

            if (inputCantidad == null || inputCantidad.trim().isEmpty()) {
                return;
            }

            int cantidad;
            try {
                cantidad = Integer.parseInt(inputCantidad.trim());
                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Cantidad inválida. Debe ser un número entero.");
                return;
            }

            ProductoBO productoBO = FabricaProductos.crearProductoBO();
            Producto producto = productoBO.buscarProductoPorNombreBO(nombreProducto);

            double precioUnitario = producto.getPrecio();
            double total = cantidad * precioUnitario;

            DefaultTableModel modelo = (DefaultTableModel) tablaProductosClienteHastaElMomento.getModel();

            Object[] nuevaFila = {
                nombreProducto,
                cantidad,
                precioUnitario,
                "",
                total
            };

            modelo.addRow(nuevaFila);

            this.dispose();
        } catch (NegocioException ex) {
            Logger.getLogger(SeleccionarProductoNuevo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed
    
    /**
     * 
     * @param evt 
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JPanel pnlTabla;
    // End of variables declaration//GEN-END:variables
}
