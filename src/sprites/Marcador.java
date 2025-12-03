package sprites;

import controlador.GestorDeDibuix;
import vista.ZonaDeJoc;

public class Marcador extends Sprite {
    private static final String TITLE = "MARCADOR";
    private static final int WIDTH = TITLE.length() * 10;
    private static final int HEIGHT = 20;

    public Marcador() {
        super(ZonaDeJoc.ANCHO-WIDTH - 15*2, 15, WIDTH, HEIGHT);
    }

    @Override
    public void pintar() {
        GestorDeDibuix.getInstancia().pintarTexto(TITLE, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void animar() {
        // TODO Auto-generated method stub
    }

    @Override
    public int getVidas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVidas'");
    }
    
}
