package tp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Interfaz {

	private JLabel lblX, lblFondo, lblRecuadro, lblMinimizar, lblFaltanPesos, lblIngresarPesos, lblCliquesGolosas,
			lblAristaIngresada, lblCantidadVertices;

	private JButton btnCalcular, btnPesosVertices, btnIngresarArista, btnCantidadVertices;

	private JFrame frame;
	private JDialog frameGrafo;
	private JPanel panelGrafo;
	private JTextField txfCantidadVertices;

	private SingleGraph grafo;

	private Integer xx = 0, yy = 0, cantidadVertices = 0;
	private Point posicionInicialAux;
	private boolean grafoInicial, puedeCalcular;

	private ArrayList<Double> pesosVertices;
	private ArrayList<Tupla<Integer, Integer>> aristas;

	private ArrayList<JButton> botones;
	private ArrayList<JLabel> etiquetas;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Interfaz() {
		inicializar();
	}

	private void inicializar() {
		frames();
		botones();
		textFields();
		labels();
		eventos();
	}

	private void frames() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(152, 251, 152));
		frame.setBounds(100, 100, 240, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Interfaz.class.getResource("/Imgs/icono.png")));

		final Point posicionInicial = new Point(frame.getX(), frame.getY());
		posicionInicialAux = posicionInicial;

		frameGrafo = new JDialog(frame, "");
		frameGrafo.setBounds(400, 100, 640, 650);
		frameGrafo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameGrafo.setUndecorated(true);

		aristas = new ArrayList<Tupla<Integer, Integer>>();
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
	}

	private void botones() {
		botones = new ArrayList<JButton>();

		btnCantidadVertices = new JButton("ingresar");
		botones.add(btnCantidadVertices);

		btnPesosVertices = new JButton("pesos");
		botones.add(btnPesosVertices);

		btnIngresarArista = new JButton("ingresar arista");
		botones.add(btnIngresarArista);

		btnCalcular = new JButton("calcular");
		botones.add(btnCalcular);

		configurarBotones();
	}

	private void labels() {
		etiquetas = new ArrayList<JLabel>();

		lblX = new JLabel("X");
		lblX.setBounds(213, 0, 22, 26);
		etiquetas.add(lblX);

		lblMinimizar = new JLabel("---");
		lblMinimizar.setBounds(189, 0, 22, 26);
		etiquetas.add(lblMinimizar);

		lblRecuadro = new JLabel("");
		lblRecuadro.setBounds(0, 0, 240, 26);
		lblRecuadro.setOpaque(true);
		lblRecuadro.setBorder(new LineBorder(new Color(178, 34, 34), 2, true));
		etiquetas.add(lblRecuadro);

		lblCliquesGolosas = new JLabel("CLIQUES GOLOSAS");
		lblCliquesGolosas.setBounds(26, 35, 199, 40);
		etiquetas.add(lblCliquesGolosas);

		lblCantidadVertices = new JLabel("Ingrese cantidad de vertices");
		lblCantidadVertices.setBounds(26, 142, 199, 40);
		etiquetas.add(lblCantidadVertices);

		lblIngresarPesos = new JLabel("Ingresar pesos de los vertices");
		lblIngresarPesos.setBounds(26, 308, 199, 40);
		etiquetas.add(lblIngresarPesos);

		lblAristaIngresada = new JLabel("");
		lblAristaIngresada.setBounds(26, 430, 199, 40);
		lblAristaIngresada.setVisible(false);
		etiquetas.add(lblAristaIngresada);

		lblFaltanPesos = new JLabel("faltan agregar los pesos");
		lblFaltanPesos.setBounds(26, 575, 199, 40);
		lblFaltanPesos.setVisible(false);
		etiquetas.add(lblFaltanPesos);

		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(Interfaz.class.getResource("/Imgs/fondo.jpg")));
		lblFondo.setBounds(0, 0, 240, 650);
		etiquetas.add(lblFondo);

		configurarEtiquetas();
	}

	private void textFields() {
		txfCantidadVertices = new JTextField();
		txfCantidadVertices.setBounds(42, 175, 163, 20);
		frame.getContentPane().add(txfCantidadVertices);
		txfCantidadVertices.setHorizontalAlignment(SwingConstants.CENTER);
		txfCantidadVertices.setColumns(10);
	}

	private void eventos() {
		for (JButton boton : botones) {
			boton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// Ingresa la cantidad de vertices de grafo
					if (boton.getText().equals("ingresar")) {
						almacenarCantidadVertices();
					}
					// Ingresa el peso de cada verice del grafo
					else if (boton.getText().equals("pesos")) {
						if (puedeAgregarPesos()) {
							pesosVertices = new ArrayList<Double>();
							aristas = new ArrayList<Tupla<Integer, Integer>>();

							eliminarAristas();

							for (int i = 0; i < cantidadVertices; i++) {
								String peso = JOptionPane.showInputDialog(frame, "Ingrese el peso del vertice " + i, "",
										JOptionPane.PLAIN_MESSAGE);

								if (pesoValido(peso)) {
									pesosVertices.add(Double.valueOf(peso));
									etiquetarVertice(i, Double.valueOf(peso));
								} else {
									eliminarEtiquetasVertices();
									break;
								}
							}
						}
					}
					// Ingresa las aristas del grafo
					else if (boton.getText().equals("ingresar arista")) {
						if (puedeCalcular && cantidadVertices > 1) {
							int[] extremos = new int[2];

							for (int i = 0; i < 2; i++) {
								int aux = i + 1;
								String vertice = JOptionPane.showInputDialog(frame,
										"Ingrese el extremo nº" + aux + " de la arista", "", JOptionPane.PLAIN_MESSAGE);

								if (esNumerico(vertice))
									extremos[i] = Integer.parseInt(vertice);
								else
									break;
							}

							if (aristaValida(extremos)) {
								aristas.add(new Tupla<Integer, Integer>(extremos[0], extremos[1]));
								anadirArista(extremos[0], extremos[1]);
								lblAristaIngresada.setText("arista ingresada!");
							} else
								lblAristaIngresada.setText("arista NO ingresada!");

							avisarUsuario(lblAristaIngresada);
						}
					}
					// Se calcula la clique de mayor peso
					else if (boton.getText().equals("calcular")) {
						if (puedeCalcular()) {
							puedeCalcular = false;
							crearGrafoSolucion();
							dibujarGrafo();
						} else
							avisarUsuario(lblFaltanPesos);
					}
				}
			});
		}

		int cantidadEtiquetasConFunciones = 2;
		for (int i = 0; i < cantidadEtiquetasConFunciones; i++) {
			JLabel etiqueta = etiquetas.get(i);

			etiqueta.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (etiqueta.getText().equals("X")) {
						System.exit(1);
					} else if (etiqueta.getText().equals("---")) {
						frame.setExtendedState(JFrame.HIDE_ON_CLOSE);
					}
				}
			});
		}

		lblRecuadro.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx = e.getX();
				yy = e.getY();
			}
		});

		lblRecuadro.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent ev) {
				int x = ev.getXOnScreen();
				int y = ev.getYOnScreen();

				frame.setBounds(x - xx, y - yy, frame.getWidth(), frame.getHeight());
				if (frameGrafo != null)
					frameGrafo.setBounds(x - xx + 250, y - yy, 640, 650);
			}
		});

		txfCantidadVertices.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10)
					almacenarCantidadVertices();
			}
		});
	}

	// ************************* funciones privadas ************************* //

	private void configurarBotones() {
		int posY = 205;
		int distanciaY = 137;

		for (JButton boton : botones) {
			boton.setBounds(63, posY, 123, 23);
			posY += distanciaY;

			boton.setFont(new Font("Quicksand Medium", Font.BOLD, 11));
			boton.setOpaque(false);
			boton.setForeground(Color.WHITE);
			boton.setBackground(Color.BLACK);
			boton.setBorder(new LineBorder(Color.WHITE, 2, true));
			frame.getContentPane().add(boton);
		}
	}

	private void configurarEtiquetas() {
		for (JLabel etiqueta : etiquetas) {
			etiqueta.setForeground(Color.WHITE);
			etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
			etiqueta.setBackground(new Color(178, 34, 34));
			etiqueta.setFont(new Font("Quicksand Medium", Font.BOLD, 12));
			frame.getContentPane().add(etiqueta);
		}

		lblX.setFont(new Font("Quicksand Medium", Font.BOLD, 18));
		lblMinimizar.setFont(new Font("Harlow Solid Italic", Font.BOLD, 18));
		lblCliquesGolosas.setFont(new Font("Quicksand Medium", Font.BOLD, 18));
	}

	private void almacenarCantidadVertices() {
		puedeCalcular = true;
		if (cantidadDeVerticesValida()) {
			cantidadVertices = Integer.valueOf(txfCantidadVertices.getText());
			pesosVertices = new ArrayList<Double>();

			mostrarGrafo();
		}
	}

	private boolean esNumerico(String input) {
		try {
			if (input == null || input.equals(""))
				return false;

			Double.parseDouble(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean pesoValido(String input) {
		return esNumerico(input) && Double.valueOf(input) >= 0;
	}

	private boolean cantidadDeVerticesValida() {
		return !txfCantidadVertices.getText().equals("") && esNumerico(txfCantidadVertices.getText())
				&& Integer.valueOf(txfCantidadVertices.getText()) > 0;
	}

	// Informa si se puede calcular la clique de mayor peso
	private boolean puedeCalcular() {
		return pesosVertices != null && pesosVertices.size() == cantidadVertices;
	}

	private boolean puedeAgregarPesos() {
		return cantidadVertices != 0 && puedeCalcular;
	}

	private boolean aristaValida(int[] extremos) {
		return verticesValidos(extremos)
				&& !Tupla.contiene(aristas, new Tupla<Integer, Integer>(extremos[0], extremos[1]));
	}

	// Informa si los vertices extremos de la arista ingresada son validos
	private boolean verticesValidos(int[] vertices) {
		boolean ret = true;
		for (int i = 0; i < vertices.length; i++) {
			ret &= vertices[i] >= 0 && vertices[i] < cantidadVertices;
		}
		return ret && vertices[0] != vertices[1];
	}

	// Informa al usuario el resultado de una accion creada por el
	private void avisarUsuario(JLabel etiqueta) {
		etiqueta.setVisible(true);
		ActionListener timer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				etiqueta.setVisible(false);
				lblAristaIngresada.setText("arista NO ingresada!");
			}
		};
		javax.swing.Timer tick = new javax.swing.Timer(2000, timer);
		tick.setRepeats(false);
		tick.start();
	}

	// ************************* funciones para el grafo *************************
	// //

	private void anadirVertice(int i, double peso) {
		grafo.addNode(String.valueOf(i));
		grafo.getNode(String.valueOf(i)).addAttribute("ui.label", i + " (" + peso + ")");
		grafo.getNode(String.valueOf(i)).setAttribute("xy", (int) (Math.random() * 640) + 1,
				(int) (Math.random() * 640) + 1);
	}

	private void etiquetarVertice(int vertice, double peso) {
		grafo.getNode(String.valueOf(vertice)).addAttribute("ui.label",
				String.valueOf(vertice) + " (" + String.valueOf(Double.valueOf(peso)) + ")");
	}

	private void eliminarEtiquetasVertices() {
		for (int i = 0; i < cantidadVertices; i++) {
			grafo.getNode(i).addAttribute("ui.label", String.valueOf(i) + " (0.0)");
		}
	}

	private void anadirArista(int i, int j) {
		grafo.addEdge(String.valueOf(i) + String.valueOf(j), String.valueOf(i), String.valueOf((j)));
	}

	private void eliminarAristas() {
		for (Edge arista : grafo.getEachEdge()) {
			grafo.removeEdge(arista);
		}
		// De nuevo porque no saca la ultima
		for (Edge arista : grafo.getEachEdge()) {
			grafo.removeEdge(arista); 
		}
	}

	private void mostrarGrafo() {
		if (!grafoInicial)
			frame.setBounds((int) posicionInicialAux.getX() - 350, (int) posicionInicialAux.getY(), 240, 650);

		grafoInicial = true;

		grafo = new SingleGraph("grafo", false, true);
		for (int i = 0; i < cantidadVertices; i++) {
			anadirVertice(i, 0.0);
		}

		dibujarGrafo();
	}

	private void crearGrafoSolucion() {
		grafo = new SingleGraph("grafo2", false, true);

		ArrayList<Tupla<Integer, Double>> grafoSolucion = Negocio.calcular(cantidadVertices, pesosVertices, aristas);

		for (int i = 0; i < grafoSolucion.size(); i++) {
			anadirVertice(grafoSolucion.get(i).getPrimerElemento(), grafoSolucion.get(i).getSegundoElemento());

			for (int j = i + 1; j < grafoSolucion.size(); j++) {
				anadirArista(grafoSolucion.get(i).getPrimerElemento(), grafoSolucion.get(j).getPrimerElemento());
			}
		}
	}

	@SuppressWarnings("serial")
	private void dibujarGrafo() {
		frameGrafo.setVisible(false);
		frameGrafo = new JDialog(frame, "");
		frameGrafo.setBounds(400, 100, 640, 650);
		frameGrafo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameGrafo.setUndecorated(true);

		panelGrafo = new JPanel(new GridLayout()) {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(640, 650);
			}
		};

		grafo.addAttribute("ui.stylesheet", "graph { fill-color: rgb(0, 0, 0); } "
				+ "node { size:14px; fill-color: rgb(178, 34, 34); text-color: rgb(255, 255, 255); text-style: bold; text-size: 14; text-background-mode: rounded-box; text-background-color: rgb(0, 0, 0); text-alignment: above;} "
				+ "edge { fill-color: rgb(255, 255, 255); size: 2px; }");

		Viewer viewer = new Viewer(grafo, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		ViewPanel viewPanel = viewer.addDefaultView(false);

		panelGrafo.add(viewPanel);
		frameGrafo.getContentPane().add(panelGrafo);
		frameGrafo.pack();
		frameGrafo.setBounds(frame.getX() + 250, frame.getY(), 640, 650);
		frameGrafo.setVisible(true);
	}
}