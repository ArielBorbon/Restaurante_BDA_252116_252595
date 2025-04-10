/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ModuloClientes;

import BO.ClienteBO.ClienteBO;
import DAO.Clientes.Encriptador;
import Entidades.Clientes.Cliente;
import Entidades.Clientes.ClientesFrecuentes;
import Fabricas.FabricaClientes;
import NegocioException.NegocioException;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class FormClientesTabla extends javax.swing.JPanel {

    public FormClientesTabla() throws NegocioException {
        initComponents();
        cargarClientesEnTabla();
    }

    public void cargarClientesEnTabla() throws NegocioException {
        ClienteBO clienteBO = FabricaClientes.crearClienteBO();
        String[] columnas = {
            "Nombre", "Correo", "Teléfono", "Fecha Registro", "Puntos", "Visitas", "Total Acumulado"
        };

        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Cliente> clientes = clienteBO.obtenerListaClientesBO();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        for (Cliente c : clientes) {

            String fechaRegistro = "";
            if (c.getFechaRegistro() != null) {
                fechaRegistro = sdf.format(c.getFechaRegistro().getTime()); // Formato: YYYY-MM-DD
            }

            String telefonoDesencriptado = "";
            try {
                telefonoDesencriptado = Encriptador.desencriptar(c.getNumTelefono());
            } catch (Exception e) {
                e.printStackTrace();  
            }

            Object[] fila = {
                c.getNombre(),
                c.getCorreo(),
                telefonoDesencriptado,
                fechaRegistro,
                (c instanceof ClientesFrecuentes) ? ((ClientesFrecuentes) c).getPuntos() : "N/A",
                (c instanceof ClientesFrecuentes) ? ((ClientesFrecuentes) c).getVisitas() : "N/A",
                (c instanceof ClientesFrecuentes) ? ((ClientesFrecuentes) c).getTotalGastado() : "N/A"
            };
            modeloTabla.addRow(fila);
        }
        jTable1.setModel(modeloTabla);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Correo", "Teléfono", "Fecha Registro", "Puntos", "Visitas", "Total Acumulado"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    public JTable getTablabaClientes() {
        return this.jTable1;
    }

    public void recargarTabla(List<Cliente> clientesFiltrados) {
        String[] columnas = {
            "Nombre", "Correo", "Teléfono", "Fecha Registro", "Puntos", "Visitas", "Total Acumulado"
        };

        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        for (Cliente c : clientesFiltrados) {

            String fechaRegistro = "";
            if (c.getFechaRegistro() != null) {
                fechaRegistro = sdf.format(c.getFechaRegistro().getTime()); // Formato: YYYY-MM-DD
            }
            String telefonoDesencriptado = "";
            try {
                telefonoDesencriptado = Encriptador.desencriptar(c.getNumTelefono());
            } catch (Exception e) {
                e.printStackTrace();  
            }

            Object[] fila = {
                c.getNombre(),
                c.getCorreo(),
                telefonoDesencriptado,
                fechaRegistro,
                (c instanceof ClientesFrecuentes) ? ((ClientesFrecuentes) c).getPuntos() : "N/A",
                (c instanceof ClientesFrecuentes) ? ((ClientesFrecuentes) c).getVisitas() : "N/A",
                (c instanceof ClientesFrecuentes) ? ((ClientesFrecuentes) c).getTotalGastado() : "N/A"
            };
            modeloTabla.addRow(fila);
        }

        jTable1.setModel(modeloTabla);
    }

    public void recargarTabla() throws NegocioException {
        cargarClientesEnTabla();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
