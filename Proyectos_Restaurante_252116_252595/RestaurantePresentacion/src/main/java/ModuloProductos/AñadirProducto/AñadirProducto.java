/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloProductos.AñadirProducto;

import BO.ProductoBO.ProductoBO;
import DTOS.Productos.NuevoProductoDTO;
import DTOS.Productos.NuevoProductoOcupaIngredienteDTO;
import Entidades.Productos.Estado_Producto;
import Entidades.Productos.Tipo_Producto;
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
 *
 * @author PC Gamer
 */
public class AñadirProducto extends javax.swing.JFrame {
        private FormProductosTablaTodos formProductosTablaTodos;
    /**
     * Creates new form AñadirProducto
     */
    public AñadirProducto(FormProductosTablaTodos formProductosTablaTodos) {
        initComponents();
        this.formProductosTablaTodos = formProductosTablaTodos;
        getContentPane().setBackground(new Color(0x78f332));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnModificarCantidad = new javax.swing.JButton();
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
        btnCrearProducto = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnModificarCantidad.setBackground(new java.awt.Color(255, 255, 153));
        btnModificarCantidad.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnModificarCantidad.setText("Modificar Cantidad");
        btnModificarCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCantidadActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel1.setText("Añadir Producto");

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

        btnEliminarIngrediente.setBackground(new java.awt.Color(255, 153, 153));
        btnEliminarIngrediente.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnEliminarIngrediente.setText("Eliminar Ingrediente");
        btnEliminarIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarIngredienteActionPerformed(evt);
            }
        });

        txtNombre.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
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

        btnCrearProducto.setBackground(new java.awt.Color(153, 255, 153));
        btnCrearProducto.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        btnCrearProducto.setText("Crear Producto");
        btnCrearProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearProductoActionPerformed(evt);
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
                                .addGap(64, 64, 64)
                                .addComponent(btnCrearProducto))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(btnCancelar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
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
                        .addComponent(btnCrearProducto)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(70, 70, 70))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
           
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnAgregarIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarIngredienteActionPerformed
            try {
                AgregarIngredienteAListaProductoNuevo formAgregarIng = new AgregarIngredienteAListaProductoNuevo(tblTablaIngredientesHastaElMomento);
                formAgregarIng.setVisible(true);
            } catch (NegocioException ex) {
                Logger.getLogger(AñadirProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btnAgregarIngredienteActionPerformed

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

    private void btnCrearProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearProductoActionPerformed
try {
        String nombre = txtNombre.getText().trim();
        String precioTexto = txtPrecio.getText().trim();
        String tipo = cmbTipo.getSelectedItem().toString();

        if (nombre.isBlank() || precioTexto.isBlank()) {
            JOptionPane.showMessageDialog(this, "Nombre y precio no pueden estar vacíos.");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.");
            return;
        }

        NuevoProductoDTO productoDTO = new NuevoProductoDTO();
        productoDTO.setNombre(nombre);
        productoDTO.setPrecio(precio);
        productoDTO.setTipo(Tipo_Producto.valueOf(tipo.toUpperCase())); 
        productoDTO.setEstado(Estado_Producto.HABILITADO);


        List<NuevoProductoOcupaIngredienteDTO> ingredientesDTO = new ArrayList<>();
        DefaultTableModel modelo = (DefaultTableModel) tblTablaIngredientesHastaElMomento.getModel();

        if (modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Debes agregar al menos un ingrediente.");
            return;
        }

        for (int i = 0; i < modelo.getRowCount(); i++) {
            String nombreIng = modelo.getValueAt(i, 0).toString();
            String unidad = modelo.getValueAt(i, 1).toString();
            double cantidad = Double.parseDouble(modelo.getValueAt(i, 2).toString());

            NuevoProductoOcupaIngredienteDTO dto = new NuevoProductoOcupaIngredienteDTO();
            dto.setNombreIngrediente(nombreIng);
            dto.setUnidadMedida(unidad);
            dto.setCantidadNecesariaProducto(cantidad);

            ingredientesDTO.add(dto);
        }


        ProductoBO productoBO = FabricaProductos.crearProductoBO();
        productoBO.registrarProductoConIngredientesBO(productoDTO, ingredientesDTO);

        JOptionPane.showMessageDialog(this, "Producto agregado correctamente.");
        formProductosTablaTodos.cargarProductosEnTablaTodosExterno(); 
        this.dispose(); 

    } catch (NegocioException ex) {
        JOptionPane.showMessageDialog(this, "Error al agregar producto: " + ex.getMessage());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + e.getMessage());
    }       
    }//GEN-LAST:event_btnCrearProductoActionPerformed

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            return;
        }

        try {
            double nuevaCantidad = Double.parseDouble(nuevaCantidadStr);
            if (nuevaCantidad < 0) {
                JOptionPane.showMessageDialog(this, "La cantidad no puede ser negativa.", "Cantidad inválida", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (nuevaCantidad == 0) {
                JOptionPane.showMessageDialog(this, "Agrega al menos 1 del ingrediente" , "Cantidad invalida" , JOptionPane.ERROR_MESSAGE);
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
    private javax.swing.JButton btnCrearProducto;
    private javax.swing.JButton btnEliminarIngrediente;
    private javax.swing.JButton btnModificarCantidad;
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
