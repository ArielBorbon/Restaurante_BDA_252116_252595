/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ModuloComandas;

import BO.ComandasBO.ComandaBO;
import Entidades.Comandas.Comanda;
import Fabricas.FabricaComandas;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC Gamer
 */
public class FormTablaComandas extends javax.swing.JPanel {

    /**
     * Creates new form FormTablaComandas
     */
    public FormTablaComandas() {
        initComponents();
        llenarTablaComandasAbiertas();
    }

    public void llenarTablaComandasAbiertas() {
        ComandaBO comandasBO = FabricaComandas.crearComandaBO();
        List<Comanda> comandasAbiertas = comandasBO.mostrarComandasAbiertasBO();

        String[] columnas = {"Folio", "Mesa", "Estado", "Total", "Fecha", "Cliente"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Comanda c : comandasAbiertas) {
            String folio = c.getFolio();
            int numeroMesa = c.getMesa() != null ? c.getMesa().getNum_mesa() : -1;
            String estado = c.getEstado() != null ? c.getEstado().toString() : "N/A";
            double total = c.getTotal();
            String fecha = c.getFechaHora() != null ? c.getFechaHora().getTime().toString() : "Sin Fecha";

            String cliente = (c.getClienteFrecuente() != null)
                    ? c.getClienteFrecuente().getNombre()
                    : "Cliente General";

            Object[] fila = {folio, numeroMesa, estado, total, fecha, cliente};
            modelo.addRow(fila);
        }

        tblComandasAbiertas.setModel(modelo);
    }

    public JTable getTblComandasAbiertas() {
        return tblComandasAbiertas;
    }
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblComandasAbiertas = new javax.swing.JTable();

        tblComandasAbiertas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblComandasAbiertas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Metodo para utilizar el filtro de reporte comandas :)
    public void llenarTablaComandasAbiertasConFiltro(Date fechaInicio, Date fechaFin) {
        ComandaBO comandasBO = FabricaComandas.crearComandaBO();
        List<Comanda> comandasAbiertas = comandasBO.mostrarComandasAbiertasBO();

        String[] columnas = {"Folio", "Mesa", "Estado", "Total", "Fecha", "Cliente"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Comanda c : comandasAbiertas) {
            Date fechaComanda = c.getFechaHora() != null ? c.getFechaHora().getTime() : null;

            // Verificar si la fecha de la comanda está dentro del rango
            if (fechaComanda != null && !fechaComanda.before(fechaInicio) && !fechaComanda.after(fechaFin)) {
                String folio = c.getFolio();
                int numeroMesa = c.getMesa() != null ? c.getMesa().getNum_mesa() : -1;
                String estado = c.getEstado() != null ? c.getEstado().toString() : "N/A";
                double total = c.getTotal();
                String fecha = sdf.format(fechaComanda);

                String cliente = (c.getClienteFrecuente() != null)
                        ? c.getClienteFrecuente().getNombre()
                        : "Cliente General";

                Object[] fila = {folio, numeroMesa, estado, total, fecha, cliente};
                modelo.addRow(fila);
            }
        }

        tblComandasAbiertas.setModel(modelo);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblComandasAbiertas;
    // End of variables declaration//GEN-END:variables
}
