/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ModuloClientes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Alberto Jimenez
 */
public class ClienteAgregar extends JFrame {

    public ClienteAgregar() {
        JFrame frame = new JFrame("Agregar Cliente");
        frame.setSize(450, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Modulo Clientes");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panel2 = new JPanel(new GridLayout(5, 2, 8, 8));

        panel2.add(new JLabel("Nombre"));
        JTextField agregarNombre = new JTextField(8);
        agregarNombre.setPreferredSize(new Dimension(100, 25));
        panel2.add(agregarNombre);

        panel2.add(new JLabel("Apellido Paterno"));
        JTextField agregarApellidoPaterno = new JTextField(8);
        agregarApellidoPaterno.setPreferredSize(new Dimension(100, 25));
        panel2.add(agregarApellidoPaterno);

        panel2.add(new JLabel("Apellido Materno"));
        JTextField agregarApellidoMaterno = new JTextField(8);
        agregarApellidoMaterno.setPreferredSize(new Dimension(100, 25));
        panel2.add(agregarApellidoMaterno);

        panel2.add(new JLabel("Correo"));
        JTextField agregarCorreo = new JTextField(12);
        agregarCorreo.setPreferredSize(new Dimension(100, 25));
        panel2.add(agregarCorreo);

        panel2.add(new JLabel("Teléfono"));
        JTextField agregarTelefono = new JTextField(8);
        agregarTelefono.setPreferredSize(new Dimension(100, 25));
        panel2.add(agregarTelefono);

        JButton btnRegistrar = new JButton("Registrar Cliente");
        btnRegistrar.setBackground(new Color(50, 205, 50));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnRegistrar.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(
                    frame, // Cambia 'this' por 'frame' si estás en un contexto estático
                    "¿Estás seguro de que deseas agregar al cliente?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                try {
                    // Aquí podrías agregar lógica real de registro si fuera necesario
                    JOptionPane.showMessageDialog(
                            frame,
                            "Registro Exitoso!",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Algo salió Mal...\nIntenta Nuevamente Después.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBackground(new Color(255, 69, 69));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnSalir.addActionListener(e -> {
            frame.dispose();
        });

        panel1.add(titulo);
        panel1.add(Box.createVerticalStrut(10));
        panel1.add(panel2);
        panel1.add(Box.createVerticalStrut(20));
        panel1.add(btnRegistrar);
        panel1.add(Box.createVerticalStrut(10));
        panel1.add(btnSalir);

        frame.add(panel1, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
