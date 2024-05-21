package juego;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	
	private Entorno entorno;
	private Princesa princesa;
	public int pos_y_bloques;
	private int cantidadFilas = 4;
    private int alturaEntreFilas = 120;
    private int coor_y_primerfila = 450;
	private List<Bloque> filaBloques;
	private boolean flag_colision_IZQUIERDA;
	private boolean flag_colision_DERECHA;
	private boolean flag_colision_CABEZA;
	private boolean flag_colision_PIES;
	
	Juego() {
		this.entorno = new Entorno(this, " Super Elizabeth Sis, Volcano Edition - Grupo ... - v1", 800, 600);
		this.entorno.iniciar();
		inicializar();										
	}
	
	//********************************************************* METODO TICK *********************************************************//
	public void tick() {
		setear();
		chequeo_colisiones();
		asignar_teclas();
		princesa.dibujar(this.entorno);
		princesa.actualizar();
		for (Bloque bloque : filaBloques) {
            bloque.dibujar(entorno);
		}
	 }

	//********************************************************* METODOS FUNCIONALES EN EL TICK *********************************************************//
	// METODO PARA INICIALIZAR TODAS LAS VARIABLES DEL JUEGO
	public void inicializar() {
        princesa = new Princesa(this.entorno.ancho()/2, 530, 20, 55);
        filaBloques = new ArrayList<>();
        for (int i = 0; i < cantidadFilas; i++) {
            crearFilaBloques(coor_y_primerfila);
            coor_y_primerfila -= alturaEntreFilas; // Aumenta la altura para la próxima fila
        }
    }   
    
    // METODO PARA SETEAR LAS VARIABLES DEL TICK
    private void setear() {
        flag_colision_IZQUIERDA = false;
        flag_colision_DERECHA = false;
        flag_colision_CABEZA = false;
        flag_colision_PIES = false;
    }
    
    // METODO PARA LA ASIGNACION DE TECLAS
    private void asignar_teclas() {
        if(this.entorno.estaPresionada(this.entorno.TECLA_DERECHA) &&
                this.princesa.getX() + this.princesa.getAncho()/ 2 < this.entorno.ancho() && flag_colision_DERECHA != true)
            this.princesa.moverDerecha();

        if(this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA) &&
                this.princesa.getX() - this.princesa.getAncho()/ 2 > 0 && flag_colision_IZQUIERDA != true)
            this.princesa.moverIzquierda();
        
        if (this.entorno.estaPresionada(this.entorno.TECLA_ESPACIO) == true)
            this.princesa.saltar();
    }
    
    // METODO PARA CHEQUEAR LAS COLISIONES
    private void chequeo_colisiones(){
        for (Bloque bloque : filaBloques) {    
            if(bloque != null) {
                bloque.dibujar(entorno);
                
                
                if(Colision_Prin_BloIZ(princesa , bloque))
                    flag_colision_IZQUIERDA = true;
                
                if(Colision_Prin_BloDER(princesa , bloque))
                    flag_colision_DERECHA = true;

                
                if(Colision_Prin_BloARR(princesa , bloque)) {
                    flag_colision_CABEZA = true;
                    princesa.setVelocidadY(0);
                    bloque = null;
                }
                
                if(Colision_Prin_BloAB(princesa , bloque)) { 
                    flag_colision_PIES = true;
                    System.out.println("HAY COLISION CON EL PISO");
                }
            }
        }
        if (flag_colision_CABEZA)
            princesa.setVelocidadY(0);
        
        if (flag_colision_PIES)
            princesa.setenElAire(false);
    }

    // METODO PARA CREAR LAS FILAS DE BLOQUES 
    private void crearFilaBloques(int y) {
        int cantidadCuadrados = 10;
        int espacioEntreCuadrados = 2;
        int anchoCuadrado = 40;
        int altoCuadrado = 40;
        int inicioX = (entorno.ancho() - (cantidadCuadrados * (anchoCuadrado + espacioEntreCuadrados))) / 2;

        for (int i = 0; i < cantidadCuadrados; i++) {
            int x = inicioX + (anchoCuadrado + espacioEntreCuadrados) * i;
            filaBloques.add(new Bloque(x, y, anchoCuadrado, altoCuadrado, Color.RED));
        }
    }
    
    //********************************************************* METODOS DE COLISIONES *********************************************************//    
    // METODO COLISIONES GENERALES
    private boolean Colision(int x1, int y1, int ancho1, int alto1, int x2, int y2, int ancho2, int alto2) {    
        Rectangle inter = new Rectangle(); // Creo un nuevo Rectángulo
        Point aux = new Point(); // Creo un Punto auxiliar
        
        //Bloque para la coor x e y del rec mayor
        inter.x = Math.min(x1 - ancho1/2, x2 - ancho2/2); // Al rectángulo le asigno  
        inter.y = Math.min(y1 - alto1/2, y2 - alto2/2);
        
        //Bloque para la coor x e y sup der del rec mayor
        aux.x = Math.max(x1 + ancho1/2, x2 + ancho2/2);
        aux.y = Math.max(y1 + alto1/2, y2 + alto2/2);
        
        //Bloque para el alto y ancho del rec mayor
        inter.width = aux.x - inter.x;
        inter.height = aux.y - inter.y;
        
        //Bloque para determinar si hay intersección 
        if (inter.width < ancho1 + ancho2 && inter.height < alto1 + alto2) {
            return true;
        }
        return false;
    }
    
    // METODO COLISION PRINCESA BLOQUE
    private boolean Colision_Princesa_Bloque(Princesa princesa, Bloque bloque) {
        if (bloque != null && Colision(princesa.getX(), princesa.getY(), princesa.getAncho(), princesa.getAlto(),
               bloque.getX(), bloque.getY(), bloque.getAncho(), bloque.getAlto())) {
            return true;    
        }
        return false;
    }
    
    // METODO COLISIONES LADO IZQUIERDO PRINCESA CON BLOQUE
    private boolean Colision_Prin_BloIZ (Princesa princesa, Bloque bloque) {
    	Point punto1   = new Point(); // Creo un Punto auxiliar
		Point punto2   = new Point(); // Creo un Punto auxiliar
		
		punto1.x = princesa.getX() - princesa.getAncho()/2;
		punto1.y = princesa.getY();
		
		punto2.x = bloque.getX() + bloque.getAncho()/2;
		punto2.y = bloque.getY();
		
		if (bloque != null && Colision_Princesa_Bloque(princesa , bloque) && punto1.x == punto2.x-2) {
			return true;
		}
		return false;
	}
    
    // METODO COLISIONES LADO DERECHO PRINCESA CON BLOQUE
    private boolean Colision_Prin_BloDER (Princesa princesa, Bloque bloque){
		Point punto1   = new Point(); // Creo un Punto auxiliar
		Point punto2   = new Point(); // Creo un Punto auxiliar
		
		punto1.x = princesa.getX() + princesa.getAncho()/2;
		punto1.y = princesa.getY();
		
		punto2.x = bloque.getX() - bloque.getAncho()/2;
		punto2.y = bloque.getY();
		
		if (bloque != null && Colision_Princesa_Bloque(princesa , bloque) && punto1.x-2 == punto2.x) {
			return true;
		}
		return false;
	}

    // METODO COLISIONES LADO ARRIBA PRINCESA CON BLOQUE
    private boolean Colision_Prin_BloARR (Princesa princesa, Bloque bloque) {
        //add alejo (es parecido a la colision de abajo princesa)
    	if (bloque != null) {
            Point punto1 = new Point();
            Point punto2 = new Point();
            
            punto1.x = princesa.getX();
            punto1.y = princesa.getY() - princesa.getAlto()/2;
            
            punto2.x = bloque.getX();
			punto2.y = bloque.getY() + bloque.getAlto()/2;
			
            if (Colision_Princesa_Bloque(princesa, bloque) && Math.abs(punto1.y - punto2.y) < 3) { //le agregue un margen de 3 pixeles para que no sea tan estricta la colision
                return true;
            }
        //    
        }
        return false;
    }

    // METODO COLISIONES LADO ABAJO PRINCESA CON BLOQUE
    private boolean Colision_Prin_BloAB (Princesa princesa, Bloque bloque) {
    	if (bloque != null) {
			Point punto1   = new Point(); // Creo un Punto auxiliar
			Point punto2   = new Point(); // Creo un Punto auxiliar
				
			punto1.x = princesa.getX(); 
			punto1.y = (princesa.getY() + princesa.getAlto()/2);
				
			punto2.x = bloque.getX();
			punto2.y = (bloque.getY() - bloque.getAlto()/2);		
					
			if (Colision_Princesa_Bloque(princesa , bloque) && punto1.y-2 == punto2.y)
				return true;
		}
		return false;
	}
	
	

	//********************************************************* METODOS MAIN *********************************************************//	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
