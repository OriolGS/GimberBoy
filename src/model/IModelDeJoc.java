package model;

import sprites.Sprite;

public interface IModelDeJoc {

	void iniciarJoc();

	void afegirElement(Sprite b);

	void eliminarElement(Sprite b);

	Sprite getSprite(int n);

	void animarJoc();

	void pintarJoc();

	void gameOver();

}