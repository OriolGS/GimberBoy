package sprites;

import controlador.GestorDeDibuix;
import model.ListModelDeJoc;
import vista.ZonaDeJoc;

public class Bullet extends Gun {
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

        checkCollision();

        if (getY() >= ZonaDeJoc.ALTO - getHeight()) {
            setLives(0);
        }
    }

    @Override
    public void killSprite() {
        ListModelDeJoc.getInstancia().balas.remove(this);
    }

    @Override
    public void onCollision(Sprite sprite) {
        sprite.setLives(sprite.getLives() - 1);
        System.out.println("Sprite " + sprite.getImageString() + " lives: " + sprite.getLives());
        this.setLives(0);
        if (!this.isEnemy && sprite.getLives() == 0) {
            this.origin.setMarcador(this.origin.getMarcador() + 1);
        }
    }

}