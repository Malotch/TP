package juego;

import java.awt.Color;

import entorno.Entorno;

public class Barra {

	// Variables de instancia
	private int x, y;
	private int ancho;
	private int alto;
	private int limiteInferior;
	private int limiteIzquierdo;
	private int limiteDerecho;
	private int limiteSuperior;

	public Barra(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.limiteInferior = this.y + (this.alto / 2);
		this.limiteIzquierdo = this.x - (this.ancho / 2);
		this.limiteDerecho = this.x + (this.ancho / 2);
		this.limiteSuperior = this.y - (this.alto / 2);
	}

	public void dibujarse(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0,
				Color.yellow);

	}

	public void dibujarseEscalera(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0,
				Color.red);

	}

	public int getX() {
		return this.x;
	}

	public boolean colisionaCon(Barra p) {
		if (p.getY() >= this.y - (this.alto / 2)
				&& p.getY() <= this.y + (this.alto / 2)
				&& p.getX() >= this.x - (this.ancho / 2)
				&& p.getX() <= this.x + (this.ancho / 2)) {
			return true;
		}

		return false;
	}

	public int getY() {
		return this.y;
	}

	public int getAncho() {
		return this.ancho;
	}

	public int getAlto() {
		return this.alto;
	}

	public int getLimiteInferior() {
		return limiteInferior;
	}

	public void setLimiteInferior(int limiteInferior) {
		this.limiteInferior = limiteInferior;
	}

	public int getLimiteIzquierdo() {
		return limiteIzquierdo;
	}

	public void setLimiteIzquierdo(int limiteIzquierdo) {
		this.limiteIzquierdo = limiteIzquierdo;
	}

	public int getLimiteDerecho() {
		return limiteDerecho;
	}

	public void setLimiteDerecho(int limiteDerecho) {
		this.limiteDerecho = limiteDerecho;
	}

	public int getLimiteSuperior() {
		return limiteSuperior;
	}

	public void setLimiteSuperior(int limiteSuperior) {
		this.limiteSuperior = limiteSuperior;
	}
}