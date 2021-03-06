package com.yuan.utr.model.persistent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement
@Entity
@Table(name="player_result_view")
public class PlayerResultView implements Serializable
{

	public PlayerResultView() {
	}

	private String firstName;
	private String lastName;
	private int matchWon;
	private int matchLost;
	private BigDecimal matchWonPercent;
	private int gameWon;
	private int gameLost;
	private BigDecimal gameWonPercent;
	private long playerId;
	private long divisionId;
	private long seasonId;

	private long id;

	@Id
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	@Column(name = "player_id")
	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	@Column(name = "division_id")
	public long getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(long divisionId) {
		this.divisionId = divisionId;
	}

	@Column(name = "season_id")
	public long getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(long seasonId) {
		this.seasonId = seasonId;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "match_won")
	public int getMatchWon() {
		return matchWon;
	}

	public void setMatchWon(int matchWon) {
		this.matchWon = matchWon;
	}

	@Column(name = "match_lost")
	public int getMatchLost() {
		return matchLost;
	}

	public void setMatchLost(int matchLost) {
		this.matchLost = matchLost;
	}

	@Column(name = "match_won_percent")
	public BigDecimal getMatchWonPercent() {
		return matchWonPercent;
	}

	public void setMatchWonPercent(BigDecimal matchWonPercent) {
		this.matchWonPercent = matchWonPercent;
	}

	@Column(name = "game_won")
	public int getGameWon() {
		return gameWon;
	}

	public void setGameWon(int gameWon) {
		this.gameWon = gameWon;
	}

	@Column(name = "game_lost")
	public int getGameLost() {
		return gameLost;
	}

	public void setGameLost(int gameLost) {
		this.gameLost = gameLost;
	}

	@Column(name = "game_won_percent")
	public BigDecimal getGameWonPercent() {
		return gameWonPercent;
	}

	public void setGameWonPercent(BigDecimal gameWonPercent) {
		this.gameWonPercent = gameWonPercent;
	}

}

