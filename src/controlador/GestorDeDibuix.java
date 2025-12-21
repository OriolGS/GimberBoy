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

    private static GestorDeDibuix instancia = null;

    public static GestorDeDibuix getInstancia() {
        if (instancia == null) {
            instancia = new GestorDeDibuix();
        }
        return instancia;
    }

    public void inicializar(MediaTracker mt, Graphics g, Component comp) {
        this.mt = mt; // el mediatraker es un carregador de imatges
        this.g = g; // context gràfic del doble buffer molt important !!
        this.comp = comp; // zona de Joc
    }

    public void pintar(String key, int x, int y) {
        g.drawImage((Image) (cacheDeImatges.get(key.toUpperCase())), x, y, comp);
    }

    public void pintar(String key, int x, int y, int size) {
        this.pintar(key, x, y, size, size);
    }

    public void pintar(String key, int x, int y, int ancho, int alto) {
        g.drawImage((Image) (cacheDeImatges.get(key.toUpperCase())), x, y, ancho, alto, comp);
    }

    public void pintarTexto(String texto, int x, int y, int ancho, int alto) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, ancho * 10, alto );
        g.setColor(Color.black);
        g.drawString(texto, x + 5, y + alto - alto / 3);
    }

    // carreguem la llista d'imatges en la cache i despres el mediatraker les
    // carrega
    public void afegirImatge(String key, Image imagen) throws InterruptedException {
        cacheDeImatges.put(key.toUpperCase(), imagen);
        mt.addImage(imagen, 0); // thows exception if not loaded correctly
        mt.waitForAll(); // tracking
    }

    public void removeImage(int x, int y, int width, int height) {
        g.fillRect(x, y, width, height);
    }
}
