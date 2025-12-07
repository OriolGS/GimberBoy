package sprites;

import controlador.GestorDeDibuix;
import vista.ZonaDeJoc;

public class Marcador extends Sprite {
    private static final String IMAGE_STRING = "MARCADOR";
    private static final int WIDTH = IMAGE_STRING.length() * 10;
    private static final int HEIGHT = 20;

    public Marcador() {
        super(ZonaDeJoc.ANCHO-WIDTH - 15*2, 15, WIDTH, HEIGHT, 0, IMAGE_STRING);
    }

    @Override
    public void pintar() {
        GestorDeDibuix.getInstancia().pintarTexto(IMAGE_STRING, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void animar() {
    }

    @Override
    public int getLives() {
        System.err.println("This is the Scoreboard, it has no lives!");
        return 0;
    }
    @Override
    public void setLives(int lives) {
        System.err.println("This is the Scoreboard, it has no lives!");
    }
    
}
