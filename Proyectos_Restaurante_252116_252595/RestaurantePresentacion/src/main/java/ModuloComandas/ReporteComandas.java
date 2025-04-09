/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloComandas;

import BO.ComandasBO.ComandaBO;
import Entidades.Comandas.Comanda;
import Fabricas.FabricaComandas;
import NegocioException.NegocioException;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Alberto Jimenez
 */
public class ReporteComandas extends javax.swing.JFrame {

    private FormTablaComandaCompleta formTablaComandaCompleta = new FormTablaComandaCompleta();

    /**
     * Creates new form ReporteComandas
     */
    public ReporteComandas() {
        initComponents();
        pnlTabla.add(formTablaComandaCompleta);
        formTablaComandaCompleta.setVisible(true);
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
        jLabel3 = new javax.swing.JLabel();
        filtroFechaInicio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        filtroFechaFin = new javax.swing.JTextField();
        pnlTabla = new javax.swing.JPanel();
        btnFiltro = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnImprimirReporte = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        jLabel1.setText("Reporte Comandas");

        jLabel3.setText("Fecha Inicio");
        jLabel3.setFont(new Font("Arial", Font.PLAIN, 14));

        filtroFechaInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroFechaInicioActionPerformed(evt);
            }
        });

        jLabel4.setText("Fecha Fin");
        jLabel4.setFont(new Font("Arial", Font.PLAIN, 14));

        pnlTabla.setLayout(new java.awt.BorderLayout());

        btnFiltro.setText("Filtrar");
        btnFiltro.setBackground(new Color(162, 132, 94));
        btnFiltro.setForeground(Color.WHITE);
        btnFiltro.setFont(new Font("Arial", Font.BOLD, 16));
        btnFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltroActionPerformed(evt);
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

        btnImprimirReporte.setText("IMPRIMIR REPORTE");
        btnImprimirReporte.setBackground(new Color(88, 86, 214));
        btnImprimirReporte.setForeground(Color.WHITE);
        btnImprimirReporte.setFont(new Font("Arial", Font.BOLD, 20));
        btnImprimirReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirReporteActionPerformed(evt);
            }
        });

        jLabel2.setText("Total:");
        jLabel2.setFont(new Font("Arial", Font.PLAIN, 14));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(135, 135, 135)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(filtroFechaInicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(166, 166, 166)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(filtroFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(168, 168, 168)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(293, 293, 293)
                                .addComponent(btnFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(btnImprimirReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filtroFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filtroFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addComponent(btnFiltro)
                .addGap(18, 18, 18)
                .addComponent(pnlTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImprimirReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filtroFechaInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroFechaInicioActionPerformed
        String fechaInicioText = filtroFechaInicio.getText();
        String fechaFinText = filtroFechaFin.getText();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date fechaInicio = sdf.parse(fechaInicioText);
            Date fechaFin = sdf.parse(fechaFinText);

            formTablaComandaCompleta.llenarTablaComandasConFiltro(fechaInicio, fechaFin);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Error al introducir las fechas. Asegúrate de que el formato sea correcto (yyyy-MM-dd).");
        }
    }//GEN-LAST:event_filtroFechaInicioActionPerformed

    private void btnFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroActionPerformed

    }//GEN-LAST:event_btnFiltroActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnImprimirReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirReporteActionPerformed
        try {
            generarReporteComandas();
        } catch (NegocioException ex) {
            Logger.getLogger(ReporteComandas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImprimirReporteActionPerformed

    private void generarReporteComandas() throws NegocioException {
        String fechaInicioTexto = filtroFechaInicio.getText();
        String fechaFinTexto = filtroFechaFin.getText();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicio = null;
        Date fechaFin = null;
        try {
            if (!fechaInicioTexto.isEmpty()) {
                fechaInicio = sdf.parse(fechaInicioTexto);
            }
            if (!fechaFinTexto.isEmpty()) {
                fechaFin = sdf.parse(fechaFinTexto);
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Error al introducir las fechas. Formato esperado: yyyy-MM-dd");
            return;
        }

        ComandaBO comandasBO = FabricaComandas.crearComandaBO();
        List<Comanda> comandasAbiertas = comandasBO.mostrarComandasAbiertasBO();

        List<Comanda> comandasFiltradas = new ArrayList<>();
        for (Comanda c : comandasAbiertas) {
            Date fechaComanda = c.getFechaHora() != null ? c.getFechaHora().getTime() : null;
            if (fechaComanda != null) {
                if ((fechaInicio == null || !fechaComanda.before(fechaInicio))
                        && (fechaFin == null || !fechaComanda.after(fechaFin))) {
                    comandasFiltradas.add(c);
                }
            }
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Reporte de Comandas");
        fileChooser.setSelectedFile(new File("reporte_comandas.pdf"));
        
        int seleccionRuta = fileChooser.showSaveDialog(this);

        if (seleccionRuta != JFileChooser.APPROVE_OPTION) {
            return; 
        }

        File archivoDestino = fileChooser.getSelectedFile();

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(archivoDestino));
            document.open();

            document.add(new Paragraph("REPORTE: COMANDAS"));
            document.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(6);
            tabla.setWidthPercentage(100);

            tabla.addCell("Folio");
            tabla.addCell("Mesa");
            tabla.addCell("Estado");
            tabla.addCell("Total");
            tabla.addCell("Fecha");
            tabla.addCell("Cliente");

            for (Comanda c : comandasFiltradas) {
                String folio = c.getFolio();
                int numeroMesa = c.getMesa() != null ? c.getMesa().getNum_mesa() : -1;
                String estado = c.getEstado() != null ? c.getEstado().toString() : "N/A";
                double total = c.getTotal();
                String fecha = c.getFechaHora() != null ? sdf.format(c.getFechaHora().getTime()) : "Sin Fecha";
                String cliente = (c.getClienteFrecuente() != null)
                        ? c.getClienteFrecuente().getNombre()
                        : "Cliente General";

                tabla.addCell(folio);
                tabla.addCell(String.valueOf(numeroMesa));
                tabla.addCell(estado);
                tabla.addCell(String.valueOf(total));
                tabla.addCell(fecha);
                tabla.addCell(cliente);
            }

            document.add(tabla);
            document.close();

            JOptionPane.showMessageDialog(this, "Reporte generado con éxito:\n" + archivoDestino.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al generar el PDF");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltro;
    private javax.swing.JButton btnImprimirReporte;
    private javax.swing.JButton btnSalir;
    private javax.swing.JTextField filtroFechaFin;
    private javax.swing.JTextField filtroFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel pnlTabla;
    // End of variables declaration//GEN-END:variables
}
