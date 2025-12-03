package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import model.ListModelDeJoc;
import vista.ZonaDeJoc;

public class ControladorDeJoc implements MouseListener, MouseMotionListener {
	
	// Per mirar que tots els torns triguin el mateix
	private long usedTime; 
	// vista és el JPanel en el que visualitzem el joc
	private final ZonaDeJoc vista;
	private int i = (int) (Math.random() * 100 + 1);
	private int j = 1;
        
	public ControladorDeJoc(ZonaDeJoc vista) {
		this.vista = vista;
	}
       
	public void jugar() {
		ListModelDeJoc.getInstancia().iniciarJoc();
	   
		// game loop for DoomsDay
		while (true) {
           
			// tip jugable: introduim un retard per tal aturar-lo uns 20 milisegons
			if (usedTime < 20) retardo();
           long startTime = System.currentTimeMillis();
           
           // comportaments asincrons: ratoli 
           // utilitzant els mouseListener i mouseMotionListener
           vista.addMouseListener(this);
           vista.addMouseMotionListener(this);          

           j++;
           i++;
           if (j >= 300) {
        	   ListModelDeJoc.getInstancia().añadirEsferaL();
        	   ListModelDeJoc.getInstancia().añadirEsferaR();
        	   j = 0;
        	   
           }           
           
           // update: Comportaments sincrons
           // generem per a cada sprite una nova posició
           // TODO: animarJoc, pintar and notificar a los observadores
           ListModelDeJoc.getInstancia().animarJoc();          
			
            // Render: Cridem al motor de gràfics	
           // Temps que es triga en fer un torn.
			usedTime = System.currentTimeMillis() - startTime;
			
           if (usedTime > 1) {
			} 
		}
	}
	
	public void retardo() {
		try {
			Thread.sleep(20 - usedTime);
                 
		} catch (InterruptedException ie) {
                    
		}
	}

	public void mousePressed(MouseEvent e) {
		ListModelDeJoc model = ListModelDeJoc.getInstancia(); // SINGLETON
		model.hemPitjatElMouse();
	}

	public void mouseMoved(MouseEvent e) {
		ListModelDeJoc gm = ListModelDeJoc.getInstancia();
		gm.coordenadesDelMouse(e.getX(), e.getY());
		gm.notifyObservers();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		ListModelDeJoc gm = ListModelDeJoc.getInstancia();
		gm.hemPitjatElMouse();
		gm.notifyObservers();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}	
	
}
