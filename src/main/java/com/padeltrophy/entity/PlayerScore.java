package com.padeltrophy.entity;

import java.util.Date;

import com.padeltrophy.util.json.ObjectIdJsonSerializer;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PlayerScore {
	
	@Id
	private ObjectId id;
	
	//Jugador que produce el voto
	@DBRef(lazy=true)
	private Player producer;
	
	//Cuando el playerId y el producerId es el mismo se refiere 
	//a la puntuación que se ha dado un jugador a si mismo
	
	private short attack;
	private short defence;
	private short technique;
	private short power;
	private short resistance;
	private short temperance;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm")
	private Date createdAt;
	public ObjectId getId() {
		return id;
	}
	public Player getProducer() {
		return producer;
	}
	public void setProducer(Player producer) {
		this.producer = producer;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public short getAttack() {
		return attack;
	}
	public void setAttack(short attack) {
		this.attack = attack;
	}
	public short getDefence() {
		return defence;
	}
	public void setDefence(short defence) {
		this.defence = defence;
	}
	public short getTechnique() {
		return technique;
	}
	public void setTechnique(short technique) {
		this.technique = technique;
	}
	public short getPower() {
		return power;
	}
	public void setPower(short power) {
		this.power = power;
	}
	public short getResistance() {
		return resistance;
	}
	public void setResistance(short resistance) {
		this.resistance = resistance;
	}
	public short getTemperance() {
		return temperance;
	}
	public void setTemperance(short temperance) {
		this.temperance = temperance;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	
}
