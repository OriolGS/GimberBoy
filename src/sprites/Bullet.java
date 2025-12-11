package sprites;

import java.util.List;

import controlador.GestorDeDibuix;
import model.ListModelDeJoc;
import vista.ZonaDeJoc;

public class Bullet extends Sprite {
    private static final String IMAGE_STRING = "Bullet";
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int LIVES = 1;
    private static final boolean IS_ENEMY = false;
    private static final boolean IS_HITTABLE = true;
    private static final int SPEED = -2;
    private Sprite origin;

    public Bullet(int x, int y, Sprite origin) {
        super(x, y, WIDTH, HEIGHT, LIVES, IMAGE_STRING, IS_ENEMY, IS_HITTABLE);
        this.origin = origin;
    }

    @Override
    public void pintar() {
        GestorDeDibuix.getInstancia().pintar(getImageString(), getX(), getY(), getWidth(), getHeight());
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

                    sprite.setLives(sprite.getLives() - 1);
                    this.setLives(0);
                    if (!this.isEnemy && sprite.getLives() == 0) {
                        this.origin.setMarcador(this.origin.getMarcador()+1);
                        System.out.println("Score updated to: " + this.origin.getMarcador());
                    }
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
    }

}