/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloClientes;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Alberto Jimenez
 */
public class ClienteMenu extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menú Clientes");
        frame.setSize(850, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titulo = new JLabel("Modulo Clientes");
        titulo.setFont(new Font("Arial", Font.BOLD, 32));

        panel1.add(titulo);
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnas = {"Nombre", "Correo", "Teléfono", "Fecha de Registro", "Puntos", "Visitas", "Total Acumulado"};
        Object[][] datos = { 
            // Aquí va la lista de los clientes
        };

        JTable table = new JTable(new DefaultTableModel(datos, columnas));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(Color.LIGHT_GRAY);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nombreLabel = new JLabel("Nombre");
        JTextField filtrarNombre = new JTextField();
        filtrarNombre.setPreferredSize(new Dimension(200, 25));
        filtrarNombre.setMaximumSize(new Dimension(300, 25));

        JLabel telefonoLabel = new JLabel("Teléfono:");
        JTextField filtrarTelefono = new JTextField();
        filtrarTelefono.setPreferredSize(new Dimension(200, 25));
        filtrarTelefono.setMaximumSize(new Dimension(300, 25));

        JLabel correoLabel = new JLabel("Correo Electrónico:");
        JTextField filtrarCorreo = new JTextField();
        filtrarCorreo.setPreferredSize(new Dimension(200, 25));
        filtrarCorreo.setMaximumSize(new Dimension(300, 25));

        nombreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        filtrarNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        telefonoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        filtrarTelefono.setAlignmentX(Component.LEFT_ALIGNMENT);
        correoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        filtrarCorreo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnFiltrar = new JButton("Filtrar");
        JButton btnAgregar = new JButton("Agregar Cliente");
        JButton btnSalir = new JButton("Salir");
        JButton btnReporte = new JButton("REPORTE CLIENTES FRECUENTES");

        btnFiltrar.setBackground(new Color(181, 136, 99));
        btnFiltrar.setForeground(Color.WHITE);
        btnFiltrar.addActionListener(e ->{
            
        });
        
        btnAgregar.setBackground(new Color(50, 205, 50));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.addActionListener(e -> {
            new ClienteAgregar();
        });

        btnSalir.setBackground(new Color(255, 69, 69));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.addActionListener(e -> {
            frame.dispose();
        });
        
        btnReporte.setBackground(new Color(30, 144, 255));
        btnReporte.setForeground(Color.WHITE);
        btnReporte.addActionListener(e -> {
        
        });
        
        panel2.add(nombreLabel);
        panel2.add(filtrarNombre);
        panel2.add(telefonoLabel);
        panel2.add(filtrarTelefono);
        panel2.add(correoLabel);
        panel2.add(filtrarCorreo);
        panel2.add(Box.createVerticalStrut(10));
        panel2.add(btnFiltrar);
        panel2.add(Box.createVerticalStrut(10));
        panel2.add(btnAgregar);
        panel2.add(Box.createVerticalStrut(10));
        panel2.add(btnSalir);
        panel2.add(Box.createVerticalStrut(10));
        panel2.add(btnReporte);

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.EAST);

        frame.setVisible(true);
    }

}
