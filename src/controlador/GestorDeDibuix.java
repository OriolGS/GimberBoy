package controlador;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.HashMap;


public class GestorDeDibuix {

    private HashMap<String, Image> cacheDeImatges = new HashMap<String, Image>();
    private MediaTracker mt;
    private Component comp;
    private Graphics g; 

    //TODO: Patró Singleton Classe Gestor de Dibuix 
   
    
    public void inicializar(MediaTracker mt, Graphics g, Component comp) {
        this.mt = mt; // el mediatraker es un carregador de imatges 
        this.g = g; // context gràfic del doble buffer molt important !!
        this.comp = comp;  // zona de Joc
    }

    public void pintar(String key, int x, int y) {
       g.drawImage((Image)(cacheDeImatges.get(key.toUpperCase())), x, y, comp);
    }

    public void pintar(String key, int x, int y, int size) {
       this.pintar(key, x, y, size, size);
    }

    public void pintar(String key, int x, int y, int ancho, int alto) {
       g.drawImage((Image) (cacheDeImatges.get(key.toUpperCase())), x, y, ancho, alto, comp);
    }

    public void pintarTexto(String texto, int x, int y, int ancho, int alto) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, ancho, alto);
        g.setColor(Color.black);
        g.drawRect(x, y, ancho, alto);
        g.drawString(texto, x + 5, y + 12);
    }
    
    // carreguem la llista d'imatges en la cache i despres el mediatraker les carrega
    public void afegirImatge(String key, Image imagen) throws InterruptedException {
        cacheDeImatges.put(key.toUpperCase(), imagen);
        mt.addImage(imagen, 0); // llença excepció si no es carrega bé
        mt.waitForAll(); // això es diu tracking
    } 
}

