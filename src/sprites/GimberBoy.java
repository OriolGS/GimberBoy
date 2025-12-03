package sprites;

import controlador.GestorDeDibuix;
import vista.ZonaDeJoc;

public class GimberBoy extends Sprite {
    private static final int WIDTH = 25;
    private static final int HEIGHT = 40;

    public GimberBoy() {
        super((ZonaDeJoc.ANCHO/2)-(WIDTH/2), (ZonaDeJoc.ALTO/3*3)-(HEIGHT*2), WIDTH, HEIGHT);
    }

    public GimberBoy(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }


    @Override
    public void pintar() {
        GestorDeDibuix.getInstancia().pintar("ROBOT", getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void animar() {}

    @Override
    public int getVidas() {
        throw new UnsupportedOperationException("Unimplemented method 'getVidas'");
    }
    
    
}