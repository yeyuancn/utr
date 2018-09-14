package com.yuan.utr.model.persistent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name="league")
public class League implements Serializable
{

	public League() {
	}

	private String name;

	private String city;

	private String state;

	private Date createTime;

	private Long currentSeasonId;

	private long id;

	@Column(name = "current_season_id")
	public Long getCurrentSeasonId() {
		return currentSeasonId;
	}

	public void setCurrentSeasonId(Long currentSeasonId) {
		this.currentSeasonId = currentSeasonId;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "city")
	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	@Column(name = "state")
	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	@Id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
