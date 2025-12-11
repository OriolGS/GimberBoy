package sprites;

import controlador.GestorDeDibuix;
import model.ListModelDeJoc;
import vista.VistaDeJoc;
import vista.ZonaDeJoc;

public class Misil extends Sprite {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int SPEED = 2;
    private static final boolean IS_ENEMY = true;
    private static final boolean IS_HITTABLE = true;

    public Misil(int x, int y, Sprite origin) {
        super(x, y, WIDTH, HEIGHT, 1, "Misil", IS_ENEMY, IS_HITTABLE);
        ListModelDeJoc.getInstancia().balas.add(this);
    }

    @Override
    public void pintar() {
        GestorDeDibuix.getInstancia()
                .pintar(getImageString(), x, y, width, height);
    }

    @Override
    public void animar() {
        setY(getY() + SPEED);

        int minX = getX();
        int minY = getY();
        int maxX = getX() + getWidth();
        int maxY = getY() + getHeight();

        for (Sprite sprite : ListModelDeJoc.getInstancia().vEntes) {
            if (!sprite.isHittable()) continue;

            if (this.isEnemy != sprite.isEnemy()) {
                boolean overlap =
                    sprite.getX() < maxX &&
                    sprite.getX() + sprite.getWidth() > minX &&
                    sprite.getY() < maxY &&
                    sprite.getY() + sprite.getHeight() > minY;

                if (overlap) {
                    // System.out.println("Hit detected");

                    sprite.setLives(sprite.getLives() - 1);
                    this.setLives(0);   // Remove missile
                    return;
                }
            }
        }

        if (getY() >= ZonaDeJoc.ALTO - getHeight()) {
            setLives(0);
        }
    }

    @Override
    public void killSprite() {
        ListModelDeJoc.getInstancia().balas.remove(this);
		System.out.println("Misil destroyed");
    }

}
