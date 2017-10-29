package juego;

import java.util.Random;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;

	// Variables y métodos propios de cada grupo
	// ...
	Barril[] barril = new Barril[5];
	Barra[] viga = new Barra[4];
	Barra[] escalera = new Barra[4];
	Personaje Donkey = new Personaje(60, 95, 50, 80);
	Personaje Mario = new Personaje(40, 550, 20, 40);;
	int contadorTicks = 0;
	Random Aleatorio = new Random();

	Juego() {

		this.entorno = new Entorno(this, "RescateDonkey Fiandrino, Grauberger, - , - ", 800, 600);

		// Inicializar lo que haga falta para el juego
		// ...

		for (int i = 1; i < escalera.length + 1; i++) {
			if (i % 2 == 0) {
				escalera[i - 1] = new Barra(250, 360, 25, 150);
			} else {
				escalera[i - 1] = new Barra(550, ((600 / viga.length) * i) + 60, 25, 150);
			}
		}

		for (int i = 1; i < viga.length + 1; i++) {
			if (i == 4) {
				viga[i - 1] = new Barra(400, 600, 850, 30);
			} else {
				if (i % 2 == 0) {
					viga[i - 1] = new Barra(600, (600 / viga.length) * i, 800, 30);
				} else {
					viga[i - 1] = new Barra(200, (600 / viga.length) * i, 800, 30);
				}
			}
		}
		for (int i = 0; i < barril.length; i++) {
			barril[i] = new Barril(50, 50, 30);
		}
		barril[0].setActivo(true);
		
		// Inicia el juego!

		this.entorno.iniciar();
	}
	
	public void dibujarElementos () {

		for (int i = 0; i < barril.length; i++) {
			if (barril[i] != null) {
				barril[i].dibujarse(entorno);
			}
		}
		for (int i = 0; i < viga.length; i++) {
			if (viga[i] != null) {
				viga[i].dibujarse(entorno);
			}
		}
		for (int i = 0; i < escalera.length; i++) {
			if (escalera[i] != null) {
				escalera[i].dibujarseEscalera(entorno);
			}
		}
		Donkey.dibujarseDonkey(entorno);
		Mario.dibujarseMario(entorno);
	}
	
	public void movimientoPersonaje () {
		boolean colisionoPersonajeViga = false;
		boolean colisionoPersonajeEscalera = false;
		Personaje.EstadosVerticales estadoVertical = Personaje.EstadosVerticales.PARADO;
		Personaje.EstadosHorizontales estadoHorizontal = Personaje.EstadosHorizontales.PARADO;
		for (int i = 0; i < viga.length && !colisionoPersonajeViga; i++) {
			colisionoPersonajeViga = ((Mario.colisionBarraVertical(viga[i]) && Mario.colisionBarraHorizontal(viga[i])));
		}
		for (int i = 0; i < escalera.length && !colisionoPersonajeEscalera; i++) {
			colisionoPersonajeEscalera = (Mario.colisionBarraVertical((escalera[i]))) && Mario.colisionBarraHorizontal(escalera[i]);
		}

		if (colisionoPersonajeViga){
			estadoHorizontal = Personaje.EstadosHorizontales.PARADO;
			estadoVertical = Personaje.EstadosVerticales.PARADO;
		}		
		else {
			estadoVertical = Personaje.EstadosVerticales.CAYENDO;
		}
		
		if (colisionoPersonajeEscalera) {
 			estadoVertical = Personaje.EstadosVerticales.ENESCALERA;
		}	
	
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && Mario.getX() - 5 > 0 )
			estadoHorizontal = Personaje.EstadosHorizontales.MOVERIZQUIERDA;
		
		if (entorno.estaPresionada(entorno.TECLA_DERECHA) && Mario.getX() + 5 < entorno.ancho() - 5 )
			estadoHorizontal = Personaje.EstadosHorizontales.MOVERDERECHA;
		
		if ( estadoVertical != Personaje.EstadosVerticales.SALTANDO) {
			if (entorno.estaPresionada(entorno.TECLA_ARRIBA) && Mario.getY() < entorno.alto() + 5 && Mario.getEstadoVertical() != Personaje.EstadosVerticales.CAYENDO) {
				for (int i = 0; i < escalera.length; i++) {
					if (estadoVertical == Personaje.EstadosVerticales.ENESCALERA) {
						estadoVertical = Personaje.EstadosVerticales.SUBIENDOESCALERA;
					} 
					else if (Mario.colisionBarraVertical(viga[i]) && Mario.colisionBarraHorizontal(viga[i]) && colisionoPersonajeEscalera == false ) {
						estadoVertical = Personaje.EstadosVerticales.SALTANDO;
					}
				}
			}
						
			if (entorno.estaPresionada(entorno.TECLA_ABAJO) && Mario.getX() < entorno.alto() + 5) {
				for (int i = 0; i < escalera.length; i++) {
					if ((Mario.colisionBarraVertical(escalera[i]) 
						&& Mario.colisionBarraHorizontal(escalera[i])
						&& (escalera[i].getLimiteInferior() >= Mario.getLimiteInferior()))) {
						estadoVertical = Personaje.EstadosVerticales.BAJANDOESCALERA;
					}
				}
			}
			
		}
 		if (Mario.getEstadoVertical() != Personaje.EstadosVerticales.SALTANDO) {
			Mario.setEstadoVertical(estadoVertical);
		}
		Mario.setEstadoHorizontal(estadoHorizontal);
		Mario.movimientoVertical();
		Mario.movimientoHorizontal();
	}
	
	public void movimientoBarriles () {
		for (int i = 0; i < barril.length; i++) {
			boolean colisiono = false;
			boolean ultimaBarra = false;
			for (int j = 0; j < viga.length && !colisiono; j++) {
				colisiono = (barril[i].colisionBarraVertical(viga[j]) && barril[i].colisionBarraHorizontal(viga[j]));
				ultimaBarra = (j == (viga.length - 1));
			}

			barril[i].setCayendo(!colisiono);

			if (barril[i].getLimiteDerecho() >= entorno.ancho()) {
				barril[i].cambiarDireccion();
			} else if (barril[i].getLimiteIzquierdo() <= 0 && !ultimaBarra) {
				barril[i].cambiarDireccion();
			}
			if (barril[i].getLimiteDerecho() == 0) {
				barril[i].setActivo(false);
			}
			barril[i].moverse();
		}
	}

	/*
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar
	 * el estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	
	public void tick() {

		this.dibujarElementos();

		this.movimientoPersonaje();
	
		this.movimientoBarriles();
				
		contadorTicks++;

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
