package model;

import sprites.Sprite;

public interface IModelDeJoc {

	void iniciarJoc();

	void afegirElement(Sprite b, boolean isBullet);

	void eliminarElement(Sprite b, boolean isBullet);

	Sprite getSprite(int n, boolean isBullet);

	void animarJoc();

	void pintarJoc();

	void gameOver();

}