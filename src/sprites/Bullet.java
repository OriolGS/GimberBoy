package sprites;

import controlador.GestorDeDibuix;
import model.ListModelDeJoc;

public class Bullet extends Sprite {
    private static final String IMAGE_STRING = "Bullet";
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int LIVES = 1;

    public Bullet(int x, int y) {
        super(x, y, WIDTH, HEIGHT, LIVES, IMAGE_STRING);
    }

    @Override
    public void pintar() {
        GestorDeDibuix.getInstancia().pintar(getImageString(), getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void animar() {
        setY(getY() - 2);
        
        if (getY() <= 0 + getHeight()) {
            setLives(0);
            return;
        }
    }

    @Override
    public void killSprite() {
        ListModelDeJoc.getInstancia().balas.remove(this);
		System.out.println("Bullet destroyed");
    }

}