/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloClientes;

import BO.ClienteBO.ClienteBO;
import Entidades.Clientes.Cliente;
import Fabricas.FabricaClientes;
import NegocioException.NegocioException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alberto Jimenez
 */
public class FormClientesTabla extends javax.swing.JFrame {


    public FormClientesTabla() throws NegocioException {
        initComponents();
    }
    
    public void cargarClientesEnTabla() throws NegocioException{
        ClienteBO clienteBO = FabricaClientes.crearClienteBO();
        String[] columnas = {
            "Nombre", "Correo", "Teléfono", "Fecha Registro", "Puntos", "Visitas", "Total Acumulado"
        };
        
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas,0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        List<Cliente> clientes = clienteBO.obtenerListaClientesBO();
        
        for (Cliente c : clientes){
            Object[] fila = {
                c.getNombre(),
                c.getCorreo(),
                c.getNumTelefono(),
                c.getFechaRegistro(),
                // faltan puntos, visitas y total gastado
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Correo", "Teléfono", "Fecha Registro", "Puntos", "Visitas", "Total Acumulado"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public JTable getTablabaClientes(){
        return this.jTable1;
    }
    
    public void recargarTabla() throws NegocioException{
        cargarClientesEnTabla();
    } 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
