package sprites;

import controlador.GestorDeDibuix;
import vista.ZonaDeJoc;

public class Fons extends Sprite {

    public Fons(String imageString) {
        super(0, 0, ZonaDeJoc.ANCHO, ZonaDeJoc.ALTO, 0, imageString, false, false);
    }

    @Override
    public void pintar() {
        GestorDeDibuix.getInstancia().pintar(getImageString(), getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void animar() {
    }

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
