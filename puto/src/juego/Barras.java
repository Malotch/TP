package juego;
import java.awt.Color;

	import entorno.Entorno;

	public class Barras {

			// Variables de instancia
			private int x, y;
			private int ancho;
			private int alto;

			public Barras(int x, int y,int ancho,int alto) 
			{
				this.x = x;
				this.y = y;
				this.ancho = ancho ;
				this.alto = alto;
			}


			public void dibujarse(Entorno entorno) 
			{
				entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.yellow);
				
			}
			public void dibujarseEscalera(Entorno entorno) 
			{
				entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.red);
				
			}

			public int getX() 
			{
				return this.x;
			}

			public boolean colisionaCon(Barras p) 
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

			public int getAncho()
			{
				return this.ancho;
			}
			public int getAlto()
			{
				return this.alto;
			}
			
		
		}
		

