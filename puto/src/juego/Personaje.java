package juego;

import java.awt.Color;

import entorno.Entorno;

public class Personaje {

	// Variables de instancia
	private int x, y;
	private int ancho;
	private int alto;
	private EstadosHorizontales estadoHorizontal;
	private EstadosVerticales estadoVertical;
	private int estadoSalto;
	private int yInicialSalto;
	private Color color;

	public Personaje(int x, int y, int ancho, int alto, Color color) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.estadoSalto = -20;
		this.yInicialSalto = this.y; 
		this.color = color; 
	}
	
	public void movimientoHorizontal() {
		switch (this.estadoHorizontal) {
			case MOVERDERECHA:
				this.moverDerecha();
				break;
			case MOVERIZQUIERDA:
				this.moverIzquierda();
				break;
			case PARADO:
				break;
		}
	}
	public void movimientoVertical () {
		switch (this.estadoVertical) {
			case SUBIENDOESCALERA:
				this.subir();
				break;
			case SALTANDO:
				this.continuarSalto();
				break;
			case CAYENDO:
				this.caer();
				break;			
			case BAJANDOESCALERA:
				this.Abajo();
				break;
			case ENESCALERA:
				break;			
			case PARADO:
				break;
		}	
	}	

	public void moverIzquierda() {
		this.x -= 3;
	}
	public void moverDerecha() {
		this.x += 3;
	}

	public void subir() {
		this.y -= 3;
	}
	
	public void saltar(int yEnSuelo, int x) {
		this.y += -(this.x-5)*(this.x+5) ;
	}

	public void caer() {
		this.y += 3;
	}

	public void Abajo() {
		this.y += 5;
	}	

	public void dibujarse(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, this.color);
	}
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean adentroBarraVertical(Barra barra) {
		return (this.getLimiteInferior() > barra.getLimiteSuperior())
				&& this.getLimiteSuperior() < barra.getLimiteInferior();
	}

	public boolean adentroBarraHorizontal(Barra barra) {
		return this.getLimiteIzquierdo() < barra.getLimiteDerecho()
				&& this.getLimiteDerecho() > barra.getLimiteIzquierdo();
	}

	public boolean colisionBarraVertical(Barra barra) {
		return (this.getLimiteInferior() >= barra.getLimiteSuperior())
				&& this.getLimiteSuperior() <= barra.getLimiteInferior();
	}

	public boolean colisionBarraHorizontal(Barra barra) {
		return this.getLimiteIzquierdo() <= barra.getLimiteDerecho()
				&& this.getLimiteDerecho() >= barra.getLimiteIzquierdo();
	}
	
	public boolean colisionBarrilVertical(Barril barril) {
		return (this.getLimiteInferior() >= barril.getLimiteSuperior())
				&& this.getLimiteSuperior() <= barril.getLimiteInferior();
	}
	
	public boolean colisionBarrilHorizontal(Barril barril) {
		return this.getLimiteIzquierdo() <= barril.getLimiteDerecho()
				&& this.getLimiteDerecho() >= barril.getLimiteIzquierdo();
	}
	
	public boolean colisionPersonajeVertical(Personaje personaje) {
		return (this.getLimiteInferior() >= personaje.getLimiteSuperior())
				&& this.getLimiteSuperior() <= personaje.getLimiteInferior();
	}

	public boolean colisionPersonajeHorizontal(Personaje personaje) {
		return this.getLimiteIzquierdo() <= personaje.getLimiteDerecho()
				&& this.getLimiteDerecho() >= personaje.getLimiteIzquierdo();
	}
	
	public double getLimiteInferior() {
		return this.y + (this.alto / 2);
	}

	public double getLimiteIzquierdo() {
		return this.x - (this.ancho / 2);
	}

	public double getLimiteDerecho() {
		return this.x + (this.ancho / 2);
	}
	
	public double getLimiteSuperior() {
		return this.y - (this.alto / 2);
	}
	
	public boolean isCayendo() {
		return (this.estadoVertical == EstadosVerticales.CAYENDO);
	}
	
	public void setCayendo() {
		this.estadoVertical = EstadosVerticales.CAYENDO;
	}

	public boolean isSaltando() {
		return this.estadoVertical == EstadosVerticales.SALTANDO;
	}

	public void setSaltando(boolean saltando) {
		this.estadoVertical = EstadosVerticales.SALTANDO;
	}
	
	public void continuarSalto () {
		if (this.estadoSalto == -20) {	
			this.estadoSalto = -19;
			this.yInicialSalto = this.y; 
		}
		this.y = this.yInicialSalto + this.variacionDeSalto();
		this.estadoSalto++;
		
		if (this.estadoSalto == 21) {
			this.estadoHorizontal = EstadosHorizontales.PARADO;
			this.estadoVertical = EstadosVerticales.PARADO;
			this.estadoSalto = -20;
		}
	}
	
	private int variacionDeSalto () {
		return ((this.estadoSalto + 20)*(this.estadoSalto - 20)/5);
	}
	
	public enum EstadosVerticales {
	    PARADO, SALTANDO, CAYENDO, ENESCALERA, SUBIENDOESCALERA, BAJANDOESCALERA
	}	
	public enum EstadosHorizontales {
		PARADO, MOVERDERECHA, MOVERIZQUIERDA
	}
	
	public EstadosVerticales getEstadoVertical() {
		return estadoVertical;
	}
	public EstadosHorizontales getEstadoHorizontal() {
		return estadoHorizontal;
	}
	public void setEstadoVertical (EstadosVerticales estado) {
		this.estadoVertical = estado;
	}
	public void setEstadoHorizontal (EstadosHorizontales estado) {
		this.estadoHorizontal = estado;
	}
	
	
}
