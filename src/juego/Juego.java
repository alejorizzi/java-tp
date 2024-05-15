package juego;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;	

	// Variables y métodos propios de cada grupo
	// ...
	private Princesa princesa;

	Juego() {
		Random rand = new Random();
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, " Super Elizabeth Sis, Volcano Edition - Grupo ... - v1", 800, 600);

		// Inicializar lo que haga falta para el juego
		// ...
		this.princesa = new Princesa(50,520,20,55);
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...
		
		
		//Asignacion de TECLAS PARA PRINCESA
		if(this.entorno.estaPresionada(this.entorno.TECLA_DERECHA) &&
		        this.princesa.getX() + this.princesa.getAncho()/ 2 < this.entorno.ancho())
			this.princesa.moverDerecha();

		if(this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA) &&
		        this.princesa.getX() - this.princesa.getAncho()/ 2 > 0)
			this.princesa.moverIzquierda();
		
		
		// Salto
	    if (this.entorno.estaPresionada(this.entorno.TECLA_ESPACIO) == true)
	    	this.princesa.saltar();

		this.princesa.actualizar();
		
		
		
		this.princesa.dibujar(this.entorno);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
