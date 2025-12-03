/*
 * To change this template, choose Tools | Templates NTD
 * and open the template in the editor.
 */

package vista;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class VistaDeJoc extends JFrame {

	private static final long serialVersionUID = 8164970557352288581L;
	private ZonaDeJoc PanelZonaJoc;

	
	// Patró model vista controlador 
	// composite: crea la vista
	// jugar: motor del game i controladors
	public VistaDeJoc() {

		enableEvents(AWTEvent.WINDOW_EVENT_MASK);

		try {
			composite();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Iniciem el joc 
		iniciarJoc();
	}

	
	// construim la vista: Patró composite autòmatic
	// Inicialización de components de Jborland 
	private void composite() throws Exception {
		this.setSize(new Dimension(ZonaDeJoc.ANCHO, ZonaDeJoc.ALTO + 20));
		this.setTitle("Max&Oriol");   
		this.setLayout( new BorderLayout(5,5));
		// instanciem el JPANEL zona de joc
		PanelZonaJoc = new ZonaDeJoc();
		// Afegim la zona de Joc a la finestra 
		this.getContentPane().add(PanelZonaJoc,BorderLayout.CENTER);
		// La finestra la deixarem a una mida fixa 
		setResizable(false);
		// la visualitzem
		setVisible(true);
	}

	// invoquem la zona de joc: Model i controladors
	public void iniciarJoc() {
		PanelZonaJoc.jugar();
	}


	// Overriden per poder enviar un exit,  al tancar la finestra
	@Override
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		System.out.println("Comienza la ejecución!");
		// invoquem al nostre constructor
		new VistaDeJoc();
	}

}
