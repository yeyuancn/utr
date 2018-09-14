package com.yuan.utr.model.persistent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement
@Entity
@Table(name="player")
public class Player implements Serializable
{

    public Player() {
    }

    private Long acctId;

    private Long divisionId;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private Float utrScore;

    private Long utrId;

    private long id;

    @Column(name = "acct_id")
    public Long getAcctId()
    {
        return acctId;
    }

    public void setAcctId(Long acctId)
    {
        this.acctId = acctId;
    }

    @Column(name = "division_id")
    public Long getDivisionId()
    {
        return divisionId;
    }

    public void setDivisionId(Long divisionId)
    {
        this.divisionId = divisionId;
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

    @Column(name = "email")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "utr_score")
    public Float getUtrScore() {
        return utrScore;
    }

    public void setUtrScore(Float utrScore) {
        this.utrScore = utrScore;
    }

    @Column(name = "utr_id")
    public Long getUtrId() {
        return utrId;
    }

    public void setUtrId(Long utrId) {
        this.utrId = utrId;
    }

    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
