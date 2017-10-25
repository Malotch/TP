package juego;
import java.awt.Color;

import entorno.Entorno;

public class Personajes {

		// Variables de instancia
		private int x, y;
		private int ancho;
		private int alto;

		public Personajes(int x, int y) 
		{
			this.x = x;
			this.y = y;
			this.ancho = 25 ;
			this.alto = 50;
		}

		public void moverIzquierda() 
		{
			this.x -= 5;
		}
				
		public void subirArriba() 
		{
			this.y -= 5;
		}
		
		public void saltar ()
		{
			this.y -= 20;
		}
		public void caer ()
		{
			this.y +=20;
		}
		public void Abajo() 
		{
			this.y += 5;
		}			
		
		public void moverDerecha() 
		{
			this.x += 5;
		}
		public void dibujarse(Entorno entorno) 
		{
			entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.blue);
		}
		public int getX() 
		{
			return this.x;
		}		
		
		public boolean ColisionViga(Barras ba ) {
		if (ba.getY() + (ba.getAlto()/2) >= this.y - (this.alto / 2) && ba.getY() + (ba.getAlto()/2) <= this.y + (this.alto / 2) &&
			ba.getX() + (ba.getAncho()/2) >= this.x - (this.ancho / 2) && ba.getX() + (ba.getAncho()/2) <= this.x + (this.ancho / 2))
		{
			return true;
		}					
		return false;					
		}
		
		public boolean ColisionEscalera(Barras ba) {
		if (ba.getY() + (ba.getAlto()/2) >= this.y - (this.alto / 2) && ba.getY() - (ba.getAlto()/2) <= this.y + (this.alto / 2) &&
			ba.getX() + (ba.getAncho()/2) >= this.x - (this.ancho / 2) && ba.getX() - (ba.getAncho()/2) <= this.x + (this.ancho / 2))
		{
			return true;
		}					
		return false;					
		}
		
		public boolean ColisionDonkey(Barril bar) {
		if (bar.getY() >= this.y - (this.alto / 2) && bar.getY() <= this.y + (this.alto / 2) &&
				bar.getX() >= this.x - (this.ancho / 2) && bar.getX() <= this.x + (this.ancho / 2))
			{
				return true;
			}					
			return false;						
		}
		public boolean colisionaCon(Personajes p) 
		{
			if (p.getY() >= this.y - (this.alto / 2) && p.getY() <= this.y + (this.alto / 2) &&
				p.getX() >= this.x - (this.ancho / 2) && p.getX() <= this.x + (this.ancho / 2))
			{
				return true;
			}
			
			return false;			
		}
		public int getY() 
		{
			return this.y;
		}
	}
	
