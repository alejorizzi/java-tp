package juego;

import java.awt.Color;
import entorno.Entorno;

public class Bloque {
	private int x;
    private int y;
    private int ancho;
    private int alto;
    private Color color;
    
    public Bloque(int x, int y, int ancho, int alto , Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.ancho = ancho;
        this.alto = alto;
    }
    
    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(x, y, ancho, alto, 0, color);
    }
    
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}
	
	public void setX(int dato) {
		this.x = dato;
	}
	
	public void setY(int dato) {
		this.y = dato;
	}
	
	public void setAlto(int dato) {
		this.alto = dato;
	}
	
	public void setAncho(int dato) {
		this.ancho = dato;
	}
    
}
