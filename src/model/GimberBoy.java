package model;

import sprites.Sprite;

public class GimberBoy extends Sprite {
    int x, y;

    public GimberBoy() {
        super(0, 0);
    }

    public GimberBoy(int x, int y) {
        super(x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void pintar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pintar'");
    }

    @Override
    public void animar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'animar'");
    }

    @Override
    public int getVidas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVidas'");
    }
    
    
}