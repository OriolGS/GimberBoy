package sprites;

import controlador.GestorDeDibuix;
import vista.ZonaDeJoc;

public class Fons extends Sprite {

    public Fons() {
        this(0, 0);
    }
    
    public Fons(int x, int y) {
        super(x, y, ZonaDeJoc.ANCHO, ZonaDeJoc.ALTO, 0);
    }

    @Override
    public void pintar() {
        GestorDeDibuix.getInstancia().pintar("FONS", getX(), getY(), getWidth(), getHeight());
    }

  

    @Override
    public void animar() {}

    @Override
    public int getLives() {
        System.err.println("This is the Background, it has no lives!");
        return 0;
    }

    @Override
    public void setLives(int lives) {
        System.err.println("This is the Background, it has no lives!");
    }

  
}
