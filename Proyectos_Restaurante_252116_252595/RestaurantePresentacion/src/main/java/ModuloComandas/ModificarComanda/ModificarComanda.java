/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloComandas.ModificarComanda;

import ModuloComandas.AñadirComanda.*;
import BO.ComandasBO.ComandaBO;
import DTOS.Comandas.NuevaComandaDTO;
import DTOS.Comandas.NuevoDetalleComandaDTO;
import Entidades.Comandas.Comanda;
import Entidades.Comandas.DetalleComanda;
import Entidades.Comandas.EstadoComanda;
import Fabricas.FabricaComandas;
import ModuloComandas.FormTablaComandas;
import NegocioException.NegocioException;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Modificador de comandas
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class ModificarComanda extends javax.swing.JFrame {

    String folioComanda;
    FormTablaComandas formTablaComanda;

    /**
     * Creates new form AñadirComanda
     *
     * @param folioComanda
     * @param formTablaComanda
     */
    public ModificarComanda(String folioComanda, FormTablaComandas formTablaComanda) {
        initComponents();
        this.folioComanda = folioComanda;
        this.formTablaComanda = formTablaComanda;
        getContentPane().setBackground(new Color(0xe71d1d));

        cargarDatosComanda();
    }

    /**
     * Este método se encarga de cargar y mostrar los datos de una comanda
     * específica en la interfaz de usuario, utilizando el folio de la comanda.
     *
     * 1. **Método**: `cargarDatosComanda()` 2. **Tipo de retorno**: `void` 3.
     * **Funcionalidad**: - Se utiliza un bloque `try-catch` para manejar
     * posibles excepciones durante la carga de datos. - Se crea una instancia
     * de `ComandaBO` utilizando la fábrica `FabricaComandas`. - Se obtiene la
     * comanda correspondiente al `folioComanda` llamando al método
     * `obtenerComandaPorFolioBO` del objeto `comandaBO`. - Se verifica si la
     * comanda tiene un cliente frecuente asociado: - Si es así, se establece el
     * nombre del cliente en el campo de texto `txtClienteSeleccionadoNombre`. -
     * Si no, se muestra "Sin Cliente" en el mismo campo. - Se establece el
     * número de mesa en el campo de texto `txtNumMesa` utilizando el número de
     * la mesa asociada a la comanda. - Se obtiene la lista de detalles de la
     * comanda llamando al método `obtenerListaDetallesComandaBO` del objeto
     * `comandaBO`. - Se crea un modelo de tabla (`DefaultTableModel`) con las
     * columnas "Producto", "Unidades", "Precio Unitario", "Nota" y "Total". -
     * Se itera sobre cada detalle de la comanda: - Se crea un arreglo de
     * objetos (`Object[]`) que representa una fila de la tabla con los datos
     * del detalle. - Se agrega la fila al modelo de la tabla. - Finalmente, se
     * establece el modelo de la tabla (`tblProductosHastaElMomento`) con el
     * modelo creado, actualizando así la visualización de los productos de la
     * comanda.
     *
     * 4. **Manejo de excepciones**: - Si ocurre una `NegocioException`, se
     * muestra un mensaje de error al usuario mediante un `JOptionPane`, y se
     * imprime la traza de la excepción en la consola.
     *
     * Este método es esencial para presentar de manera clara y organizada la
     * información de una comanda específica en la interfaz de usuario,
     * facilitando la consulta de detalles de la comanda.
     */
    private void cargarDatosComanda() {
        try {
            ComandaBO comandaBO = FabricaComandas.crearComandaBO();
            Comanda comanda = comandaBO.obtenerComandaPorFolioBO(folioComanda);

            if (comanda.getClienteFrecuente() != null) {
                txtClienteSeleccionadoNombre.setText(comanda.getClienteFrecuente().toString());
            } else {
                txtClienteSeleccionadoNombre.setText("Sin Cliente");
            }

            txtNumMesa.setText(String.valueOf(comanda.getMesa().getNum_mesa()));

            List<DetalleComanda> detalles = comandaBO.obtenerListaDetallesComandaBO(comanda);

            DefaultTableModel modelo = new DefaultTableModel(new String[]{"Producto", "Unidades", "Precio Unitario", "Nota", "Total"}, 0);

            for (DetalleComanda detalle : detalles) {
                Object[] fila = {
                    detalle.getProducto().getNombre(),
                    detalle.getCantidad(),
                    detalle.getPrecioUnitario(),
                    detalle.getNotasEspeciales(),
                    detalle.getImporteTotal()
                };
                modelo.addRow(fila);
            }

            tblProductosHastaElMomento.setModel(modelo);

        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos de la comanda: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductosHastaElMomento = new javax.swing.JTable();
        btnAgregarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        txtClienteSeleccionadoNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnBuscadorClientes = new javax.swing.JButton();
        btnModificarComanda = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnEditarNota = new javax.swing.JButton();
        txtNumMesa = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Comanda");

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel1.setText("Ver detalles Comanda");

        tblProductosHastaElMomento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Productos", "Unidades", "Precio Unitario", "Nota", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProductosHastaElMomento);
        if (tblProductosHastaElMomento.getColumnModel().getColumnCount() > 0) {
            tblProductosHastaElMomento.getColumnModel().getColumn(0).setResizable(false);
            tblProductosHastaElMomento.getColumnModel().getColumn(1).setResizable(false);
            tblProductosHastaElMomento.getColumnModel().getColumn(2).setResizable(false);
            tblProductosHastaElMomento.getColumnModel().getColumn(3).setResizable(false);
            tblProductosHastaElMomento.getColumnModel().getColumn(4).setResizable(false);
        }

        btnAgregarProducto.setBackground(new java.awt.Color(204, 255, 204));
        btnAgregarProducto.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        btnAgregarProducto.setText("Agregar Producto");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        btnEliminarProducto.setBackground(new java.awt.Color(255, 102, 102));
        btnEliminarProducto.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        btnEliminarProducto.setText("Eliminar Producto");
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });

        txtClienteSeleccionadoNombre.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtClienteSeleccionadoNombre.setEnabled(false);
        txtClienteSeleccionadoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteSeleccionadoNombreActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel2.setText("Asignar A un cliente");

        btnBuscadorClientes.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        btnBuscadorClientes.setText("Buscador Clientes");
        btnBuscadorClientes.setEnabled(false);

        btnModificarComanda.setBackground(new java.awt.Color(204, 204, 204));
        btnModificarComanda.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        btnModificarComanda.setText("Modificar Comanda");
        btnModificarComanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarComandaActionPerformed(evt);
            }
        });

        btnAtras.setBackground(new java.awt.Color(255, 51, 51));
        btnAtras.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnAtras.setText("Atras");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel3.setText("(Puede Quedar En Blanco)");

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel4.setText("Agregar A Mesa");

        btnEditarNota.setBackground(new java.awt.Color(255, 255, 204));
        btnEditarNota.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnEditarNota.setText("Editar Nota");
        btnEditarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarNotaActionPerformed(evt);
            }
        });

        txtNumMesa.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNumMesa.setEnabled(false);
        txtNumMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumMesaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscadorClientes)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnEliminarProducto)
                                    .addComponent(btnAgregarProducto)
                                    .addComponent(txtClienteSeleccionadoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNumMesa))))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1)
                        .addGap(104, 104, 104)
                        .addComponent(btnEditarNota))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(211, 211, 211)
                        .addComponent(btnModificarComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(201, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btnEditarNota))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnAgregarProducto)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarProducto)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClienteSeleccionadoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscadorClientes)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtNumMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras)
                    .addComponent(btnModificarComanda))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * 
     * @param evt 
     */
    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        try {
            SeleccionarProductoNuevo seleccionarProducto = new SeleccionarProductoNuevo(tblProductosHastaElMomento);
            seleccionarProducto.setVisible(true);
        } catch (NegocioException ex) {
            Logger.getLogger(ModificarComanda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarProductoActionPerformed
    
    /**
     * 
     * @param evt 
     */
    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        int filaSeleccionada = tblProductosHastaElMomento.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un Producto para eliminar.");
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) tblProductosHastaElMomento.getModel();
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de eliminar este Producto?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            modelo.removeRow(filaSeleccionada);
        }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed
    
    /**
     * 
     * @param evt 
     */
    private void btnEditarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarNotaActionPerformed
        int filaSeleccionada = tblProductosHastaElMomento.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor selecciona una fila para editar la nota.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String notaActual = (String) tblProductosHastaElMomento.getValueAt(filaSeleccionada, 3);
        String nuevaNota = JOptionPane.showInputDialog(this, "Nota:", notaActual);

        if (nuevaNota != null) {
            tblProductosHastaElMomento.setValueAt(nuevaNota, filaSeleccionada, 3);
        }
    }//GEN-LAST:event_btnEditarNotaActionPerformed
    
    /**
     * 
     * @param evt 
     */
    private void btnModificarComandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarComandaActionPerformed
        try {
            int filas = tblProductosHastaElMomento.getRowCount();
            if (filas == 0) {
                JOptionPane.showMessageDialog(this, "No hay productos en la comanda para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String folio = folioComanda;
            if (folio == null || folio.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Folio inválido de la comanda.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<NuevoDetalleComandaDTO> detalles = new ArrayList<>();
            for (int i = 0; i < filas; i++) {
                NuevoDetalleComandaDTO detalle = new NuevoDetalleComandaDTO();
                detalle.setNombreProducto((String) tblProductosHastaElMomento.getValueAt(i, 0)); // Nombre
                detalle.setCantidad((int) tblProductosHastaElMomento.getValueAt(i, 1)); // Unidades
                detalle.setPrecioUnitario((double) tblProductosHastaElMomento.getValueAt(i, 2)); // Precio unitario
                detalle.setNotas_producto((String) tblProductosHastaElMomento.getValueAt(i, 3)); // Nota
                detalle.setImporteTotal((double) tblProductosHastaElMomento.getValueAt(i, 4)); // Total
                detalle.setFolioComanda(folio);
                detalles.add(detalle);
            }

            NuevaComandaDTO comandaDTO = new NuevaComandaDTO();
            comandaDTO.setFolio(folio);
            comandaDTO.setCorreoCliente(txtClienteSeleccionadoNombre.getText());
            comandaDTO.setNum_mesa(Integer.parseInt(txtNumMesa.getText()));
            comandaDTO.setEstado_comanda(EstadoComanda.ABIERTA);
            comandaDTO.setFecha_hora(Calendar.getInstance());

            ComandaBO comandasBO = FabricaComandas.crearComandaBO();
            comandasBO.modificarComandaBO(comandaDTO, detalles);

            JOptionPane.showMessageDialog(this, "Comanda modificada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            formTablaComanda.llenarTablaComandas();
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar la comanda: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnModificarComandaActionPerformed
    
    /**
     * 
     * @param evt 
     */
    private void txtClienteSeleccionadoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteSeleccionadoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteSeleccionadoNombreActionPerformed
    
    /**
     * 
     * @param evt 
     */
    private void txtNumMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumMesaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumMesaActionPerformed

    /**
     * 
     * @param evt 
     */
    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBuscadorClientes;
    private javax.swing.JButton btnEditarNota;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnModificarComanda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProductosHastaElMomento;
    private javax.swing.JTextField txtClienteSeleccionadoNombre;
    private javax.swing.JTextField txtNumMesa;
    // End of variables declaration//GEN-END:variables
}
