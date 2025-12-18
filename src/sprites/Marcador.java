package sprites;

import controlador.GestorDeDibuix;
import vista.ZonaDeJoc;

public class Marcador extends Sprite {
    private static final String IMAGE_STRING = "MARCADOR";
    private static final int WIDTH = IMAGE_STRING.length() * 10;
    private static final int HEIGHT = 20;
    private static final boolean IS_ENEMY = false;
    private static final boolean IS_HITTABLE = false;

    public Marcador() {
        super(ZonaDeJoc.ANCHO - WIDTH - 15 * 2, 15, WIDTH, HEIGHT, 0, IMAGE_STRING, IS_ENEMY, IS_HITTABLE);
    }

    @Override
    public void pintar() {
        GestorDeDibuix.getInstancia().pintarTexto(getImageString(), getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void animar() {
    }

    @Override
    public int getLives() {
        System.err.println("This is the Scoreboard, it has no lives!");
        return 0;
    }

    @Override
    public void setLives(int lives) {
        System.err.println("This is the Scoreboard, it has no lives!");
    }

    @Override
    public void setImageString(String contador) {
        super.setImageString(IMAGE_STRING + contador);
    }

}
