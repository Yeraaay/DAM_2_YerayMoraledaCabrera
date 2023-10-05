package Actividades;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class adivinarNumero {

    private JFrame frame;
    private JTextField textField;
    private JLabel label;
    private JLabel labelFinal;
    private JButton boton;
    private int intentos = 5;
    private int numeroRandom;
    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    adivinarNumero window = new adivinarNumero();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public adivinarNumero() {
        numeroRandom = (int) (Math.random() * 101); //Genera el número aleatorio una vez
        adivinarNumeros();
    }

    private void adivinarNumeros() {
        frame = new JFrame();
        frame.setBounds(100, 100, 400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        boton = new JButton("Adivinar");
        boton.setBounds(105, 150, 180, 35);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(boton);

        textField = new JTextField();
        textField.setBounds(240, 35, 105, 35);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        label = new JLabel("Adivine el número (1-100): ");
        label.setBounds(43, 35, 173, 35);
        frame.getContentPane().add(label);

        labelFinal = new JLabel("");
        labelFinal.setBounds(60, 101, 260, 35);
        frame.getContentPane().add(labelFinal);

        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adivinar();
                textField.requestFocusInWindow();
            }
        });

        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == ' ') {
                    adivinar();
                    textField.requestFocusInWindow();
                }
            }
        });
    }

    private void adivinar() {
        try {
            int numero = Integer.parseInt(textField.getText());
            if (intentos > 0) { //Verifica si hay intentos restantes
                if (numero == numeroRandom) {
                    labelFinal.setText("¡Correcto! El número es " + numeroRandom);
                    textField.setEnabled(false);
                } else if (numero < numeroRandom) {
                    labelFinal.setText("El número a adivinar es mayor. Intentos: " + --intentos + ".");
                } else if (numero > numeroRandom) {
                    labelFinal.setText("El número a adivinar es menor. Intentos: " + --intentos + ".");
                }
                if (intentos == 0) {
                    labelFinal.setText("No quedan intentos, el número era: " + numeroRandom);
                    textField.setEnabled(false);
                    boton.setVisible(false);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage().toString());
        }
        textField.setText("");
    }
}
