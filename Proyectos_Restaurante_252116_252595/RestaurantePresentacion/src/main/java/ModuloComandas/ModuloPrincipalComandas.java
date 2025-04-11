/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloComandas;

import BO.ComandasBO.ComandaBO;
import BO.MesaBO.MesaBO;
import DTOS.Comandas.NuevaComandaDTO;
import DTOS.Comandas.NuevoDetalleComandaDTO;
import DTOS.Mesa.NuevaMesaDTO;
import Entidades.Clientes.ClientesFrecuentes;
import Entidades.Comandas.Comanda;
import Entidades.Comandas.DetalleComanda;
import Entidades.Comandas.EstadoComanda;
import Fabricas.FabricaComandas;
import Fabricas.FabricaMesas;
import Menu.MenuModulos;
import ModuloComandas.AñadirComanda.AñadirComanda;
import ModuloComandas.ModificarComanda.ModificarComanda;
import NegocioException.NegocioException;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Menu principal del modulo comandas
 * 
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
public class ModuloPrincipalComandas extends javax.swing.JFrame {

    private FormTablaComandas formTablaComanda = new FormTablaComandas();
    
    private String rol;
    
    /**
     * Creates new form ModuloPrincipalComandas
     * @param rol
     */
    public ModuloPrincipalComandas(String rol) {
        initComponents();
        pnlTabla.add(formTablaComanda);
        formTablaComanda.setVisible(true);
        getContentPane().setBackground(new Color(0xe71d1d));
        this.rol = rol;
    }
    
    public ModuloPrincipalComandas() {
        initComponents();
        pnlTabla.add(formTablaComanda);
        formTablaComanda.setVisible(true);
        getContentPane().setBackground(new Color(0xe71d1d));

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
        pnlTabla = new javax.swing.JPanel();
        btnAñadirComanda = new javax.swing.JButton();
        btnDetallesComanda = new javax.swing.JButton();
        btnCancelarComanda = new javax.swing.JButton();
        btnMarcarComoCompletada = new javax.swing.JButton();
        btnReporteComandas = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menú Comanda");

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        jLabel1.setText("Modulo Comandas");

        pnlTabla.setLayout(new java.awt.BorderLayout());

        btnAñadirComanda.setBackground(new java.awt.Color(153, 255, 153));
        btnAñadirComanda.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        btnAñadirComanda.setText("Añadir Comanda");
        btnAñadirComanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirComandaActionPerformed(evt);
            }
        });

        btnDetallesComanda.setBackground(new java.awt.Color(204, 204, 204));
        btnDetallesComanda.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        btnDetallesComanda.setText("Detalles Comanda");
        btnDetallesComanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetallesComandaActionPerformed(evt);
            }
        });

        btnCancelarComanda.setBackground(new java.awt.Color(255, 153, 153));
        btnCancelarComanda.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        btnCancelarComanda.setText("Cancelar Comanda");
        btnCancelarComanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarComandaActionPerformed(evt);
            }
        });

        btnMarcarComoCompletada.setBackground(new java.awt.Color(255, 255, 204));
        btnMarcarComoCompletada.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        btnMarcarComoCompletada.setText("Marcar Completada");
        btnMarcarComoCompletada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcarComoCompletadaActionPerformed(evt);
            }
        });

        btnReporteComandas.setBackground(new java.awt.Color(153, 255, 255));
        btnReporteComandas.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        btnReporteComandas.setText("Reporte De Comandas");
        btnReporteComandas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteComandasActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(255, 102, 102));
        btnSalir.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnlTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDetallesComanda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelarComanda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAñadirComanda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnMarcarComoCompletada, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnSalir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReporteComandas, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(btnAñadirComanda)
                        .addGap(32, 32, 32)
                        .addComponent(btnDetallesComanda)
                        .addGap(216, 216, 216)
                        .addComponent(btnMarcarComoCompletada)
                        .addGap(51, 51, 51)
                        .addComponent(btnCancelarComanda)
                        .addGap(0, 67, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnReporteComandas)
                    .addComponent(btnSalir))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarComandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarComandaActionPerformed
         int filaSeleccionada = formTablaComanda.getTblComandasAbiertas().getSelectedRow();
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Selecciona una comanda para cancelar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String folio = (String) formTablaComanda.getTblComandasAbiertas().getValueAt(filaSeleccionada, 0); // Asumiendo que el folio está en la columna 0

    int opcion = JOptionPane.showConfirmDialog(this, 
        "¿Estás seguro de que deseas cancelar la comanda con folio: " + folio + "?", 
        "Confirmar cancelación", 
        JOptionPane.YES_NO_OPTION);

    if (opcion != JOptionPane.YES_OPTION) {
        return;
    }

    try {
        ComandaBO comandaBO = FabricaComandas.crearComandaBO();
        Comanda comanda = comandaBO.obtenerComandaPorFolioBO(folio);

        if (comanda.getEstado() == EstadoComanda.CANCELADA) {
            JOptionPane.showMessageDialog(this, "La comanda ya está cancelada.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (comanda.getEstado() == EstadoComanda.ENTREGADA) {
            JOptionPane.showMessageDialog(this, "No puedes cancelar una comanda ya entregada.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        comandaBO.cancelarComandaBO(folio);

        MesaBO mesaBO = FabricaMesas.crearMesaBO();
        NuevaMesaDTO nuevaMesaDTO = new NuevaMesaDTO();
        nuevaMesaDTO.setEstado(comanda.getMesa().getEstado());
        nuevaMesaDTO.setNum_mesa(comanda.getMesa().getNum_mesa());
        mesaBO.disponibilizarMesaBO(nuevaMesaDTO);

        JOptionPane.showMessageDialog(this, "¡Comanda cancelada exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        formTablaComanda.llenarTablaComandas();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al cancelar la comanda: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnCancelarComandaActionPerformed

    private void btnReporteComandasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteComandasActionPerformed
        try {
            ReporteComandas reporteComandas = new ReporteComandas();
            reporteComandas.setVisible(true);
        } catch (NegocioException ex) {
            Logger.getLogger(ModuloPrincipalComandas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteComandasActionPerformed

    private void btnAñadirComandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirComandaActionPerformed
        AñadirComanda añadirComanda = new AñadirComanda(formTablaComanda);
        añadirComanda.setVisible(true);
    }//GEN-LAST:event_btnAñadirComandaActionPerformed

    private void btnDetallesComandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesComandaActionPerformed

        int filaSeleccionada = formTablaComanda.getTblComandasAbiertas().getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una comanda para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String folioComanda = (String) formTablaComanda.getTblComandasAbiertas().getValueAt(filaSeleccionada, 0);

        ModificarComanda modificarComanda = new ModificarComanda(folioComanda, formTablaComanda);
        modificarComanda.setVisible(true);


    }//GEN-LAST:event_btnDetallesComandaActionPerformed

    private void btnMarcarComoCompletadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcarComoCompletadaActionPerformed
        try {
            int filaSeleccionada = formTablaComanda.getTblComandasAbiertas().getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una comanda para marcar como completada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String folio = (String) formTablaComanda.getTblComandasAbiertas().getValueAt(filaSeleccionada, 0);
            ComandaBO comandasBO = FabricaComandas.crearComandaBO();

            Comanda comanda = comandasBO.obtenerComandaPorFolioBO(folio);
            List<DetalleComanda> detalles = comandasBO.obtenerListaDetallesComandaBO(comanda);

            if (comanda.getEstado() == EstadoComanda.ENTREGADA) {
                JOptionPane.showMessageDialog(this, "Esta comanda ya fue marcada como entregada.", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            NuevaComandaDTO dto = new NuevaComandaDTO();
            dto.setFolio(folio);

            if (comanda.getClienteFrecuente() != null) {
                dto.setCorreoCliente(comanda.getClienteFrecuente().getCorreo());
            } else {
                dto.setCorreoCliente(null);
            }

            dto.setEstado_comanda(EstadoComanda.ENTREGADA);
            dto.setNum_mesa(comanda.getMesa().getNum_mesa());
            dto.setFecha_hora(comanda.getFechaHora());

            List<NuevoDetalleComandaDTO> detallesDTO = new ArrayList<NuevoDetalleComandaDTO>();
            double totalComanda = 0.0;
            
            for (DetalleComanda detalle : detalles) {
                NuevoDetalleComandaDTO detalleDTO = new NuevoDetalleComandaDTO();
                detalleDTO.setCantidad(detalle.getCantidad());
                detalleDTO.setFolioComanda(folio);
                detalleDTO.setImporteTotal(detalle.getImporteTotal());
                detalleDTO.setNombreProducto(detalle.getProducto().getNombre());
                detalleDTO.setNotas_producto(detalle.getNotasEspeciales());
                detalleDTO.setPrecioUnitario(detalle.getPrecioUnitario());
                detallesDTO.add(detalleDTO);
                totalComanda += detalle.getImporteTotal();
            }

            comandasBO.modificarComandaBO(dto, detallesDTO);

            // Restar stock
            comandasBO.restarStockIngredientesPorProductosComandaBO(detallesDTO);
            
            //quitarle lo ocupado a la mesa
            MesaBO mesaBO = FabricaMesas.crearMesaBO();
            NuevaMesaDTO nuevaMesaDTO = new NuevaMesaDTO();
            nuevaMesaDTO.setEstado(comanda.getMesa().getEstado());
            nuevaMesaDTO.setNum_mesa(comanda.getMesa().getNum_mesa());
            mesaBO.disponibilizarMesaBO(nuevaMesaDTO);
            
            // Actualiza las visitas, total y puntos de los clientes
            if (comanda.getClienteFrecuente() != null) {
                ClientesFrecuentes clienteFrecuente = comanda.getClienteFrecuente();
                comandasBO.actualizarClienteFrecuente(clienteFrecuente.getId(), totalComanda);
            }

            JOptionPane.showMessageDialog(this, "Comanda marcada como entregada y stock actualizado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            formTablaComanda.llenarTablaComandas();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al completar la comanda: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnMarcarComoCompletadaActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        MenuModulos menu = new MenuModulos(rol);
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAñadirComanda;
    private javax.swing.JButton btnCancelarComanda;
    private javax.swing.JButton btnDetallesComanda;
    private javax.swing.JButton btnMarcarComoCompletada;
    private javax.swing.JButton btnReporteComandas;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel pnlTabla;
    // End of variables declaration//GEN-END:variables
}
