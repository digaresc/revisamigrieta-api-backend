package com.revisamigrieta.backend.models;

public class EstadoDeObra {
	private boolean hundimientos;
	private boolean desplomes;
	private boolean golpeteo;
	private boolean desprendimiento;
	private boolean vibraciones;
	private boolean pisosHuecos;
	private boolean mas20porciento;

	public boolean isHundimientos() {
		return hundimientos;
	}

	public void setHundimientos(boolean hundimientos) {
		this.hundimientos = hundimientos;
	}

	public boolean isDesplomes() {
		return desplomes;
	}

	public void setDesplomes(boolean desplomes) {
		this.desplomes = desplomes;
	}

	public boolean isGolpeteo() {
		return golpeteo;
	}

	public void setGolpeteo(boolean golpeteo) {
		this.golpeteo = golpeteo;
	}

	public boolean isDesprendimiento() {
		return desprendimiento;
	}

	public void setDesprendimiento(boolean desprendimiento) {
		this.desprendimiento = desprendimiento;
	}

	public boolean isVibraciones() {
		return vibraciones;
	}

	public void setVibraciones(boolean vibraciones) {
		this.vibraciones = vibraciones;
	}

	public boolean isPisosHuecos() {
		return pisosHuecos;
	}

	public void setPisosHuecos(boolean pisosHuecos) {
		this.pisosHuecos = pisosHuecos;
	}

	public boolean isMas20porciento() {
		return mas20porciento;
	}

	public void setMas20porciento(boolean mas20porciento) {
		this.mas20porciento = mas20porciento;
	}
}
