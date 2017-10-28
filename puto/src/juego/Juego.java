package juego;


import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	// ...
	Barril [] barril = new Barril[5];
	Barra [] vigas = new Barra[4];
	Barra [] escalera = new Barra [3];
	Personajes donkey =  new Personajes (15,5);
	Personajes Mario = new Personajes (5,550);;
	boolean colEscalera;
	boolean colViga;
	int contadorTicks = 0;
	
	Juego()
	{
		
this.entorno = new Entorno(this, "RescateDonkey Seba puto V0.01", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		
		for (int i = 1; i< escalera.length+1;i++)
		{
			if 	(i % 2 == 0) 
			{
				escalera[i-1]=new Barra(250,360,25,160);
			}
			else
			{
				escalera[i-1] = new Barra(550,((600/vigas.length)*i)+60,25,160);
			}
		}
		

		for (int i = 1;i < vigas.length+1;i++)
		{
			if 	(i == 4) {
				vigas[i-1]=new Barra(400,600,850,30);
			}
			else{
				if (i % 2 == 0) {
					vigas[i-1] = new Barra(600,(600/vigas.length)*i,800,30);
				}
				else {
					vigas[i-1]=new Barra (200,(600/vigas.length)*i,800,30);
				}
			}	
}
		for (int i = 0;i < barril.length;i++) 
		{
			 barril[i]= new Barril (50,50,50);
		}
		barril[0].setActivo(true);
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
		
		for (int i=0;i<barril.length;i++)
		{
			if (barril[i]!=null)
			{
				barril[i].dibujarse(entorno);
			}		
		}		
		for (int i=0;i<vigas.length;i++)
		{
			if (vigas[i]!=null)
			{
				vigas[i].dibujarse(entorno);
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
				if (Mario.ColisionEscalera(escalera[i]) || Mario.ColisionViga(vigas[i])) 
				{
					Mario.subirArriba();	
				}
			}
		}
		if (entorno.estaPresionada(entorno.TECLA_ABAJO) && Mario.getX() < entorno.alto() + 5)
		{
			for (int i =0; i<escalera.length;i++)
			{
				if (Mario.ColisionEscalera(escalera[i]) && !Mario.ColisionViga(vigas[i])) 
				{
					Mario.Abajo();
				}
			}
		}
		for (int i =0; i<vigas.length;i++)
		{			
			for (int j=0; j<escalera.length+1;j++)
				{
					if (Mario.ColisionViga(vigas[i]) && Mario.getY() < entorno.alto() -50 && !Mario.ColisionEscalera(escalera[j]))
					{
						Mario.caer();
					}
				}
		}
		
		// Movimiento Barriles
		for (int i =0; i<barril.length;i++)
		{
			boolean colisiono = false;
			boolean ultimaBarra = false;
			for (int j =0; j<vigas.length && !colisiono; j++)
			{
				colisiono = (barril[i].colisionBarraVertical(vigas[j]) && barril[i].colisionBarraHorizontal(vigas[j]));
				ultimaBarra = (j==(vigas.length-1));
			}
			 
			barril[i].setCayendo(!colisiono);

			
		
			if (barril[i].getLimiteDerecho() >= entorno.ancho())
			{
				barril[i].cambiarDireccion();
			}
			else if (barril[i].getLimiteIzquierdo() <= 0 && !ultimaBarra)
			{
				barril[i].cambiarDireccion();				
			}
			
			barril[i].moverse();
		}
		contadorTicks++;
		
}	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
