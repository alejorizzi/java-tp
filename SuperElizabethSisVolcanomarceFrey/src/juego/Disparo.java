package juego;
import java.awt.Color;
import java.awt.Rectangle;

import entorno.Entorno;

public class Disparo {

	private int x;
    private int y;
    private int velocidadX;
    private boolean activo;
    private static final int VELOCIDAD_DISPARO = 5;
	private static final char TECLA_C = 'C';
	

    public Disparo(int x, int y, boolean haciaDerecha) {
        this.x = x;
        this.y = y;
        this.velocidadX = haciaDerecha ? VELOCIDAD_DISPARO : -VELOCIDAD_DISPARO;
        this.activo = true;
    }

    public void mover() {
        if (activo) {
            x += velocidadX;
        }
            
    }


    public boolean estaActivo() {
        return activo;
    }

    public void desactivar() {
        activo = false;
    }

    public void dibujar(Entorno entorno) {
        if (activo) {
            entorno.dibujarRectangulo(x, y, 10, 5, 0, Color.YELLOW);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean dispararSiPresionado(Entorno entorno, Princesa princesa) {
        
		if (entorno.estaPresionada(TECLA_C) && !activo) {
            // Solo dispara si la tecla 'c' está presionada y el disparo no está activo
            this.x = princesa.getX();
            this.y = princesa.getY();
            this.velocidadX = princesa.estaCaminando() ? VELOCIDAD_DISPARO : -VELOCIDAD_DISPARO;
            this.activo = true;
        }
		return activo;
    }

	public int getAncho() {
		
		return 0;
	}

	public void mover(int ancho) {
		
	}
}
