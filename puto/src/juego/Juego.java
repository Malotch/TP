package juego;

import java.awt.Color;
import java.util.Random;
import entorno.Entorno;
import entorno.InterfaceJuego;
import juego.Personaje.EstadosHorizontales;
import juego.Personaje.EstadosVerticales;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;

	// Variables y métodos propios de cada grupo
	// ...
	Barril[] barril = new Barril[100];
	Barra[] viga = new Barra[4];
	Barra[] escalera = new Barra[4];
	Personaje donkey = null;
	Personaje mario = null;
	int contadorTicks = 0;
	int tickSalidaBarril = 0;
	Random numeroAleatorioBarril = new Random();
	int ultimaSalidaBarril = 0;
	private estadosDeJuego estadoDeJuego;

	Juego() {

		this.entorno = new Entorno(this, "RescateDonkey Fiandrino, Grauberger, - , - ", 800, 600);
		this.estadoInicial();
		// Inicializar lo que haga falta para el juego
		// ...
	}
		public void estadoInicial () {
			estadoDeJuego = estadoDeJuego.JUGANDO;
			mario = new Personaje(40, 550, 20, 40, Color.BLUE);
			donkey = new Personaje(60, 95, 50, 80, Color.GREEN);
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
			
			// Inicia el juego!
	
			this.entorno.iniciar();
		}
	
	public void dibujarElementos () {

		for (int i = 0; i < barril.length; i++) {
			if (barril[i].isActivo()) {
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
		donkey.dibujarse(entorno);
		mario.dibujarse(entorno);
	}
	
	public void movimientoPersonaje () {
		boolean colisionoPersonajeViga = false;
		boolean colisionoPersonajeEscalera = false;
		boolean personajeAdentroViga = false;
		Personaje.EstadosVerticales estadoVertical = Personaje.EstadosVerticales.PARADO;
		Personaje.EstadosHorizontales estadoHorizontal = Personaje.EstadosHorizontales.PARADO;
		for (int i = 0; i < viga.length && !colisionoPersonajeViga; i++) {
			colisionoPersonajeViga = ((mario.colisionBarraVertical(viga[i]) && mario.colisionBarraHorizontal(viga[i])));
			personajeAdentroViga = ((mario.adentroBarraVertical(viga[i]) && mario.adentroBarraHorizontal(viga[i])));
		}
		for (int i = 0; i < escalera.length && !colisionoPersonajeEscalera; i++) {
			colisionoPersonajeEscalera = (mario.colisionBarraVertical((escalera[i]))) && mario.colisionBarraHorizontal(escalera[i]);
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
	
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && mario.getX() - 5 > 0 && !personajeAdentroViga)
			estadoHorizontal = Personaje.EstadosHorizontales.MOVERIZQUIERDA;
		
		if (entorno.estaPresionada(entorno.TECLA_DERECHA) && mario.getX() + 5 < entorno.ancho() - 5 && !personajeAdentroViga)
			estadoHorizontal = Personaje.EstadosHorizontales.MOVERDERECHA;
		
		if ( estadoVertical != Personaje.EstadosVerticales.SALTANDO) {
			if (entorno.estaPresionada(entorno.TECLA_ARRIBA) && mario.getY() < entorno.alto() + 5 && mario.getEstadoVertical() != Personaje.EstadosVerticales.CAYENDO) {
				for (int i = 0; i < escalera.length; i++) {
					if (estadoVertical == Personaje.EstadosVerticales.ENESCALERA) {
						estadoVertical = Personaje.EstadosVerticales.SUBIENDOESCALERA;
					} 
					else if (mario.colisionBarraVertical(viga[i]) && mario.colisionBarraHorizontal(viga[i]) && colisionoPersonajeEscalera == false ) {
						estadoVertical = Personaje.EstadosVerticales.SALTANDO;
					}
				}
			}
						
			if (entorno.estaPresionada(entorno.TECLA_ABAJO) && mario.getX() < entorno.alto() + 5) {
				for (int i = 0; i < escalera.length; i++) {
					if ((mario.colisionBarraVertical(escalera[i]) 
						&& mario.colisionBarraHorizontal(escalera[i])
						&& (escalera[i].getLimiteInferior() >= mario.getLimiteInferior()))) {
						estadoVertical = Personaje.EstadosVerticales.BAJANDOESCALERA;
					}
				}
			}
			
		}
		for (int i = 0; i < barril.length; i++) {
			if (mario.colisionBarrilHorizontal(barril[i]) && mario.colisionBarrilVertical(barril[i])){
				estadoDeJuego = estadoDeJuego.PERDIDO;
			}
		}
		if (mario.colisionPersonajeHorizontal(donkey) && mario.colisionPersonajeVertical(donkey)){
			estadoDeJuego = estadoDeJuego.GANADO;
		}
 		if (mario.getEstadoVertical() != Personaje.EstadosVerticales.SALTANDO) {
			mario.setEstadoVertical(estadoVertical);
		}
		mario.setEstadoHorizontal(estadoHorizontal);
		mario.movimientoVertical();
		mario.movimientoHorizontal();
	}
	
	public void movimientoBarriles () {
		
		for (int i = 0; i < barril.length; i++) {		
			if (barril[i].isActivo()) {
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
					barril[i].reSet();
				}			
				barril[i].moverse();
			}
		}
}
	/*
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar
	 * el estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	
	public void tick() {
		switch (this.estadoDeJuego) {
			case JUGANDO:
				if ( contadorTicks%100 == 0) {
					if (numeroAleatorioBarril.nextInt(10) < 5) { 
						this.lanzarBarril();
					}
				}
				
				this.dibujarElementos();

				this.movimientoPersonaje();
			
				this.movimientoBarriles();
						
				contadorTicks++;
				break;
			case GANADO:
				festejoPersonaje (mario);
				entorno.escribirTexto("GANASTE", 300, 300);
				break;
			case PERDIDO:
				festejoPersonaje (donkey);
				entorno.escribirTexto("PERDISTE apreta enter para volver a arrancar", 300, 300);
				if (entorno.estaPresionada(entorno.TECLA_ENTER)) {
					this.estadoInicial();
				}				
				break;
		}
		

	}

	private void lanzarBarril() {
		for (int i = 0; i < barril.length; i++) {			
			if (!barril[i].isActivo()) {
				barril[i].setActivo(true);
				break;
			}
		}
		
	}
	public void festejoPersonaje (Personaje personaje) {
		personaje.setEstadoVertical(Personaje.EstadosVerticales.SALTANDO);
		personaje.movimientoVertical();
		personaje.dibujarse(entorno);
	}
	
	public enum estadosDeJuego {
		JUGANDO, GANADO, PERDIDO
	}
	
	public estadosDeJuego getestadosDeJuego() {
		return estadoDeJuego;
	}
	
	public void setestadosDeJuego (estadosDeJuego estado) {
		this.estadoDeJuego = estado;
	}


	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
