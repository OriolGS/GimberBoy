package sprites;

import controlador.GestorDeDibuix;
import vista.ZonaDeJoc;

public class GimberBoy extends Sprite {
    private static final int WIDTH = 35;
    private static final int HEIGHT = 50;

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
    public void animar() {
        // TODO: generate code to update position with mouse
        setY(getY() -1);
    }

    @Override
    public int getVidas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVidas'");
    }
    
    
}