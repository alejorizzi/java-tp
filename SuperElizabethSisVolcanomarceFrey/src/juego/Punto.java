package juego;

public class Punto {
	double x;
	double y;
	
	public Punto(double x , double y) {
		this.x = x;
		this.y = y;
	}
	
	public void imprimir() {
		System.out.println("[" + this.x + " , " + this.y + "]");
	}
	
	public void desplazar(double desp_x , double desp_y) {
		this.x += desp_x;
		this.y += desp_y;
	}
	
	public static double distancia (Punto p1 , Punto p2) {
		double dx = p1.x - p2.x;
		double dy = p1.y - p2.y;
		return Math.sqrt(dx*dx + dy*dy);
	}
	
}
