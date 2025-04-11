/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloClientes;

import BO.ClienteBO.ClienteBO;
import DTOS.Clientes.NuevoClienteDTO;
import Fabricas.FabricaClientes;
import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 * Agregar Clientes
 * 
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class ClienteAgregar extends javax.swing.JFrame {

    private FormClientesTabla formClientesTabla;

    /**
     * Creates new form ClienteAgregar
     *
     * @param formClientesTabla
     */
    public ClienteAgregar(FormClientesTabla formClientesTabla) {
        initComponents();
        this.formClientesTabla = formClientesTabla;
        getContentPane().setBackground(new Color(0x4dd3e0));
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
        agregarNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        agregarApellidoPaterno = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        agregarApellidoMaterno = new javax.swing.JTextField();
        agregarCorreo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        agregarTelefono = new javax.swing.JTextField();
        btnRegistrarCliente = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Cliente");

        jLabel1.setText("Modulo Cliente");
        jLabel1.setFont(new Font("Arial", Font.BOLD, 32));

        jLabel2.setText("Nombre");
        jLabel2.setFont(new Font("Arial", Font.PLAIN, 14));

        jLabel3.setText("Apellido Paterno");
        jLabel3.setFont(new Font("Arial", Font.PLAIN, 14));

        jLabel4.setText("Apellido Materno");
        jLabel4.setFont(new Font("Arial", Font.PLAIN, 14));

        jLabel5.setText("Correo");
        jLabel5.setFont(new Font("Arial", Font.PLAIN, 14));

        jLabel6.setText("Teléfono");
        jLabel6.setFont(new Font("Arial", Font.PLAIN, 14));

        btnRegistrarCliente.setText("Registrar Cliente");
        btnRegistrarCliente.setBackground(new Color(52, 199, 89));
        btnRegistrarCliente.setForeground(Color.WHITE);
        btnRegistrarCliente.setFont(new Font("Arial", Font.BOLD, 16));
        btnRegistrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarClienteActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(agregarNombre)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(agregarApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(67, 67, 67)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(agregarApellidoMaterno)
                                    .addComponent(agregarCorreo)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(137, 137, 137)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(agregarTelefono)
                                    .addComponent(btnRegistrarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))))
                        .addGap(0, 46, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(agregarApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agregarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(agregarCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agregarApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(agregarTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(btnRegistrarCliente)
                .addGap(18, 18, 18)
                .addComponent(btnSalir)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * 
     * @param evt 
     */
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    
    /**
     * 
     * @param evt 
     */
    private void btnRegistrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarClienteActionPerformed
        String nombre = agregarNombre.getText().trim();
        String apellidoP = agregarApellidoPaterno.getText().trim();
        String apellidoM = agregarApellidoMaterno.getText().trim();
        String correo = agregarCorreo.getText().trim();
        String numTelefono = agregarTelefono.getText().trim();

        if (nombre.isEmpty() || apellidoP.isEmpty() || apellidoM.isEmpty() || numTelefono.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, complete todos los campos.",
                    "Campos incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!numTelefono.matches("\\d+")) {
            JOptionPane.showMessageDialog(this,
                    "El número de teléfono solo debe tener números.",
                    "Número inválido",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (numTelefono.length() != 10) {
            JOptionPane.showMessageDialog(this,
                    "El número de teléfono debe tener 10 dígitos.",
                    "Número inválido",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombreCompleto = nombre + " " + apellidoP + " " + apellidoM;

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Deseas registrar al cliente " + nombre + " " + apellidoP + " " + apellidoM + "?",
                "Confirmar Registro",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                NuevoClienteDTO nuevoClienteDTO = new NuevoClienteDTO();
                nuevoClienteDTO.setNombre(nombreCompleto);
                nuevoClienteDTO.setCorreo(correo);
                nuevoClienteDTO.setNumTelefono(numTelefono);
                nuevoClienteDTO.setFechaRegistro(Calendar.getInstance());

                ClienteBO clienteBO = FabricaClientes.crearClienteBO();
                clienteBO.registrarClienteBO(nuevoClienteDTO);

                JOptionPane.showMessageDialog(this,
                        "Cliente Registrado Exitosamente",
                        "Exito",
                        JOptionPane.INFORMATION_MESSAGE
                );

                formClientesTabla.recargarTabla();
                this.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado al registrar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Registro cancelado.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnRegistrarClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField agregarApellidoMaterno;
    private javax.swing.JTextField agregarApellidoPaterno;
    private javax.swing.JTextField agregarCorreo;
    private javax.swing.JTextField agregarNombre;
    private javax.swing.JTextField agregarTelefono;
    private javax.swing.JButton btnRegistrarCliente;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
