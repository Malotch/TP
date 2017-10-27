package juego;

import java.awt.Color;
import java.util.Random;

import entorno.Entorno;

public class Barril {
		// Variables de instancia
		private int x, y;
		private double diametro;
		private int velocidad;
		private int direccion;
		//private boolean atrapada;
		
		public Barril(int x, int y,int diametro) 
		{
			//this.x = x;
			//this.y = y;
			this.diametro = diametro;
			Random gen = new Random();			
			this.x = x;
			this.y = y;
			this.velocidad = 1 + gen.nextInt(5);
			//this.y = gen.nextInt(600);
			
		}

		public void dibujarse(Entorno entorno) 
		{
			entorno.dibujarCirculo(this.x, this.y, this.diametro, Color.white);		
		}

		public int avanzar(int velocidad) 
		{
			return this.x+=this.velocidad*velocidad;
		}
		
		public int caer (int y)
		{
			return this.y+=y;
		}
		
/*		public void avanzarDerecha() 
		{
			this.x+= this.velocidad;
		}
		
*/		public boolean ColisionViga(Barras ba ) {
		if (ba.getY() + (ba.getAlto()/2) >= this.y - (this.diametro / 2) && ba.getY() - (ba.getAlto()/2) <= this.y + (this.diametro / 2) &&
			ba.getX() + (ba.getAncho()/2) >= this.x - (this.diametro/ 2) && ba.getX() - (ba.getAncho()/2) <= this.x + (this.diametro / 2))
		{
			return true;
		}					
		return false;					
		}

		public int getY() 
		{
			return this.y;
		}

		public int getX() 
		{
			return this.x;
		}
		public double getDiametro() 
		{
			return this.diametro;
		}
		

}
