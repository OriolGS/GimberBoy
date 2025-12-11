package sprites;

import controlador.GestorDeDibuix;
import vista.ZonaDeJoc;

public class EsferaR extends Sprite {
    private static final String IMAGE_STRING = "BolaR";
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private static final int LIVES = 1;
    private static final boolean IS_ENEMY = true;
    private static final boolean IS_HITTABLE = true;
    private boolean goRight = false;
    private boolean goDown = true;

    public EsferaR() {
        super((int) (Math.random() * (ZonaDeJoc.ANCHO - ZonaDeJoc.ANCHO / 2) + ZonaDeJoc.ANCHO / 2), 
            (int) (Math.random() * (ZonaDeJoc.ALTO / 3)),
            WIDTH, HEIGHT, LIVES, IMAGE_STRING, IS_ENEMY, IS_HITTABLE);
    }

    @Override
    public void pintar() {
        GestorDeDibuix.getInstancia().pintar(getImageString(), getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void animar() {
        x = isGoRight() ? getX() + 4 : getX() - 4;
        y = isGoDown() ? getY() + 4 : getY() - 4;
        setX(x);
        setY(y);
        
        if (getX() >= (ZonaDeJoc.ANCHO - getWidth() - ZonaDeJoc.MARGIN)) {
            setGoRight(false);
        } else if (getX() <= (getWidth() + ZonaDeJoc.MARGIN)) {
            setGoRight(true);
        }

        if (getY() >= ((ZonaDeJoc.ALTO / 3 * 2)   - getHeight() - ZonaDeJoc.MARGIN)) {
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
