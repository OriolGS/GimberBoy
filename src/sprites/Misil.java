package sprites;

import controlador.GestorDeDibuix;
import vista.ZonaDeJoc;

public class Misil extends Sprite {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int SPEED = 2;
    private Sprite origin;

    public Misil(int x, int y, Sprite origin) {
        super(x, y, WIDTH, HEIGHT, 1, "Misil");
        this.origin = origin;
    }

    @Override
    public void pintar() {
        GestorDeDibuix.getInstancia()
                .pintar(getImageString(), x, y, width, height);
    }

    @Override
    public void animar() { 
        setY(getY() + SPEED);

        if (getY() >= ZonaDeJoc.ALTO - getHeight()) {
            setLives(0);
            return;
        }
    }
}
