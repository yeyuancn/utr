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
@Table(name = "match_result")
public class MatchResult implements Serializable
{

    public MatchResult() {}

    private long divisionId;

    private long winnerId;

    private long loserId;

    private String set1Score;

    private String set2Score;

    private String set3Score;

    private String matchScore;

    private int gameWon;

    private int gameLost;

    private boolean enterByWinner;

    private Date matchDate;

    private Date recordTime;

    private String matchMemo;

    private long id;

    @Column(name = "division_id")
    public long getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(long divisionId) {
        this.divisionId = divisionId;
    }

    @Column(name = "winner_id")
    public long getWinnerId()
    {
        return winnerId;
    }

    public void setWinnerId(long winnerId)
    {
        this.winnerId = winnerId;
    }

    @Column(name = "loser_id")
    public long getLoserId()
    {
        return loserId;
    }

    public void setLoserId(long loserId)
    {
        this.loserId = loserId;
    }

    @Column(name = "set1_score")
    public String getSet1Score()
    {
        return set1Score;
    }

    public void setSet1Score(String set1Score)
    {
        this.set1Score = set1Score;
    }

    @Column(name = "set2_score")
    public String getSet2Score()
    {
        return set2Score;
    }

    public void setSet2Score(String set2Score)
    {
        this.set2Score = set2Score;
    }

    @Column(name = "set3_score")
    public String getSet3Score()
    {
        return set3Score;
    }

    public void setSet3Score(String set3Score)
    {
        this.set3Score = set3Score;
    }

    @Column(name = "match_score")
    public String getMatchScore()
    {
        return matchScore;
    }

    public void setMatchScore(String matchScore)
    {
        this.matchScore = matchScore;
    }

    @Column(name = "enter_by_winner")
    public boolean getEnterByWinner()
    {
        return enterByWinner;
    }

    public void setEnterByWinner(boolean enterByWinner)
    {
        this.enterByWinner = enterByWinner;
    }

    @Column(name = "match_date")
    public Date getMatchDate()
    {
        return matchDate;
    }

    public void setMatchDate(Date matchDate)
    {
        this.matchDate = matchDate;
    }

    @Column(name = "record_time")
    public Date getRecordTime()
    {
        return recordTime;
    }

    public void setRecordTime(Date recordTime)
    {
        this.recordTime = recordTime;
    }

    @Id
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    @Column(name = "game_won")
    public int getGameWon()
    {
        return gameWon;
    }

    public void setGameWon(int gameWon)
    {
        this.gameWon = gameWon;
    }

    @Column(name = "game_lost")
    public int getGameLost()
    {
        return gameLost;
    }

    public void setGameLost(int gameLost)
    {
        this.gameLost = gameLost;
    }

    @Column(name = "match_memo")
    public String getMatchMemo()
    {
        return matchMemo;
    }

    public void setMatchMemo(String matchMemo)
    {
        this.matchMemo = matchMemo;
    }
}
