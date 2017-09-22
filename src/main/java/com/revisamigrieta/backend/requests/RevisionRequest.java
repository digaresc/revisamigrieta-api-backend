package com.revisamigrieta.backend.requests;

import java.util.Date;

public class RevisionRequest {
	private GrietaRequest grietaRequest;
	private boolean piso;
	private boolean loza;
	private boolean grietasFormanDiagonalDelPiso;
	private boolean grietasParalelasAPiso;
	private boolean diagonalEnLozaDeEsquinaACentro;
	private boolean peligroIniminente;
	private String comentariosAdicionales;
	private String revisadaPor;
	private Date createdOn;



	public GrietaRequest getGrietaRequest() {
		return grietaRequest;
	}

	public void setGrietaRequest(GrietaRequest grietaRequest) {
		this.grietaRequest = grietaRequest;
	}

	public boolean isPiso() {
		return piso;
	}

	public void setPiso(boolean piso) {
		this.piso = piso;
	}

	public boolean isLoza() {
		return loza;
	}

	public void setLoza(boolean loza) {
		this.loza = loza;
	}

	public boolean isGrietasFormanDiagonalDelPiso() {
		return grietasFormanDiagonalDelPiso;
	}

	public void setGrietasFormanDiagonalDelPiso(boolean grietasFormanDiagonalDelPiso) {
		this.grietasFormanDiagonalDelPiso = grietasFormanDiagonalDelPiso;
	}

	public boolean isGrietasParalelasAPiso() {
		return grietasParalelasAPiso;
	}

	public void setGrietasParalelasAPiso(boolean grietasParalelasAPiso) {
		this.grietasParalelasAPiso = grietasParalelasAPiso;
	}

	public boolean isDiagonalEnLozaDeEsquinaACentro() {
		return diagonalEnLozaDeEsquinaACentro;
	}

	public void setDiagonalEnLozaDeEsquinaACentro(boolean diagonalEnLozaDeEsquinaACentro) {
		this.diagonalEnLozaDeEsquinaACentro = diagonalEnLozaDeEsquinaACentro;
	}

	public boolean isPeligroIniminente() {
		return peligroIniminente;
	}

	public void setPeligroIniminente(boolean peligroIniminente) {
		this.peligroIniminente = peligroIniminente;
	}

	public String getComentariosAdicionales() {
		return comentariosAdicionales;
	}

	public void setComentariosAdicionales(String comentariosAdicionales) {
		this.comentariosAdicionales = comentariosAdicionales;
	}

	public String getRevisadaPor() {
		return revisadaPor;
	}

	public void setRevisadaPor(String revisadaPor) {
		this.revisadaPor = revisadaPor;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}




}

