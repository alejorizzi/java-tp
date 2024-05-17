package juego;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	private static final char TECLA_c = 'C';

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	//Mejorar BLOQUES
	 private List<Bloque> filaBloques;
	// Variables y métodos propios de cada grupo
	// ...
	private Princesa princesa;
	private disparo disparo;

	


	Juego() {
		Random rand = new Random();
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, " Super Elizabeth Sis, Volcano Edition - Grupo ... - v1", 800, 600);
		
		//Mejorar  (FILA BLOQUES)
		this.filaBloques = new ArrayList<>();
		
		int cantidadFilas = 5;
        int alturaEntreFilas = 120; // Altura entre cada fila de cuadrados
        int y = 100; // Altura inicial de la primera fila de cuadrados

        for (int i = 0; i < cantidadFilas; i++) {
            crearFilaBloques(y);
            y += alturaEntreFilas; // Aumenta la altura para la próxima fila
        }

        
        
        
		// Inicializar lo que haga falta para el juego
		// ...
		this.princesa = new Princesa(this.entorno.ancho()/2,530,20,55);	
		
		// Inicia el juego!
		this.entorno.iniciar();
		
		
	}

	//Bloques
	private void crearFilaBloques(int y) {
		 int cantidadCuadrados = 20;
		 int espacioEntreCuadrados = 1;
		 int anchoCuadrado = 40;
		 int altoCuadrado = 40;
		 int inicioX = (entorno.ancho() - (cantidadCuadrados * (anchoCuadrado + espacioEntreCuadrados))) / 2;

		 for (int i = 0; i < cantidadCuadrados; i++) {
			 int x = inicioX + (anchoCuadrado + espacioEntreCuadrados) * i;
			 filaBloques.add(new Bloque(x, y, anchoCuadrado, Color.RED));
		 }
		// TODO Auto-generated method stub
		
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
		
		//Mejorar BLOQUES
		for (Bloque cuadrado : filaBloques) {
            cuadrado.dibujar(entorno);
		}
		
		
		
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
		
		// se crea DISPARO
		
		if (disparo != null && disparo.estaActivo()) {
            disparo.mover();
           this.disparo.dibujar(this.entorno);
        }
		
		if (disparo != null) {
		    if (disparo.estaActivo()) {
		        disparo.mover();
		        disparo.dibujar(entorno);
		    } else {
		        disparo = null; // Crea un nuevo disparo si no hay otro disparo activo
		    }
		}
		// Crea un nuevo disparo si el disparo anterior ha salido de la pantalla
		if (entorno.sePresiono(TECLA_c)) {
		    disparo = new disparo(princesa.getX(), princesa.getY(), princesa.estaCaminando());
		}
	}

	
	
		
		
	
		  
	 
	

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
