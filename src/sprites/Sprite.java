package sprites;

import java.util.List;

import model.ListModelDeJoc;

public abstract class Sprite {
	protected int x, y, width, height, lives;
	protected String imageString;
	protected List<Sprite> leafs = null;
	
	public Sprite(int x, int y, int width, int height, int lives, String imageString) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.lives = lives;
		this.imageString = imageString;
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getLives() {
		return lives;
	}
	
	public String getImageString() {
		return imageString;
	}

	public void setLives(int lives) {
		this.lives = lives;

		if (this.lives == 0) {
			// TODO: Show an ID and name to have a better notification
			killSprite();
		}
	}

	public abstract void pintar();

	public abstract void animar();

	public void killSprite() {
		ListModelDeJoc.getInstancia().vEntes.remove(this);
		System.out.println("Sprite destroyed");
	}

}
