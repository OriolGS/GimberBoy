/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
public class Fons extends Sprite {

    public Fons(int x, int y) {
        super(x, y);
      
    }

    @Override
    public void pintar() {
        
        GestorDeDibuix.getInstancia().pintar("FONS", getX(), getY(), ZonaDeJoc.ANCHO, ZonaDeJoc.ALTO);
    }

  

    @Override
    public void animar() {
    }

  
}
