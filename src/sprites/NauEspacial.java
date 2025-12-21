package sprites;

import controlador.GestorDeDibuix;
import vista.ZonaDeJoc;

import java.util.ArrayList;
import java.util.Iterator;

public class NauEspacial extends Sprite {
    // Simulate settings
    private static final String IMAGE_STRING = "NauEspacial";
    private static final int WIDTH = 40;
    private static final int HEIGHT = 30;
    private static final int LIVES = 3;
    private static final boolean IS_ENEMY = true;
    private static final boolean IS_HITTABLE = true;
    private static final int DISAPPEAR_FACTOR = 500;
    private static final int APPEAR_FACTOR = 400;

    // Exclusive variables for this Sprite
    private boolean displayed;
    private int disappear_factor;
    private int appear_factor;

    public NauEspacial() {
        super((int) (Math.random() * (ZonaDeJoc.ANCHO - WIDTH)),
                (int) (Math.random() * (ZonaDeJoc.ALTO / 3 * 2 - HEIGHT)),
                WIDTH, HEIGHT, LIVES, IMAGE_STRING, IS_ENEMY, IS_HITTABLE);
        this.leafs = new ArrayList<>();
        this.displayed = true;
        this.disappear_factor = DISAPPEAR_FACTOR;
        this.appear_factor = APPEAR_FACTOR;
    }

    // Composite method
    public void shoot() {
        if (displayed && this.leafs.size() < 1) {
            Misil m = new Misil(this.getX() + this.getWidth() / 2 - 3, this.getY() + this.getHeight(), this);
            leafs.add(m);
        }
    }

    private void isDisplayable() {
        if (disappear_factor != 0) {
            disappear_factor--;
            setDisplayed(true);
        } else if (appear_factor != 0) {
            appear_factor--;
            setDisplayed(false);
            this.setX(ZonaDeJoc.ANCHO * 2);
            this.setY(ZonaDeJoc.ALTO * 2);
        } else {
            disappear_factor = DISAPPEAR_FACTOR;
            appear_factor = APPEAR_FACTOR;
            this.setX((int) (Math.random() * (ZonaDeJoc.ANCHO - WIDTH)));
            this.setY((int) (Math.random() * (ZonaDeJoc.ALTO / 3 * 2 - HEIGHT)));
        }
    }

    @Override
    public void pintar() {
        if (displayed) {
            GestorDeDibuix.getInstancia().pintar(getImageString(), getX(), getY(), getWidth(), getHeight());
        } else {
            GestorDeDibuix.getInstancia().removeImage(getX(), getY(), getWidth(), getHeight());
        }

        isDisplayable();
    }

    @Override
    public void animar() {
        this.shoot();

        Iterator<Sprite> it = leafs.iterator();
        while (it.hasNext()) {
            Sprite s = it.next();
            s.animar();

            if (s.getLives() == 0) {
                it.remove();
            }
        }
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

}
