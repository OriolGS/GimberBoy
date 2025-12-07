package sprites;

import controlador.GestorDeDibuix;
import vista.ZonaDeJoc;

public class NauEspacial extends Sprite {
    private static final String IMAGE_STRING = "NauEspacial";
    private static final int WIDTH = 40;
    private static final int HEIGHT = 30;
    private static final int LIVES = 3;

    // TODO: Improve mechanism to control appearance
    private static int disppear_factor = 100;
    private static int appear_factor = 75;
    private boolean displayed = true;

    public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    public NauEspacial() {
        super((int) (Math.random() * (ZonaDeJoc.ANCHO - WIDTH)), (int) (Math.random() * (ZonaDeJoc.ALTO / 3 * 2 - HEIGHT)),
                WIDTH, HEIGHT, LIVES, IMAGE_STRING);
    }

    @Override
    public void pintar() {
        if (displayed) {
            GestorDeDibuix.getInstancia().pintar(getImageString(), getX(), getY(), getWidth(), getHeight());
        } else {
            GestorDeDibuix.getInstancia().removeImage(this.x, this.y, this.width, this.height);
        }
        isDisplayable();
    }

    @Override
    public void animar() {
    }

    // TODO: Create a factory to controll this
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
