/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloComandas.AñadirComanda;

import BO.ClienteBO.ClienteBO;
import BO.ComandasBO.ComandaBO;
import BO.MesaBO.MesaBO;
import DTOS.Comandas.NuevaComandaDTO;
import DTOS.Comandas.NuevoDetalleComandaDTO;
import DTOS.Mesa.NuevaMesaDTO;
import Entidades.Clientes.Cliente;
import Entidades.Comandas.EstadoComanda;
import Entidades.Mesa.Mesa;
import Fabricas.FabricaClientes;
import Fabricas.FabricaComandas;
import Fabricas.FabricaMesas;
import ModuloComandas.FormTablaComandas;
import NegocioException.NegocioException;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Añadir Comandas
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class AñadirComanda extends javax.swing.JFrame {

    private FormTablaComandas formComandasAbiertas;
    private String nombreCliente;

    /**
     * Creates new form AñadirComanda
     *
     * @param formTablaComandas
     */
    public AñadirComanda(FormTablaComandas formTablaComandas) {
        initComponents();
        llenarComboBoxMesasDisponibles();
        getContentPane().setBackground(new Color(0xe71d1d));
        this.formComandasAbiertas = formTablaComandas;
    }

    /**
     * Este método se encarga de llenar un combo box con la lista de mesas
     * disponibles en el sistema.
     *
     * 1. **Método**: `llenarComboBoxMesasDisponibles()` 2. **Tipo de retorno**:
     * `void` 3. **Funcionalidad**: - Se elimina cualquier elemento existente en
     * el combo box `cmbAgregarAMesa` utilizando `removeAllItems()`. - Se crea
     * una instancia de `MesaBO` utilizando la fábrica `FabricaMesas`. - Se
     * obtiene una lista de mesas disponibles llamando al método
     * `obtenerListaMesasDisponiblesBO` del objeto `mesasBO`. - Se itera sobre
     * cada mesa en la lista de mesas disponibles: - Se agrega el número de la
     * mesa al combo box `cmbAgregarAMesa` utilizando `addItem`, convirtiendo el
     * número de la mesa a `String`.
     *
     * Este método es esencial para proporcionar al usuario una lista
     * actualizada de mesas disponibles, permitiendo seleccionar una mesa al
     * agregar una nueva comanda.
     */
    private void llenarComboBoxMesasDisponibles() {
        cmbAgregarAMesa.removeAllItems();
        MesaBO mesasBO = FabricaMesas.crearMesaBO();
        List<Mesa> mesasDisponibles = mesasBO.obtenerListaMesasDisponiblesBO();

        for (Mesa mesa : mesasDisponibles) {
            cmbAgregarAMesa.addItem(String.valueOf(mesa.getNum_mesa()));
        }
    }

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
        cmbAgregarAMesa = new javax.swing.JComboBox<>();
        btnCrearComanda = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnEditarNota = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Comanda");

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel1.setText("Añadir Comandas");

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

        btnBuscadorClientes.setBackground(new java.awt.Color(204, 255, 255));
        btnBuscadorClientes.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        btnBuscadorClientes.setText("Buscador Clientes");
        btnBuscadorClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscadorClientesActionPerformed(evt);
            }
        });

        cmbAgregarAMesa.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cmbAgregarAMesa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbAgregarAMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAgregarAMesaActionPerformed(evt);
            }
        });

        btnCrearComanda.setBackground(new java.awt.Color(255, 153, 102));
        btnCrearComanda.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        btnCrearComanda.setText("Crear Comanda");
        btnCrearComanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearComandaActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(104, 104, 104)
                .addComponent(btnEditarNota)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCrearComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscadorClientes)))
                        .addContainerGap(12, Short.MAX_VALUE))
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
                                    .addComponent(cmbAgregarAMesa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                        .addComponent(cmbAgregarAMesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCrearComanda)
                    .addComponent(btnAtras))
                .addGap(17, 17, 17))
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
            Logger.getLogger(AñadirComanda.class.getName()).log(Level.SEVERE, null, ex);
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
    private void btnCrearComandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearComandaActionPerformed
        try {
            int filas = tblProductosHastaElMomento.getRowCount();
            if (filas == 0) {
                JOptionPane.showMessageDialog(this, "Agrega al menos un producto antes de crear la comanda.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String mesaSeleccionada = (String) cmbAgregarAMesa.getSelectedItem();
            if (mesaSeleccionada == null || mesaSeleccionada.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Selecciona una mesa para la comanda.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String clienteNombre = txtClienteSeleccionadoNombre.getText().trim();
            if (clienteNombre.isEmpty()) {
                clienteNombre = null;
            }

            ClienteBO clienteBO = FabricaClientes.crearClienteBO();
            List<Cliente> clienteLoco = clienteBO.filtrarPorNombre(clienteNombre);
            Cliente clienteLoco2 = new Cliente();

            if (!clienteLoco.isEmpty()) {
                clienteLoco2 = clienteLoco.get(0);
                clienteNombre = clienteLoco2.getCorreo();
            }

            ComandaBO comandasBO = FabricaComandas.crearComandaBO();
            String folio = comandasBO.generarFolioComandaBO();

            int numeroMesa = Integer.parseInt(mesaSeleccionada);

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

            boolean hayStock = comandasBO.verificarStockNecesarioProductosBO(detalles);
            if (!hayStock) {
                JOptionPane.showMessageDialog(this, "No hay stock suficiente para los productos seleccionados.", "Stock insuficiente", JOptionPane.WARNING_MESSAGE);
                return;
            }

            MesaBO mesaBO = FabricaMesas.crearMesaBO();
            NuevaMesaDTO nuevaMesaDTO = new NuevaMesaDTO();
            Mesa mesa = mesaBO.obtenerMesaPorNumMesaBO(numeroMesa);
            nuevaMesaDTO.setNum_mesa(numeroMesa);
            nuevaMesaDTO.setEstado(mesa.getEstado());
            mesaBO.ocuparMesaBO(nuevaMesaDTO);

            Calendar fecha_hora = Calendar.getInstance();
            // Creamos la comanda
            NuevaComandaDTO nuevaComanda = new NuevaComandaDTO();
            nuevaComanda.setFolio(folio);
            nuevaComanda.setFecha_hora(fecha_hora);
            nuevaComanda.setEstado_comanda(EstadoComanda.ABIERTA);
            nuevaComanda.setCorreoCliente(clienteNombre);
            nuevaComanda.setNum_mesa(numeroMesa);

            comandasBO.registrarComandaBO(nuevaComanda, detalles);
            //  comandasBO.restarStockIngredientesPorProductosComandaBO(detalles);

            JOptionPane.showMessageDialog(this, "¡Comanda creada exitosamente con folio: " + folio + "!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            formComandasAbiertas.llenarTablaComandas();
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error al crear la comanda: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (NegocioException ex) {
            Logger.getLogger(AñadirComanda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCrearComandaActionPerformed
    
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
    private void cmbAgregarAMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAgregarAMesaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAgregarAMesaActionPerformed
    
    /**
     * 
     * @param evt 
     */
    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private Long clienteIdSeleccionado = null;

    /**
     * 
     * @param evt 
     */
    private void btnBuscadorClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscadorClientesActionPerformed

        try {
            AñadirClienteComanda añadirClienteComanda = new AñadirClienteComanda(this);
            añadirClienteComanda.setVisible(true);
        } catch (NegocioException ex) {
            Logger.getLogger(AñadirComanda.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnBuscadorClientesActionPerformed
    
    /**
     * 
     * @return 
     */
    public String getNombreCliente() {
        return nombreCliente;
    }
    
    /**
     * 
     * @param nombreCliente 
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    /**
     * 
     * @return 
     */
    public JTextField getTxtClienteSeleccionadoNombre() {
        return txtClienteSeleccionadoNombre;
    }
    
    /**
     * 
     * @param txtClienteSeleccionadoNombre 
     */
    public void setTxtClienteSeleccionadoNombre(JTextField txtClienteSeleccionadoNombre) {
        this.txtClienteSeleccionadoNombre = txtClienteSeleccionadoNombre;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBuscadorClientes;
    private javax.swing.JButton btnCrearComanda;
    private javax.swing.JButton btnEditarNota;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JComboBox<String> cmbAgregarAMesa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProductosHastaElMomento;
    private javax.swing.JTextField txtClienteSeleccionadoNombre;
    // End of variables declaration//GEN-END:variables
}
