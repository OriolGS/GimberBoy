package sprites;

import controlador.GestorDeDibuix;
import vista.VistaDeJoc;
import vista.ZonaDeJoc;

public class GimberBoy extends Sprite {
    private static final int WIDTH = 25;
    private static final int HEIGHT = 40;
    private static final int LIVES = 3;

    public GimberBoy() {
        super((ZonaDeJoc.ANCHO/2)-(WIDTH/2), (ZonaDeJoc.ALTO/3*3)-(HEIGHT*2), WIDTH, HEIGHT, LIVES);
    }

    public GimberBoy(int x, int y) {
        super(x, y, WIDTH, HEIGHT, LIVES);
    }


    @Override
    public void pintar() {
        GestorDeDibuix.getInstancia().pintar("ROBOT", getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void animar() {}

    @Override
    public void setLives(int lives) {
        // TODO Auto-generated method stub
        super.setLives(lives);
        if (super.getLives() == 0) {
            System.out.println("GimberBoy destroyed. Game Over");
            System.exit(0);
        }
    }

    
    
}