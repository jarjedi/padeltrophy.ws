package com.padeltrophy.entity;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.padeltrophy.util.json.ObjectIdJsonSerializer;
import com.padeltrophy.util.json.TournamentLocationDeserializer;
import com.padeltrophy.util.json.TournamentOrganizerDeserializer;
import com.padeltrophy.util.json.TournamentOrganizerSerializer;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Tournament {

	@Id
    private ObjectId id;
	private String name;
	private String province;
	@DBRef(lazy=false)
	private Club location;
	@DBRef(lazy=true)
	private Player organizer;
	private String telephone;
	private String billboardUrl;
	private boolean approved;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date dateFrom;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date dateTo;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date registrationDateFrom;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm")
	private Date registrationDateTo;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm")
	private Date createdAt;
	private ArrayList<Category> categories;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvince() {
		return this.province;
	}
	public Club getLocation() {
		return location;
	}
	//@JsonDeserialize(using = TournamentLocationDeserializer.class)
	public void setLocation(Club location) {
		this.location = location;
	}
	//@JsonSerialize(using=TournamentOrganizerSerializer.class)
	public Player getOrganizer() {
		return organizer;
	}
	//@JsonDeserialize(using = TournamentOrganizerDeserializer.class)
	public void setOrganizer(Player organizer) {
		this.organizer = organizer;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getBillboardUrl() {
		return billboardUrl;
	}
	public void setBillboardUrl(String billboardUrl) {
		this.billboardUrl = billboardUrl;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getRegistrationDateFrom() {
		return registrationDateFrom;
	}
	public void setRegistrationDateFrom(Date registrationDateFrom) {
		this.registrationDateFrom = registrationDateFrom;
	}
	public Date getRegistrationDateTo() {
		return registrationDateTo;
	}
	public void setRegistrationDateTo(Date registrationDateTo) {
		this.registrationDateTo = registrationDateTo;
	}
	public ArrayList<Category> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}
}
