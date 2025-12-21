package sprites;

import java.util.ArrayList;

import controlador.GestorDeDibuix;
import model.ListModelDeJoc;
import vista.ZonaDeJoc;

public class GimberBoy extends Sprite {
    // Simulate settings
    private static final String IMAGE_STRING = "GimberBoy";
    private static final int WIDTH = 40;
    private static final int HEIGHT = 30;
    private static final int LIVES = 3;
    private static final boolean IS_ENEMY = false;
    private static final boolean IS_HITTABLE = true;

    public GimberBoy() {
        super((ZonaDeJoc.ANCHO / 2) - (WIDTH / 2), (ZonaDeJoc.ALTO / 3 * 3) - (HEIGHT * 2), WIDTH, HEIGHT, LIVES,
                IMAGE_STRING, IS_ENEMY, IS_HITTABLE);
        this.leafs = new ArrayList<>();
    }

    // Composite method
    public void shoot() {
        Bullet b = new Bullet(this.getX() + this.getWidth() / 2, this.getY(), this);
        leafs.add(b);
        
    }

    @Override
    public void pintar() {
        GestorDeDibuix.getInstancia().pintar(getImageString(), getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void animar() {
    }

    @Override
    public void setLives(int lives) {
        super.setLives(lives);
        if (this.getLives() == 0) {
            ListModelDeJoc.getInstancia().gameOver();
        }
    }

    // TODO: Make marcador a singleton so it can be updated from the sprites that are killed
    @Override
    public void setMarcador(int marcador) {
        super.setMarcador(marcador);
        ListModelDeJoc.getInstancia().m.setImageString(String.valueOf(marcador));

    }

}