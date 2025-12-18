package sprites;

import controlador.GestorDeDibuix;
import vista.ZonaDeJoc;

public class Fons extends Sprite {
    private static final String IMAGE_STRING = "FONS";
    private static final boolean IS_ENEMY = false;
    private static final boolean IS_HITTABLE = false;

    public Fons() {
        this(0, 0);
    }

    public Fons(int x, int y) {
        super(x, y, ZonaDeJoc.ANCHO, ZonaDeJoc.ALTO, 0, IMAGE_STRING, IS_ENEMY, IS_HITTABLE);
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
