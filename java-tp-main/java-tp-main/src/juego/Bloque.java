package juego;

import java.awt.Color;
import entorno.Entorno;


public class Bloque {
	private int x;
    private int y;
    private int lado;
    private Color color;
    
    public Bloque(int x, int y, int lado, Color color) {
        this.x = x;
        this.y = y;
        this.lado = lado;
        this.color = color;
    }
    
    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(x, y, lado, lado, 0, color);
    }
}
