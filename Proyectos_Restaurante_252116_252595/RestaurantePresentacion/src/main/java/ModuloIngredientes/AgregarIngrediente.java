/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloIngredientes;

import BO.IngredienteBO.IngredienteBO;
import DTOS.Ingredientes.NuevoIngredienteDTO;
import Fabricas.FabricaIngredientes;
import NegocioException.NegocioException;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * Agregar Ingredientes
 * 
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class AgregarIngrediente extends javax.swing.JFrame {

    private FormIngredientesTabla formIngredientesTabla;
    
    /**
     * 
     * @param formIngredientesTabla 
     */
    public AgregarIngrediente(FormIngredientesTabla formIngredientesTabla) {
        initComponents();
        this.formIngredientesTabla = formIngredientesTabla;
        getContentPane().setBackground(new Color(0xb8893c));
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnCrearIngrediente = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        cmbUnidad_Medida = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Ingrendiente");

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel1.setText("Añadir Ingrediente");

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel3.setText("Unidad_Media:");

        jLabel4.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel4.setText("Stock:");

        btnCrearIngrediente.setBackground(new java.awt.Color(102, 255, 102));
        btnCrearIngrediente.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnCrearIngrediente.setText("Crear Ingrediente");
        btnCrearIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearIngredienteActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(255, 102, 102));
        btnSalir.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnSalir.setText("Cancelar");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        txtNombre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtStock.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        cmbUnidad_Medida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ML", "GR", "PZA" }));
        cmbUnidad_Medida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUnidad_MedidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(cmbUnidad_Medida, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNombre)
                            .addComponent(txtStock, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(btnSalir)))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCrearIngrediente)
                .addGap(110, 110, 110))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(98, 98, 98)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbUnidad_Medida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(btnCrearIngrediente)
                .addGap(32, 32, 32)
                .addComponent(btnSalir)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * 
     * @param evt 
     */
    private void cmbUnidad_MedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUnidad_MedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbUnidad_MedidaActionPerformed

    /**
     * 
     * @param evt 
     */
    private void btnCrearIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearIngredienteActionPerformed

        String nombre = txtNombre.getText().trim();
        String unidadMedida = (String) cmbUnidad_Medida.getSelectedItem();
        String stockTexto = txtStock.getText().trim();

        if (nombre.isEmpty() || unidadMedida == null || stockTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double stock;
        try {
            stock = Double.parseDouble(stockTexto);
            if (stock < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un stock válido (número no negativo).", "Valor inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas agregar el ingrediente '" + nombre + "' con " + stock + " " + unidadMedida + "?",
                "Confirmar creación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                NuevoIngredienteDTO nuevoIngredienteDTO = new NuevoIngredienteDTO();
                nuevoIngredienteDTO.setNombre(nombre);
                nuevoIngredienteDTO.setUnidad_medida(unidadMedida);
                nuevoIngredienteDTO.setStock(stock);

                IngredienteBO ingredienteBO = FabricaIngredientes.crearIngredienteBO();
                ingredienteBO.registrarIngredienteBO(nuevoIngredienteDTO);

                JOptionPane.showMessageDialog(this, "Ingrediente registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                formIngredientesTabla.recargarTabla();
                this.dispose();

            } catch (NegocioException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de negocio", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado al registrar el ingrediente.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else {

            JOptionPane.showMessageDialog(this, "Registro cancelado por el usuario.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnCrearIngredienteActionPerformed
    
    /**
     * 
     * @param evt 
     */
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearIngrediente;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cmbUnidad_Medida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
