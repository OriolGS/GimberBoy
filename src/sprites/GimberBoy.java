package sprites;

import controlador.GestorDeDibuix;

public class GimberBoy extends Sprite {
    int x, y;

    public GimberBoy() {
        super(500, 625);
    }

    public GimberBoy(int x, int y) {
        super(x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        System.err.println("Setting X to: " + x);
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        System.err.println("Setting Y to: " + y);
        this.y = y;
    }

    @Override
    public void pintar() {
        GestorDeDibuix.getInstancia().pintar("ROBOT", getX(), getY(), 50);
    }

    @Override
    public void animar() {
        System.out.println("ANIMANT!");
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'animar'");
    }

    @Override
    public int getVidas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVidas'");
    }
    
    
}