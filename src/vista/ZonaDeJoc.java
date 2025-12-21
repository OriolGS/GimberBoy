package vista;

import controlador.ControladorDeJoc;
import controlador.GestorDeDibuix;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.ListModelDeJoc;

@SuppressWarnings("deprecation")
public class ZonaDeJoc extends JPanel implements Observer {

	private static final long serialVersionUID = -3271023844287269386L;

	// Dimensió de la zona de joc, són constants de moment, per poder compartir-les
	public static final int ANCHO = 1000;
	public static final int ALTO = 750;
	public static final int MARGIN = 20;
	public static final int WIDTH_WITHOUT_MARGIN = ANCHO - MARGIN;
	public static final int HEIGHT_WITHOUT_MARGIN = ALTO - MARGIN;
	public boolean restart = true;

	private final BufferedImage doblebufer; // final en properes iteracions
	private ControladorDeJoc controlador;// controlador del game

	// constructor: Generem un bufer per gestionar els elements gràfics
	public ZonaDeJoc() {

		// El nostre Doble Búfer
		doblebufer = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
		// iniciarem el GestorDeDibuix
		iniciMotorGrafic();

		// el controlador dels esdeveniments en zona de joc, es el motor del game.
		controlador = new ControladorDeJoc(this);

		ListModelDeJoc.getInstancia().addObserver(this);

		// Dimensionem el JPanel Zona de Joc
		setBounds(0, 0, ZonaDeJoc.ANCHO, ZonaDeJoc.ALTO);
		setVisible(true);

	}

	public void jugar() {
		while (restart) {
			controlador.jugar();
			setRestart(controlador.gameOverView());
		}
		System.out.println("Game ended");
		finalizar();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ListModelDeJoc.getInstancia().pintarJoc();
		// Draw HUD/text on top of the sprites so it is not overwritten by the
		// background
		g.drawImage(doblebufer, 0, 0, this);
	}

	public void finalizar() {
		// Deixa de mirar patró observer
		setVisible(false);
	}

	// inicialitzem el motorGraphic GestorDedibuix i carreguem les imatges del game
	// en el motor
	public void iniciMotorGrafic() {
		// Utilitzem el Patró singlenton per tal inicialitzar el motor graphic
		GestorDeDibuix.getInstancia().inicializar(new MediaTracker(this), doblebufer.getGraphics(), this);
		String cacheImatges[][] = {
				{ "FONS", "bg.png" },
				{ "GimberBoy", "GimberBoy.gif" },
				{ "NauEspacial", "NauEspacial.png" },
				{ "BolaL", "BolaL.gif" },
				{ "BolaR", "BolaR.gif" },
				{ "Bullet", "Bullet.gif" },
				{ "Misil", "Misil.png" },
				{ "GameOver", "GameOver.png" }
		};

		try {
			// Carreguem imatge a imatge al gestor De Dibuix
			for (int i = 0; i < cacheImatges.length; i++) {
				GestorDeDibuix.getInstancia().afegirImatge(cacheImatges[i][0], loadImage(cacheImatges[i][1]));
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Càrrega d'una imatge del disc o desde una URL en el bufferedImages
	public BufferedImage loadImage(String nombre) {
		URL url = null;
		try {
			url = getClass().getClassLoader().getResource(nombre);
			// la classe ImageIO carrega l'imatge en un BufferedImatge
			return ImageIO.read(url);

		} catch (Exception e) {
			System.err.println("No s'ha pogut carregar la imatge " + nombre + " de " + url);
			System.err.println("L'ha sigut: " + e.getClass().getName() + " " + e.getMessage());
			System.exit(0);
			return null;
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

	public void addMouseListener(ControladorDeJoc controlador) {
		super.addMouseListener(controlador);
	}

	public void addMouseMotionListener(ControladorDeJoc controlador) {
		super.addMouseMotionListener(controlador);
	}

	public void setRestart(boolean exit) {
		this.restart = exit;
	}
}
