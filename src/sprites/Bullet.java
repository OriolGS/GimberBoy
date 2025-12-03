package sprites;

public class Bullet extends Sprite {
    public Bullet(int x, int y) {
        super(x, y, 10, 10);
    }

    int x, y;

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
        throw new UnsupportedOperationException("Unimplemented method 'pintar'");
    }

    @Override
    public void animar() {
        throw new UnsupportedOperationException("Unimplemented method 'animar'");
    }

    @Override
    public int getVidas() {
        throw new UnsupportedOperationException("Unimplemented method 'getVidas'");
    }
    

}