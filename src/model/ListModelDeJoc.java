package model;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Observable;

import sprites.*;
import vista.ZonaDeJoc;

@SuppressWarnings("deprecation")
public class ListModelDeJoc extends Observable implements IModelDeJoc{

	public Fons f = null;
	private GimberBoy g = null;
	public Marcador m = null;
	public NauEspacial n = null;
	private boolean playing = true; 

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
		vEntes.clear();
		balas.clear();
		f = new Fons("FONS");
		g = new GimberBoy();
		m = new Marcador();
		n = new NauEspacial();
		playing = true;

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
			s.animar();
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
		g.shoot();
	}

	public void coordenadesDelMouse(int x, int y) {
		if (y >= ZonaDeJoc.HEIGHT_WITHOUT_MARGIN - g.getHeight()) {
			// Mouse below screen
			y = ZonaDeJoc.HEIGHT_WITHOUT_MARGIN - g.getHeight();
		} else if (y <= ZonaDeJoc.ALTO / 3 * 2) {
			// Mouse above expected zone
			y = ZonaDeJoc.ALTO / 3 * 2;
		}

		if (x >= ZonaDeJoc.WIDTH_WITHOUT_MARGIN - g.getWidth()) {
			x = ZonaDeJoc.WIDTH_WITHOUT_MARGIN - g.getWidth();
		} else if (x <= ZonaDeJoc.MARGIN) {
			x = ZonaDeJoc.MARGIN;
		}
		g.setX(x - (g.getWidth() / 2));
		g.setY(y);

	}

	public void añadirEsferaL() {
		vEntes.add(new EsferaL());
	}

	public void añadirEsferaR() {
		vEntes.add(new EsferaR());
	}

	@Override
	public void afegirElement(Sprite b) {
		// TODO Auto-generated method stub
	}

	@Override
	public void eliminarElement(Sprite b) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'eliminarElement'");
	}

	@Override
	public Sprite getSprite(int n) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getSprite'");
	}

	@Override
	public void gameOver() {
		this.playing = false;
		vEntes.clear();
		balas.clear();
	}

	public boolean getPlaying() {
		return this.playing;
	}

}
