package sprites;

import controlador.GestorDeDibuix;

public class Bullet extends Sprite {
    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;
    private int vida = 1;

    public Bullet(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }

    @Override
    public void pintar() {
        GestorDeDibuix.getInstancia().pintar("BOLA", getX(), getY(), getWidth(), getHeight());    
    }

    @Override
    public void animar() {
        if (getY() > 0 - getHeight()) {
            setY(getY() - 2);
        } else {
            this.vida = 0;
        }
    }

    @Override
    public int getVidas() {
        // throw new UnsupportedOperationException("Unimplemented method 'getVidas'");
        return this.vida;
    }
    
}