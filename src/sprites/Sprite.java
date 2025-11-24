/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sprites;


public abstract class Sprite {
	protected int x, y;
	
	public Sprite(int x, int y) {
		setX(x);
		setY(y);
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

	
	public abstract void pintar();

	public abstract void animar();
}
