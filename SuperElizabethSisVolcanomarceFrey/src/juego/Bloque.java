package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;

public class Bloque {
	private int x;
    private int y;
    private int ancho;
    private int alto;
    private Color color;
    private Image imagen;
    
    public Bloque(int x, int y, int ancho, int alto , Image imagen) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.imagen = imagen;
    }
    
    public void dibujar(Entorno entorno) {
    	entorno.dibujarImagen(this.imagen, this.x, this.y, 0, 1.0);
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
