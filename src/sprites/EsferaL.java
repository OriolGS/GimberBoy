package sprites;

import controlador.GestorDeDibuix;
import vista.ZonaDeJoc;

public class EsferaL extends Sprite {
    private static final String IMAGE_STRING = "BolaL";
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    private static final int LIVES = 2;
    private static final boolean IS_ENEMY = true;
    private static final boolean IS_HITTABLE = true;
    private boolean goRight = true;
    private boolean goDown = true;

    public EsferaL() {
        super((int) (Math.random() * ZonaDeJoc.ANCHO / 2), (int) (Math.random() * ZonaDeJoc.ALTO / 2),
                WIDTH, HEIGHT, LIVES, IMAGE_STRING, IS_ENEMY, IS_HITTABLE);
    }

    @Override
    public void pintar() {
        GestorDeDibuix.getInstancia().pintar(getImageString(), getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void animar() {
        x = isGoRight() ? getX() + 2 : getX() - 2;
        y = isGoDown() ? getY() + 2 : getY() - 2;
        setX(x);
        setY(y);

        if (getX() >= (ZonaDeJoc.ANCHO - getWidth() - ZonaDeJoc.MARGIN)) {
            setGoRight(false);
        } else if (getX() <= (getWidth() + ZonaDeJoc.MARGIN)) {
            setGoRight(true);
        }

        if (getY() >= ((ZonaDeJoc.ALTO / 3 * 2) - getHeight() - ZonaDeJoc.MARGIN)) {
            setGoDown(false);
        } else if (getY() <= getHeight() + ZonaDeJoc.MARGIN) {
            setGoDown(true);
        }
    }

    public void setGoRight(boolean goRight) {
        this.goRight = goRight;
    }

    public void setGoDown(boolean goDown) {
        this.goDown = goDown;
    }

    public boolean isGoRight() {
        return goRight;
    }

    public boolean isGoDown() {
        return goDown;
    }

}
