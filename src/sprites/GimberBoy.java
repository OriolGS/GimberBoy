package sprites;

import controlador.GestorDeDibuix;
import model.ListModelDeJoc;
import vista.ZonaDeJoc;

public class GimberBoy extends Sprite {
    private static final String IMAGE_STRING = "GimberBoy";
    private static final int WIDTH = 40;
    private static final int HEIGHT = 30;
    private static final int LIVES = 3;
    private static final boolean IS_ENEMY = false;
    private static final boolean IS_HITTABLE = true;

    public GimberBoy() {
        super((ZonaDeJoc.ANCHO / 2) - (WIDTH / 2), (ZonaDeJoc.ALTO / 3 * 3) - (HEIGHT * 2), WIDTH, HEIGHT, LIVES,
                IMAGE_STRING, IS_ENEMY, IS_HITTABLE);
    }

    public GimberBoy(int x, int y) {
        super(x, y, WIDTH, HEIGHT, LIVES, IMAGE_STRING, IS_ENEMY, IS_HITTABLE);
    }

    @Override
    public void pintar() {
        GestorDeDibuix.getInstancia().pintar(getImageString(), getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void animar() {
    }

    @Override
    public void setLives(int lives) {
        super.setLives(lives);
        if (super.getLives() == 0) {
            System.exit(0);
        }
    }

    @Override
    public void setMarcador(int marcador) {
        super.setMarcador(marcador);
        ListModelDeJoc.getInstancia().m.setImageString(String.valueOf(marcador));

    }

}