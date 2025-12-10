package sprites;

import controlador.GestorDeDibuix;
import vista.ZonaDeJoc;

import java.util.ArrayList;
import java.util.Iterator;

public class NauEspacial extends Sprite {

    private static final String IMAGE_STRING = "NauEspacial";
    private static final int WIDTH = 40;
    private static final int HEIGHT = 30;
    private static final int LIVES = 3;

    private boolean displayed = true;
    private static int disppear_factor = 100;
    private static int appear_factor = 75;

        public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    public NauEspacial() {
        super((int) (Math.random() * (ZonaDeJoc.ANCHO - WIDTH)), (int) (Math.random() * (ZonaDeJoc.ALTO / 3 * 2 - HEIGHT)),
              WIDTH, HEIGHT, LIVES, IMAGE_STRING);
        this.leafs = new ArrayList<>();
    }

    // Composite method
    public void shoot() {
        if (displayed && leafs.size() <= 5) {
            Misil m = new Misil(x + width / 2 - 3, y + 10, this);
            leafs.add(m);
        }
    }

    @Override
    public void pintar() {
        if (displayed) {
            GestorDeDibuix.getInstancia().pintar(getImageString(), getX(), getY(), getWidth(), getHeight());
            // TODO: create delay
            shoot();
        } else {
            GestorDeDibuix.getInstancia().removeImage(this.x, this.y, this.width, this.height);
        }

        for (Sprite s : leafs) {
            s.pintar();
        }

        isDisplayable();
    }

    @Override
    public void animar() {
        // Animate children
        Iterator<Sprite> it = leafs.iterator();
        while (it.hasNext()) {
            Sprite s = it.next();
            s.animar();

            if (s.getLives() == 0) {
                it.remove();
            }
        }
    }

    private void isDisplayable() {
        if (disppear_factor != 0) {
            disppear_factor --;
            setDisplayed(true);
        } else if (appear_factor != 0) {
            appear_factor --;
            setDisplayed(false);
        } else {
            disppear_factor = 500;
            appear_factor = 400;
            this.setX((int) (Math.random() * (ZonaDeJoc.ANCHO - WIDTH)));
            this.setY((int) (Math.random() * (ZonaDeJoc.ALTO / 3 * 2 - HEIGHT)));
        }
    }

}
