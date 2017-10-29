package juego;

import java.awt.Color;

import entorno.Entorno;

public class Barril {
	// Variables de instancia
	private int x, y;
	private double diametro;
	private int velocidad;
	private boolean cayendo;
	private int direccion;
	private boolean activo;

	public Barril(int x, int y, int diametro) {
		this.diametro = diametro;
		this.x = x;
		this.y = y;
		this.velocidad = 3;
		this.cayendo = true;
		this.direccion = 1;
		this.activo = false;
	}

	public void dibujarse(Entorno entorno) {
		entorno.dibujarCirculo(this.x, this.y, this.diametro, Color.white);
	}

	public void avanzar(int variacion) {
		this.x += variacion * this.direccion;
	}

	public void moverse() {
		if (this.isCayendo()) {
			this.caer(1);
			this.avanzar(1);
		} else {
			this.avanzar(this.velocidad);
		}
	}

	public void cambiarDireccion() {
		this.direccion *= -1;
	}

	public int caer(int y) {
		return this.y += y;
	}

	public boolean colisionBarraVertical(Barra barra) {
		return (this.getLimiteInferior() >= barra.getLimiteSuperior())
				&& this.getLimiteSuperior() <= barra.getLimiteInferior();
	}

	public boolean colisionBarraHorizontal(Barra barra) {
		return this.getLimiteIzquierdo() <= barra.getLimiteDerecho()
				&& this.getLimiteDerecho() >= barra.getLimiteIzquierdo();
	}

	public int getY() {
		return this.y;
	}

	public int getX() {
		return this.x;
	}

	public double getDiametro() {
		return this.diametro;
	}

	public double getLimiteInferior() {
		return this.y + (this.diametro / 2);
	}

	public double getLimiteIzquierdo() {
		return this.x - (this.diametro / 2);
	}

	public double getLimiteDerecho() {
		return this.x + (this.diametro / 2);
	}

	public double getLimiteSuperior() {
		return this.y - (this.diametro / 2);
	}

	public boolean isCayendo() {
		return cayendo;
	}

	public void setCayendo(boolean cayendo) {
		this.cayendo = cayendo;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void reSet() {
		this.x = 50;
		this.y = 50;
		this.cambiarDireccion();
		this.setActivo(true);
	}

}
