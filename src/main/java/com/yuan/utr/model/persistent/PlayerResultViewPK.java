package com.yuan.utr.model.persistent;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PlayerResultViewPK implements Serializable {

    private long playerId;
    private long divisionId;

    public PlayerResultViewPK() {}

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

}