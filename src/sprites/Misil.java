package sprites;

import controlador.GestorDeDibuix;
import model.ListModelDeJoc;
import vista.ZonaDeJoc;

public class Misil extends Gun {

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
        this.setLives(0);
    }

}
