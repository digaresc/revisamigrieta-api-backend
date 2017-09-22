package com.revisamigrieta.backend.models;

import com.google.appengine.api.datastore.GeoPt;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.Date;

@Entity
public class GrietaModel {

	@Id
	public Long id;

	@Index
	private boolean pared;
	@Index
	private boolean externa;
	@Index
	private boolean interna;
	@Index
	private boolean critica;
	@Index
	private boolean hundimientos;
	@Index
	private boolean desplomes;
	@Index
	private boolean golpeteo;
	@Index
	private boolean desprendimiento;
	@Index
	private boolean vibraciones;
	@Index
	private boolean pisosHuecos;
	@Index
	private boolean diagonalConHorizontalDePiso;
	@Index
	private boolean paralelaAlPiso;
	@Index
	private boolean loza;

	@Index
	private GeoPt geoPt;

	@Index
	private boolean diagonalEnLozaDeEsquinaACentro;

	@Index
	private Date createdOn;

	@Index
	private String message;
	@Index
	private String reportadaPor;

	public GrietaModel() {
		createdOn = new Date();
	}


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

	public GeoPt getGeoPt() {
		return geoPt;
	}

	public void setGeoPt(GeoPt geoPt) {
		this.geoPt = geoPt;
	}

	@Override
	public String toString() {
		return "GrietaModel{" +
				"id=" + id +
				", pared=" + pared +
				", externa=" + externa +
				", interna=" + interna +
				", critica=" + critica +
				", hundimientos=" + hundimientos +
				", desplomes=" + desplomes +
				", golpeteo=" + golpeteo +
				", desprendimiento=" + desprendimiento +
				", vibraciones=" + vibraciones +
				", pisosHuecos=" + pisosHuecos +
				", diagonalConHorizontalDePiso=" + diagonalConHorizontalDePiso +
				", paralelaAlPiso=" + paralelaAlPiso +
				", loza=" + loza +
				", geoPt=" + geoPt +
				", diagonalEnLozaDeEsquinaACentro=" + diagonalEnLozaDeEsquinaACentro +
				", createdOn=" + createdOn +
				", message='" + message + '\'' +
				", reportadaPor='" + reportadaPor + '\'' +
				'}';
	}
}
