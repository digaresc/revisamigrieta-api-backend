package com.revisamigrieta.backend.models;

import java.util.Date;

public class RevisionModel {
	private boolean diagonalesLosa;
	private boolean paralelasPiso;
	private boolean diagonalesPiso;
	private boolean peligroInminente;
	private String comentarios;
	private String revisadaPor;
	private Date createdOn;
	private Date modifiedOn;

	public RevisionModel() {
		createdOn = new Date();
	}

	public boolean isDiagonalesLosa() {
		return diagonalesLosa;
	}

	public void setDiagonalesLosa(boolean diagonalesLosa) {
		this.diagonalesLosa = diagonalesLosa;
	}

	public boolean isParalelasPiso() {
		return paralelasPiso;
	}

	public void setParalelasPiso(boolean paralelasPiso) {
		this.paralelasPiso = paralelasPiso;
	}

	public boolean isDiagonalesPiso() {
		return diagonalesPiso;
	}

	public void setDiagonalesPiso(boolean diagonalesPiso) {
		this.diagonalesPiso = diagonalesPiso;
	}

	public boolean isPeligroInminente() {
		return peligroInminente;
	}

	public void setPeligroInminente(boolean peligroInminente) {
		this.peligroInminente = peligroInminente;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
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

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}


}

