package sprites;

import controlador.GestorDeDibuix;
import vista.ZonaDeJoc;

public class GimberBoy extends Sprite {
    private static final String IMAGE_STRING = "GimberBoy";
    private static final int WIDTH = 40;
    private static final int HEIGHT = 30;
    private static final int LIVES = 3;

    public GimberBoy() {
        super((ZonaDeJoc.ANCHO/2)-(WIDTH/2), (ZonaDeJoc.ALTO/3*3)-(HEIGHT*2), WIDTH, HEIGHT, LIVES, IMAGE_STRING);
    }

    public GimberBoy(int x, int y) {
        super(x, y, WIDTH, HEIGHT, LIVES, IMAGE_STRING);
    }


    @Override
    public void pintar() {
        GestorDeDibuix.getInstancia().pintar(IMAGE_STRING, getX(), getY(), getWidth(), getHeight());
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