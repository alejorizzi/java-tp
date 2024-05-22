package juego;

import java.awt.Color;
import entorno.Entorno;


public class Princesa {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	
	//Para Salto
	private double velocidad;
	private int velocidadY; 
	private boolean enElAire;  //Estado de salto
	private static  int GRAVEDAD = 1;
	private static  int IMPULSO_SALTO = -18;
	private static  int SUELO_Y = 530;
	
	
	public Princesa(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		
	//Para Salto
		this.velocidadY = 0;
        this.enElAire = false;
        this.velocidad = velocidad;
	}
	
	
	//DIBUJO
	public void dibujar(Entorno entorno) {
		
		entorno.dibujarRectangulo(this.x,this.y,this.ancho,this.alto, 0,Color.GRAY);
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
	
	
    //VELOCIDAD
	public void moverDerecha()
	{
		this.x = this.x + 2;
	}
	
	public void moverIzquierda()
	{
		this.x = this.x - 2;
	}
	
	//Salto
	public void setVelocidadY(int dato) {
		this.velocidadY = dato;
	}
	
	public void setSUELO_Y (int dato) {
		this.SUELO_Y = dato; 
	}
	
	public void saltar() {
		if (!enElAire) {
			velocidadY = IMPULSO_SALTO;
			enElAire = true;
		}
	}
	
	public void setenElAire (boolean dato) {
		this.enElAire = dato; 
	}
		
	public void actualizar() {
	     if (enElAire) {
	         velocidadY += GRAVEDAD;
	         y += velocidadY;

	            if (y >= SUELO_Y) {
	                y = SUELO_Y;
	                velocidadY = 0;
	                enElAire = false;
	            }
	      }	     
    }
	public boolean estaCaminando() {
		return false;
	}

}
	
