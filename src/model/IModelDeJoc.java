package model;

import sprites.Sprite;


// TODO 1: Patró Adapter ROL 
public interface IModelDeJoc {

	void iniciarJoc();

	void afegirElement(Sprite b);

	void eliminarElement(Sprite b);

	Sprite getSprite(int n);

	void animarJoc();

	void pintarJoc();
	
	void gestorColisions();

}