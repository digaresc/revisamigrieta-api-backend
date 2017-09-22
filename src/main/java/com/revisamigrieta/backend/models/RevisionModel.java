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
	private boolean piso;
	@Index
	private boolean loza;
	@Index
	private boolean grietasFormanDiagonalDelPiso;
	@Index
	private boolean grietasParalelasAPiso;
	@Index
	private boolean diagonalEnLozaDeEsquinaACentro;
	@Index
	private boolean peligroIniminente;
	@Index
	private String comentariosAdicionales;
	@Index
	private String revisadaPor;

	@Index
	private Date createdOn;

	public RevisionModel() {
		createdOn = new Date();
	}

	public GrietaModel getGrietaModelRef() {
		return grietaModelRef.get();
	}

	public void setGrietaModelRef(String grietaModelId) {
		this.grietaModelRef = Ref.create(Key.create(GrietaModel.class, grietaModelId));
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

	@Override
	public String toString() {
		return "RevisionModel{" +
				"id=" + id +
				", grietaModelRef=" + grietaModelRef +
				", piso=" + piso +
				", loza=" + loza +
				", grietasFormanDiagonalDelPiso=" + grietasFormanDiagonalDelPiso +
				", grietasParalelasAPiso=" + grietasParalelasAPiso +
				", diagonalEnLozaDeEsquinaACentro=" + diagonalEnLozaDeEsquinaACentro +
				", peligroIniminente=" + peligroIniminente +
				", comentariosAdicionales='" + comentariosAdicionales + '\'' +
				", revisadaPor='" + revisadaPor + '\'' +
				", createdOn=" + createdOn +
				'}';
	}
}

