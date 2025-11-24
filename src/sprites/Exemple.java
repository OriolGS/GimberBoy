/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sprites;

import controlador.GestorDeDibuix;
import vista.ZonaDeJoc;

/**
 *
 * @author Ernest
 * @version 2025
 */

public class Exemple extends Sprite {

	public Exemple() {
		super(0,70);
	}
	
	public void animar() {
		x+=1;
		if (x>= ZonaDeJoc.ANCHO) 
			x=0;	
	}

	public void pintar() {
		// pintem aquest sprite en el doblebufer
		// el nostra GestorDeDibuix el mètode pintar es troba sobrecarregat 
		GestorDeDibuix.getInstancia().pintar("BOLA", getX(), getY(), 30);
	}

	@Override
	public int getVidas() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getVidas'");
	}

	
}

