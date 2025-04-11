/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloProductos.ModificarProducto;

import BO.IngredienteBO.IngredienteBO;
import ModuloProductos.AñadirProducto.*;
import BO.ProductoBO.ProductoBO;
import DTOS.Ingredientes.IngredienteConCantidadNecesariaDTO;
import DTOS.Productos.NuevoProductoDTO;
import DTOS.Productos.NuevoProductoOcupaIngredienteDTO;
import Entidades.Ingredientes.Ingrediente;
import Entidades.Productos.Estado_Producto;
import Entidades.Productos.Producto;
import Entidades.Productos.Tipo_Producto;
import Fabricas.FabricaIngredientes;
import Fabricas.FabricaProductos;
import ModuloProductos.FormProductosTablaTodos;
import NegocioException.NegocioException;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Menu modificador de productos
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class ModificarProducto extends javax.swing.JFrame {

    private FormProductosTablaTodos formProductosTablaTodos;
    private Producto productoOriginal;

    /**
     * Creates new form AñadirProducto
     *
     * @param formTabla
     * @param producto
     * @throws NegocioException.NegocioException
     */
    public ModificarProducto(FormProductosTablaTodos formTabla, Producto producto) throws NegocioException {
        initComponents();
        this.formProductosTablaTodos = formTabla;
        this.productoOriginal = producto;
        cargarDatosProducto();
        getContentPane().setBackground(new Color(0x78f332));
    }

    /**
     * 
     * @throws NegocioException 
     */
    private void cargarDatosProducto() throws NegocioException {
        txtNombre.setText(productoOriginal.getNombre());
        txtPrecio.setText(String.valueOf(productoOriginal.getPrecio()));
        cmbTipo.setSelectedItem(productoOriginal.getTipo().toString());

        IngredienteBO ingBO = FabricaIngredientes.crearIngredienteBO();

        //esta lista tiene la cantidad que ocupa, la cual necesitamos colocar en la tercera columna
        // en lugar de traer la lista de Ingrediente...
        // List<Ingrediente> ingredientes = ingBO.obtenerIngredientesPorNombreProductoBO(...);
        List<IngredienteConCantidadNecesariaDTO> listaProductoOcupa
                = ingBO.obtenerIngredientesConCantidadPorProductoBO(productoOriginal.getNombre());

        DefaultTableModel modelo = (DefaultTableModel) tblTablaIngredientesHastaElMomento.getModel();
        modelo.setRowCount(0);

        for (IngredienteConCantidadNecesariaDTO dto : listaProductoOcupa) {
            Object[] fila = {
                dto.getNombreIngrediente(),
                dto.getUnidadMedida(),
                dto.getCantidadIngredienteNecesaria()
            };
            modelo.addRow(fila);
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
        tblTablaIngredientesHastaElMomento = new javax.swing.JTable();
        btnAgregarIngrediente = new javax.swing.JButton();
        btnEliminarIngrediente = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        cmbTipo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnModificarProducto = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnModificarCantidad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Producto");

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel1.setText("Modificar Producto");

        tblTablaIngredientesHastaElMomento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Unidad de medida", "Cantidad Necesaria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblTablaIngredientesHastaElMomento);
        if (tblTablaIngredientesHastaElMomento.getColumnModel().getColumnCount() > 0) {
            tblTablaIngredientesHastaElMomento.getColumnModel().getColumn(0).setPreferredWidth(100);
        }

        btnAgregarIngrediente.setBackground(new java.awt.Color(204, 255, 204));
        btnAgregarIngrediente.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnAgregarIngrediente.setText("Agregar Ingrediente");
        btnAgregarIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarIngredienteActionPerformed(evt);
            }
        });

        btnEliminarIngrediente.setBackground(new java.awt.Color(255, 204, 204));
        btnEliminarIngrediente.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnEliminarIngrediente.setText("Eliminar Ingrediente");
        btnEliminarIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarIngredienteActionPerformed(evt);
            }
        });

        txtNombre.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtNombre.setEnabled(false);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtPrecio.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        cmbTipo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PLATO_FUERTE", "ENTRADA", "POSTRE", "BEBIDA", "SNACK" }));
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel3.setText("Tipo de Producto:");

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel4.setText("Precio:");

        btnModificarProducto.setBackground(new java.awt.Color(102, 255, 102));
        btnModificarProducto.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        btnModificarProducto.setText("Crear Modificacion");
        btnModificarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProductoActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 102, 102));
        btnCancelar.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnModificarCantidad.setBackground(new java.awt.Color(255, 255, 153));
        btnModificarCantidad.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnModificarCantidad.setText("Modificar Cantidad");
        btnModificarCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCantidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(txtNombre)
                                .addComponent(cmbTipo, 0, 275, Short.MAX_VALUE)
                                .addComponent(txtPrecio))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(btnCancelar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(btnModificarProducto)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAgregarIngrediente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEliminarIngrediente))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificarCantidad)))
                .addGap(58, 58, 58))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModificarCantidad)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminarIngrediente)
                            .addComponent(btnAgregarIngrediente))
                        .addContainerGap(79, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificarProducto)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(70, 70, 70))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     * @param evt 
     */
    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed

    }//GEN-LAST:event_txtNombreActionPerformed

    /**
     * 
     * @param evt 
     */
    private void btnAgregarIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarIngredienteActionPerformed
        try {
            AgregarIngredienteAListaProductoNuevo formAgregarIng = new AgregarIngredienteAListaProductoNuevo(tblTablaIngredientesHastaElMomento);
            formAgregarIng.setVisible(true);
        } catch (NegocioException ex) {
            Logger.getLogger(ModificarProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarIngredienteActionPerformed

    /**
     * 
     * @param evt 
     */
    private void btnEliminarIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarIngredienteActionPerformed
        int filaSeleccionada = tblTablaIngredientesHastaElMomento.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un ingrediente para eliminar.");
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) tblTablaIngredientesHastaElMomento.getModel();
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de eliminar este ingrediente?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            modelo.removeRow(filaSeleccionada);
        }
    }//GEN-LAST:event_btnEliminarIngredienteActionPerformed

    /**
     * 
     * @param evt 
     */
    private void btnModificarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProductoActionPerformed
        try {
            String nombreNuevo = txtNombre.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            Tipo_Producto tipo = Tipo_Producto.valueOf((String) cmbTipo.getSelectedItem());

            ProductoBO productoBO = FabricaProductos.crearProductoBO();

            String nuevoNombre = txtNombre.getText().trim();
            if (!nuevoNombre.equals(productoOriginal.getNombre())) {
                Producto productoExistente = productoBO.buscarProductoPorNombreBO(nuevoNombre);
                if (productoExistente != null) {
                    JOptionPane.showMessageDialog(this, "Ya existe un producto con ese nombre");
                    return;
                }
            }

            DefaultTableModel modelo = (DefaultTableModel) tblTablaIngredientesHastaElMomento.getModel();
            List<NuevoProductoOcupaIngredienteDTO> listaIngredientes = new ArrayList<>();
            String nombreProductoNuevo = txtNombre.getText();
            for (int i = 0; i < modelo.getRowCount(); i++) {
                String nombreIng = modelo.getValueAt(i, 0).toString();
                String unidad = modelo.getValueAt(i, 1).toString();
                double cantidad = Double.parseDouble(modelo.getValueAt(i, 2).toString());

                NuevoProductoOcupaIngredienteDTO chilo = new NuevoProductoOcupaIngredienteDTO();
                chilo.setNombreIngrediente(nombreIng);
                chilo.setCantidadNecesariaProducto(cantidad);
                chilo.setUnidadMedida(unidad);
                chilo.setNombreProducto(nombreProductoNuevo);

                listaIngredientes.add(chilo);
            }

            NuevoProductoDTO nuevoProductoDTO = new NuevoProductoDTO();
            nuevoProductoDTO.setNombre(nombreNuevo);
            nuevoProductoDTO.setPrecio(precio);
            nuevoProductoDTO.setTipo(tipo);
            nuevoProductoDTO.setEstado(Estado_Producto.HABILITADO);

            productoBO.modificarProductoBO(nuevoProductoDTO, listaIngredientes);
            JOptionPane.showMessageDialog(this, "Producto modificado correctamente.");

            formProductosTablaTodos.cargarProductosEnTablaTodosExterno();
            this.dispose();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnModificarProductoActionPerformed

    /**
     * 
     * @param evt 
     */
    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoActionPerformed

    /**
     * 
     * @param evt 
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * 
     * @param evt 
     */
    private void btnModificarCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCantidadActionPerformed
        int filaSeleccionada = tblTablaIngredientesHastaElMomento.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una fila primero.", "Sin selección", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombreIngrediente = (String) tblTablaIngredientesHastaElMomento.getValueAt(filaSeleccionada, 0);
        String unidad = (String) tblTablaIngredientesHastaElMomento.getValueAt(filaSeleccionada, 1);

        String nuevaCantidadStr = JOptionPane.showInputDialog(this, "Ingresa la nueva cantidad para " + nombreIngrediente + " (" + unidad + "):");

        if (nuevaCantidadStr == null) {
            // Cancelado
            return;
        }

        try {
            double nuevaCantidad = Double.parseDouble(nuevaCantidadStr);
            if (nuevaCantidad < 0) {
                JOptionPane.showMessageDialog(this, "La cantidad no puede ser negativa.", "Cantidad inválida", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (nuevaCantidad == 0) {
                JOptionPane.showMessageDialog(this, "Agrega al menos 1 del ingrediente", "Cantidad invalida", JOptionPane.ERROR_MESSAGE);
                return;
            }

            tblTablaIngredientesHastaElMomento.setValueAt(nuevaCantidad, filaSeleccionada, 2);
            JOptionPane.showMessageDialog(this, "Cantidad necesaria cambiada exitosamente.", "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingresa un número válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarCantidadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarIngrediente;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarIngrediente;
    private javax.swing.JButton btnModificarCantidad;
    private javax.swing.JButton btnModificarProducto;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTablaIngredientesHastaElMomento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
