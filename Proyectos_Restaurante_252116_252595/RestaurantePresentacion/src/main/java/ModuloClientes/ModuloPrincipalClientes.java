/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloClientes;

import BO.ClienteBO.ClienteBO;
import DAO.Clientes.Encriptador;
import Entidades.Clientes.Cliente;
import Fabricas.FabricaClientes;
import Menu.MenuModulos;
import NegocioException.NegocioException;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Menu principal del modulo Clientes
 * 
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class ModuloPrincipalClientes extends javax.swing.JFrame {

    private final FormClientesTabla formClientesTabla = new FormClientesTabla();

    private String rol;

    public ModuloPrincipalClientes(String rol) throws NegocioException {
        initComponents();
        formClientesTabla.setVisible(true);
        this.pnlFormClientes.add(formClientesTabla);
        getContentPane().setBackground(new Color(0x4dd3e0));
        this.rol = rol;

    }

    public ModuloPrincipalClientes() throws NegocioException {
        initComponents();
        formClientesTabla.setVisible(true);
        this.pnlFormClientes.add(formClientesTabla);
        getContentPane().setBackground(new Color(0x4dd3e0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        filtroNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        filtroTelefono = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        filtroCorreo = new javax.swing.JTextField();
        btnFiltro = new javax.swing.JButton();
        btnAgregarCliente = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();
        pnlFormClientes = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menú  Cliente");

        jLabel1.setText("Modulo Cliente");
        jLabel1.setFont(new Font("Arial", Font.BOLD, 32));

        jLabel2.setText("Nombre");
        jLabel2.setFont(new Font("Arial", Font.PLAIN, 14));

        jLabel3.setText("Teléfono");
        jLabel3.setFont(new Font("Arial", Font.PLAIN, 14));

        jLabel4.setText("Correo");
        jLabel4.setFont(new Font("Arial", Font.PLAIN, 14));

        btnFiltro.setText("Filtrar");
        btnFiltro.setBackground(new Color(162, 132, 94));
        btnFiltro.setForeground(Color.WHITE);
        btnFiltro.setFont(new Font("Arial", Font.BOLD, 16));
        btnFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltroActionPerformed(evt);
            }
        });

        btnAgregarCliente.setText("Agregar Cliente");
        btnAgregarCliente.setBackground(new Color(52, 199, 89));
        btnAgregarCliente.setForeground(Color.WHITE);
        btnAgregarCliente.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarClienteActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.setBackground(new Color(236, 34, 31));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(new Font("Arial", Font.BOLD, 16));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnReporte.setText("REPORTE CLIENTES FRECUENTES");
        btnReporte.setBackground(new Color(50, 173, 230));
        btnReporte.setForeground(Color.WHITE);
        btnReporte.setFont(new Font("Arial", Font.BOLD, 16));
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        pnlFormClientes.setPreferredSize(new java.awt.Dimension(638, 521));
        pnlFormClientes.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(filtroNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addGap(60, 60, 60))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlFormClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filtroCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filtroTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addGap(52, 52, 52))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filtroTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filtroNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filtroCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFiltro)
                        .addGap(61, 61, 61)
                        .addComponent(btnAgregarCliente)
                        .addGap(28, 28, 28)
                        .addComponent(btnSalir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlFormClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)))
                .addComponent(btnReporte)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClienteActionPerformed
        ClienteAgregar clienteAgregar = new ClienteAgregar(formClientesTabla);
        clienteAgregar.setVisible(true);
    }//GEN-LAST:event_btnAgregarClienteActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        MenuModulos menu = new MenuModulos(rol);
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroActionPerformed
        try {
            ClienteBO clienteBO = FabricaClientes.crearClienteBO();

            String nombreFiltro = filtroNombre.getText();
            String telefonoFiltro = filtroTelefono.getText();
            String correoFiltro = filtroCorreo.getText();

            if (nombreFiltro.isEmpty() && telefonoFiltro.isEmpty() && correoFiltro.isEmpty()) {
                formClientesTabla.recargarTabla();
            }

            List<Cliente> clientesFiltrados = clienteBO.filtrarClientes(
                    nombreFiltro.isEmpty() ? null : nombreFiltro,
                    correoFiltro.isEmpty() ? null : correoFiltro);

            List<Cliente> clientesFiltradosPorTelefono = new ArrayList<>();

            for (Cliente cliente : clientesFiltrados) {
                String telefonoDesencriptado = "";
                try {
                    telefonoDesencriptado = Encriptador.desencriptar(cliente.getNumTelefono());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (telefonoFiltro.isEmpty() || telefonoDesencriptado.contains(telefonoFiltro)) {
                    clientesFiltradosPorTelefono.add(cliente);
                }
            }

            formClientesTabla.recargarTabla(clientesFiltradosPorTelefono);

        } catch (NegocioException ex) {
            Logger.getLogger(ModuloPrincipalClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ModuloPrincipalClientes.class.getName()).log(Level.SEVERE, "Error al desencriptar teléfono", ex);
        }
    }//GEN-LAST:event_btnFiltroActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        ReporteClientes reporteClientes;
        try {
            reporteClientes = new ReporteClientes();
            reporteClientes.setVisible(true);
        } catch (NegocioException ex) {
            Logger.getLogger(ModuloPrincipalClientes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnReporteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCliente;
    private javax.swing.JButton btnFiltro;
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton btnSalir;
    private javax.swing.JTextField filtroCorreo;
    private javax.swing.JTextField filtroNombre;
    private javax.swing.JTextField filtroTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel pnlFormClientes;
    // End of variables declaration//GEN-END:variables
}
