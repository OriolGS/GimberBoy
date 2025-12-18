package model;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Observable;

import sprites.*;
import vista.ZonaDeJoc;

@SuppressWarnings("deprecation")
public class ListModelDeJoc extends Observable {

	// Patró Adapter, ROL Adaptad

	public Fons f = new Fons();
	private GimberBoy g = new GimberBoy();
	public Marcador m = new Marcador();
	public NauEspacial n = new NauEspacial();

	public LinkedList<Sprite> vEntes = new LinkedList<Sprite>();
	public LinkedList<Sprite> balas = new LinkedList<Sprite>();

	// Patró Singleton classe ListModelDeJoc
	private static ListModelDeJoc instancia = null;

	public static ListModelDeJoc getInstancia() {
		if (instancia == null) {
			instancia = new ListModelDeJoc();
		}
		return instancia;
	}

	public void iniciarJoc() {
		// Creates initial entities of the game
		vEntes.add(0, g);
		vEntes.add(1, m);
		vEntes.add(2, n);
	}

	public void animarJoc() {
		Sprite s;
		Sprite b;
		@SuppressWarnings("unchecked")
		ListIterator<Sprite> iter = ((LinkedList<Sprite>) vEntes.clone()).listIterator(0);
		@SuppressWarnings("unchecked")
		ListIterator<Sprite> iterBala = ((LinkedList<Sprite>) balas.clone()).listIterator(0);
		while (iter.hasNext()) {
			s = iter.next();
			s.animar(); // Show sprites in game
		}

		while (iterBala.hasNext()) {
			b = iterBala.next();
			if (b.getLives() > 0) {
				b.animar();
			} else {
				balas.remove(b);
			}
		}

		setChanged();
		notifyObservers();

	}

	public void pintarJoc() {
		Sprite s;
		Sprite b;
		@SuppressWarnings("unchecked")
		ListIterator<Sprite> iter = ((LinkedList<Sprite>) vEntes.clone()).listIterator(0);
		@SuppressWarnings("unchecked")
		ListIterator<Sprite> iterBala = ((LinkedList<Sprite>) balas.clone()).listIterator(0);

		if (f != null) {
			f.pintar();
		}

		while (iter.hasNext()) {
			s = iter.next();
			s.pintar();
		}

		while (iterBala.hasNext()) {
			b = iterBala.next();
			b.pintar();
		}
	}

	public void hemPitjatElMouse() {
		balas.add(new Bullet(g.getX(), g.getY() - ZonaDeJoc.MARGIN, this.g));
	}

	public void coordenadesDelMouse(int x, int y) {
		if (y >= ZonaDeJoc.ALTO - g.getHeight() - ZonaDeJoc.MARGIN) {
			// Mouse below screen
			y = ZonaDeJoc.ALTO - g.getHeight() - ZonaDeJoc.MARGIN;
		} else if (y <= ZonaDeJoc.ALTO / 3 * 2) {
			// Mouse above expected zone
			y = ZonaDeJoc.ALTO / 3 * 2;
		}

		if (x >= ZonaDeJoc.ANCHO - g.getWidth() - ZonaDeJoc.MARGIN) {
			x = ZonaDeJoc.ANCHO - g.getWidth() - ZonaDeJoc.MARGIN;
		} else if (x <= 10) {
			x = 10;
		}
		g.setX(x);
		g.setY(y);

	}

	public void añadirEsferaL() {
		vEntes.add(new EsferaL());
	}

	public void añadirEsferaR() {
		vEntes.add(new EsferaR());
	}

}
