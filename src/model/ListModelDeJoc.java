package model;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Observable;

import sprites.*;
import controlador.ControladorDeJoc;
import controlador.GestorDeDibuix;

import sprites.*;
import vista.ZonaDeJoc;

public class ListModelDeJoc extends Observable {

	// Patró Adapter, ROL Adaptad

	private GimberBoy g = new GimberBoy();
	private Marcador m = new Marcador();
	public NauEspacial n;

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
		// TODO: show initial srpites
		vEntes.add(0, new Fons());
		vEntes.add(1, g);
		vEntes.add(2, m);

	}

	public void animarJoc() {

		// aqui controlar listas clon de enemigos y balas

		Sprite s;
		Sprite b;
		ListIterator<Sprite> iter = ((LinkedList<Sprite>) vEntes.clone()).listIterator(0);
		ListIterator<Sprite> iterBala = ((LinkedList<Sprite>) balas.clone()).listIterator(0);

		while (iter.hasNext()) {
			s = iter.next();
			s.animar(); // Show sprites in game
		}

		while (iterBala.hasNext()) {
			b = iterBala.next();
			if (b.getVidas() > 0) {
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
		ListIterator<Sprite> iter = ((LinkedList<Sprite>) vEntes.clone()).listIterator(0);
		ListIterator<Sprite> iterBala = ((LinkedList<Sprite>) balas.clone()).listIterator(0);

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

		balas.add(new Bullet(g.getX(), g.getY() - 15));

	}

	public void coordenadesDelMouse(int x, int y) {
		Sprite s;
		// TODO 3: Asignar Mouse a Gimberboy
		ListIterator<Sprite> iter = vEntes.listIterator(this.g.getX());
		s = iter.next();
		if (y >= ZonaDeJoc.ALTO / 3 * 2 + 15) { // movemos y
			y = ZonaDeJoc.ALTO - 50;
		} else {
			y = ZonaDeJoc.ALTO / 3 * 2;
		}
		if (x >= ZonaDeJoc.ANCHO - 40) { // movemos x
			x = ZonaDeJoc.ANCHO - 40;
		} else if (x <= 10) {
			x = 10;
		}
		s.setX(x);
		s.setY(y);
	}

	public void añadirEsferaL(){

	}

	public void añadirEsferaR(){

	}

} // end ControladorDeJoc
