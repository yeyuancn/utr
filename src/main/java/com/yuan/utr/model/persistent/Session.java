package com.yuan.utr.model.persistent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name="session")
public class Session implements Serializable
{

	public Session() {
	}

	private String uuid;

	private long playerId;

	private Date expireDate;

	private long id;

	@Column(name = "uuid")
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "expire_date")
	public Date getExpireDate()
	{
		return expireDate;
	}

	public void setExpireDate(Date expireDate)
	{
		this.expireDate = expireDate;
	}

	@Column(name = "player_id")
	public long getPlayerId()
	{
		return playerId;
	}

	public void setPlayerId(long playerId)
	{
		this.playerId = playerId;
	}

	@Id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
