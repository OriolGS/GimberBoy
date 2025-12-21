package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import model.ListModelDeJoc;
import sprites.Fons;
import vista.ZonaDeJoc;

public class ControladorDeJoc implements MouseListener, MouseMotionListener {

	// Per mirar que tots els torns triguin el mateix
	private long usedTime;
	// vista és el JPanel en el que visualitzem el joc
	private final ZonaDeJoc vista;
	private int i = (int) (Math.random() * 100 + 1);
	private int j = 1;
	private boolean response = false;
	private boolean restart = false; 

	public ControladorDeJoc(ZonaDeJoc vista) {
		this.vista = vista;
	}

	public void jugar() {
		prepareGameController();

		// game loop for DoomsDay
		while (ListModelDeJoc.getInstancia().getPlaying()) {

			// tip jugable: introduim un retard per tal aturar-lo uns 20 milisegons
			if (usedTime < 20)
				retardo();
			long startTime = System.currentTimeMillis();

			// comportaments asincrons: ratoli
			// utilitzant els mouseListener i mouseMotionListener

			j++;
			i++;
			if (j >= 300) {
				ListModelDeJoc.getInstancia().añadirEsferaL();
				ListModelDeJoc.getInstancia().añadirEsferaR();
				j = 0;

			}

			// update: Comportaments sincrons
			// generem per a cada sprite una nova posició
			// TODO: animarJoc, pintar and notificar a los observadores
			ListModelDeJoc.getInstancia().animarJoc();

			// Render: Cridem al motor de gràfics
			// Temps que es triga en fer un torn.
			usedTime = System.currentTimeMillis() - startTime;

			if (usedTime > 1) {
			}
		}
	}

	private void prepareGameController() {
		setResponse(false);
		setRestart(false);
		vista.addMouseListener(this);
		vista.addMouseMotionListener(this);
		j = 1;
		ListModelDeJoc.getInstancia().iniciarJoc();
	}

	public void retardo() {
		try {
			Thread.sleep(20 - usedTime);

		} catch (InterruptedException ie) {

		}
	}

	public boolean gameOverView() {
		ListModelDeJoc.getInstancia().f = new Fons("GameOver");
		ListModelDeJoc.getInstancia().f.pintar();
		while (!response) {
			// Wait for user input
			System.out.println("Click to restart");
		}
		return restart;
	}

	public void mousePressed(MouseEvent e) {
		if (ListModelDeJoc.getInstancia().getPlaying()) {
			ListModelDeJoc gm = ListModelDeJoc.getInstancia();
			gm.hemPitjatElMouse();
			gm.notifyObservers();
		} else {
			this.setResponse(true);
			this.setRestart(true);
		}
	}

	public void mouseMoved(MouseEvent e) {
		ListModelDeJoc gm = ListModelDeJoc.getInstancia();
		gm.coordenadesDelMouse(e.getX(), e.getY());
		gm.notifyObservers();
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public boolean isResponse() {
		return response;
	}

	public void setResponse(boolean response) {
		this.response = response;
	}

	public boolean isRestart() {
		return restart;
	}

	public void setRestart(boolean restart) {
		this.restart = restart;
	}

}
