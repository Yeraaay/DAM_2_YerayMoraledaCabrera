package Calculadora;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;

public class calculadora {

	private JFrame frame;
	private static JTextField textField;
	private JButton[][] buttons;
	private JButton botonDividir;
	private JButton botonSumar;
	private JButton botonRestar;
	private JButton botonMulti;
	private JButton botonPunto;
	private JButton botonCero;
	private JButton botonIgual;
	private JButton botonCE;
	private String operacion;


	//Creamos 2 "Stack" diferentes en el que iremos apilando los numeros obtenidos y los distintos operadores
	private static Stack<Double> pilaNumeros = new Stack<>();
	private static Stack<Character> pilaOperadores = new Stack<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					calculadora window = new calculadora();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public calculadora() {
		Calculadora();
	}


	private void Calculadora() {

		frame = new JFrame();
		frame.setBounds(100, 100, 425, 555);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		textField = new JTextField();
		textField.setBounds(28, 34, 346, 69);
		frame.getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);

		//El contenido del TextField se escribirá a la derecha
		textField.setHorizontalAlignment(JTextField.RIGHT);

		/*
		 * Modificamos la fuente del TextField para que se vea el contenido
		 * a un tamaño adecuado
		 */
		Font fuenteTextField = new Font("Sans-Serif", Font.BOLD, 35);
		textField.setFont(fuenteTextField);

		/*
		 * Añadimos un "keyListener" para controlar que solo se ingresan valores
		 * numéricos dentro del TextField
		 */
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				if (!Character.isDigit(tecla) && tecla != '.') e.consume();
			}
		});


		/*
		 * Creamos lso botones del 1 al 9 con una matriz de numeros, en el que van incrementando de valor
		 * dentro de los bucles "for"
		 */
		buttons = new JButton[3][3];
		Font fuente = new Font("Arial", Font.PLAIN, 40);

		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++ ) {
				final String numero = String.valueOf(i*3 + j + 1);
				buttons[i][j] = new JButton(numero);
				buttons[i][j].setBounds(30 + (j*90), 150 + (i*80), 70, 50);
				buttons[i][j].setBackground(Color.LIGHT_GRAY);
				buttons[i][j].setForeground(Color.BLACK);
				buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				buttons[i][j].setFont(fuente);

				final String numeroPulsado = buttons[i][j].getText();
				buttons[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textField.setText(textField.getText() + (numeroPulsado));
						textField.requestFocusInWindow();
					}
				});

				frame.getContentPane().add(buttons[i][j]);
			}
		}


		//Boton DIVIDIR
		botonDividir = new JButton("÷");
		botonDividir.setBounds(300, 150, 70, 50);
		botonDividir.setFont(fuente);
		botonDividir.setBackground(Color.LIGHT_GRAY);
		botonDividir.setForeground(Color.BLACK);
		botonDividir.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		botonDividir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operacion = "/";
				textField.setText(textField.getText() + "÷");
				textField.requestFocusInWindow();
			}
		});
		frame.getContentPane().add(botonDividir);

		//Boton MULTIPLICAR
		botonMulti = new JButton("*");
		botonMulti.setBounds(300, 230, 70, 50);
		botonMulti.setFont(fuente);
		botonMulti.setBackground(Color.LIGHT_GRAY);
		botonMulti.setForeground(Color.BLACK);
		botonMulti.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		botonMulti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operacion = "*";
				textField.setText(textField.getText() + "*");
				textField.requestFocusInWindow();
			}
		});
		frame.getContentPane().add(botonMulti);

		//Boton RESTAR
		botonRestar = new JButton("-");
		botonRestar.setBounds(300, 310, 70, 50);
		botonRestar.setFont(fuente);
		botonRestar.setBackground(Color.LIGHT_GRAY);
		botonRestar.setForeground(Color.BLACK);
		botonRestar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		botonRestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operacion = "-";
				textField.setText(textField.getText() + "-");
				textField.requestFocusInWindow();
			}
		});
		frame.getContentPane().add(botonRestar);

		//Boton SUMAR
		botonSumar = new JButton("+");
		botonSumar.setBounds(300, 390, 70, 50);
		botonSumar.setFont(fuente);
		botonSumar.setBackground(Color.LIGHT_GRAY);
		botonSumar.setForeground(Color.BLACK);
		botonSumar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		botonSumar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operacion = "+";
				textField.setText(textField.getText() + "+");
				textField.requestFocusInWindow();
			}
		});
		frame.getContentPane().add(botonSumar);

		//Boton PUNTO
		botonPunto = new JButton(".");
		botonPunto.setBounds(120, 390, 70, 50);
		botonPunto.setFont(fuente);
		botonPunto.setBackground(Color.LIGHT_GRAY);
		botonPunto.setForeground(Color.BLACK);
		botonPunto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		botonPunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + ".");
				textField.requestFocusInWindow();
			}
		});
		frame.getContentPane().add(botonPunto);

		//Boton CERO
		botonCero = new JButton("0");
		botonCero.setBounds(30, 390, 70, 50);
		botonCero.setFont(fuente);
		botonCero.setBackground(Color.LIGHT_GRAY);
		botonCero.setForeground(Color.BLACK);
		botonCero.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		botonCero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + "0");
				textField.requestFocusInWindow();
			}
		});
		frame.getContentPane().add(botonCero);

		//Boton CE (Limpieza de contenido)
		botonCE = new JButton("CE");
		botonCE.setBounds(300, 114, 70, 20);
		Font fuenteCE = new Font("Arial", Font.BOLD, 12);
		botonCE.setFont(fuenteCE);
		botonCE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField.requestFocusInWindow();
			}
		});
		frame.getContentPane().add(botonCE);

		//Boton IGUAL
		botonIgual = new JButton("=");
		botonIgual.setBounds(210, 390, 70, 50);
		botonIgual.setFont(fuente);
		botonIgual.setForeground(Color.WHITE);
		botonIgual.setBackground(Color.GRAY);
		botonIgual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String contenido = textField.getText();
				double resultado = calcularOperacion(contenido);
				//Mostramos el resultado por pantalla
				textField.setText(Double.toString(resultado));
				textField.requestFocusInWindow();

				/*
				 * Esta es una forma de hacerla, pero que nos permite solo ejecutar una operación entre 2 valores.
				 * Si queremos que haya una concatenación de operaciones, tendremos que usar un "Stack" como antes hemos usado.
				 */

				//				String contenido = textField.getText();
				//				if( operacion != null ) {
				//					String[] numerosCalcular = contenido.split(Pattern.quote(operacion));
				//					if (numerosCalcular.length == 2 ) {
				//						double numero1 = Double.parseDouble(numerosCalcular[0]);
				//						double numero2 = Double.parseDouble(numerosCalcular[1]);
				//						double resultado = 0.0;
				//						if (operacion.equals("+")) resultado = numero1 + numero2;
				//						else if (operacion.equals("-")) resultado = numero1 - numero2;
				//						else if (operacion.equals("÷")) {
				//							if (numero2 != 0) resultado = numero1 / numero2;
				//						}
				//						else if (operacion.equals("*")) resultado = numero1 * numero2;
				//						
				//						textField.setText(String.valueOf(resultado));
				//					}
				//				}
			}
		});
		frame.getContentPane().add(botonIgual);


        //Botón M+
        JButton botonMemoriaSumar = new JButton("M+");
        botonMemoriaSumar.setBounds(210, 114, 70, 20);
        botonMemoriaSumar.setFont(fuenteCE);
        botonMemoriaSumar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Agrega el valor actual en pantalla a la memoria (pilaNumeros)
                String contenido = textField.getText();
                if (!contenido.isEmpty()) {
                    try {
                        double valorEnPantalla = Double.parseDouble(textField.getText());
                        pilaNumeros.push(valorEnPantalla);
                        textField.setText("");
                        textField.requestFocusInWindow();
                    } catch (NumberFormatException ex) {
                        System.out.println(ex.getMessage().toString());
                    }
                }
            }
        });
        frame.getContentPane().add(botonMemoriaSumar);

        //Botón M-
        JButton botonMemoriaRestar = new JButton("M-");
        botonMemoriaRestar.setBounds(120, 114, 70, 20);
        botonMemoriaRestar.setFont(fuenteCE);
        botonMemoriaRestar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Resta el valor actual en pantalla de la memoria (pilaNumeros)
                String contenido = textField.getText();
                if (!contenido.isEmpty()) {
                    try {
                        double valorEnPantalla = Double.parseDouble(textField.getText());
                        if (!pilaNumeros.isEmpty()) {
                            double valorMemoria = pilaNumeros.pop();
                            valorMemoria -= valorEnPantalla;
                            pilaNumeros.push(valorMemoria);
                        }
                        textField.setText("");
                        textField.requestFocusInWindow();
                    } catch (NumberFormatException ex) {
                    	System.out.println(ex.getMessage().toString());
                    }
                }
            }
        });
        frame.getContentPane().add(botonMemoriaRestar);

        //Botón MR (Recuperar memoria)
        JButton botonMemoriaRecuperar = new JButton("MR");
        botonMemoriaRecuperar.setBounds(28, 114, 70, 20);
        botonMemoriaRecuperar.setFont(fuenteCE);
        botonMemoriaRecuperar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Recupera el valor de la memoria y lo muestra en pantalla
                if (!pilaNumeros.isEmpty()) {
                    double valorMemoria = pilaNumeros.peek();
                    textField.setText(Double.toString(valorMemoria));
                }
                textField.requestFocusInWindow();
            }
        });
		frame.getContentPane().add(botonMemoriaRecuperar);
		
		textField.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char tecla = e.getKeyChar();
		        if (!Character.isDigit(tecla) && tecla != '.') {
		            e.consume();
		        }
		    }

		    @Override
		    public void keyPressed(KeyEvent e) {
		        char tecla = e.getKeyChar();
		        if (tecla == KeyEvent.VK_ENTER) {
		            calcularResultado();
		        } else if (tecla == '+') {
		            agregarOperador("+");
		        } else if (tecla == '-') {
		            agregarOperador("-");
		        } else if (tecla == '*') {
		            agregarOperador("*");
		        } else if (tecla == '/') {
		            agregarOperador("/");
		        }
		    }
		});

	}
	
	private void calcularResultado() {
	    String contenido = textField.getText();
	    double resultado = calcularOperacion(contenido);
	    textField.setText(Double.toString(resultado));
	    textField.requestFocusInWindow();
	}

	private void agregarOperador(String operador) {
	    operacion = operador;
	    textField.setText(textField.getText() + operador);
	    textField.requestFocusInWindow();
	}


	public static double calcularOperacion(String contenido) {

		for (int i = 0; i < contenido.length(); i++) {
			char caracter = contenido.charAt(i);

			if (Character.isDigit(caracter) || caracter == '.') {
				StringBuilder numero = new StringBuilder();
				while (i < contenido.length() && (Character.isDigit(contenido.charAt(i)) || contenido.charAt(i) == '.')) {
					numero.append(contenido.charAt(i));
					i++;
				}
				pilaNumeros.push(Double.parseDouble(numero.toString()));
				i--;
			} else if (caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/') {
				while (!pilaOperadores.isEmpty() && tieneMayorPrecedencia(caracter, pilaOperadores.peek())) {
					realizarOperaciones();
				}
				pilaOperadores.push(caracter);
			}
		}

		while (!pilaOperadores.isEmpty()) {
			realizarOperaciones();
		}
		
	    if (!pilaNumeros.isEmpty()) {
	        return pilaNumeros.pop();
	    } else {
	        //Controlamos el caso en el que no haya números en la pila
	        return 0.0;
	    }
	}


	public static boolean tieneMayorPrecedencia(char op1, char op2) {
		if ((op2 == '*' || op2 == '/') && (op1 == '+' || op1 == '-')) {
			return true;
		}
		return false;
	}

	public static void realizarOperaciones() {
		if (pilaNumeros.size() >=2 && !pilaOperadores.isEmpty()) {
			//Sacamos el operador del Stack de operadores mediante el método "pop()"
			char operador = pilaOperadores.pop();
			//Hacemos lo mismo con el Stack de numeros
			double num2 = pilaNumeros.pop();
			double num1 = pilaNumeros.pop();
			double resultado = 0;

			switch (operador) {
			case '+':
				resultado = num1 + num2;
				break;
			case '-':
				resultado = num1 - num2;
				break;
			case '*':
				resultado = num1 * num2;
				break;
			case '/':
				if (num2 != 0)resultado = num1 / num2;
				else resultado = Double.NaN; //Controlamos la división por cero
				break;
			}

			pilaNumeros.push(resultado);
		}
	}
}