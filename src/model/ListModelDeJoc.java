/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.LinkedList;
import java.util.ListIterator;
import sprites.*;

import controlador.GestorDeDibuix;

import sprites.*;
import vista.ZonaDeJoc;


public class ListModelDeJoc {  //TODO: Implementar Observable
	 
	// Patró Adapter, ROL Adaptad

	private GimberBoy g = new GimberBoy();
	private Marcador m = new Marcador(15,15);
	public NauEspacial n;
	
	public LinkedList<Sprite> vEntes = new LinkedList<Sprite>();
	public LinkedList<Sprite> balas = new LinkedList<Sprite>();  
	
	//TODO 1: Patró Singleton classe ListModelDeJoc
   


	public void iniciarJoc() {
		//TODO
        vEntes.add(0, new Fons());
	
	}
	
	public void animarJoc()
	{
		
		//aqui controlar listas clon de enemigos y balas
		
		 Sprite s;
		 Sprite b;
		 ListIterator<Sprite> iter= ((LinkedList<Sprite>) vEntes.clone()).listIterator(0);
		 ListIterator<Sprite> iterBala= ((LinkedList<Sprite>) balas.clone()).listIterator(0);

		 while(iter.hasNext()){
		 s = iter.next();
		 s.animar();
		 }
		 
		 while(iterBala.hasNext()){
			 b = iterBala.next();
			 if(b.getVidas()>0) {
				 b.animar();
			 }
			 else {
				 balas.remove(b);
			 }
			 }
		//TODO 3: Patró Observer: ROL Observable, marca el objeto como cambiado
		 
	}
	

	public void pintarJoc()
	{
		 Sprite s;
		 Sprite b;
		 ListIterator<Sprite> iter= ((LinkedList<Sprite>) vEntes.clone()).listIterator(0);
		 ListIterator<Sprite> iterBala= ((LinkedList<Sprite>) balas.clone()).listIterator(0);

		 while(iter.hasNext()){
		 s = iter.next();
		 s.pintar();
		 }
		 
		 while(iterBala.hasNext()){
			 b = iterBala.next();
			 b.pintar();
			 }
	}	


	
	public void hemPitjatElMouse() {
		
		balas.add(new Bullet(g.getX(), g.getY()-15));

	}
	public void coordenadesDelMouse(int x, int y) {
		Sprite s;
		//TODO 3: Asignar Mouse a Gimberboy
		ListIterator<Sprite> iter= vEntes.listIterator("TODO");
		s = iter.next();
		if(y>=ZonaDeJoc.ALTO/3*2+15){ //movemos y
			y=ZonaDeJoc.ALTO-50;
		}else{
			y=ZonaDeJoc.ALTO/3*2;
		}
		if(x>=ZonaDeJoc.ANCHO-40){ //movemos x
			x=ZonaDeJoc.ANCHO-40;
		}else if (x<=10){
			x=10;
		}
		s.setX(x);
		s.setY(y);
	}	
	
       
        
   

        
	
	
        
} // end ControladorDeJoc
  