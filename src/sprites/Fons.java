package sprites;

import controlador.GestorDeDibuix;
import vista.ZonaDeJoc;

public class Fons extends Sprite {

    public Fons() {
        this(0, 0);
    }
    
    public Fons(int x, int y) {
        super(x, y, ZonaDeJoc.ANCHO, ZonaDeJoc.ALTO);
      
    }

    @Override
    public void pintar() {
        GestorDeDibuix.getInstancia().pintar("FONS", getX(), getY(), getWidth(), getHeight());
    }

  

    @Override
    public void animar() {}

    @Override
    public int getVidas() {
        throw new UnsupportedOperationException("Unimplemented method 'getVidas'");
    }

  
}
