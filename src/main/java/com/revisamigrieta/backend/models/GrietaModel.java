package com.revisamigrieta.backend.models;

import com.google.appengine.api.datastore.GeoPt;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;
import java.util.Date;

@Entity
public class GrietaModel {

	@Id
	public Long id;

	@Index
	private ArrayList<String> files;

	@Index
	private GeoPt geolocalizacion;

	@Index
	private TipoEnum tipo;

	@Index
	private UbicacionEnum ubicacion;

	@Index
	private String tweet;

	@Index
	private boolean revisada;

	@Index
	private Date createdOn;

	@Index
	private Date modifiedOn;

	@Index
	private String comentario;

	@Index
	private boolean diagonalesLosa;

	@Index
	private boolean diagonalesPiso;

	@Index
	private boolean paralelasPiso;

	@Index
	private String userId;


	@Index
	private EstadoDeObra estadoDeObra;

	public GrietaModel() {
		createdOn = new Date();
	}

	public ArrayList<String> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<String> files) {
		this.files = files;
	}

	public GeoPt getGeolocalizacion() {
		return geolocalizacion;
	}

	public void setGeolocalizacion(GeoPt geolocalizacion) {
		this.geolocalizacion = geolocalizacion;
	}

	public TipoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoEnum tipo) {
		this.tipo = tipo;
	}

	public UbicacionEnum getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(UbicacionEnum ubicacionEnum) {
		this.ubicacion = ubicacionEnum;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public boolean isRevisada() {
		return revisada;
	}

	public void setRevisada(boolean revisada) {
		this.revisada = revisada;
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

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public boolean isDiagonalesLosa() {
		return diagonalesLosa;
	}

	public void setDiagonalesLosa(boolean diagonalesLosa) {
		this.diagonalesLosa = diagonalesLosa;
	}

	public boolean isDiagonalesPiso() {
		return diagonalesPiso;
	}

	public void setDiagonalesPiso(boolean diagonalesPiso) {
		this.diagonalesPiso = diagonalesPiso;
	}

	public boolean isParalelasPiso() {
		return paralelasPiso;
	}

	public void setParalelasPiso(boolean paralelasPiso) {
		this.paralelasPiso = paralelasPiso;
	}

	public EstadoDeObra getEstadoDeObra() {
		return estadoDeObra;
	}

	public void setEstadoDeObra(EstadoDeObra estadoDeObra) {
		this.estadoDeObra = estadoDeObra;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "GrietaModel{" +
				"id=" + id +
				", files=" + files +
				", geolocalizacion=" + geolocalizacion +
				", tipo=" + tipo +
				", ubicacionEnum=" + ubicacion +
				", tweet='" + tweet + '\'' +
				", revisada=" + revisada +
				", createdOn=" + createdOn +
				", modifiedOn=" + modifiedOn +
				", comentario='" + comentario + '\'' +
				", diagonalesLosa=" + diagonalesLosa +
				", diagonalesPiso=" + diagonalesPiso +
				", paralelasPiso=" + paralelasPiso +
				", userId='" + userId + '\'' +
				", estadoDeObra=" + estadoDeObra +
				'}';
	}
}



