package com.padeltrophy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.padeltrophy.util.json.ObjectIdJsonSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Document
public class Club {

	@Id
	private ObjectId id;
	private String name;
	private String telephone;
	private ArrayList<String> gallery;
	Location location;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm")
	private Date createdAt;
	private String logoUrl;
	private boolean approved;
	private int indoorCourts;
	private int outdoorCourts;

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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public ArrayList<String> getGallery() {
		return gallery;
	}

	public void setGallery(ArrayList<String> gallery) {
		this.gallery = gallery;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public int getIndoorCourts() {
		return indoorCourts;
	}

	public void setIndoorCourts(int indoorCourts) {
		this.indoorCourts = indoorCourts;
	}

	public int getOutdoorCourts() {
		return outdoorCourts;
	}

	public void setOutdoorCourts(int outdoorCourts) {
		this.outdoorCourts = outdoorCourts;
	}
}
