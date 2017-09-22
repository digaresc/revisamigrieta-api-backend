package com.revisamigrieta.backend.requests;

import java.util.Date;

public class GrietaRequest {

	private boolean pared;
	private boolean externa;
	private boolean interna;
	private boolean critica;
	private boolean hundimientos;
	private boolean desplomes;
	private boolean golpeteo;
	private boolean desprendimiento;
	private boolean vibraciones;
	private boolean pisosHuecos;
	private boolean diagonalConHorizontalDePiso;
	private boolean paralelaAlPiso;
	private boolean loza;
	private boolean diagonalEnLozaDeEsquinaACentro;
	private Date createdOn;
	private Float latitude;
	private Float longitude;


	private String message;
	private String reportadaPor;

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isPared() {
		return pared;
	}

	public void setPared(boolean pared) {
		this.pared = pared;
	}

	public boolean isExterna() {
		return externa;
	}

	public void setExterna(boolean externa) {
		this.externa = externa;
	}

	public boolean isInterna() {
		return interna;
	}

	public void setInterna(boolean interna) {
		this.interna = interna;
	}

	public boolean isCritica() {
		return critica;
	}

	public void setCritica(boolean critica) {
		this.critica = critica;
	}

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

	public boolean isDiagonalConHorizontalDePiso() {
		return diagonalConHorizontalDePiso;
	}

	public void setDiagonalConHorizontalDePiso(boolean diagonalConHorizontalDePiso) {
		this.diagonalConHorizontalDePiso = diagonalConHorizontalDePiso;
	}

	public boolean isParalelaAlPiso() {
		return paralelaAlPiso;
	}

	public void setParalelaAlPiso(boolean paralelaAlPiso) {
		this.paralelaAlPiso = paralelaAlPiso;
	}

	public boolean isLoza() {
		return loza;
	}

	public void setLoza(boolean loza) {
		this.loza = loza;
	}

	public boolean isDiagonalEnLozaDeEsquinaACentro() {
		return diagonalEnLozaDeEsquinaACentro;
	}

	public void setDiagonalEnLozaDeEsquinaACentro(boolean diagonalEnLozaDeEsquinaACentro) {
		this.diagonalEnLozaDeEsquinaACentro = diagonalEnLozaDeEsquinaACentro;
	}

	public String getReportadaPor() {
		return reportadaPor;
	}

	public void setReportadaPor(String reportadaPor) {
		this.reportadaPor = reportadaPor;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
}
