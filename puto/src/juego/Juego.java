package juego;


import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	// ...
	Barril [] ba = new Barril[5];
	Barras [] viga = new Barras[4];
	Barras [] escalera = new Barras [3];
	Personajes donkey =  new Personajes (15,5);
	Personajes Mario = new Personajes (5,550);;
	boolean colEscalera;
	boolean colViga;
	
	Juego()
	{
		
this.entorno = new Entorno(this, "RescateDonkey - Grupo Apellido1 - Apellido2 -Apellido3 - V0.01", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		
		for (int i = 1; i< escalera.length+1;i++)
		{
			if 	(i % 2 == 0) 
			{
				escalera[i-1]=new Barras(250,360,25,160);
			}
			else
			{
				escalera[i-1] = new Barras(550,((600/viga.length)*i)+60,25,160);
			}
		}
		
		for (int i = 1;i < viga.length+1;i++)
		{
			if 	(i == 4) {
				viga[i-1]=new Barras(400,600,850,30);
			}
			else{
				if (i % 2 == 0) {
					viga[i-1] = new Barras(600,(600/viga.length)*i,800,30);
				}
				else {
					viga[i-1]=new Barras (200,(600/viga.length)*i,800,30);
				}
			}	
		}
		for (int i = 1;i < ba.length+1;i++) 
		{
			 ba[i-1]= new Barril (50,50,50);
		}
		
		// Inicia el juego!
		
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		//Dibujar elementos 
		
		for (int i=0;i<ba.length;i++)
		{
			if (ba[i]!=null)
			{
				ba[i].dibujarse(entorno);
			}		
		}		
		for (int i=0;i<viga.length;i++)
		{
			if (viga[i]!=null)
			{
				viga[i].dibujarse(entorno);
			}		
		}		
		for (int i=0;i<escalera.length;i++)
		{
			if (escalera[i]!=null)
			{
				escalera[i].dibujarseEscalera(entorno);
			}		
		}	
		
		Mario.dibujarse(entorno);	
		
		// Movimiento Personaje 
		
			
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && Mario.getX() -5 > 0)	Mario.moverIzquierda();
		if (entorno.estaPresionada(entorno.TECLA_DERECHA) && Mario.getX() +5 < entorno.ancho() - 5)	Mario.moverDerecha();			
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA) && Mario.getX() < entorno.alto() - 5) 
		{
			for (int i =0; i<escalera.length;i++)
			{
				if (Mario.ColisionEscalera(escalera[i]) || Mario.ColisionViga(viga[i])) 
				{
					Mario.subirArriba();	
				}
			}
		}
		if (entorno.estaPresionada(entorno.TECLA_ABAJO) && Mario.getX() < entorno.alto() + 5)
		{
			for (int i =0; i<escalera.length;i++)
			{
				if (Mario.ColisionEscalera(escalera[i]) && !Mario.ColisionViga(viga[i])) 
				{
					Mario.Abajo();
				}
			}
		}
		for (int i =0; i<viga.length;i++)
		{			
			for (int j=0; j<escalera.length+1;j++)
				{
					if (!Mario.ColisionViga(viga[i]) && Mario.getY() < entorno.alto() -50 && !Mario.ColisionEscalera(escalera[j]))
					{
						Mario.caer();
					}
				}
		}
		
		// Movimiento Barriles
		
		for (int i =0; i<ba.length;i++)
		{
			for (int j =0; j<viga.length;j++)
			{
				if (!ba[i].ColisionViga(viga[j]))
				{
					ba[i].caer(1);
				}
				else
				{
					ba[i].caer(0);
					i++;
				}
			}
			ba[i].avanzar(+1);
			if (ba[i].getX()==entorno.ancho()-ba[i].getDiametro())
			{
				ba[i].avanzar(1);
			}
			else if (ba[i].getX()==ba[i].getDiametro())
			{
				ba[i].avanzar(-2);				
			}
		}
}	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
