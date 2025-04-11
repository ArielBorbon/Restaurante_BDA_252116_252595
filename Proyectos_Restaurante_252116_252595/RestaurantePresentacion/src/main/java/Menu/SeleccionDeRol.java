/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Menu;

import BO.MesaBO.MesaBO;
import Fabricas.FabricaMesas;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class SeleccionDeRol extends javax.swing.JFrame {

    /**
     * Creates new form InicioSesion
     */
    public SeleccionDeRol() {
        initComponents();
        getContentPane().setBackground(new Color(0xF4FC89));
        btnInsertarMesas.setVisible(false);
        
        MesaBO mesaBO = FabricaMesas.crearMesaBO();
        if (!mesaBO.existenMesasBO()) {
            btnInsertarMesas.setVisible(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor. Hola
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAdministrador = new javax.swing.JButton();
        btnMesero = new javax.swing.JButton();
        btnInsertarMesas = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Selección Rol");

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        jLabel1.setText("RESTAURANTE DESHUESADERO");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/DeshuesaderoLogo.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel3.setText("Selecciona Tu ROL:");

        btnAdministrador.setBackground(new java.awt.Color(72, 170, 246));
        btnAdministrador.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnAdministrador.setText("Administrador");
        btnAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministradorActionPerformed(evt);
            }
        });

        btnMesero.setBackground(new java.awt.Color(255, 93, 97));
        btnMesero.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnMesero.setText("Mesero");
        btnMesero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMeseroActionPerformed(evt);
            }
        });

        btnInsertarMesas.setBackground(new java.awt.Color(153, 255, 153));
        btnInsertarMesas.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnInsertarMesas.setText("Insertar Mesas");
        btnInsertarMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarMesasActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(327, 327, 327)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(346, 346, 346)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(394, 394, 394)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMesero, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdministrador)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(203, 203, 203)
                        .addComponent(btnInsertarMesas, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(36, 36, 36)
                .addComponent(btnAdministrador)
                .addGap(61, 61, 61)
                .addComponent(btnMesero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnInsertarMesas)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(19, 19, 19))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministradorActionPerformed
        String rol = "Admin";
        MenuModulos menu = new MenuModulos(rol);
        menu.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnAdministradorActionPerformed

    private void btnMeseroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMeseroActionPerformed
       String rol = "Mesero";
       MenuModulos menu = new MenuModulos(rol);
       menu.setVisible(true);
       dispose();
    }//GEN-LAST:event_btnMeseroActionPerformed

    private void btnInsertarMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarMesasActionPerformed
        for (int i = 0; i < 20; i++) {
            MesaBO mesaBO = FabricaMesas.crearMesaBO();
            mesaBO.crearMesaEnOrdenBO();
        }
        btnInsertarMesas.setVisible(false);
        
        
         JOptionPane.showMessageDialog(null, "20 Mesas Añadidas Exitosamente","Éxito", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnInsertarMesasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdministrador;
    private javax.swing.JButton btnInsertarMesas;
    private javax.swing.JButton btnMesero;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
