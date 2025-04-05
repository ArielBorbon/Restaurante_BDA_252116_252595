/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ModuloClientes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
    
    private JFrame frame;
    private JTextField agregarNombre;
    private JTextField agregarApellidoPaterno;
    private JTextField agregarApellidoMaterno;
    private JTextField agregarCorreo;
    private JTextField agregarTelefono;

    public ClienteAgregar() {
        frame();
    }

    private void frame() {
        frame = new JFrame("Agregar Cliente");
        frame.setSize(450, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel panelPrincipal = panel1();
        frame.add(panelPrincipal, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JPanel panel1() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Modulo Clientes");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panelFormulario = formulario();
        JButton btnRegistrar = btnRegistrar();
        JButton btnSalir = btnSalir();

        panel1.add(titulo);
        panel1.add(Box.createVerticalStrut(10));
        panel1.add(panelFormulario);
        panel1.add(Box.createVerticalStrut(20));
        panel1.add(btnRegistrar);
        panel1.add(Box.createVerticalStrut(10));
        panel1.add(btnSalir);

        return panel1;
    }

    private JPanel formulario() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 8, 8));

        panel.add(new JLabel("Nombre"));
        agregarNombre = new JTextField(8);
        panel.add(agregarNombre);

        panel.add(new JLabel("Apellido Paterno"));
        agregarApellidoPaterno = new JTextField(8);
        panel.add(agregarApellidoPaterno);

        panel.add(new JLabel("Apellido Materno"));
        agregarApellidoMaterno = new JTextField(8);
        panel.add(agregarApellidoMaterno);

        panel.add(new JLabel("Correo"));
        agregarCorreo = new JTextField(12);
        panel.add(agregarCorreo);

        panel.add(new JLabel("Teléfono"));
        agregarTelefono = new JTextField(8);
        panel.add(agregarTelefono);

        return panel;
    }

    private JButton btnRegistrar() {
        JButton btn = new JButton("Registrar Cliente");
        btn.setBackground(new Color(50, 205, 50));
        btn.setForeground(Color.WHITE);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(
                    frame,
                    "¿Estás seguro de que deseas agregar al cliente?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                try {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Registro Exitoso!",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    limpiarFormulario();
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

        return btn;
    }

    private JButton btnSalir() {
        JButton btn = new JButton("Salir");
        btn.setBackground(new Color(255, 69, 69));
        btn.setForeground(Color.WHITE);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn.addActionListener(e -> frame.dispose());

        return btn;
    }
    
    private void limpiarFormulario(){
        this.agregarNombre.setText("");
        this.agregarApellidoPaterno.setText("");
        this.agregarApellidoMaterno.setText("");
        this.agregarCorreo.setText("");
        this.agregarTelefono.setText("");
    }
}
