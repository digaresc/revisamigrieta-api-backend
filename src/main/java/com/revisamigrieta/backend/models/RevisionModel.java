package com.revisamigrieta.backend.models;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;


import java.util.Date;

@Entity
public class RevisionModel {

	@Id
	public Long id;

	@Index
	@Load
	Ref<GrietaModel> grietaModelRef;

	@Index
	private boolean diagonalesLosa;

	@Index
	private boolean paralelasPiso;

	@Index
	private boolean diagonalesPiso;

	@Index
	private boolean peligroInminente;

	@Index
	private String comentarios;

	@Index
	private String revisadaPor;

	@Index
	private Date createdOn;

	@Index
	private Date modifiedOn;

	public RevisionModel() {
		createdOn = new Date();
	}

	public GrietaModel getGrietaModelRef() {
		return grietaModelRef.get();
	}

	public void setGrietaModelRef(String grietaModelId) {
		this.grietaModelRef = Ref.create(Key.create(GrietaModel.class, grietaModelId));
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

	@Override
	public String toString() {
		return "RevisionModel{" +
				"id=" + id +
				", grietaModelRef=" + grietaModelRef +
				", diagonalesLosa=" + diagonalesLosa +
				", paralelasPiso=" + paralelasPiso +
				", diagonalesPiso=" + diagonalesPiso +
				", peligroInminente=" + peligroInminente +
				", comentarios='" + comentarios + '\'' +
				", revisadaPor='" + revisadaPor + '\'' +
				", createdOn=" + createdOn +
				", modifiedOn=" + modifiedOn +
				'}';
	}
}

