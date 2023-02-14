import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
	
	private static final long serialVersionUID = -5988765421013846922L;
	//Guardamos los vértices en un hashmap para poder encontrarlos por su index, ya que es único.
	public static HashMap<Integer, Vertex> vertices = new HashMap();
	public static List<Face> caras = new ArrayList();
	public static Camera camara;
	public static JFrame ventana;
	
	//Los controles para poder movernos
	private static KeyListener listener;
	
	private static int width = 600;
	private static int height = 600;

	public static void main(String[] args) {
		
		leerInput("exampleCube");
		//leerInput("tomb3");
		iniciarCamara();
		
		crearVentana();
		
		
		
		while (1==1) {
			
			ventana.repaint();
			
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//Este color es un negro con transparencia, lo que quiere decir que por donde haya mas caras se debería ver mas oscuro
		g.setColor(new Color(0,0,0,100));
		
		for (Face face : caras) {
			
			//Declaramos los dos arrays, que van a tener la misma dimensión que el número de vértices
			int[] verticesX = new int[face.getVertices().size()];
			int[] verticesY = new int[face.getVertices().size()];
			
			int indexArrays = 0;
			
			//Cargamos el listado de vértices para poder empezar a recalcularlos
			List<Vertex> listadoCara = face.getVertices();
			
			//Recalculamos cada vértice
			for (Vertex vertex : listadoCara) {
				
				double vX = vertex.getvX();
				double vY = vertex.getvY();
				double vZ = vertex.getvZ();
				
				double cX = camara.getX();
				double cY = camara.getY();
				double cZ = camara.getZ();
				
				double difX = cX - vX;
				double difY = cY - vY;
				double difZ = cZ - vZ;
				
				//Angulo X es igual a cateto opuesto / hipotenusa
				double senX = difX/(Math.sqrt(difX*difX+difY*difY));
				double senZ = difZ/(Math.sqrt(difZ*difZ+difY*difY));
				
				//Con esto hemos calculado los valores de los senos
				//Sacamos el valor de los ángulos con la fórmula del arco seno
				double angX = Math.asin(senX);
				double angZ = Math.asin(senZ);
				
				//Como tanx = cateto Opuesto / cateto Adyacente
				//tanx * cateto Adyacente = cateto Opuesto
				//Pero cateto Adayacente = 1, ya que sería la distancia Y entre la cámara y el plano
				//Por tanto: tanx = cateto Opuesto
				
				double X = Math.tan(angX);
				double Z = Math.tan(angZ);
				
				//Amplificacmos los valores para castearlos a int
				//Los ponemos con referencia al centro de la pantalla
				verticesX[indexArrays] = (int) (X * 1000) + width/2;
				//Como Z es el eje que nos mide la altura, en el plano 2D se llama Y
				verticesY[indexArrays] = (int) (Z * 1000) + height/2;
				
				indexArrays++;
			}
			
			
			g.fillPolygon(verticesX, verticesY, face.getVertices().size());
			g.drawPolygon(verticesX, verticesY, face.getVertices().size());
		}
	}
	
	private static void crearVentana() {
		ventana = new JFrame("KaS - .obj visualizer");
		var panel = new Main();
        ventana.setBackground(Color.white);
        ventana.getContentPane().setPreferredSize(new Dimension(width,height));
        ventana.pack();
        ventana.addKeyListener(listener);
        ventana.setFocusable(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.getContentPane().add(panel, BorderLayout.CENTER);
        ventana.setVisible(true);
        
	}	

	/**
	 * Creamos una cámara que después podremos controlar y con la que se van a calcular todas las proyecciones.
	 * la posisión que le damos por defecto es: <br/>
	 * X = 0<br/>
	 * Y = 10<br/>
	 * Z = 0
	 */
	private static void iniciarCamara() {
		camara = new Camera(0,10,0);
		listener = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
                case KeyEvent.VK_W:
                	camara.setY(camara.getY()-0.1);
                    break;
                case KeyEvent.VK_S:
                	camara.setY(camara.getY()+0.1);
                    break;
                case KeyEvent.VK_A:
                	camara.setX(camara.getX()+0.1);
                    break;
                case KeyEvent.VK_D:
                	camara.setX(camara.getX()-0.1);
                    break;
				case KeyEvent.VK_Q:
	            	camara.setZ(camara.getZ()-0.1);
	                break;
				case KeyEvent.VK_E:
	            	camara.setZ(camara.getZ()+0.1);
	                break;
	            }
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		};
	}

	/**
	 * Este método lee el archivo cuyo nombre le demos y prepara todos los vértices y caras para poder operar con ellos.
	 * @param filename
	 */
	private static void leerInput(String filename) {
		
		List<String> input = ReadFile.listaString(filename);
		
		for (String string : input) {
			String[] linea = string.split(" ");
			
			if (linea[0].compareTo("v")==0) {
				//Aquí nos encontramos con un vértice
				Vertex v = new Vertex(linea);
				vertices.put(v.getIndex(), v);
			} else if (linea[0].compareTo("f")==0) {
				//Aquí en teoría nos encontramos con una cara
				caras.add(new Face(linea));
			}
		}
	}

}
